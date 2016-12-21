import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        WorkWithDataBase db = new WorkWithDataBase();
        db.createDataBase();
        db.addDataInDataBase();
        db.showDataInDataBase();
        db.totalSumSalary();
    }
}
