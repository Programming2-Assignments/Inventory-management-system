import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerProductDatabase {
    private String fileName;
    private ArrayList<CustomerProduct> records;

    CustomerProductDatabase(String fileName) {
        this.fileName = fileName;
        records = new ArrayList<>();
    }

    public void readFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null){
                records.add(createRecordFrom(line));
            }
        }
        catch (IOException e){
            System.out.println("Error reading file");
        }
    }

    public CustomerProduct createRecordFrom(String line){
//        line=line.trim();
        String[] fields = line.split(",");
        String customerSSN = fields[0];
        String productID = fields[1];
        LocalDate purchaseDate = LocalDate.parse(fields[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate);
        return cp;
    }

    public ArrayList<CustomerProduct> returnAllRecords(){
        return new ArrayList<>(records);
    }

    public boolean contains(String key ){
        for (CustomerProduct cp : records){
            if(cp.getSearchKey().equals(key))
                return true;
        }
        return false;
    }

    public CustomerProduct getRecord(String key){
        for (CustomerProduct cp : records){
            if(cp.getSearchKey().equals(key))
                return cp;
        }
        return null;
    }

    public void insertRecord(CustomerProduct record){
        records.add(record);
    }

    public void deleteRecord(String key){
        boolean check = records.removeIf(cp -> cp.getSearchKey().equals(key));
        if(check)
            System.out.println("Deleted record");
        else
            System.out.println("Record not found");
    }

    public void saveToFile(){
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (CustomerProduct record : records){
                bw.write(record.lineRepresentation());
                bw.newLine();
            }
           System.out.println("Successfully wrote to the file");
       }
       catch (IOException e){
           System.out.println("Error writing file");
       }
    }
}
