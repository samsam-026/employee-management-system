package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class CustomerUpdate {
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label txtCustomerID;

    //when the fxml file is loading this function will be called
    @FXML
    private void initialize() {
        txtCustomerID.setText((CustomerUpdateView.classID));
        txtFName.setPromptText("John");
        txtLName.setPromptText("Steve");
        txtContact.setPromptText("777450345");
        txtEmail.setPromptText("helloWorld@gmail.com");
    }

    //the information entered in the fields are assigned to variables
    public void addToDatabase(ActionEvent evt) {
        String enteredFName = String.valueOf(txtFName.getText()).trim();
        String enteredLName = String.valueOf(txtLName.getText()).trim();
        String enteredContact = String.valueOf(txtContact.getText());
        String enteredEmail = String.valueOf(txtEmail.getText()).trim();
        //the information is then validated
        String errorMessage = "";
        boolean valid = true;
        String validLName = Validation.validateString(enteredLName);
        int validContactNumber = Validation.validateID(enteredContact, "ContactNo");
        String validEmail = Validation.validEmail(enteredEmail);
        String validFName = Validation.validateString(enteredFName);
        int i;
        for (i = 0; i < database.getWorkList().size(); i++) {
            if (Integer.parseInt(CustomerUpdateView.classID) == database.getCustomerList().get(i).getCustomerID()) {
                break;
            }
        }
        Customer customer = database.getCustomerList().get(i);
        database.getCustomerList().remove(i);
        if (validFName.equals("false")) {
            valid = false;
            errorMessage += "Invalid of Customer First Name";
        } else if (!validFName.equals("true")) {
            customer.setFName(validFName);
        }
        if (validLName.equals("false")) {
            valid = false;
            errorMessage += "\n Invalid Customer Last Name";
        } else if (!validLName.equals("true")) {
            customer.setLName(validLName);
        }
        if (validContactNumber == -2 || validContactNumber == 0) {
            valid = false;
            errorMessage += "\n Invalid Contact Number";
        } else if (validContactNumber != -1) {
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
        if (validEmail.equals("false")) {
            valid = false;
            errorMessage += "\nThe Email is invalid";
        } else if (!validEmail.equals("true")) {
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
        }else{
            Alert message = new Alert(Alert.AlertType.INFORMATION, "The new Details have been added to the database");
            message.showAndWait();
        }
        //after assigning the information we then insert the instance to the list again at the previous position
        database.setCustomerList(i, customer);

    }
}


