package sample;

import java.io.Serializable;

public class Work implements Serializable {
    //the attributes are declared
    private int workID;
    private int contractID;
    private int empID;
    private int roleID;
    private int numHoursWorked;

    //in the constructor the attributes are initialized
    public Work() {
        this.workID= 0 ;
        this.contractID = 0;
        this.empID = 0;
        this.roleID = 0;
        this.numHoursWorked = 0;
    }

    //the setter methods are used to set the code
    public void setWorkID(int workID) {
        this.workID=(workID);
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setNumHoursWorked(int numHoursWorked) {
        this.numHoursWorked = numHoursWorked;
    }

    //the getter methods are used to access the variables
    public int getWorkIDArray() {
        return workID;
    }

    public int getContractID() {
        return contractID;
    }

    public int getEmpID() {
        return empID;
    }

    public int getRoleID() {
        return roleID;
    }

    public int getNumHoursWorked() {
        return numHoursWorked;
    }
}
