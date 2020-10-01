package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class ContractUpdate {
    @FXML
    private Label txtContractID;
    @FXML
    private TextField txtContractName;
    @FXML
    private TextArea txtContractDesc;
    @FXML
    private TextField txtContractCreateDate;
    @FXML
    private TextField txtJobTypeName;
    @FXML
    private TextField txtProjectLeaderEmpID;
    @FXML
    private TextField txtCustomerID;

    //when the page is loading this function will be called
    @FXML
    private void initialize() {
        txtContractID.setText((ContractDelUpdateView.classID));
        txtContractName.setPromptText("EasyToYou");
        txtContractDesc.setPromptText("Helps in delivery");
        txtContractCreateDate.setPromptText("12/08/2018");
        txtJobTypeName.setPromptText("Mobile Application");
        txtProjectLeaderEmpID.setPromptText("1");
        txtCustomerID.setPromptText("1");
    }

    //if the update button in clicked this method is called
    public void addToDatabase(ActionEvent evt) {
        //information from all fields are first extracted
        String enteredContractName = String.valueOf(txtContractName.getText()).trim();
        String enteredDesc = String.valueOf(txtContractDesc.getText()).trim();
        String ContractCreateDate = String.valueOf(txtContractCreateDate.getText());
        String enteredJobName = String.valueOf(txtJobTypeName.getText()).trim();
        String enteredLeadID = String.valueOf(txtProjectLeaderEmpID.getText()).trim();
        String enteredCustomerID = String.valueOf(txtCustomerID.getText()).trim();
        //then the above information is validated
        String validContName = Validation.validateString(enteredContractName);
        String validJobName = Validation.validateString(enteredJobName);
        String validDesc = Validation.validateString(enteredDesc);
        int validLeadID = Validation.validateID(enteredLeadID, "ProjectLeaderID");
        String validDate = Validation.validateDate(ContractCreateDate);
        int validCustomerID = Validation.validateID(enteredCustomerID, "CustomerID");
        //using the id we get the contract then we delete it
        int i;
        for (i = 0; i < database.getContractList().size(); i++) {
            if (Integer.parseInt(ContractDelUpdateView.classID) == database.getContractList().get(i).getContractID()) {
                break;
            }
        }
        boolean valid = true;
        String errorMessage = "";
        Contract contract = database.getContractList().get(i);
        database.getContractList().remove(i);
        //if the fields have been filled only then they are validated else they wont't be validated
        if (validContName.equals("false")) {
            errorMessage += "\nInvalid format of Contract Name";
            valid = false;
            //if it is valid then they are passed to the setter method of the attribute
        } else if (!validContName.equals("true")) {
            contract.setContractName(validContName);
        }
        if (validJobName.equals("false")) {
            errorMessage += "\nInvalid format of Job Type Name";
            valid = false;
        } else if (!validJobName.equals("true")) {
            contract.setJobTypeName(validJobName);
        }
        if (validDesc.equals("false")) {
            errorMessage += "\nInvalid format of Contract Descriptions";
            valid = false;
        } else if (!validDesc.equals("true")) {
            contract.setContractDesc(validDesc);
        }
        if (validLeadID == 0) {
            errorMessage += "\nThe Employee ID is invalid";
            valid = false;
            //the lead id is checked if it is present in the employee table
        } else if (validLeadID != -1) {
            String validLead = "false";
            for (Employee employee1 : database.getEmployeeList()) {
                if (validLeadID == employee1.getContactNo()) {
                    validLead = "true";
                    break;
                }
            }
            if (validLead.equals("true")) {
                contract.setProjectLeaderEmpID(validLeadID);
            } else {
                errorMessage += "\nThe Employee ID hasn't been entered into the Employee ID";
                valid = false;
            }
        }
        if (validDate.equals("false")) {
            errorMessage += "\nThe Creation Date is invalid";
            valid = false;
        } else if (!validDate.equals("true")) {
            contract.setContractCreateDate(validDate);
        }
        if (validCustomerID == 0) {
            errorMessage += "\nThe Customer ID is invalid";
            valid = false;
        } else if (validCustomerID != -1) {
            String validCustomer = "true";
            for (Customer customer : database.getCustomerList()) {
                if (validCustomerID == customer.getCustomerID()) {
                    validCustomer = "false";
                    break;
                }
            }
            if (validCustomer.equals("false")) {
                contract.setCustomerID(validCustomerID);
            } else {
                errorMessage += "\nThe Customer has not been entered in the Customer Table";
                valid = false;
            }
        }
        if (!valid) {
            Alert error = new Alert(Alert.AlertType.ERROR, errorMessage);
            error.showAndWait();
        } else {
            Alert message = new Alert(Alert.AlertType.INFORMATION, "The new Details have been added to the database");
            message.showAndWait();
        }
        //once the entered fields have been updated the updated contract is then placed into the array at the previous position
        database.setContractList(i, contract);
    }
}

