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

public class WorkDelUpdate {
    @FXML
    private TextField txtWorkID;

    @FXML
    private void initialize() {
        txtWorkID.setPromptText("1");

    }

    public static String classID;

    //the function is used to view the details of a record in the work table based on the work id
    public void viewWork(ActionEvent evt) {
        //validate id and presence check of id in table
        String enteredWorkID = String.valueOf(txtWorkID.getText());
        int validID = Validation.validateID(enteredWorkID, "WorkID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getWorkList().size(); i++) {
                if (validID == database.getWorkList().get(i).getWorkIDArray()) {
                    present = true;
                    break;
                }

            }
            //if present then take information from the arraylist and display to user using the alert
            if (present) {
                Work work = database.getWorkList().get(i);
                String message = "The details are as follow:\nWork id: " + work.getWorkIDArray() + "\nContract ID: " + work.getContractID() + "\nRole ID: " + work.getRoleID();
                message += "\nEmployee ID: " + work.getEmpID() + "\nNumber of Hours Worked: " + work.getNumHoursWorked();
                Alert information = new Alert(Alert.AlertType.INFORMATION, message);
                information.showAndWait();
            }else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The work ID is not entered into the system");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Work ID");
            errorMessage.showAndWait();
        }

    }

    //we delete the work entry if the work id valid and present
    public void deleteWork(ActionEvent evt) {
        String enteredWorkID = String.valueOf(txtWorkID.getText());
        int validID = Validation.validateID(enteredWorkID, "WorkID");
        if ((validID != 0) && (validID != -1)) {
            int i;
            boolean present = false;
            for (i = 0; i < database.getWorkList().size(); i++) {
                if (validID == database.getWorkList().get(i).getWorkIDArray()) {
                    present = true;
                    break;
                }

            }
            if (present) {
                if (Validation.alert().equals("true")) {
                    database.getWorkList().remove(i);
                    Alert information = new Alert(Alert.AlertType.INFORMATION, "Deleted From the Database");
                    information.showAndWait();
                }
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The work ID is not entered into the system");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Incorrect format of Work ID");
            errorMessage.showAndWait();
        }


    }

    //the fxml file is uploaded if the workID entered is valid else an alert will be displayed with an appropriate message
    public void updateWork(ActionEvent evt) {
        String enteredWork = String.valueOf(txtWorkID.getText());
        classID = enteredWork;
        int validWorkID = Validation.validateID(enteredWork, "EmpID");
        if (!((validWorkID == 0) || (validWorkID == -1))) {
            String validWork = "false";
            for (Work work : database.getWorkList()) {
                if (validWorkID == work.getWorkIDArray()) {
                    validWork = "true";
                    break;
                }
            }
            if (validWork.equals("true")) {
                try {
                    Parent rootNode = FXMLLoader.load(getClass().getResource("workUpdate.fxml"));
                    Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
                    Stage stageAddEmployee = new Stage();
                    stageAddEmployee.setScene(sceneAddEmployee);
                    stageAddEmployee.setTitle("Update Work");
                    stageAddEmployee.showAndWait();
                } catch (IOException e) {
                    System.out.println("Please check whether the file exists");
                }
            } else {
                Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The Work has not been entered in the system");
                errorMessage.showAndWait();
            }
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "Invalid WorkID");
            errorMessage.showAndWait();
        }
    }


}

