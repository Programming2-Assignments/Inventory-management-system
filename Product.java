public class Product {

    // Private variables
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    // Constructor
    public Product(
            String productID,
            String productName,
            String manufacturerName,
            String supplierName,
            int quantity,
            float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getQuantity() {
        return quantity;
    }

    // Returns the product id
    public String getSearchKey() {
        return productID;
    }

    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method
    // Returns the data of the product comma separated
    public String lineRepresentation() {
        return productID + "," + productName + ","
                + manufacturerName + "," + supplierName
                + "," + quantity + "," + price;
    }

}
