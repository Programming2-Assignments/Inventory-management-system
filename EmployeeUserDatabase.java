import java.io.*;
import java.util.ArrayList;

public class EmployeeUserDatabase {
    private String fileName;
    private ArrayList<EmployeeUser> records;

    public EmployeeUserDatabase(String fileName) {
        this.fileName = fileName;
        this.records = new ArrayList<>();
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

    public EmployeeUser createRecordFrom(String line){
//        line=line.trim();
        String[] fields = line.split(",");
        String employeeId = fields[0];
        String name = fields[1];
        String email = fields[2];
        String address = fields[3];
        String phoneNumber = fields[4];

        EmployeeUser eu = new EmployeeUser(employeeId,name,email,address,phoneNumber);
        return eu;
    }

    public ArrayList<EmployeeUser> returnAllRecords(){
        return new ArrayList<>(records);
    }

    public boolean contains(String key ){
        for (EmployeeUser eu : records){
            if(eu.getSearchKey().equals(key))
                return true;
        }
        return false;
    }

    public EmployeeUser getRecord(String key){
        for (EmployeeUser eu : records){
            if(eu.getSearchKey().equals(key))
                return eu;
        }
        return null;
    }

    public void insertRecord(EmployeeUser record){
        records.add(record);
    }

    public void deleteRecord(String key){
        boolean check = records.removeIf(eu -> eu.getSearchKey().equals(key));
        if(check)
            System.out.println("Deleted record");
        else
            System.out.println("Record not found");
    }

    public void saveToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (EmployeeUser record : records){
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
