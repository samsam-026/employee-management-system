package sample;

import java.io.*;

public class RoleInsert {
    //this is the final object of the role type available
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File f = new File("Role.txt");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //a 2d array is used to save the role description and the role salary
        String[][] role = new String[3][2];
        role[0][0] = "Hardware Technician";
        role[0][1] = "32";
        role[1][0] = "Programmer";
        role[1][1] = "50";
        role[2][0] = "Software Installer";
        role[2][1] = "26";
        //the instances are made and written to the file
        for (int i = 0; i < 3; i++) {
            Role role1 = new Role();
            role1.setRoleID(i + 1);
            role1.setRoleDesc(role[i][0]);
            role1.setHourlyPay(Integer.parseInt(role[i][1]));
            oos.writeObject(role1);
        }
        fos.close();
        oos.close();
    }
}
