import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends DataBase<CustomerProduct> {

    CustomerProductDatabase(String fileName) {
        super(fileName);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public CustomerProduct createRecordFrom(String line){
//        line=line.trim();
        String[] fields = line.split(",");
        String customerSSN = fields[0];
        String productID = fields[1];
        LocalDate purchaseDate = LocalDate.parse(fields[2].trim(), formatter);
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