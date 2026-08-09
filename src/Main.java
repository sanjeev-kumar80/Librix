import model.*;

public class Main {

  public static void main(String[] args) {

    User student = new Student(1, "Rahul", "rahul@gmail.com");

    User teacher = new Teacher(2, "Amit", "amit@gmail.com");

    User special = new SpecialMember(3, "Dr. Sharma", "sharma@gmail.com");

    System.out.println(student.getUserType());
    System.out.println("Fine: ₹" + student.getFinePerDay());

    System.out.println();

    System.out.println(teacher.getUserType());
    System.out.println("Fine: ₹" + teacher.getFinePerDay());

    System.out.println();

    System.out.println(special.getUserType());
    System.out.println("Fine: ₹" + special.getFinePerDay());
  }
}