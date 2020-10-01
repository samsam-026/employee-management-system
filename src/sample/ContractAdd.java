package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class ContractAdd {
    //declaring the variables
    @FXML
    private TextField txtContractID;
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

    //when the fxml file loads this function will run
    @FXML
    private void initialize() {
        generate();
        txtContractID.setEditable(false);
        txtContractName.setPromptText("EasyToYou");
        txtContractDesc.setPromptText("Helps in delivery");
        txtContractCreateDate.setPromptText("12/08/2018");
        txtJobTypeName.setPromptText("Mobile Application");
        txtProjectLeaderEmpID.setPromptText("1");
        txtCustomerID.setPromptText("1");
    }

    public void generate() {
        //automatically generates the id for the new entry using the previous entry's id
        if (database.getContractList().size() == 0) {
            txtContractID.setText(String.valueOf(1));
        } else {
            int id = (database.getContractList().get(database.getContractList().size() - 1).getContractID()) + 1;
            txtContractID.setText(String.valueOf(id));
        }
    }

    //the function called when the add button is clicked
    public void addToDatabase(ActionEvent evt) {
        //information from all fields are first extracted
        int validID = Integer.valueOf(txtContractID.getText());
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
        boolean valid = true;
        String errorMessage = "";
        Contract contract = new Contract();
        //if the fields have been filled only then they are validated else they wont't be validated
        if (validContName.equals("false") || validContName.equals("true")) {
            errorMessage += "\nInvalid format of Contract Name";
            valid = false;
            //if it is valid then they are passed to the setter method of the attribute
        } else {
            contract.setContractName(validContName);
        }
        if (validJobName.equals("false") || validJobName.equals("true")) {
            errorMessage += "\nInvalid format of Job Type Name";
            valid = false;
        } else {
            contract.setJobTypeName(validJobName);
        }
        if (validDesc.equals("false") || validDesc.equals("true")) {
            errorMessage += "\nInvalid format of Contract Descriptions";
            valid = false;
        } else {
            contract.setContractDesc(validDesc);
        }
        if (validLeadID == 0 || validLeadID == -1) {
            errorMessage += "\nThe Employee ID is invalid";
            valid = false;
            //the lead id is checked if it is present in the employee table
        } else {
            String validLead = "false";
            for (Employee employee1 : database.getEmployeeList()) {
                if (validLeadID == employee1.getEmpID()) {
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
        if (validDate.equals("false") || validDate.equals("true")) {
            errorMessage += "\nThe Creation Date is invalid";
            valid = false;
        } else {
            contract.setContractCreateDate(validDate);
        }
        if (validCustomerID == 0 || validCustomerID == -1) {
            errorMessage += "\nThe Customer ID is invalid";
            valid = false;
        } else {
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
            contract.setContractID(validID);
            database.setContractList(0, contract);
            Alert message = new Alert(Alert.AlertType.INFORMATION, "The new Details have been added to the database");
            message.showAndWait();
            txtContractID.setEditable(false);
            txtContractCreateDate.setText("");
            txtContractDesc.setText("");
            txtContractName.setText("");
            txtJobTypeName.setText("");
            txtProjectLeaderEmpID.setText("");
            txtCustomerID.setText("");
            initialize();
        }
    }
}





