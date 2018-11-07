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

    public void execute_query_problem1() throws SQLException{
        String sqlStr = "SELECT avg(SPEED) FROM PC";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){

            System.out.println("avg(speed): " + rs.getFloat("avg(SPEED)"));
            //System.out.println("type: "+rs.getString("TYPE"));
        }
        rs.close();
        stmt.close();
    }

    public void execute_query_problem2() throws SQLException{
        String sqlStr = "SELECT price FROM PC" + " Where price > 2000";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){

            System.out.println("price: " + rs.getInt("price"));
            //System.out.println("type: "+rs.getString("TYPE"));
        }
        rs.close();
        stmt.close();
    }


    public void execute_query_problem3() throws SQLException{
        String sqlStr = "SELECT model, speed, hd FROM PC" + " Where (cd = '8x' or cd = '6x')" + " and price < 2000";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){

            System.out.print("model : " + rs.getInt("model") + " | ");
            System.out.print("speed : " + rs.getInt("speed") + " | ");
            System.out.println("hd : " + rs.getFloat("hd"));
            //System.out.println("type: "+rs.getString("TYPE"));
        }
        rs.close();
        stmt.close();
    }

    public static void main(String[] args){

        Test1 t1 = new Test1();
        System.out.println("문제1. pc의 평균 속력을 구하라.");
        try{
            t1.execute_query_problem1();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
        System.out.println("----------------------------------");
        System.out.println("문제2. pc에서 price가 2000이상인 가격을 구하라.");
        try{
            t1.execute_query_problem2();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
        System.out.println("----------------------------------");
        System.out.println("문제3. 6배속이나 8배속의 CD를 가지고 있으며 가격이 $2000 미만인 PC들의 모델 번호, 속도, 하드디스크 용량을 구하라..");
        try{
            t1.execute_query_problem3();
            dbTest.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }

}
