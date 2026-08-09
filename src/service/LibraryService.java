package service;

import model.Book;
import model.User;
import model.IssueRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

  private List<Book> books = new ArrayList<>();
  private List<User> users = new ArrayList<>();
  private List<IssueRecord> issueRecords = new ArrayList<>();

  // Add Book
  public void addBook(Book book) {
    books.add(book);
    System.out.println("Book added successfully!");
  }

  // Add User
  public void addUser(User user) {
    users.add(user);
    System.out.println("User added successfully!");
  }

  // Show all books
  public void showAllBooks() {

    if (books.isEmpty()) {
      System.out.println("No books available.");
      return;
    }

    for (Book book : books) {
      System.out.println(
          book.getBookId() + " | " +
              book.getTitle() + " | " +
              book.getAuthor() + " | " +
              book.getCategory() + " | Quantity: " +
              book.getQuantity());
    }
  }

  // Search Book
  public Book searchBook(int bookId) {

    for (Book book : books) {
      if (book.getBookId() == bookId) {
        return book;
      }
    }

    return null;
  }

  // Issue Book
  public void issueBook(int recordId, int bookId, int userId) {

    Book book = searchBook(bookId);

    if (book == null) {
      System.out.println("Book not found!");
      return;
    }

    User user = findUser(userId);

    if (user == null) {
      System.out.println("User not found!");
      return;
    }

    if (!book.isAvailable()) {
      System.out.println("Book is not available!");
      return;
    }

    book.setQuantity(book.getQuantity() - 1);

    LocalDate issueDate = LocalDate.now();
    LocalDate dueDate = issueDate.plusDays(14);

    IssueRecord record = new IssueRecord(
        recordId,
        book,
        user,
        issueDate,
        dueDate);

    issueRecords.add(record);

    System.out.println("Book issued successfully!");
    System.out.println("Due Date: " + dueDate);
  }

  // Find User
  private User findUser(int userId) {

    for (User user : users) {
      if (user.getUserId() == userId) {
        return user;
      }
    }

    return null;
  }

  // Show issued books
  public void showIssuedBooks() {

    if (issueRecords.isEmpty()) {
      System.out.println("No books are currently issued.");
      return;
    }

    for (IssueRecord record : issueRecords) {

      System.out.println(
          "Book: " + record.getBook().getTitle() +
              " | User: " + record.getUser().getName() +
              " | Due Date: " + record.getDueDate());
    }
  }
}