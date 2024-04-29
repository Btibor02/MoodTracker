import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    String SUrl, SUser, SPass;

    public Statement connection() {
        SUrl = "jdbc:mysql://localhost:3306/moodtracker";
        SUser = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            return st;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
