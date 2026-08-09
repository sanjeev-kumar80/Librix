import model.*;
import service.LibraryService;
import exception.*;

public class Main {

  public static void main(String[] args) {

    LibraryService library = new LibraryService();

    // =========================
    // BOOKS
    // =========================

    Book book1 = new Book(
        101,
        "Clean Code",
        "Robert Martin",
        "Programming",
        "9780132350884",
        3);

    Book book2 = new Book(
        102,
        "Effective Java",
        "Joshua Bloch",
        "Programming",
        "9780134685991",
        2);

    // =========================
    // USERS
    // =========================

    User student = new Student(
        1,
        "Rahul",
        "rahul@gmail.com");

    User teacher = new Teacher(
        2,
        "Amit",
        "amit@gmail.com");

    User special = new SpecialMember(
        3,
        "Dr. Sharma",
        "sharma@gmail.com");

    // =========================
    // ADD BOOKS
    // =========================

    library.addBook(book1);
    library.addBook(book2);

    // =========================
    // ADD USERS
    // =========================

    library.addUser(student);
    library.addUser(teacher);
    library.addUser(special);

    // =========================
    // SHOW ALL BOOKS
    // =========================

    System.out.println("\n--- ALL BOOKS ---");

    library.showAllBooks();

    // =========================
    // STUDENT TEST
    // =========================

    System.out.println("\n--- STUDENT TEST ---");

    try {

      library.issueBook(1, 101, 1);

      library.makeBookOverdue(1, 5);

      library.returnBook(1);

    } catch (
        BookNotFoundException | UserNotFoundException | BookNotAvailableException | BookAlreadyReturnedException e) {

      System.out.println(
          "ERROR: " + e.getMessage());
    }

    // =========================
    // TEACHER TEST
    // =========================

    System.out.println("\n--- TEACHER TEST ---");

    try {

      library.issueBook(2, 102, 2);

      library.makeBookOverdue(2, 5);

      library.returnBook(2);

    } catch (
        BookNotFoundException | UserNotFoundException | BookNotAvailableException | BookAlreadyReturnedException e) {

      System.out.println(
          "ERROR: " + e.getMessage());
    }

    // =========================
    // SPECIAL MEMBER TEST
    // =========================

    System.out.println("\n--- SPECIAL MEMBER TEST ---");

    try {

      library.issueBook(3, 101, 3);

      library.makeBookOverdue(3, 5);

      library.returnBook(3);

    } catch (
        BookNotFoundException | UserNotFoundException | BookNotAvailableException | BookAlreadyReturnedException e) {

      System.out.println(
          "ERROR: " + e.getMessage());
    }

    // =========================
    // SHOW BOOKS
    // =========================

    System.out.println("\n--- BOOKS AFTER TESTING ---");

    library.showAllBooks();

    System.out.println("\n--- SEARCH BOOK ---");

    try {

      Book foundBook = library.searchBook(101);

      System.out.println(
          "Book Found: " + foundBook.getTitle());

      System.out.println(
          "Author: " + foundBook.getAuthor());

      System.out.println(
          "Category: " + foundBook.getCategory());

      System.out.println(
          "Quantity: " + foundBook.getQuantity());

    } catch (BookNotFoundException e) {

      System.out.println(
          "ERROR: " + e.getMessage());
    }

    // =========================
    // EXCEPTION TEST
    // =========================

    System.out.println("\n--- EXCEPTION TEST ---");

    try {

      library.issueBook(4, 999, 1);

    } catch (
        BookNotFoundException | UserNotFoundException | BookNotAvailableException e) {

      System.out.println(
          "ERROR: " + e.getMessage());
    }
  }
}