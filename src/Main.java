import model.*;
import service.LibraryService;

public class Main {

  public static void main(String[] args) {

    LibraryService library = new LibraryService();

    // Books
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

    // Users
    User student = new Student(1, "Rahul", "rahul@gmail.com");

    User teacher = new Teacher(2, "Amit", "amit@gmail.com");

    User special = new SpecialMember(3, "Dr. Sharma", "sharma@gmail.com");

    // Add books
    library.addBook(book1);
    library.addBook(book2);

    // Add users
    library.addUser(student);
    library.addUser(teacher);
    library.addUser(special);

    System.out.println("\n--- ALL BOOKS ---");

    library.showAllBooks();

    System.out.println("\n--- ISSUE BOOK ---");

    library.issueBook(1, 101, 1);

    System.out.println("\n--- MAKE BOOK OVERDUE ---");

    library.makeBookOverdue(1, 5);

    System.out.println("\n--- RETURN BOOK ---");

    library.returnBook(1);

    System.out.println("\n--- BOOKS AFTER RETURN ---");

    library.showAllBooks();

    System.out.println("\n--- TEACHER TEST ---");

    library.issueBook(2, 102, 2);

    library.makeBookOverdue(2, 5);

    library.returnBook(2);

    System.out.println("\n--- SPECIAL MEMBER TEST ---");

    library.issueBook(3, 101, 3);

    library.makeBookOverdue(3, 5);

    library.returnBook(3);

  }
}