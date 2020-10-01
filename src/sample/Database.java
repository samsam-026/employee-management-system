package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    //declaring and initializing lists of the classes
    private List<Employee> employeeList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<Contract> contractList = new ArrayList<>();
    private List<Work> workList = new ArrayList<>();
    private List<Role> roleList = new ArrayList<>();

    //the constructor is used to upload all the information in the files to the lists available
    public Database() throws IOException, ClassNotFoundException {
        uploadEmployeeList();
        uploadCustomerList();
        uploadContractList();
        uploadWorkList();
        uploadRoleList();
    }

    //the 5 functions below reads the objects from the file till the end of the file
    public void uploadEmployeeList() throws IOException, ClassNotFoundException {
        File f = new File("Employee.txt");
        //if there are no objects in the file this code block won't execute
        if (f.length() != 0) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Employee employee = (Employee) ois.readObject();
                    employeeList.add(employee);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            fis.close();
        }

    }

    public void uploadCustomerList() throws IOException, ClassNotFoundException {
        File f = new File("Customer.txt");
        if (f.length() != 0) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Customer customer = (Customer) ois.readObject();
                    customerList.add(customer);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            fis.close();
        }
    }

    public void uploadContractList() throws IOException, ClassNotFoundException {
        File f = new File("Contract.txt");
        if (f.length() != 0) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Contract contract = (Contract) ois.readObject();
                    contractList.add(contract);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            fis.close();
        }
    }

    public void uploadWorkList() throws IOException, ClassNotFoundException {
        File f = new File("Work.txt");
        if (f.length() != 0) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Work work = (Work) ois.readObject();
                    workList.add(work);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            fis.close();
        }
    }

    public void uploadRoleList() throws IOException, ClassNotFoundException {
        File f = new File("Role.txt");

        FileInputStream fis = new FileInputStream(f);

        ObjectInputStream ois = new ObjectInputStream(fis);
        while (true) {
            try {
                roleList.add((Role) ois.readObject());
            } catch (EOFException e) {
                break;
            }
        }
        ois.close();
        fis.close();

    }

    //the 5 below functions are used to append the instance in a specific index or at the end
    public void setEmployeeList(int index, Employee employee) {
        if (index != 0) {
            employeeList.add(index, employee);
        } else {
            employeeList.add(employee);
        }
    }

    public void setCustomerList(int index, Customer customer) {
        if (index != 0) {
            customerList.add(index, customer);
        } else {
            customerList.add(customer);
        }
    }

    public void setContractList(int index, Contract contract) {
        if (index != 0) {
            contractList.add(index, contract);
        } else {
            contractList.add(contract);
        }
    }

    public void setWorkList(int index, Work work) {
        if (index != 0) {
            workList.add(index, work);
        } else {
            workList.add(work);
        }
    }

    //the 5 below functions are used to access the list
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }


}
