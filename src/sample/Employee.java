package sample;

import javafx.scene.control.Alert;

import java.io.Serializable;
import java.util.ArrayList;

public class Employee implements Serializable {
    //the variables are declared
    private int empID;
    private String fName;
    private String lName;
    private String dob;
    private String address;
    private int contactNo;
    private String email;
    private ArrayList<Integer> roleArray;

    //in the constructor it is initialized
    public Employee() {
        this.empID = 0;
        this.fName = "";
        this.lName = "";
        this.dob = null;
        this.address = "";
        this.contactNo = 0;
        this.email = "";
        this.roleArray = new ArrayList<>();
    }

    //the setter methods are below for the attributes declared
    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleArray(int roleID) {
        if (roleArray.size() < 3) {
            roleArray.add(roleID);
        } else {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR, "The maximum number of Roles have been reached");
            errorMessage.showAndWait();
        }
    }

    //to access the attributes above the getter methods are used
    public int getEmpID() {
        return empID;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getDOB() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Integer> getRoleArray() {
        return roleArray;
    }

}
