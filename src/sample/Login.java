package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private
    Button login;
    @FXML
    private void initialize() {
        txtPassword.setPromptText("Password");
        txtUsername.setPromptText("Username");
    }

    public void login(ActionEvent evt) throws IOException {
        //read the file called login
        File fileHandle = new File("Login.txt");
        Scanner sc = new Scanner(fileHandle);
        //appending all the lines of the file to a string array
        List<String> lines = new ArrayList<>();
        //read until the end of the file
        while (sc.hasNext()) {
            lines.add(sc.nextLine());
        }
        //get the first line and split to get the user name
        String[] line1 = lines.get(0).split(":");
        String username = line1[1];
        //get the second line and split and get the password
        String[] line2 = lines.get(1).split(":");
        String password = line2[1];
        //verifies if the entered password and username is the saved one in the file
        if (username.equals(String.valueOf(txtUsername.getText())) && password.equals(String.valueOf(txtPassword.getText()))) {
            //a alert is used to inform the user about the successful login
            Alert message = new Alert(Alert.AlertType.INFORMATION, "Successful Login");
            message.showAndWait();
            //if it is true then the menu.fxml is loaded
            try {
                Parent rootNode = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
                Stage stageAddEmployee = new Stage();
                stageAddEmployee.setScene(sceneAddEmployee);
                stageAddEmployee.setTitle("Company Database");
                stageAddEmployee.show();
                closeLogin();

            } catch (IOException e) {
                System.out.println("Please check whether the file exists");
            }
            //in the case that the user enters a wrong password or username an alert will be made
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR, "Incorrect Username or password");
            error.showAndWait();
        }
    }
    private void closeLogin() {
        Stage stage1 = (Stage) login.getScene().getWindow();
        stage1.close();
    }
}

