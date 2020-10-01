package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static sample.Menu.database;

public class RoleView {
    @FXML
    private TextField txtWorkID;
    @FXML
    private void initialize() {
        txtWorkID.setPromptText("1");

    }

    //the function roleView is used to view the roles using the role id
    public void roleView(ActionEvent evt) {
        String enteredRoleID = String.valueOf(txtWorkID.getText());
        int validID = Validation.validateID(enteredRoleID, "RoleID");
        //the role ids are first validated and checked if it is in then list
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getRoleList().size(); i++) {
                if (validID == database.getRoleList().get(i).getRoleID()) {
                    present = true;
                    break;
                }
            }
            //if it is in the list then using the get method we can access the roleList and iterate and find the role and display the information to the user
            if (present) {
                Role role = database.getRoleList().get(i);
                String message = "The details are as follow:\nRole id: " + role.getRoleID() + "\nRole Description: " + role.getRoleDesc() + "\nHourly Pay for the Role: $" + role.getHourlyPay();
                Alert information = new Alert(Alert.AlertType.INFORMATION, message);
                information.showAndWait();
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Role ID has not been entered into the Role Table");
                errorMessage.showAndWait();
            }
            //if we cannot find the role id then error alert wil be displayed
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Role ID");
            errorMessage.showAndWait();
        }

    }
}
