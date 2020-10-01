package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class WorkUpdate {
    @FXML
    private TextField txtEmpID;
    @FXML
    private Label txtWorkID;
    @FXML
    private TextField txtContractID;
    @FXML
    private TextField txtRoleID;
    @FXML
    private TextField txtNoHoursWorked;

    //the placeholder for the fields available
    @FXML
    private void initialize() {
        txtWorkID.setText((WorkDelUpdate.classID));
        txtEmpID.setPromptText("1");
        txtRoleID.setPromptText("1");
        txtContractID.setPromptText("1");
        txtNoHoursWorked.setPromptText("30");
    }

    //when the add button is clicked this function is called
    public void addToDatabase(ActionEvent evt) {
        //the entered details are put into variables
        String enteredEmpID = String.valueOf(txtEmpID.getText());
        String enteredContractID = String.valueOf(txtContractID.getText()).trim();
        String enteredRoleID = String.valueOf(txtRoleID.getText());
        String enteredHoursWorked = String.valueOf(txtNoHoursWorked.getText()).trim();
        //the variables are then validated and the reult is put into a new set of variables
        int validEmpID = Validation.validateID(enteredEmpID, "EmpID");
        int validContractID = Validation.validateID(enteredContractID, "ContractID");
        int validRoleID = Validation.validateID(enteredRoleID, "RoleID");
        int validHoursWorked = Validation.validateID(enteredHoursWorked, "NumberOfHoursWorked");
        //once the id's instance is found then the instance is temporarily put into another variable and then it is deleted in the main list
        int i;
        for (i = 0; i < database.getWorkList().size(); i++) {
            if (Integer.parseInt(WorkDelUpdate.classID) == database.getWorkList().get(i).getWorkIDArray()) {
                break;
            }
        }
        String errorMessage = "";
        boolean valid = true;
        Work work = database.getWorkList().get(i);
        database.getWorkList().remove(i);
        //if the user enters the valid details then the they will change in the instance using the set method
        if (validRoleID == 0) {
            valid = false;
            errorMessage += "The Role ID is invalid";
        } else if (validRoleID != -1) {
            String validRole = "true";
            for (Role role : database.getRoleList()) {
                if (validRoleID == role.getRoleID()) {
                    validRole = "false";
                    break;
                }
            }
            if (validRole.equals("false")) {
                work.setRoleID(validRoleID);
            } else {
                valid = false;
                errorMessage += "\nThe Role has not been entered in the Role Table";
            }
        }
        if (validContractID == 0) {
            valid = false;
            errorMessage += "\nThe Contract ID is invalid";
        } else if (validContractID != -1) {
            String validContract = "true";
            for (Contract contract : database.getContractList()) {
                if (validContractID == contract.getContractID()) {
                    validContract = "false";
                    break;
                }
            }
            if (validContract.equals("false")) {
                work.setContractID(validContractID);
            } else {
                valid = false;
                errorMessage = "\nThe Contract has not been entered in the Contract Table";
            }
        }
        if (validEmpID == 0) {
            valid = false;
            errorMessage += "\nThe Employee ID is invalid";
        } else if (validEmpID != -1) {
            String validEmp = "true";
            for (Employee emp : database.getEmployeeList()) {
                if (validEmpID == emp.getEmpID()) {
                    validEmp = "false";
                    valid = false;
                    break;
                }
            }
            if (validEmp.equals("false")) {
                work.setEmpID(validEmpID);
            } else {
                errorMessage += "\nThe Employee has not been entered in the Employee Table";
            }
        }
        if (validHoursWorked == 0) {
            valid = false;
            errorMessage += "\nThe Numbers of Hours Worked entered is invalid";
        } else if (validHoursWorked != -1) {
            work.setNumHoursWorked(validHoursWorked);
        }
        if (!valid) {
            Alert error = new Alert(Alert.AlertType.ERROR, errorMessage);
            error.showAndWait();
        } else {
            Alert message = new Alert(Alert.AlertType.INFORMATION, "The new Details have been added to the database");
            message.showAndWait();
        }
        //and using the setWorkList method we can insert the instance in its previous position
        database.setWorkList(i, work);

    }
}

