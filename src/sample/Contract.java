package sample;

import java.io.Serializable;

public class Contract implements Serializable {
    //declaring the variables
    private int contractID;
    private String contractName;
    private String contractDesc;
    private String contractCreateDate;
    private String jobTypeName;
    private int projectLeaderEmpID;
    private int customerID;
//constructor(initializing variables)
    public Contract() {
        this.contractID = 0;
        this.contractName = "";
        this.contractDesc = "";
        this.jobTypeName = "";
        this.projectLeaderEmpID = 0;
        this.customerID = 0;
    }
    //setter methods for the above variables
    public void setContractID(int contractID){
        this.contractID=contractID;
    }

    public void setContractName(String contractName){
        this.contractName=contractName;
    }

    public void setContractDesc(String contractDesc){
        this.contractDesc=contractDesc;
    }

    public void setContractCreateDate(String contractCreateDate) {
        this.contractCreateDate=contractCreateDate;
    }

    public void setJobTypeName(String jobTypeName){
        this.jobTypeName=jobTypeName;
    }

    public void setProjectLeaderEmpID(int projectLeaderEmpID) {
        this.projectLeaderEmpID=projectLeaderEmpID;
    }

    public void setCustomerID(int customerID){
        this.customerID=customerID;
    }

    //getter methods for the above variables

    public int getContractID() {
        return contractID;
    }

    public String getContractName(){
        return contractName;
    }

    public String getContractDesc() {
       return contractDesc;
    }

    public String getContractCreateDate() {
        return contractCreateDate;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public int getProjectLeaderEmpID() {
        return projectLeaderEmpID;
    }

    public int getCustomerID() {
        return customerID;
    }

}
