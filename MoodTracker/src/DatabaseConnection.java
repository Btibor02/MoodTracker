import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    String SUrl, SUser, SPass;

    public Statement connection() throws ClassNotFoundException, SQLException {
        SUrl = "jdbc:mysql://localhost:3306/moodtracker";
        SUser = "root";
        SPass = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
        Statement st = con.createStatement();

        return st;


    }
    public Connection preparedConnection() throws Exception {
        SUrl = "jdbc:mysql://localhost:3306/moodtracker";
        SUser = "root";
        SPass = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(SUrl, SUser, SPass);
    }
}

