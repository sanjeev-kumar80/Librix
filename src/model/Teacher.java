package model;

public class Teacher extends User {

  public Teacher(int userId, String name, String email) {
    super(userId, name, email);
  }

  @Override
  public double getFinePerDay() {
    return 5.0;
  }

  @Override
  public String getUserType() {
    return "Teacher";
  }
}