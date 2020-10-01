//importing the classes from the javafx library
package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

public class Menu {
    public static Database database;

    //the initialize method will call the onload method from the database class
    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        database = new Database();
    }
//this function is used to confirm the exiting of the screen and used to call the write function to write the objects to the files
    public void exitScene(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the Program");
        Optional<ButtonType> confirmExit = alert.showAndWait();
        if (confirmExit.isPresent() && confirmExit.get() == ButtonType.OK) {
            try {
                write();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.exit();
        }

    }

    //the write method writes the respective the arraylists into the separate files
    public static void write() throws IOException {
        //writing to the employee text file
        File f = new File("Employee.txt");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //writing all the objects in the arraylists
        for (Employee employee : database.getEmployeeList()) {
            oos.writeObject(employee);
        }
        //writing to the Contract text file
        f = new File("Contract.txt");
        fos = new FileOutputStream(f);
        oos = new ObjectOutputStream(fos);
        //writing all the objects in the arraylists
        for (Contract contract : database.getContractList()) {
            oos.writeObject(contract);
        }
        //writing to the Customer text file
        f = new File("Customer.txt");
        fos = new FileOutputStream(f);
        oos = new ObjectOutputStream(fos);
        //writing all the objects in the arraylists
        for (Customer customer : database.getCustomerList()) {
            oos.writeObject(customer);
        }
        //writing to the Work text file
        f = new File("Work.txt");
        fos = new FileOutputStream(f);
        oos = new ObjectOutputStream(fos);
        //writing all the objects in the arraylists
        for (Work work : database.getWorkList()) {
            oos.writeObject(work);
        }
        oos.close();
        fos.close();

    }

    //the method called when the add button under the employee menu item is pressed
    public void addEmployee(ActionEvent evt) throws IOException {
        //loads another scene the employeeadd.xml file
        Parent rootNode = FXMLLoader.load(getClass().getResource("employeeAdd.fxml"));
        //sets the scene size
        Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
        //makes an instance of the Stage class
        Stage stageAddEmployee = new Stage();
        //sets the scene for the particular stage
        stageAddEmployee.setScene(sceneAddEmployee);
        //sets the title of the new scene
        stageAddEmployee.setTitle("Add Employee");
        stageAddEmployee.showAndWait();
    }

    //the method called when the update, view or delete button under the employee menu item is pressed
    public void updateDeleteViewEmployee(ActionEvent evt) {
        try {
            //the fxml file is called
            Parent rootNode = FXMLLoader.load(getClass().getResource("employeeDelUpdateView.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("Update, Delete or View Employee Details");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }
    }

    //the method called when the add button under the contract menu item is pressed
    public void addContract(ActionEvent evt) {
        //the fxml file is called
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("contractAdd.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("Add Contract");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }

    }

    //the method called when the update, view or delete button under the Contract menu item is pressed
    public void updateDeleteViewContract(ActionEvent evt) throws IOException {
//loads the file
        Parent rootNode = FXMLLoader.load(getClass().getResource("contractDelUpdateView.fxml"));
        Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
        Stage stageAddEmployee = new Stage();
        stageAddEmployee.setScene(sceneAddEmployee);
        stageAddEmployee.setTitle("Update, Delete or View Contract Details");
        stageAddEmployee.showAndWait();
    }

    //the method called when the add button under the customer menu item is pressed
    public void addCustomer(ActionEvent evt) {
//loads the fxml file
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("customerAdd.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("Add Customer");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }

    }

    //the method called when the update, view or delete button under the Customer menu item is pressed
    public void updateDeleteViewCustomer(ActionEvent evt) {
        //loads the fxml file
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("customerUpdateView.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("Update or View Customer Details");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }
    }

    //the method called when the add button under the work menu item is clicked
    public void addWork(ActionEvent evt) {
//loads the fxml file
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("workAdd.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 600);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("Add Work");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }

    }

    //the method called the update, view or delete button is pressed under the work menu item
    public void updateDeleteViewWork(ActionEvent evt) {
        //loads the fxml file
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("workDelUpdate.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("Update, Delete or View Work Details");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }
    }

    //this method is called when the user clicks  view under the role menu item
    public void viewRole(ActionEvent evt) {
        //loads the fxml file
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("roleView.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("View Role");
            stageAddEmployee.show();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }

    }

    //the method is called when the user clicks the view button under the salary menu item
    public void viewSalary(ActionEvent evt) {
        //loads the fxml file
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("salaryView.fxml"));
            Scene sceneAddEmployee = new Scene(rootNode, 600, 300);
            Stage stageAddEmployee = new Stage();
            stageAddEmployee.setScene(sceneAddEmployee);
            stageAddEmployee.setTitle("View Salary");
            stageAddEmployee.showAndWait();
        } catch (IOException e) {
            System.out.println("Please check whether the file exists");
        }
    }


}
