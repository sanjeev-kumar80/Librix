package model;

import java.time.LocalDate;

public class IssueRecord {

  private int recordId;
  private Book book;
  private User user;
  private LocalDate issueDate;
  private LocalDate dueDate;
  private LocalDate returnDate;
  private double fine;

  public IssueRecord(int recordId, Book book, User user,
      LocalDate issueDate, LocalDate dueDate) {

    this.recordId = recordId;
    this.book = book;
    this.user = user;
    this.issueDate = issueDate;
    this.dueDate = dueDate;
    this.fine = 0;
  }

  public int getRecordId() {
    return recordId;
  }

  public Book getBook() {
    return book;
  }

  public User getUser() {
    return user;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public double getFine() {
    return fine;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  public void setFine(double fine) {
    this.fine = fine;
  }
}