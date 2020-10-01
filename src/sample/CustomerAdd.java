package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class CustomerAdd {
    @FXML
    private TextField txtCustomerID;
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtEmail;

    //the function is called when the fxml file is loading
    @FXML
    private void initialize() {
        generate();
        txtCustomerID.setEditable(false);
        txtFName.setPromptText("John");
        txtLName.setPromptText("Steve");
        txtContact.setPromptText("777450345");
        txtEmail.setPromptText("helloWorld@gmail.com");
    }

    //this function automatically generates the customer id for every new entry
    public void generate() {
        if (database.getCustomerList().size() == 0) {
            txtCustomerID.setText(String.valueOf(1));
        } else {
            int id = (database.getCustomerList().get(database.getCustomerList().size() - 1).getCustomerID()) + 1;
            txtCustomerID.setText(String.valueOf(id));
        }
    }

    //this function is called when the add button is clicked
    public void addToDatabase(ActionEvent evt) {
        //all the information entered fields are assigned to variables
        int enteredCustomerID = Integer.valueOf(txtCustomerID.getText());
        String enteredFName = String.valueOf(txtFName.getText()).trim();
        String enteredLName = String.valueOf(txtLName.getText()).trim();
        String enteredContact = String.valueOf(txtContact.getText());
        String enteredEmail = String.valueOf(txtEmail.getText()).trim();
        //the assigned variables are then validated
        String errorMessage = "";
        boolean valid = true;
        String validLName = Validation.validateString(enteredLName);
        int validContactNumber = Validation.validateID(enteredContact, "ContactNo");
        String validEmail = Validation.validEmail(enteredEmail);
        String validFName = Validation.validateString(enteredFName);
        Customer customer = new Customer();
        if (validFName.equals("false") || validFName.equals("true")) {
            valid = false;
            errorMessage += "Invalid of Customer First Name";
        } else {
            customer.setFName(validFName);
        }
        if (validLName.equals("false") || validLName.equals("true")) {
            valid = false;
            errorMessage += "\n Invalid Customer Last Name";
        } else {
            customer.setLName(validLName);
        }
        if (validContactNumber == -2 || validContactNumber == 0 || validContactNumber == -1) {
            valid = false;
            errorMessage += "\n Invalid Contact Number";
        } else {
            String validContact = "true";
            for (Customer customer1 : database.getCustomerList()) {
                if (validContactNumber == customer1.getContactNumber()) {
                    errorMessage += "\nThe Contact Number has Already been entered for another Customer";
                    validContact = "false";
                    valid = false;
                    break;
                }
            }
            if (validContact.equals("true")) {
                customer.setContactNumber(validContactNumber);
            }
        }
        if (validEmail.equals("false") || validEmail.equals("true")) {
            valid = false;
            errorMessage += "\nThe Email is invalid";
        } else {
            String validEmailAdd = "true";
            for (Customer customer1 : database.getCustomerList()) {
                if (validEmail.equals(customer1.getEmail())) {
                    validEmailAdd = "false";
                    valid = false;
                    errorMessage += "\nThe Email is has Already been entered for another Customer";
                    break;
                }
            }
            if (validEmailAdd.equals("true")) {
                customer.setEmail(validEmail);
            }
        }
        if (!valid) {
            Alert error = new Alert(Alert.AlertType.ERROR, errorMessage);
            error.showAndWait();
        } else {
            customer.setCustomerID(enteredCustomerID);
            database.setCustomerList(0, customer);
            Alert added = new Alert(Alert.AlertType.INFORMATION, "The new details have been entered into the system");
            added.showAndWait();
            txtCustomerID.setEditable(false);
            txtFName.setText("");
            txtLName.setText("");
            txtContact.setText("");
            txtEmail.setText("");
            initialize();
        }
    }
}

