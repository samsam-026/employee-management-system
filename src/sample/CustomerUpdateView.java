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

public class CustomerUpdateView {
    @FXML
    private TextField txtCustomerID;

    public static String classID;
    @FXML
    private void initialize() {
        txtCustomerID.setPromptText("1");
    }

    //this function is called when the view button is pressed
    public void viewCustomer(ActionEvent evt) {
        //the id is first taken from the text field and then it is validated
        String enteredCustomerID = String.valueOf(txtCustomerID.getText());
        int validID = Validation.validateID(enteredCustomerID, "EmpID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            //if it is a valid id we search the customerList to find the customer instance
            for (i = 0; i < database.getCustomerList().size(); i++) {
                if (validID == database.getCustomerList().get(i).getCustomerID()) {
                    present = true;
                    break;
                }
            }
            //and after it is found the details are displayed using an alert
            if (present) {
                Customer customer = database.getCustomerList().get(i);
                String message = "The details are as follow:\nCustomer id: " + customer.getCustomerID() + "\nFirst Name: " + customer.getFName() + "\nLast Name: " + customer.getLName();
                message += "\nContact Number: " + customer.getContactNumber() + "\nEmail Address: " + customer.getEmail();
                Alert information = new Alert(Alert.AlertType.INFORMATION, message);
                information.showAndWait();
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Customer ID");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Customer ID");
            errorMessage.showAndWait();
        }

    }

    //this function validates and checks if such an id exists in the customerList and then if so it uploads the fxml file
    public void updateEmp(ActionEvent evt) {
        String enteredCustomer = String.valueOf(txtCustomerID.getText());
        classID = enteredCustomer;
        int validCustomer = Validation.validateID(enteredCustomer, "EmpID");
        if (!((validCustomer == 0) || (validCustomer == -1))) {
            String validCustom = "false";
            for (Customer customer : database.getCustomerList()) {
                if (validCustomer == customer.getCustomerID()) {
                    validCustom = "true";
                    break;
                }
            }
            //after checking if the id is in the customerList the fxml file is uploaded
            //else the user gets an error
            if (validCustom.equals("true")) {
                try {
                    Parent rootNode = FXMLLoader.load(getClass().getResource("customerUpdate.fxml"));
                    Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
                    Stage stageAddEmployee = new Stage();
                    stageAddEmployee.setScene(sceneAddEmployee);
                    stageAddEmployee.setTitle("Update Customer");
                    stageAddEmployee.showAndWait();
                } catch (IOException e) {
                    System.out.println("Please check whether the file exists");
                }
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Customer has not been entered in the system");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Invalid CustomerID");
            errorMessage.showAndWait();
        }
    }


}

