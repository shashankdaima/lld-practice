# Library Management System — Low Level Design

## Problem Statement

Design a library management system that handles books, members, borrowing, returns, and fines.

---

## Requirements

### Functional
- Add and manage books in the library catalog
- Register and manage library members
- Allow members to borrow books (check availability)
- Allow members to return borrowed books
- Track due dates for borrowed books
- Calculate and collect fines for overdue returns
- Search books by title, author, or ISBN

### Non-Functional
- Thread-safe borrow/return operations (multiple members at same time)
- A book can have multiple copies
- A member can borrow a limited number of books at a time

---

## Entities

```
Library
├── BookCatalog
│   └── Book
│       ├── bookId
│       ├── title
│       ├── author
│       ├── isbn
│       └── BookCopy[]
│           ├── copyId
│           ├── isAvailable
│           └── BookCopyStatus (AVAILABLE / BORROWED / LOST)
├── Member
│   ├── memberId
│   ├── name
│   ├── email
│   └── BorrowRecord[]
├── BorrowRecord
│   ├── recordId
│   ├── member
│   ├── bookCopy
│   ├── borrowDate
│   ├── dueDate
│   └── returnDate
└── FineCalculator
    └── FineStrategy (interface)
        └── PerDayFineStrategy
```

---

## Class Design

### Enums
```java
enum BookCopyStatus  { AVAILABLE, BORROWED, LOST }
enum MemberStatus    { ACTIVE, SUSPENDED }
```

### Core Classes

| Class | Responsibility |
|---|---|
| `Library` | Singleton. Entry point. Manages catalog and members. |
| `BookCatalog` | Holds all books. Supports search by title/author/ISBN. |
| `Book` | Represents a book with multiple physical copies. |
| `BookCopy` | One physical copy of a book. Tracks availability. |
| `Member` | Library member. Holds active borrow records. |
| `BorrowRecord` | Created on borrow. Tracks copy, dates, return status. |
| `LibraryService` | Orchestrates borrow and return flow. |
| `FineCalculator` | Calculates fine based on overdue days using a strategy. |

---

## Key Design Patterns

| Pattern | Where Used |
|---|---|
| **Singleton** | `Library` — only one library instance |
| **Strategy** | `FineStrategy` — swap fine logic without changing core |
| **Repository** | `BookCatalog` — encapsulates book search and storage |

---

## Borrowing Rules

- A member can borrow max **5 books** at a time
- Borrow period is **14 days** by default
- Fine is charged **per day** after due date
- A `SUSPENDED` member cannot borrow books
- A book with no `AVAILABLE` copies cannot be borrowed

---

## Flow

```
Borrow:
Member requests book
    → LibraryService.borrowBook(member, isbn)
        → BookCatalog.findAvailableCopy(isbn)
        → validate member (not suspended, under borrow limit)
        → copy.setStatus(BORROWED)
        → create BorrowRecord(member, copy, dueDate)
        → return BorrowRecord

Return:
Member returns book
    → LibraryService.returnBook(borrowRecord)
        → set returnDate = now
        → copy.setStatus(AVAILABLE)
        → FineCalculator.calculate(borrowRecord)
        → return fine amount
```

---

## Fine Calculation

```
if returnDate > dueDate:
    overdueDays = days between dueDate and returnDate
    fine = overdueDays * ratePerDay (default ₹10/day)
else:
    fine = 0
```

---

## Tech Stack

- **Java 17**
- **Gradle 9** (build tool)
- **JUnit 5** (unit testing)
- **Docker** (containerization)

## Build & Run

```bash
gradle build        # compile + test
gradle run          # run main
gradle clean test   # force re-run tests

docker compose up --build   # run in Docker
```
