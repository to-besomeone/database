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
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:"+"@localhost:1521:XE", username, password);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public void execute_query() throws SQLException{
        String sqlStr = "SELECT maker, type FROM product" + " Where model=2004";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            System.out.println("make: " + rs.getString("MAKER"));
            System.out.println("type: "+rs.getString("TYPE"));
        }
        rs.close();
        stmt.close();
    }

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
