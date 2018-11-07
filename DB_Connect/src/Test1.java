import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
    private String username = "database";
    private String password = "database";
    private static Connection dbTest;

    Test1(){
        connectDB();
    }

    private void connectDB(){
        try{
            //JDBC Driver Loading
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:"+"@localhost:1521:XE", database, database);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        } catch 
    }

    public void execute_query() throws SQLException{}

    public static void main(String[] args){
        Test1 t1 = new Test1();
        try{
            t1.execute_query();
            dbTest.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }

}
