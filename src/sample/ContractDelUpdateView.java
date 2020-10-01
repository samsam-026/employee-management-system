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

public class ContractDelUpdateView {
    @FXML
    private TextField txtContID;

    @FXML
    private void initialize() {
        txtContID.setPromptText("1");
    }

    public static String classID;

    //this function validates if the id entered is an integer and also if it is present in the contract file
    public String validate() {
        String enteredContractID = String.valueOf(txtContID.getText());
        classID = enteredContractID;
        int validEmpID = Validation.validateID(enteredContractID, "ContractID");
        String validID = "false";
        if (!((validEmpID == 0) || (validEmpID == -1))) {
            //once the id is present the loop breaks
            for (Contract contract : database.getContractList()) {
                if (validEmpID == contract.getContractID()) {
                    validID = "true";
                    break;
                }
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Invalid ContractID");
            errorMessage.showAndWait();
        }
        return validID;
    }

    //this function is called when the view button is clicked
    public void viewContract(ActionEvent evt) {
        //valdiates the id and also finds the exact position of the id in the arraylisttract the object and then display it attributes
        String enteredContID = String.valueOf(txtContID.getText());
        int validID = Validation.validateID(enteredContID, "ContractID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getContractList().size(); i++) {
                if (validID == database.getContractList().get(i).getContractID()) {
                    present = true;
                    break;
                }

            }
            if (present) {
                //once the contract has been found the information is concatenated and displayed using an alert of alertType Information
                Contract contract = database.getContractList().get(i);
                String message = "The details are as follow:\nContract ID: " + contract.getContractID() + "\nContract Name: " + contract.getContractName() + "\nContract Description: " + contract.getContractDesc() + "\nContract Creation Date: " + contract.getContractCreateDate();
                message += "\nJobType Name: " + contract.getJobTypeName() + "\nProject Leader(Employee ID): " + contract.getProjectLeaderEmpID() + "\nCustomer ID: " + contract.getCustomerID();
                Alert information = new Alert(Alert.AlertType.INFORMATION, message);
                information.showAndWait();
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Invalid Contract ID");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Emp ID");
            errorMessage.showAndWait();
        }

    }

    //this function is called when the delete button is clicked
    public void deleteContract(ActionEvent evt) {
        //asks the user if they would want to really delete the record in the table if true
        //validates the id and checks if it is in the contract table
        String enteredContID = String.valueOf(txtContID.getText());
        int validID = Validation.validateID(enteredContID, "ContractID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getContractList().size(); i++) {
                if (validID == database.getContractList().get(i).getContractID()) {
                    present = true;
                    break;
                }

            }
            //if it is present then we check if it there are any records in the work table with this id for the contract id attribute
            if (present) {
                if (Validation.alert().equals("true")) {
                    int customerID = database.getContractList().get(i).getCustomerID();
                    database.getContractList().remove(i);

                    int m;
                    //if so using a for loop we delete all records
                    for (m = 0; m < database.getWorkList().size(); m++) {
                        if (validID == database.getWorkList().get(m).getContractID()) {
                            database.getWorkList().remove(m);
                        }
                    }

                    //and then if the customer had only contract and if the contract is deleted then customer will also be deleted
                    int j;
                    //searches if the above customer id has more records
                    boolean customerPresent = false;
                    for (j = 0; j < database.getContractList().size(); j++) {
                        if (customerID == database.getContractList().get(j).getCustomerID()) {
                            customerPresent = true;
                            break;
                        }
                    }
                    if (!customerPresent) {
                        //if they don't then they will be removed from the list
                        int k;
                        for (k = 0; k < database.getCustomerList().size(); k++) {
                            if (customerID == database.getCustomerList().get(k).getCustomerID()) {
                                database.getCustomerList().remove(k);
                                break;
                            }
                        }
                    }
                    //an alert is used to inform the user that the record has been deleted
                    Alert information = new Alert(Alert.AlertType.INFORMATION, "Deleted From the Database");
                    information.showAndWait();
                }
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The contract ID is not entered into the system");
                errorMessage.showAndWait();
            }
            // alerts are made if either the contract id is invalid or in the wrong format
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Contract ID");
            errorMessage.showAndWait();
        }

    }


    //a new fxml is uploaded when the update button is clicked and if the id is valid
    public void updateContract(ActionEvent evt) {
        String validID = validate();
        if (validID.equals("true")) {
            try {
                Parent rootNode = FXMLLoader.load(getClass().getResource("contractUpdate.fxml"));
                Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
                Stage stageAddEmployee = new Stage();
                stageAddEmployee.setScene(sceneAddEmployee);
                stageAddEmployee.setTitle("Update Contract");
                stageAddEmployee.showAndWait();
            } catch (IOException e) {
                System.out.println("Please check whether the file exists");
            }
            //if the id is invalid then an alert will be made
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Contract has not been entered in the system");
            errorMessage.showAndWait();
        }
    }
}


