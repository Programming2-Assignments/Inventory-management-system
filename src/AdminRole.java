import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;
    public AdminRole(){
        this.database= new EmployeeUserDatabase("Employees.txt");
        this.database.readFromFile();
    }
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
        EmployeeUser temp = new EmployeeUser(employeeId, name,email,address,phoneNumber);
        System.out.println("checking if the employee exist .");
        if(!database.contains(employeeId))
        {
            database.insertRecord(temp);
            database.saveToFile();
            System.out.println("the employee is added succesfully !");
        }
        else
        {
            System.out.println("the employee already exists !");
        }
    }
    public EmployeeUser[] getListOfEmployees()
    {
        ArrayList<EmployeeUser> temp = database.returnAllRecords();
        return temp.toArray(new EmployeeUser[0]);
    }
    public void removeEmployee(String key)
    {
        database.deleteRecord(key);
        database.saveToFile();
        System.out.println("employee has been deleted succesfully .\n changes has been done.");
    }
    public void logout() {
        System.out.println("employee database is succesfully saved in file \n Admin logging out.");
        database.saveToFile();
    }
}

