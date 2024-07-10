package Model;

public class Student {
    private String studentName;
    private int  rollNo;

    // setting details, it will be called in controller and the rollNo and the studentName will come from the controller or what ?? 
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getRollNo() {
        return rollNo;
    }
}
