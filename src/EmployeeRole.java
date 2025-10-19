import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("Products.txt");
        productsDatabase.readFromFile();
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        customerProductDatabase.readFromFile();
    }
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity , float price)
    {
        Product temp = new Product(productID, productName,manufacturerName,supplierName,quantity, price);
        System.out.println("checking if the product exist .");
        if(!productsDatabase.contains(productID))
        {
            productsDatabase.insertRecord(temp);
            System.out.println("the product is added succesfully !");
        }
        else
        {
            System.out.println("the product already exists !");
        }
    }
    public Product[] getListOfProducts()
    {
        ArrayList<Product> temp = productsDatabase.returnAllRecords();
        return temp.toArray(new Product[0]);
    }
    public CustomerProduct[] getListOfPurchasingOperations()
    {
        ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        return list.toArray(new CustomerProduct[0]);
    }
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
        Product p = productsDatabase.getRecord(productID);

        if (p == null){return false;}
        if (p.getQuantity() <= 0){return false;}
        p.setQuantity(p.getQuantity()-1);
        CustomerProduct c = new CustomerProduct(customerSSN,productID,purchaseDate);
        customerProductDatabase.insertRecord(c);
        customerProductDatabase.saveToFile();
        productsDatabase.deleteRecord(p.getSearchKey());
        productsDatabase.insertRecord(p);
        return true;
    }
    public double returnProduct(String customerSSN, String productID,LocalDate purchaseDate ,LocalDate returnDate)
    {
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();

        if (returnDate.isBefore(purchaseDate)){return -1;}

        Product p = productsDatabase.getRecord(productID);
        if (p == null ){return -1;}

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String key = customerSSN + "," + productID + "," + purchaseDate.format(fmt);
        if (!customerProductDatabase.contains(key)) return -1;

        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if(days>14){return -1;}

        p.setQuantity(p.getQuantity()+1);
        productsDatabase.deleteRecord(p.getSearchKey());
        productsDatabase.insertRecord(p);
        customerProductDatabase.deleteRecord(key);

        return p.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate)
    {
        customerProductDatabase.readFromFile();
        ArrayList<CustomerProduct> temp = customerProductDatabase.returnAllRecords();
        boolean updated = false;
        for(CustomerProduct e:temp)
        {
            if(e.getCustomerSSN().equals(customerSSN) && e.getPurchaseDate().equals(purchaseDate)) {
                if (!e.isPaid()) {
                    e.setPaid(true);
                    updated = true;
                }
                break;
            }
        }
        if(updated)customerProductDatabase.saveToFile();
        return updated;
    }
    public void logout()
    {
        System.out.println("all changes are saved. \nlogged out!");
        customerProductDatabase.saveToFile();
        productsDatabase.saveToFile();
    }

}
