package service;

import model.Book;
import model.User;
import model.IssueRecord;

import exception.BookNotAvailableException;
import exception.BookNotFoundException;
import exception.UserNotFoundException;
import exception.BookAlreadyReturnedException;

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

  // Show All Books
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
  public Book searchBook(int bookId)
      throws BookNotFoundException {

    for (Book book : books) {

      if (book.getBookId() == bookId) {
        return book;
      }
    }

    throw new BookNotFoundException(
        "Book with ID " + bookId + " not found!");
  }

  // Find User
  private User findUser(int userId)
      throws UserNotFoundException {

    for (User user : users) {

      if (user.getUserId() == userId) {
        return user;
      }
    }

    throw new UserNotFoundException(
        "User with ID " + userId + " not found!");
  }

  // Issue Book
  public void issueBook(
      int recordId,
      int bookId,
      int userId)
      throws BookNotFoundException,
      UserNotFoundException,
      BookNotAvailableException {

    Book book = searchBook(bookId);

    User user = findUser(userId);

    if (!book.isAvailable()) {

      throw new BookNotAvailableException(
          "Book '" + book.getTitle()
              + "' is currently unavailable!");
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

  // Return Book
  public void returnBook(int recordId)
      throws BookAlreadyReturnedException {

    IssueRecord record = null;

    for (IssueRecord r : issueRecords) {

      if (r.getRecordId() == recordId) {
        record = r;
        break;
      }
    }

    if (record == null) {
      System.out.println("Issue record not found!");
      return;
    }

    if (record.getReturnDate() != null) {

      throw new BookAlreadyReturnedException(
          "Book has already been returned!");
    }

    LocalDate returnDate = LocalDate.now();

    record.setReturnDate(returnDate);

    long lateDays = 0;

    if (returnDate.isAfter(record.getDueDate())) {

      lateDays = java.time.temporal.ChronoUnit.DAYS.between(
          record.getDueDate(),
          returnDate);
    }

    double fine = lateDays * record.getUser().getFinePerDay();

    record.setFine(fine);

    Book book = record.getBook();

    book.setQuantity(book.getQuantity() + 1);

    System.out.println("Book returned successfully!");
    System.out.println("Return Date: " + returnDate);
    System.out.println("Late Days: " + lateDays);
    System.out.println("Fine: ₹" + fine);
  }

  // Make Book Overdue - Testing Only
  public void makeBookOverdue(
      int recordId,
      int daysLate) {

    for (IssueRecord record : issueRecords) {

      if (record.getRecordId() == recordId) {

        LocalDate oldDueDate = LocalDate.now().minusDays(daysLate);

        record.setDueDate(oldDueDate);

        System.out.println(
            "Book due date changed for testing: "
                + oldDueDate);

        return;
      }
    }

    System.out.println("Issue record not found!");
  }

  // Show Issued Books
  public void showIssuedBooks() {

    if (issueRecords.isEmpty()) {
      System.out.println(
          "No books are currently issued.");
      return;
    }

    for (IssueRecord record : issueRecords) {

      System.out.println(
          "Book: "
              + record.getBook().getTitle()
              + " | User: "
              + record.getUser().getName()
              + " | Due Date: "
              + record.getDueDate());
    }
  }
}