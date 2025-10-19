import java.io.*;
import java.util.ArrayList;

public class ProductDatabase extends DataBase<Product> {

    public ProductDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public Product createRecordFrom(String line){
//        line=line.trim();
        String[] fields = line.split(",");
        String productID = fields[0];
        String productName = fields[1];
        String manufacturerName = fields[2];
        String supplierName = fields[3];
        int quantity = Integer.parseInt(fields[4]);
        float price = Float.parseFloat(fields[5]);
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        return p;
    }

    @Override
    public String getSearchKey(Product record) {
        return record.getSearchKey();
    }

    @Override
    public String lineRepresentation(Product record) {
        return record.lineRepresentation();
    }

}