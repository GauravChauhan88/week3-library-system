package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private FileHandler fileHandler;
    private static final double FINE_PER_DAY = 5.0;
    
    public Library() {
        this.fileHandler = new FileHandler();
        loadData();
    }
    
    private void loadData() {
        books = fileHandler.loadBooks();
        members = fileHandler.loadMembers();
        System.out.println(">>> System Initialized: Loaded " + books.size() + " books and " + members.size() + " members.");
    }
    
    public void addBook(Book book) {
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.println("Error: A book with this ISBN already exists.");
            return;
        }
        books.add(book);
        fileHandler.saveBooks(books);
        System.out.println("Book added successfully: " + book.getTitle());
    }
    
    public void removeBook(String isbn) {
        Book bookToRemove = findBookByIsbn(isbn);
        if (bookToRemove != null) {
            if (!bookToRemove.isAvailable()) {
                System.out.println("Cannot remove book. It is currently borrowed.");
                return;
            }
            books.remove(bookToRemove);
            fileHandler.saveBooks(books);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found with ISBN: " + isbn);
        }
    }
    
    public Book findBookByIsbn(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }
    
    public List<Book> searchBooks(String keyword) {
        return books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                         b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                         b.getIsbn().contains(keyword))
            .collect(Collectors.toList());
    }
    
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n=== ALL BOOKS ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
    
    public void registerMember(Member member) {
        if (findMemberById(member.getId()) != null) {
            System.out.println("Error: Member with this ID already exists.");
            return;
        }
        members.add(member);
        fileHandler.saveMembers(members);
        System.out.println("Member registered successfully: " + member.getName());
    }
    
    public Member findMemberById(String id) {
        return members.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void borrowBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);
        
        if (book == null || member == null) { System.out.println("Book or Member not found!"); return; }
        if (!book.isAvailable()) { System.out.println("Book is already borrowed!"); return; }
        
        book.setAvailable(false);
        book.setBorrowedBy(memberId);
        book.setDueDate(LocalDate.now().plusWeeks(2));
        
        member.borrowBook(isbn);
        fileHandler.saveBooks(books);
        fileHandler.saveMembers(members);
        
        System.out.println("Book borrowed successfully! Due: " + book.getDueDate());
    }

    public void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null || book.isAvailable()) { System.out.println("Book error or not borrowed."); return; }

        Member member = findMemberById(book.getBorrowedBy());
        if (book.isOverdue()) {
            long days = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
            System.out.printf("⚠️ OVERDUE! Fine applied: %.2f\n", days * FINE_PER_DAY);
        }

        if (member != null) member.returnBook(isbn);
        book.setAvailable(true);
        book.setBorrowedBy(null);
        book.setDueDate(null);

        fileHandler.saveBooks(books);
        if (member != null) fileHandler.saveMembers(members);
        System.out.println("Book returned successfully!");
    }
    
    public void displayStatistics() {
        long avail = books.stream().filter(Book::isAvailable).count();
        System.out.println("\n=== STATISTICS ===");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available: " + avail);
        System.out.println("Borrowed: " + (books.size() - avail));
        System.out.println("Members: " + members.size());
    }
}