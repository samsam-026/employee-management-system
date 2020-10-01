package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class SalaryView {
    @FXML
    private TextField txtWorkID;
    @FXML
    private void initialize() {
        txtWorkID.setPromptText("1");

    }

    //the function is used to view and calculate the salary based on the id entered
    public void viewSalary() {
        //the id is first checked if it available in the list
        String enteredEmpId = txtWorkID.getText();
        int validEmpID = Validation.validateID(enteredEmpId, "empID");
        if (validEmpID == 0 || validEmpID == -1) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The ID is invalid");
            errorMessage.showAndWait();
        } else {
            boolean validID=false;
            Employee employee1;
            for (Employee employee : database.getEmployeeList()) {
                employee1 = employee;
                if (validEmpID == employee.getEmpID()) {
                    validID=true;
                    double salary = 0.0;
                    boolean salaryPresent = false;
                    //after finding the employee using the id we can check the contracts the employee is working in
                    for (Work work : database.getWorkList()) {
                        if (work.getEmpID() == employee1.getEmpID()) {
                            //and once the work has been found the role's pay can be found by using the roleid of the work table and roleid of the role table
                            for (Role role : database.getRoleList()) {
                                if (work.getRoleID() == role.getRoleID()) {
                                    double pay = role.getHourlyPay();
                                    //the multiplication of the hourly pay and the number of hours worked in the contract will give the salary
                                    salary += pay * work.getNumHoursWorked();
                                    salaryPresent = true;
                                }
                            }
                        }
                    }
                    //the user has been assigned the work only then the salary will be displayed else an appropriate error alert will be displayed
                    if (!salaryPresent) {
                        Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Work ID has not been assigned yet to the employee");
                        errorMessage.showAndWait();
                    } else {
                        Alert message = new Alert(Alert.AlertType.INFORMATION, "The Salary of the Employee : $" + salary);
                        message.showAndWait();
                        break;
                    }
                }
            }
            if(!validID){
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Employee ID has not been added to the Employee Table");
                errorMessage.showAndWait();
            }
        }
    }
}
