import java.io.*;
import java.util.ArrayList;

public class ProductDatabase {
    private String fileName;
    private ArrayList<Product> records;

    public ProductDatabase(String fileName) {
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

    public ArrayList<Product> returnAllRecords(){
        return new ArrayList<>(records);
    }

    public boolean contains(String key ){
        for (Product p : records){
            if(p.getSearchKey().equals(key))
                return true;
        }
        return false;
    }

    public Product getRecord(String key){
        for (Product p : records){
            if(p.getSearchKey().equals(key))
                return p;
        }
        return null;
    }

    public void insertRecord(Product record){
        records.add(record);
    }

    public void deleteRecord(String key){
        boolean check = records.removeIf(p -> p.getSearchKey().equals(key));
        if(check)
            System.out.println("Deleted record");
        else
            System.out.println("Record not found");
    }

    public void saveToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (Product record : records){
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