package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class EmployeeUpdate {
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtDOB;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRoleID;
    @FXML
    private Label txtEmpID;

    //when the fxml file is loading the entered employee id in the previous stage will be set to the id label
    @FXML
    private void initialize() {
        txtEmpID.setText((EmployeeDelUpdateView.classID));
        txtFName.setPromptText("John");
        txtLName.setPromptText("Steve");
        txtAddress.setPromptText("57 Rajasinghe Road, Colombo 6");
        txtDOB.setPromptText("12/12/2000");
        txtContact.setPromptText("777450345");
        txtEmail.setPromptText("helloWorld@gmail.com");
        txtRoleID.setPromptText("1");
    }

    //this function is called when the update button is clicked
    public void addToDatabase(ActionEvent evt) {
        //the entered information is taken using the get text method
        String enteredFName = String.valueOf(txtFName.getText()).trim();
        String enteredLName = String.valueOf(txtLName.getText()).trim();
        String enteredDOB = String.valueOf(txtDOB.getText());
        String enteredAddress = String.valueOf(txtAddress.getText()).trim();
        String enteredContact = String.valueOf(txtContact.getText()).trim();
        String enteredEmail = String.valueOf(txtEmail.getText()).trim();
        String enteredRoleID = String.valueOf(txtRoleID.getText()).trim();
        //they are then validated using the user defined functions.
        String validEmpFName = Validation.validateString(enteredFName);
        String validLName = Validation.validateString(enteredLName);
        String validAddress = Validation.validateAddress(enteredAddress);
        String validDate = Validation.validateDate(enteredDOB);
        int validContactNumber = Validation.validateID(enteredContact, "ContactNo");
        int validRoleID = Validation.validateID(enteredRoleID, "RoleID");
        String validEmail = Validation.validEmail(enteredEmail);

        //the id is used to find the instance in the Employee list and then it is removed from the list
        int i;
        for (i = 0; i < database.getEmployeeList().size(); i++) {
            if (Integer.parseInt(EmployeeDelUpdateView.classID) == database.getEmployeeList().get(i).getEmpID()) {
                break;
            }
        }
        Employee employee = database.getEmployeeList().get(i);
        database.getEmployeeList().remove(i);
        String errorMessage = "";
        boolean valid = true;
        //then the each field is checked separately and if it acceptable then it is assigned to instance's attributes
        if (validEmpFName.equals("false")) {
            errorMessage += "Invalid Format of Employee First Name";
            valid = false;
        } else if (!validEmpFName.equals("true")) {
            employee.setFName(validEmpFName);
        }
        if (validLName.equals("false")) {
            errorMessage += "\nInvalid format of Employee Last Name";
            valid = false;
        } else if (!validLName.equals("true")) {
            employee.setLName(validLName);
        }
        if (validAddress.equals("true")) {
            errorMessage += "\nInvalid format of Address";
            valid = false;
        } else if (!validAddress.equals("false")) {
            employee.setAddress(validAddress);
        }
        if (validContactNumber == -2 || validContactNumber == 0) {
            errorMessage += "\nThe Contact Number is invalid";
            valid = false;
        } else if (validContactNumber != -1) {
            String validContact = "true";
            for (Employee employee1 : database.getEmployeeList()) {
                if (validContactNumber == employee1.getContactNo()) {
                    errorMessage += "\nThe Contact Number has Already been entered for another Employee";
                    valid = false;
                    validContact = "false";
                    break;
                }
            }
            if (validContact.equals("true")) {
                employee.setContactNo(validContactNumber);
            }
        }
        if (validEmail.equals("false")) {
            errorMessage += "\nInvalid Email entered";
            valid = false;
        } else if (!validEmail.equals("true")) {
            String validEmailAdd = "true";
            for (Employee employee1 : database.getEmployeeList()) {
                if (validEmail.equals(employee1.getEmail())) {
                    validEmailAdd = "false";
                    errorMessage += "The Email has Already been entered for another Employee";
                    valid = false;
                    break;
                }
            }
            if (validEmailAdd.equals("true")) {
                employee.setEmail(validEmail);
            }
        }
        if (validRoleID == 0) {
            errorMessage += "\nThe Role ID is invalid";
            valid = false;
        } else if (validRoleID != -1) {
            String validRole = "true";
            for (Role role : database.getRoleList()) {
                if (validRoleID == role.getRoleID()) {
                    validRole = "false";
                    break;
                }
            }
            if (validRole.equals("false")) {
                employee.setRoleArray(validRoleID);
            } else {
                errorMessage += "\nThe Role has not been entered in the Role Table";
                valid = false;
            }
        }
        if (validDate.equals("false")) {
            errorMessage += "\nThe Date of Birth ID is invalid";
            valid = false;
        } else if (!validDate.equals("true")) {
            employee.setDOB(validDate);
        }
        if (!valid) {
            Alert error = new Alert(Alert.AlertType.ERROR, errorMessage);
            error.showAndWait();
        } else {
            Alert message = new Alert(Alert.AlertType.INFORMATION, "The new Details have been added to the database");
            message.showAndWait();
        }
        //then once the attributes have benn updated the instance is inserted to the list using the initial index
        database.setEmployeeList(i, employee);
    }
}

