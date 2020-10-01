package sample;

import java.io.Serializable;

public class Customer implements Serializable {

    //declaring the variables
    private int customerID;
    private String fName;
    private String lName;
    private int contactNumber;
    private String email;

//initializing the variables using the constructor
    public Customer() {
        this.customerID = 0;
        this.fName = "";
        this.lName = "";
        this.contactNumber = 0;
        this.email = "";
    }

    //set methods for the above attributes
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //get methods for the attributes
    public int getCustomerID() {
        return customerID;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }
}
