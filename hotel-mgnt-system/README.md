# Hotel Booking System — LLD Practice

A low-level design exercise covering the core domain of a hotel management system: room inventory, reservations, check-in/out, and cancellations.

---

## Problem Scope

Design and implement the backend logic for a hotel booking system. The focus is on domain modeling, state transitions, and business rules — not on infrastructure or persistence layers.

---

## Core Features

### Room Management

- Maintain an inventory of rooms with types (Single, Double, Suite, etc.) and floor/number identifiers
- Each room has a status: `AVAILABLE`, `BOOKED`, `CHECKED_IN`, `UNDER_MAINTENANCE`
- Rooms have associated amenities and a per-night price
- Support filtering rooms by type, availability, and date range

### Reservations

- A guest can search for available rooms for a given date range
- Booking a room creates a `Reservation` tied to a guest, room, check-in date, and check-out date
- A reservation moves through states: `PENDING` → `CONFIRMED` → `CHECKED_IN` → `COMPLETED` / `CANCELLED`
- Prevent double-booking: a room can only have one confirmed reservation per date range

### Check-in / Check-out

- Check-in is only allowed on or after the reservation's check-in date
- Check-out calculates the final bill (base room cost × nights + any extra charges)
- Late checkout may incur additional fees
- After checkout, the room status returns to `AVAILABLE`

### Cancellations

- A reservation can be cancelled any time before check-in
- Cancellation policy determines the refund amount based on how far in advance the cancellation occurs:
  - More than 48 hours before check-in: full refund
  - Between 24–48 hours: partial refund
  - Less than 24 hours: no refund
- Cancelling a reservation frees up the room for that date range

### Billing

- Invoice is generated on checkout
- Charges include: room rate, additional services (room service, spa, etc.), and applicable taxes
- Payment is recorded but the actual payment gateway is out of scope

---

## Authentication & Authorization

Auth is role-based. There are three roles in the system:

**Guest**

- Can register and log in with email + password
- JWT issued on login, sent as `Authorization: Bearer <token>` on subsequent requests
- Can only view and manage their own reservations
- Cannot access other guests' data or admin endpoints

**Front Desk Staff**

- Authenticated the same way (email + password → JWT)
- Can perform check-in and check-out operations on behalf of any guest
- Can view all reservations and room statuses
- Cannot modify room inventory or pricing

**Admin**

- Full access: manage room inventory, set pricing, override reservations, view reports
- Admin accounts are provisioned internally — no self-registration

**Token lifecycle**

- Access tokens expire after a short window (e.g., 15 minutes)
- Refresh tokens are issued alongside and stored server-side (or as an httpOnly cookie)
- On refresh, the old refresh token is invalidated (token rotation)
- Logout invalidates the refresh token immediately

**Authorization enforcement**

- Every endpoint checks the role from the decoded JWT before executing business logic
- A guest hitting a staff/admin endpoint gets a `403 Forbidden`, not a data leak
- Room and reservation IDs in URLs are validated against the authenticated user's ownership before any operation

---

### Design Patterns for Auth

Auth has two distinct responsibilities — **authentication** (who are you?) and **authorization** (what are you allowed to do?). Different patterns apply to each.

#### 1. Strategy Pattern — for Authentication Methods

Use Strategy to make the authentication mechanism swappable without changing any surrounding code.

Define a common `AuthStrategy` interface with a single method like `authenticate(request) → AuthenticatedUser`. Then provide concrete implementations:

- `PasswordAuthStrategy` — looks up user by email, verifies bcrypt hash, issues JWT
- `OAuthStrategy` — validates OAuth token with a provider (Google, etc.), maps to an internal user
- `ApiKeyStrategy` — for staff integrations; validates a static API key against a store

The auth entry point holds a reference to whichever strategy is active and delegates to it. To add a new auth method (e.g., SSO), you write a new strategy class — nothing else changes. This is the right pattern here because the **algorithm for verifying identity varies**, which is exactly the Strategy use case.

```
AuthStrategy (interface)
  └── authenticate(request) → AuthenticatedUser

PasswordAuthStrategy   implements AuthStrategy
OAuthStrategy          implements AuthStrategy
ApiKeyStrategy         implements AuthStrategy
```

The `AuthContext` (or `AuthService`) holds the active strategy and calls it. The rest of the system only knows about `AuthenticatedUser` — it doesn't care how the user was verified.

---

#### 2. Chain of Responsibility — for Authorization Checks

Once the user is identified, authorization is not a single check — it's a sequence of checks that must all pass:

1. Is the token present and well-formed?
2. Is it not expired?
3. Does the user's role permit this action?
4. Does the user own the specific resource they're acting on?

Model each check as a handler in a chain. Each handler either passes control to the next handler or short-circuits with a `403`. Adding a new check (e.g., account suspension check) means inserting a new handler — not editing existing ones.

```
TokenValidationHandler
  → ExpiryHandler
    → RoleCheckHandler
      → ResourceOwnershipHandler
        → [proceed to business logic]
```

This keeps authorization logic out of business logic and makes the rules easy to reason about independently.

---

#### 3. Decorator / Middleware — for Applying Auth at the Boundary

Auth should wrap endpoints, not live inside them. Use a Decorator (or middleware, depending on your framework) that intercepts every request, runs the auth chain, and only forwards the call if it passes.

Business logic methods remain unaware of auth entirely — they receive an already-validated `AuthenticatedUser` and act on it. This enforces the separation: auth is a cross-cutting concern handled at the entry point, not scattered through the domain layer.

---

**Summary**


| Concern                     | Pattern                 | Why                                                |
| --------------------------- | ----------------------- | -------------------------------------------------- |
| How identity is verified    | Strategy                | The algorithm varies (password, OAuth, API key)    |
| Whether access is allowed   | Chain of Responsibility | Multiple sequential checks, each can short-circuit |
| Attaching auth to endpoints | Decorator / Middleware  | Cross-cutting concern; keeps business logic clean  |


---

## Key Business Rules

1. A room cannot be booked if it has an overlapping confirmed or checked-in reservation
2. Check-in date must be before check-out date; minimum stay is 1 night
3. A guest cannot have more than `N` active reservations simultaneously (configurable)
4. Room status `UNDER_MAINTENANCE` blocks all booking attempts regardless of date
5. Only staff or admin can transition a room to/from `UNDER_MAINTENANCE`
6. Refunds are calculated at cancellation time using the policy in effect at booking time

---

## State Machines

**Reservation States**

```
PENDING → CONFIRMED → CHECKED_IN → COMPLETED
                   ↘ CANCELLED
         ↘ CANCELLED
```

**Room States**

```
AVAILABLE ⇄ BOOKED ⇄ CHECKED_IN → AVAILABLE
AVAILABLE ⇄ UNDER_MAINTENANCE
```

---

## Out of Scope

- Actual payment processing
- Email/SMS notifications (interface can be defined, implementation skipped)
- Multi-property / multi-hotel support
- UI / frontend

---

## Design Goals

- Keep the domain model clean and free of framework dependencies
- Business rules live in domain/service layer, not in controllers or repositories
- State transitions are explicit and validated — no silent skips
- Auth concerns are handled at the boundary; domain objects should not know about roles

