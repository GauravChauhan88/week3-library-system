package library;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readIntegerInput("Enter choice: ");
            switch (choice) {
                case 1 -> handleAddBook();
                case 2 -> library.displayAllBooks();
                case 3 -> handleSearchBooks();
                case 4 -> handleRegisterMember();
                case 5 -> handleBorrowBook();
                case 6 -> handleReturnBook();
                case 7 -> library.displayStatistics();
                case 8 -> {
                    System.out.println("Exiting system...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice (1-8).");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add Book  | 2. View Books | 3. Search Books");
        System.out.println("4. Register  | 5. Borrow Book | 6. Return Book");
        System.out.println("7. Statistics| 8. Exit");
    }

    private static void handleAddBook() {
        System.out.print("ISBN: "); String isbn = scanner.nextLine().trim();
        System.out.print("Title: "); String title = scanner.nextLine().trim();
        System.out.print("Author: "); String author = scanner.nextLine().trim();
        int year = readIntegerInput("Year: ");
        if(isbn.isEmpty() || title.isEmpty()) return;
        library.addBook(new Book(isbn, title, author, year));
    }

    private static void handleSearchBooks() {
        System.out.print("Search Term: ");
        List<Book> res = library.searchBooks(scanner.nextLine().trim());
        if (res.isEmpty()) System.out.println("No matching records.");
        else res.forEach(System.out::println);
    }

    private static void handleRegisterMember() {
        System.out.print("Member ID: "); String id = scanner.nextLine().trim();
        System.out.print("Name: "); String name = scanner.nextLine().trim();
        if(id.isEmpty() || name.isEmpty()) return;
        library.registerMember(new Member(id, name));
    }

    private static void handleBorrowBook() {
        System.out.print("ISBN: "); String isbn = scanner.nextLine().trim();
        System.out.print("Member ID: "); String id = scanner.nextLine().trim();
        library.borrowBook(isbn, id);
    }

    private static void handleReturnBook() {
        System.out.print("ISBN: "); String isbn = scanner.nextLine().trim();
        library.returnBook(isbn);
    }

    private static int readIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }
}