import Model.Student;
import View.StudentView;
import Controller.StudentController;

public class MVCPatternDemo {
    public static void main(String[] args) {

        // program starts from here, creating the model, view and controller
        // the database initial thing should happen here

        // Step 1: Create the model
        Student student = new Student();

        // Step 2: Create the view
        StudentView view = new StudentView();

        // Step 3: Create the controller, controller is initialized with the model
        // (student) and view
        StudentController controller = new StudentController(student, view);

        // Step 4: Simulate user input and update the view
        controller.updateStudentData(); // Simulates user entering data

        // Update the view with new data
        controller.updateView(); // View displays the updated student details
        System.out.println("New changed first one");
    }

}
