package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class WorkAdd {
    @FXML
    private TextField txtEmpID;
    @FXML
    private TextField txtWorkID;
    @FXML
    private TextField txtContractID;
    @FXML
    private TextField txtRoleID;
    @FXML
    private TextField txtNoHoursWorked;

    //initially the generates the id and the user cannot change it
    @FXML
    private void initialize() {
        generate();
        txtWorkID.setEditable(false);
        txtEmpID.setPromptText("1");
        txtRoleID.setPromptText("1");
        txtContractID.setPromptText("1");
        txtNoHoursWorked.setPromptText("30");

    }

    //generates the id based on the last entered entry's id
    private void generate() {
        if (database.getWorkList().size() == 0) {
            txtWorkID.setText(String.valueOf(1));
        } else {
            int id = (database.getWorkList().get(database.getWorkList().size() - 1).getWorkIDArray()) + 1;
            txtWorkID.setText(String.valueOf(id));
        }
    }

    //when the user clicks the add button then this function will run
    public void addToDatabase(ActionEvent evt) {
        //the entered details are put into variables
        int validWorkID = Integer.valueOf(txtWorkID.getText());
        String enteredEmpID = String.valueOf(txtEmpID.getText());
        String enteredContractID = String.valueOf(txtContractID.getText()).trim();
        String enteredRoleID = String.valueOf(txtRoleID.getText());
        String enteredHoursWorked = String.valueOf(txtNoHoursWorked.getText()).trim();
        //the variables are then validated and the reult is put into a new set of variables
        int validEmpID = Validation.validateID(enteredEmpID, "EmpID");
        int validContractID = Validation.validateID(enteredContractID, "ContractID");
        int validRoleID = Validation.validateID(enteredRoleID, "RoleID");
        int validHoursWorked = Validation.validateID(enteredHoursWorked, "NumberOfHoursWorked");
        String errorMessage = "";
        boolean valid = true;
        Work work = new Work();
        //if the user enters the valid details then the they will change in the instance using the set method
        if (validContractID == 0 || validContractID == -1) {
            valid = false;
            errorMessage += "\nThe Contract ID is invalid";
        } else {
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
                errorMessage += "\nThe Contract has not been entered in the Contract Table";
            }
        }
        if (validEmpID == 0 || validEmpID == -1) {
            valid = false;
            errorMessage += "\nThe Employee ID is invalid";
        } else {
            String validEmp = "true";
            int i;
            for (i = 0; i < database.getEmployeeList().size(); i++) {
                if (validEmpID == database.getEmployeeList().get(i).getEmpID()) {
                    validEmp = "false";
                    break;
                }
            }
            if (validEmp.equals("false")) {
                work.setEmpID(validEmpID);
                if (validRoleID == 0 || validRoleID == -1) {
                    valid = false;
                    errorMessage += "The Role ID is invalid";
                } else {
                    String validRole = "true";
                    for (Role role : database.getRoleList()) {
                        if (validRoleID == role.getRoleID()) {
                            validRole = "false";
                            break;
                        }
                    }
                    if (validRole.equals("false")) {
                        String roleEntered = "true";
                        for (int roleID : database.getEmployeeList().get(i).getRoleArray()) {
                            if (validRoleID == roleID) {
                                roleEntered = "false";
                                break;
                            }
                        }
                        if (roleEntered.equals("false")) {
                            work.setRoleID(validRoleID);
                        } else {
                            valid = false;
                            errorMessage += "\nThe Role has not been entered into the employee record";
                        }
                    } else {
                        errorMessage += "\nThe Role has not been entered into the Role Table";

                    }
                }
            } else {
                errorMessage += "\nThe Employee has not been entered in the Employee Table";
            }
        }
        if (validHoursWorked == 0 || validHoursWorked == -1) {
            valid = false;
            errorMessage += "\nThe Numbers of Hours Worked entered is invalid";
        } else {
            work.setNumHoursWorked(validHoursWorked);
        }
        if (!valid) {
            Alert error = new Alert(Alert.AlertType.ERROR, errorMessage);
            error.showAndWait();
        } else {
            work.setWorkID(validWorkID);
            database.setWorkList(0, work);
            Alert message = new Alert(Alert.AlertType.INFORMATION, "The new Details have been added to the database");
            message.showAndWait();
            txtWorkID.setEditable(false);
            txtNoHoursWorked.setText("");
            txtContractID.setText("");
            txtRoleID.setText("");
            txtEmpID.setText("");
            initialize();
        }
        //and using the setWorkList method we can insert the instance in its previous position


    }
}

