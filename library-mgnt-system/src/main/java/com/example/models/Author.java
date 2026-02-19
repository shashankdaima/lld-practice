package main.java.com.example.models;

public class Author {
    private String name;
    private String authorId;
    private String email;

    public Author(String name, String authorId, String email) {
        this.name = name;
        this.authorId = authorId;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
