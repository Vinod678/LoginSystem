import java.sql.*;
import java.util.HashMap;

public class IDandPasswords {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="vinod";

    IDandPasswords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserValid(String username, String password){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            return  resultSet.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }

}
