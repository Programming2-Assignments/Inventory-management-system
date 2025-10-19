import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerProductDatabase extends DataBase<CustomerProduct> {

    CustomerProductDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public CustomerProduct createRecordFrom(String line){
//        line=line.trim();
        String[] fields = line.split(",");
        String customerSSN = fields[0];
        String productID = fields[1];
        LocalDate purchaseDate = LocalDate.parse(fields[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate);
        return cp;
    }

    @Override
    public String getSearchKey(CustomerProduct record) {
        return record.getSearchKey();
    }

    @Override
    public String lineRepresentation(CustomerProduct record) {
        return record.lineRepresentation();
    }

}
