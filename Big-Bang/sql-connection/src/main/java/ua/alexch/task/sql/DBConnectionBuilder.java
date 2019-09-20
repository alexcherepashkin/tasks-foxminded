package ua.alexch.task.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionBuilder {

    public DBConnectionBuilder() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find the driver!");
            ex.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
//        String url = "jdbc:postgresql:foxx_db";
        String url = "jdbc:postgresql:postgres";
        String user = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
    }
}
