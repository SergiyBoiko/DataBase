import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static Connection getConnection()throws SQLException,ClassNotFoundException {
    Class.forName("org.h2.Driver");
    String url = "jdbc:h2:~/myDataBase;IFEXISTS=TRUE";
    return DriverManager.getConnection(url, "sa", "");
}
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        WorkWithDataBase db = new WorkWithDataBase();
        Connection connection = getConnection();
        db.createDataBase(connection);
        db.addDataInDataBase(connection);
        db.showDataInDataBase(connection);
        db.totalSumSalary(connection);
    }


}
