package Controller;

import Model.Student;
import View.StudentView;

public class StudentController {
    private Student student;
    private StudentView view;

    public StudentController(Student student, StudentView view) {
        this.student = student;
        this.view = view;
    }

    // Simulate user interaction to update student data
    public void updateStudentData() {
        
        // holding the data comming from the view (Name and roll no)
        String studentName = view.captureStudentName();
        int rollNo = view.captureStudentRollNo();

        // Update model with new data
        student.setStudentName(studentName);
        student.setRollNo(rollNo);
    }

    // Get data from the model
    public String getStudentName() {
        return student.getStudentName();
    }

    public int getRollNo() {
        return student.getRollNo();
    }

    // Update the view
    public void updateView() {
        view.printStudentDetails(student.getStudentName(), Integer.toString(student.getRollNo()));
    }
}
