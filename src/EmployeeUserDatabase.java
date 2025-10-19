public class EmployeeUserDatabase extends DataBase<EmployeeUser> {

    public EmployeeUserDatabase(String fileName) {
        super(fileName);
    }

    @Override
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

    @Override
    public String getSearchKey(EmployeeUser record) {
        return record.getSearchKey();
    }

    @Override
    public String lineRepresentation(EmployeeUser record) {
        return record.lineRepresentation();
    }

}
