import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Day9hw {
    private String username = "database";
    private String password = "database";
    private static Connection dbTest;

    Day9hw(){
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

    public void execute_query_problem() throws SQLException{
        String sqlStr = "SELECT maker, model, price FROM pc natural join product Where cd = '8x' and ram >= 24";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        System.out.println("MAKER\t\t\t\tMODEL\tPRICE");
        System.out.println("--------------------------------");
        while(rs.next()){
            System.out.println(rs.getString("maker")+rs.getInt("model")+"\t"+rs.getInt("PRICE"));
        }

        sqlStr = "SELECT sum(price) from LAPTOP natural join product where screen > 11 and maker = 'D' or maker = 'G'";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        System.out.println("SUM(PRICE)");
        System.out.println("----------");
        while(rs.next()){
            System.out.println(rs.getInt("sum(price)"));
        }

        sqlStr = "SELECT count(model) from ((select model from pc where hd > 2.4) union (select model from laptop where speed > 130))cn";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        System.out.println("COUNT(MODEL)");
        System.out.println("-------------");
        while(rs.next()){
            System.out.println(rs.getInt("count(model)"));
        }

        sqlStr = "select model, price from pc where cd ='8x' and speed > some (select speed from laptop)";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        System.out.println("  MODEL   PRICE");
        System.out.println("------- --------");
        while(rs.next()){
            System.out.println(rs.getInt("model")+"\t"+rs.getInt("price"));
        }

        sqlStr = "select maker, speed from laptop natural join product where hd >= 1";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        System.out.println("MAKER\t\t\tSPEED");
        System.out.println("--------------- ----------");
        while(rs.next()){
            System.out.println(rs.getString("maker")+"\t"+rs.getInt("speed"));
        }

        sqlStr = "(select model from pc where speed > all (select speed from laptop where model = 2005))" +
                " union (select model from laptop where speed > all (select speed from laptop where model = 2005))";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        System.out.println("\tMODEL");
        System.out.println("------------");
        while(rs.next()){
            System.out.println(rs.getInt("model"));
        }

        sqlStr = "select model, price from printer where color = 'true' and model = some (select model from product where maker = 'D')";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        System.out.println("MODEL\tPRICE");
        System.out.println("-------- ----------");
        while(rs.next()){
            System.out.println(rs.getInt("model")+"\t"+rs.getInt("price"));
        }
        rs.close();
        stmt.close();
    }
    public static void main(String[] args){

        Day9hw t1 = new Day9hw();
        try{
            t1.execute_query_problem();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }

}
