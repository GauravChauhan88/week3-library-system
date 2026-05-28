package library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIR = "data/";
    private static final String BOOKS_FILE = DATA_DIR + "books.txt";
    private static final String MEMBERS_FILE = DATA_DIR + "members.txt";

    public FileHandler() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> loadBooks() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error saving books data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Member> loadMembers() {
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Member>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public void saveMembers(List<Member> members) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        } catch (IOException e) {
            System.err.println("Error saving members data: " + e.getMessage());
        }
    }
}