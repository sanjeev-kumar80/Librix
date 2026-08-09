import model.*;
import service.LibraryService;
import ui.LibraryGUI;

import javax.swing.*;

public class Main {

  public static void main(String[] args) {

    LibraryService library = new LibraryService();

    // Books
    library.addBook(
        new Book(
            101,
            "Clean Code",
            "Robert Martin",
            "Programming",
            "9780132350884",
            3));

    library.addBook(
        new Book(
            102,
            "Effective Java",
            "Joshua Bloch",
            "Programming",
            "9780134685991",
            2));

    // Users
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

    // Start GUI
    SwingUtilities.invokeLater(() -> {

      LibraryGUI gui = new LibraryGUI(library);

      gui.setVisible(true);
    });
  }
}