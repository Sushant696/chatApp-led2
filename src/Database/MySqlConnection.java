package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection implements Database {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "newpassword";
    private static final String DATABASE = "demo";  
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";

    @Override
    public Connection openConnection() {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL + DATABASE, USERNAME, PASSWORD);
            if (connection == null) {
                System.out.println("Database connection failed");
            } else {
                System.out.println("Database connection succeeded");
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
