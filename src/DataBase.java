import java.io.*;
import java.util.ArrayList;

public abstract class DataBase<t> {
    private String fileName;
    private ArrayList<t> records;

    public DataBase(String fileName) {
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

    public boolean contains(String key ){
        for (t x : records){
            if(getSearchKey(x).equals(key))
                return true;
        }
        return false;
    }

    public void deleteRecord(String key){
        boolean check = records.removeIf(x -> getSearchKey(x).equals(key));
        if(check)
            System.out.println("Deleted record");
        else
            System.out.println("Record not found");
    }

    public void saveToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (t record : records){
                bw.write(lineRepresentation(record));
                bw.newLine();
            }
            System.out.println("Successfully wrote to the file");
        }
        catch (IOException e){
            System.out.println("Error writing file");
        }
    }
    
    public ArrayList<t> returnAllRecords(){
        return new ArrayList<>(records);
    }

    public t getRecord(String key){
        for (t x : records){
            if(getSearchKey(x).equals(key))
                return x;
        }
        return null;
    }

    public void insertRecord(t record){
        records.add(record);
    }

    public abstract String getSearchKey(t record);
    public abstract String lineRepresentation(t record);
    public abstract t createRecordFrom(String line);
}
