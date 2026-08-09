package model;

public class Student extends User {

  public Student(int userId, String name, String email) {
    super(userId, name, email);
  }

  @Override
  public double getFinePerDay() {
    return 10.0;
  }

  @Override
  public String getUserType() {
    return "Student";
  }
}