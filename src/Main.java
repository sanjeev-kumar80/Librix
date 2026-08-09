import model.*;
import service.LibraryService;
import exception.*;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    LibraryService library = new LibraryService();

    Scanner sc = new Scanner(System.in);

    // =========================
    // DEFAULT BOOKS
    // =========================

    library.addBook(new Book(
        101,
        "Clean Code",
        "Robert Martin",
        "Programming",
        "9780132350884",
        3));

    library.addBook(new Book(
        102,
        "Effective Java",
        "Joshua Bloch",
        "Programming",
        "9780134685991",
        2));

    // =========================
    // DEFAULT USERS
    // =========================

    library.addUser(
        new Student(
            1,
            "Rahul",
            "rahul@gmail.com"));

    library.addUser(
        new Teacher(
            2,
            "Amit",
            "amit@gmail.com"));

    library.addUser(
        new SpecialMember(
            3,
            "Dr. Sharma",
            "sharma@gmail.com"));

    // =========================
    // MENU
    // =========================

    while (true) {

      System.out.println("\n============================");
      System.out.println("          LIBRIX");
      System.out.println("============================");
      System.out.println("1. Show All Books");
      System.out.println("2. Search Book");
      System.out.println("3. Issue Book");
      System.out.println("4. Return Book");
      System.out.println("5. Show Issued Books");
      System.out.println("6. Exit");
      System.out.println("============================");

      System.out.print("Enter your choice: ");

      if (!sc.hasNextInt()) {
        System.out.println("Invalid input! Please enter a number.");
        break;
      }

      int choice = sc.nextInt();

      switch (choice) {

        case 1:

          System.out.println("\n--- ALL BOOKS ---");

          library.showAllBooks();

          break;

        case 2:

          System.out.print(
              "Enter Book ID: ");

          int searchId = sc.nextInt();

          try {

            Book book = library.searchBook(searchId);

            System.out.println(
                "\nBook Found!");

            System.out.println(
                "Title: "
                    + book.getTitle());

            System.out.println(
                "Author: "
                    + book.getAuthor());

            System.out.println(
                "Category: "
                    + book.getCategory());

            System.out.println(
                "Quantity: "
                    + book.getQuantity());

          } catch (BookNotFoundException e) {

            System.out.println(
                "ERROR: "
                    + e.getMessage());
          }

          break;

        case 3:

          System.out.print(
              "Enter Record ID: ");

          int recordId = sc.nextInt();

          System.out.print(
              "Enter Book ID: ");

          int bookId = sc.nextInt();

          System.out.print(
              "Enter User ID: ");

          int userId = sc.nextInt();

          try {

            library.issueBook(
                recordId,
                bookId,
                userId);

          } catch (
              BookNotFoundException | UserNotFoundException | BookNotAvailableException e) {

            System.out.println(
                "ERROR: "
                    + e.getMessage());
          }

          break;

        case 4:

          System.out.print(
              "Enter Record ID: ");

          int returnRecordId = sc.nextInt();

          try {

            library.returnBook(
                returnRecordId);

          } catch (BookAlreadyReturnedException e) {

            System.out.println(
                "ERROR: "
                    + e.getMessage());
          }

          break;

        case 5:

          System.out.println(
              "\n--- ISSUED BOOKS ---");

          library.showIssuedBooks();

          break;

        case 6:

          System.out.println(
              "\nThank you for using LIBRIX!");

          sc.close();

          return;

        default:

          System.out.println(
              "Invalid choice!");
      }
    }
  }
}