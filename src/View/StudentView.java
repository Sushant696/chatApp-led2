package View;

import java.util.Scanner;

public class StudentView {
    private Scanner scanner;

    public StudentView() {
        scanner = new Scanner(System.in);
    }

    public String captureStudentName() {
        System.out.print("Enter student name: ");
        return scanner.nextLine();
    }

    public int captureStudentRollNo() {
        System.out.print("Enter student roll number: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printStudentDetails(String studentName, String rollNo) {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + rollNo);
    }
}
