package ui;

import exception.BookNotFoundException;
import model.Book;
import service.LibraryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryGUI extends JFrame {

  private LibraryService library;

  private DefaultTableModel tableModel;
  private JTable table;

  public LibraryGUI(LibraryService library) {

    this.library = library;

    setTitle("LIBRIX - Library Management System");

    setSize(900, 600);

    setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);

    setLocationRelativeTo(null);

    createUI();

    loadBooks();
  }

  private void createUI() {

    JPanel mainPanel = new JPanel(
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

    JButton showAllButton = new JButton("Show All");

    searchPanel.add(searchField);
    searchPanel.add(searchButton);
    searchPanel.add(showAllButton);

    // =========================
    // TABLE
    // =========================

    String[] columns = {
        "Book ID",
        "Title",
        "Author",
        "Category",
        "Quantity"
    };

    tableModel = new DefaultTableModel(
        columns,
        0);

    table = new JTable(tableModel);

    JScrollPane scrollPane = new JScrollPane(table);

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

    buttonPanel.add(addBookButton);
    buttonPanel.add(issueButton);
    buttonPanel.add(returnButton);
    buttonPanel.add(membersButton);

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

        tableModel.setRowCount(0);

        tableModel.addRow(
            new Object[] {
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getQuantity()
            });

      } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
            this,
            "Please enter a valid Book ID.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);

      } catch (BookNotFoundException ex) {

        JOptionPane.showMessageDialog(
            this,
            ex.getMessage(),
            "Book Not Found",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    // =========================
    // SHOW ALL
    // =========================

    showAllButton.addActionListener(e -> {

      loadBooks();
    });

    // =========================
    // ADD BOOK
    // =========================

    addBookButton.addActionListener(e -> {

      showAddBookDialog();
    });

    add(mainPanel);
  }

  // =========================
  // LOAD BOOKS
  // =========================

  private void loadBooks() {

    tableModel.setRowCount(0);

    for (Book book : library.getBooks()) {

      tableModel.addRow(
          new Object[] {
              book.getBookId(),
              book.getTitle(),
              book.getAuthor(),
              book.getCategory(),
              book.getQuantity()
          });
    }
  }

  // =========================
  // ADD BOOK DIALOG
  // =========================

  private void showAddBookDialog() {

    JTextField idField = new JTextField();

    JTextField titleField = new JTextField();

    JTextField authorField = new JTextField();

    JTextField categoryField = new JTextField();

    JTextField isbnField = new JTextField();

    JTextField quantityField = new JTextField();

    JPanel panel = new JPanel(
        new GridLayout(6, 2, 10, 10));

    panel.add(new JLabel("Book ID:"));
    panel.add(idField);

    panel.add(new JLabel("Title:"));
    panel.add(titleField);

    panel.add(new JLabel("Author:"));
    panel.add(authorField);

    panel.add(new JLabel("Category:"));
    panel.add(categoryField);

    panel.add(new JLabel("ISBN:"));
    panel.add(isbnField);

    panel.add(new JLabel("Quantity:"));
    panel.add(quantityField);

    int result = JOptionPane.showConfirmDialog(
        this,
        panel,
        "Add New Book",
        JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {

      try {

        int id = Integer.parseInt(
            idField.getText());

        int quantity = Integer.parseInt(
            quantityField.getText());

        Book book = new Book(
            id,
            titleField.getText(),
            authorField.getText(),
            categoryField.getText(),
            isbnField.getText(),
            quantity);

        library.addBook(book);

        loadBooks();

        JOptionPane.showMessageDialog(
            this,
            "Book added successfully!");

      } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
            this,
            "Book ID and Quantity must be numbers.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}