package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Menu.database;

public class EmployeeDelUpdateView {
    @FXML
    private TextField txtEmpID;

    public static String classID;

    @FXML
    private void initialize() {
        txtEmpID.setPromptText("1");
    }

    //this function is called when the view button is clicked
    public void viewEmp(ActionEvent evt) {
        //initially the id entered is validated and checked if the id is present in the list of employees
        String enteredEmpID = String.valueOf(txtEmpID.getText());
        int validID = Validation.validateID(enteredEmpID, "EmpID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getEmployeeList().size(); i++) {
                if (validID == database.getEmployeeList().get(i).getEmpID()) {
                    present = true;
                    break;
                }

            }
            //if it is present then employee instance is accessed using the getter method and get method available for the arraylist
            if (present) {
                Employee employee = database.getEmployeeList().get(i);
                //the details are displayed using an alert
                String message = "The details are as follow:\nEmployee id: " + employee.getEmpID() + "\nFirst Name: " + employee.getFName() + "\nLast Name: " + employee.getLName() + "\nAddress: " + employee.getAddress();
                message += "\nContact Number: " + employee.getContactNo() + "\nEmail Address: " + employee.getEmail() + "\nDate Of Birth: " + employee.getDOB();
                message += "\nRole ID Array: [";
                for (int roleID : employee.getRoleArray()) {
                    message += roleID + ",";
                }
                message += "]";
                Alert information = new Alert(Alert.AlertType.INFORMATION, message);
                information.showAndWait();
                //error alerts are displayed when the employee id is invalid
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Employee ID cannot be found");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Emp ID");
            errorMessage.showAndWait();
        }

    }

    //when the delete button is clicked then this function is called
    public void deleteEmp(ActionEvent evt) {
        //the id is first validated and checked if it is available in the EmployeeList
        String enteredEmpID = String.valueOf(txtEmpID.getText());
        int validID = Validation.validateID(enteredEmpID, "EmpID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getEmployeeList().size(); i++) {
                if (validID == database.getEmployeeList().get(i).getEmpID()) {
                    present = true;
                    break;
                }

            }
            //if it is present then  the employee entry is deleted and the work entry related to the employee is also deleted
            //if the id is incorrect or not available then an appropriate message will alerted
            if (present) {
                if (Validation.alert().equals("true")) {
                    database.getEmployeeList().remove(i);
                    Alert information = new Alert(Alert.AlertType.INFORMATION, "Deleted From the Database");
                    information.showAndWait();
                    int m;
                    for (m = 0; m < database.getWorkList().size(); m++) {
                        if (validID == database.getWorkList().get(m).getEmpID()) {
                            database.getWorkList().remove(m);
                        }
                    }
                }
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Employee ID not entered into the system");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Emp ID");
            errorMessage.showAndWait();
        }


    }

    //if the user clicks the update button then this function is invoked
    public void updateEmp(ActionEvent evt) {
        //the id is first validated and checked if it is available in the EmployeeList
        String enteredEmpID = String.valueOf(txtEmpID.getText());
        classID = enteredEmpID;
        int validEmpID = Validation.validateID(enteredEmpID, "EmpID");
        if (!((validEmpID == 0) || (validEmpID == -1))) {
            String validID = "false";
            for (Employee employee : database.getEmployeeList()) {
                if (validEmpID == employee.getEmpID()) {
                    validID = "true";
                    break;
                }
            }
            //if the id passes the validation then the fxml file is uploaded
            if (validID.equals("true")) {
                try {
                    Parent rootNode = FXMLLoader.load(getClass().getResource("employeeUpdate.fxml"));
                    Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
                    Stage stageAddEmployee = new Stage();
                    stageAddEmployee.setScene(sceneAddEmployee);
                    stageAddEmployee.setTitle("Update Employee");
                    stageAddEmployee.showAndWait();
                } catch (IOException e) {
                    System.out.println("Please check whether the file exists");
                }
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Employee has not been entered in the system");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Invalid EmployeeID");
            errorMessage.showAndWait();
        }
    }


}
