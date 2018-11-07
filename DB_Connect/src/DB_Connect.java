import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connect {
    private static Connection dbTest;

    DB_Connect() {
        connectDB();
    }

    private void connectDB(){
        try{
            //JDBC Driver Loading
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:"+"@localhost:1521:XE", "database","database");
            System.out.println("데이터베이스에 연결 되었습니다");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Connection failed.");
            System.out.println("SQLException" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static void main(String[] argv){
      //  System.out.println("SQL");
        new DB_Connect();
        try{
            dbTest.close();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:"+e);
        }
    }
}
