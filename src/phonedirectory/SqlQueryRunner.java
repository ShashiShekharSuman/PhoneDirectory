package phonedirectory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SqlQueryRunner {

    private class Details {

        static String username = "";
        static String password = "";

    }

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/phone_dir",
                    Details.username, Details.password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("expmail.SqlQueryRunner.openConnection()");
        }
    }

    ResultSet executeQuery(String query) {
        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }

    void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
