package model;

public class Book {

  private int bookId;
  private String title;
  private String author;
  private String category;
  private String isbn;
  private int quantity;

  public Book(int bookId, String title, String author,
      String category, String isbn, int quantity) {

    this.bookId = bookId;
    this.title = title;
    this.author = author;
    this.category = category;
    this.isbn = isbn;
    this.quantity = quantity;
  }

  public int getBookId() {
    return bookId;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getCategory() {
    return category;
  }

  public String getIsbn() {
    return isbn;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public boolean isAvailable() {
    return quantity > 0;
  }
}