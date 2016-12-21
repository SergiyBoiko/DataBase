import java.sql.*;

public class WorkWithDataBase {

    void createDataBase(Connection conn) throws ClassNotFoundException, SQLException {
        Statement st = conn.createStatement();
        st.execute("DROP TABLE EMPLOYEE IF EXISTS");
        st.execute("CREATE TABLE EMPLOYEE(id INT PRIMARY KEY, name VARCHAR(255) NOT NULL);");
        st.execute("DROP TABLE SALARY IF EXISTS");
        st.execute("CREATE TABLE SALARY (id INT PRIMARY KEY, date DATE NOT NULL, value DECIMAL NOT NULL, emp_id INT, FOREIGN KEY (emp_id) REFERENCES EMPLOYEE (id))");
        System.out.println("DataBase create");
    }
    void addDataInDataBase(Connection conn) throws SQLException, ClassNotFoundException {
        Statement st = conn.createStatement();
//        String sql =
//                "load data infile '~/fileData/EMPLOYEE.txt' \n" +
//                        "into table EMPLOYEE \n" +
//                        "CHARACTER SET cp1251";
        st.execute("INSERT INTO EMPLOYEE VALUES(1, 'Ivanov');");
        st.execute("INSERT INTO EMPLOYEE VALUES(2, 'Petrov');");
        st.execute("INSERT INTO EMPLOYEE VALUES(3, 'Sidorov');");
        st.execute("INSERT INTO EMPLOYEE VALUES(4, 'Andreev');");

        st.execute("INSERT INTO SALARY VALUES(1, '2016-9-23','3201.53',(SELECT id FROM EMPLOYEE WHERE name = 'Ivanov'));");
        st.execute("INSERT INTO SALARY VALUES(2, '2016-9-23','3501.23',(SELECT id FROM EMPLOYEE WHERE name = 'Petrov'));");
        st.execute("INSERT INTO SALARY VALUES(3, '2016-9-23','1021.23',(SELECT id FROM EMPLOYEE WHERE name = 'Sidorov'));");
        st.execute("INSERT INTO SALARY VALUES(4, '2016-9-23','3141.23',(SELECT id FROM EMPLOYEE WHERE name = 'Andreev'));");
        st.execute("INSERT INTO SALARY VALUES(5, '2016-10-28','2901.53',(SELECT id FROM EMPLOYEE WHERE name = 'Ivanov'));");
        st.execute("INSERT INTO SALARY VALUES(6, '2016-10-28','3501.47',(SELECT id FROM EMPLOYEE WHERE name = 'Petrov'));");
        st.execute("INSERT INTO SALARY VALUES(7, '2016-10-28','1321.45',(SELECT id FROM EMPLOYEE WHERE name = 'Sidorov'));");
        st.execute("INSERT INTO SALARY VALUES(8, '2016-10-28','3120.23',(SELECT id FROM EMPLOYEE WHERE name = 'Andreev'));");
        st.execute("INSERT INTO SALARY VALUES(9, '2016-11-28','120.23',(SELECT id FROM EMPLOYEE WHERE name = 'Andreev'));");
        System.out.println("Data added into DataBase");
    }
    void showDataInDataBase(Connection conn)throws SQLException, ClassNotFoundException{
        Statement st = conn.createStatement();
        Statement statement = conn.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM EMPLOYEE");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SALARY");

        String id = "", name = "", date = "", value = "", emp_id = "";

        while (result.next()) {
            id = result.getString("id");
            name = result.getString("name");

            System.out.printf("%3s | %-10s \n", id, name);
        }
        while (resultSet.next()){
            id = resultSet.getString("id");
            date = resultSet.getString("date");
            value = resultSet.getString("value");
            emp_id = resultSet.getString("emp_id");

            System.out.printf("%3s | %10s | %10s | %3s \n",id,date,value,emp_id);
        }
    }
    void totalSumSalary(Connection conn)throws SQLException, ClassNotFoundException{
        Statement st = conn.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT name, emp_id,  SUM (value) AS TotalSumSalary FROM SALARY, EMPLOYEE WHERE EMPLOYEE.id = SALARY.emp_id  GROUP BY emp_id");
        String totalSumSalary = "", name = "";
        while(resultSet.next()){
            name = resultSet.getString("name");
            totalSumSalary = resultSet.getString("TotalSumSalary");

            System.out.printf("%10s | %10s \n", name, totalSumSalary);
        }

    }
}
