import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    private static Scanner input = new Scanner(System.in);

    public static boolean validateINT(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateSTR(String test) {
        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return !test.isEmpty();
    }

    public static boolean validateEMAIL(String test) {
        return test.contains("@") && test.contains(".");
    }


    public static void main(String[] args) {
        System.out.println("Inventory Management System");
        while (true) {
            System.out.println("\nLogin as:");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 3:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Please enter 1, 2, or 3.");
            }
        }
    }
    private static void adminMenu() {
        AdminRole admin = new AdminRole();

        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. View Employees");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String id = input.nextLine().trim();

                    System.out.print("Enter Employee Name: ");
                    String name = input.nextLine().trim();

                    System.out.print("Enter Employee Email: ");
                    String email = input.nextLine().trim();

                    System.out.print("Enter Employee Address: ");
                    String address = input.nextLine().trim();

                    System.out.print("Enter Employee Phone Number: ");
                    String phone = input.nextLine().trim();

                    if (!validateSTR(name)) System.out.println("Invalid name.");
                    else if (!validateEMAIL(email)) System.out.println("Invalid email format.");
                    else if (!validateINT(phone)) System.out.println("Invalid phone number.");
                    else {
                        admin.addEmployee(id, name, email, address, phone);
                    }
                    break;

                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    String removeID = input.nextLine().trim();
                    admin.removeEmployee(removeID);
                    break;

                case 3:
                    EmployeeUser[] employees = admin.getListOfEmployees();
                    System.out.println("\n Employee List ");
                    for (int i = 0; i < employees.length; i++) {
                        System.out.println(employees[i].lineRepresentation());
                    }
                    break;

                case 4:
                    admin.logout();
                    System.out.println("Admin logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private static void employeeMenu() {
        EmployeeRole employee = new EmployeeRole();
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("\nEmployee Menu");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Purchase Product");
            System.out.println("4. Return Product");
            System.out.println("5. Apply Payment");
            System.out.println("6. View Purchases");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String P_id = input.nextLine().trim();

                    System.out.print("Enter Product Name: ");
                    String P_name = input.nextLine().trim();

                    System.out.print("Enter Manufacturer Name: ");
                    String M_name = input.nextLine().trim();

                    System.out.print("Enter Supplier Name: ");
                    String S_name = input.nextLine().trim();

                    System.out.print("Enter Quantity: ");
                    String qtySTR = input.nextLine().trim();

                    System.out.print("Enter the price:");
                    String priSTR = input.nextLine().trim();

                    if (!validateINT(qtySTR)) {
                        System.out.println("Invalid quantity.");
                        break;
                    }

                    float pri = Float.parseFloat(priSTR);
                    int qty = Integer.parseInt(qtySTR);
                    employee.addProduct(P_id, P_name, M_name, S_name, qty ,pri);
                    break;

                case 2:
                    Product[] products = employee.getListOfProducts();
                    System.out.println("\n Product List ");
                    for (int i = 0; i < products.length; i++) {
                        System.out.println(products[i].lineRepresentation());
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer SSN: ");
                    String PUR_ssn = input.nextLine().trim();

                    System.out.print("Enter Product ID: ");
                    String PUR_prod_ID = input.nextLine().trim();

                    LocalDate date = LocalDate.now();
                    if (employee.purchaseProduct(PUR_ssn, PUR_prod_ID, date))
                        System.out.println("Purchase successful.");
                    else
                        System.out.println("Purchase failed.");
                    break;

                case 4:
                    System.out.print("Enter Customer SSN: ");
                    String R_ssn = input.nextLine().trim();

                    System.out.print("Enter Product ID: ");
                    String R_prod = input.nextLine().trim();

                    System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                    LocalDate P_date = LocalDate.parse(input.nextLine().trim(), Date);

                    System.out.print("Enter Return Date (dd-MM-yyyy): ");
                    LocalDate R_date = LocalDate.parse(input.nextLine().trim(), Date);

                    double refund = employee.returnProduct(R_ssn, R_prod, P_date, R_date);
                    if (refund == -1) System.out.println("Return failed.");
                    else System.out.println("Return accepted. Refund: " + refund);
                    break;

                case 5:
                    System.out.print("Enter Customer SSN: ");
                    String C_ssn = input.nextLine().trim();

                    System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                    LocalDate payDate = LocalDate.parse(input.nextLine().trim(), Date);

                    if (employee.applyPayment(C_ssn, payDate))
                        System.out.println("Payment applied.");
                    else
                        System.out.println("Payment failed.");
                    break;

                case 6:
                    CustomerProduct[] Purchace_Operations = employee.getListOfPurchasingOperations();
                    System.out.println("\n Purchases ");
                    for (CustomerProduct cp : Purchace_Operations) {
                        System.out.println(cp.lineRepresentation());
                    }
                    break;

                case 7:
                    employee.logout();
                    System.out.println("Employee logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
