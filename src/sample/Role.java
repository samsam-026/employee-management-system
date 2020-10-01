package sample;

import java.io.Serializable;

public class Role implements Serializable {
    //the attributes are declared
    private int roleID;
    private String roleDesc;
    private double hourlyPay;

    //using the constructor the attributes are initialized
    public Role(){
        this.roleID=0;
        this.roleDesc="";
        this.hourlyPay=0;
    }
    //using the set method below the attributes are changed
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    //using the get methods the attributes can be accessed

    public int getRoleID() {
        return roleID;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }
}
