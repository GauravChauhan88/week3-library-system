package library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private List<String> borrowedBooks;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(String isbn) {
        if (!borrowedBooks.contains(isbn)) {
            borrowedBooks.add(isbn);
        }
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Borrowed Books: %d", id, name, borrowedBooks.size());
    }
}