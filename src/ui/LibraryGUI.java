package ui;

import model.Book;
import service.LibraryService;

import javax.swing.*;
import java.awt.*;

public class LibraryGUI extends JFrame {

  private LibraryService library;

  public LibraryGUI(LibraryService library) {

    this.library = library;

    setTitle("LIBRIX - Library Management System");

    setSize(900, 600);

    setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);

    setLocationRelativeTo(null);

    createUI();
  }

  private void createUI() {

    // Main panel
    JPanel mainPanel = new JPanel();

    mainPanel.setLayout(
        new BorderLayout(20, 20));

    mainPanel.setBorder(
        BorderFactory.createEmptyBorder(
            20, 20, 20, 20));

    // =========================
    // HEADER
    // =========================

    JLabel title = new JLabel(
        "LIBRIX",
        SwingConstants.CENTER);

    title.setFont(
        new Font(
            "Arial",
            Font.BOLD,
            32));

    JLabel subtitle = new JLabel(
        "Library Management System",
        SwingConstants.CENTER);

    subtitle.setFont(
        new Font(
            "Arial",
            Font.PLAIN,
            16));

    JPanel header = new JPanel(
        new GridLayout(2, 1));

    header.add(title);
    header.add(subtitle);

    mainPanel.add(
        header,
        BorderLayout.NORTH);

    // =========================
    // SEARCH
    // =========================

    JPanel searchPanel = new JPanel(
        new FlowLayout());

    JTextField searchField = new JTextField(20);

    JButton searchButton = new JButton("Search Book");

    searchPanel.add(
        searchField);

    searchPanel.add(
        searchButton);

    // =========================
    // BOOK TABLE
    // =========================

    String[] columns = {
        "Book ID",
        "Title",
        "Author",
        "Category",
        "Quantity"
    };

    Object[][] data = {
        {
            101,
            "Clean Code",
            "Robert Martin",
            "Programming",
            3
        },
        {
            102,
            "Effective Java",
            "Joshua Bloch",
            "Programming",
            2
        }
    };

    JTable table = new JTable(
        data,
        columns);

    JScrollPane scrollPane = new JScrollPane(table);

    // =========================
    // CENTER
    // =========================

    JPanel centerPanel = new JPanel(
        new BorderLayout(10, 10));

    centerPanel.add(
        searchPanel,
        BorderLayout.NORTH);

    centerPanel.add(
        scrollPane,
        BorderLayout.CENTER);

    mainPanel.add(
        centerPanel,
        BorderLayout.CENTER);

    // =========================
    // BUTTONS
    // =========================

    JPanel buttonPanel = new JPanel(
        new FlowLayout());

    JButton addBookButton = new JButton("Add Book");

    JButton issueButton = new JButton("Issue Book");

    JButton returnButton = new JButton("Return Book");

    JButton membersButton = new JButton("Members");

    buttonPanel.add(
        addBookButton);

    buttonPanel.add(
        issueButton);

    buttonPanel.add(
        returnButton);

    buttonPanel.add(
        membersButton);

    mainPanel.add(
        buttonPanel,
        BorderLayout.SOUTH);

    // =========================
    // SEARCH ACTION
    // =========================

    searchButton.addActionListener(e -> {

      try {

        int id = Integer.parseInt(
            searchField.getText());

        Book book = library.searchBook(id);

        JOptionPane.showMessageDialog(
            this,
            "Book Found!\n\n"
                + "Title: "
                + book.getTitle()
                + "\nAuthor: "
                + book.getAuthor()
                + "\nCategory: "
                + book.getCategory()
                + "\nQuantity: "
                + book.getQuantity());

      } catch (Exception ex) {

        JOptionPane.showMessageDialog(
            this,
            ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    // =========================
    // ADD TO FRAME
    // =========================

    add(mainPanel);
  }
}