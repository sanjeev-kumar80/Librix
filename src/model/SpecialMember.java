package model;

public class SpecialMember extends User {

  public SpecialMember(int userId, String name, String email) {
    super(userId, name, email);
  }

  @Override
  public double getFinePerDay() {
    return 0.0;
  }

  @Override
  public String getUserType() {
    return "Special Member";
  }
}