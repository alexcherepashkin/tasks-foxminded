package ua.alexch.task.sql;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionFactory {
    private String jdbcDriver;
    private String url;
    private String user;
    private String password;

    public DBConnectionFactory(File fileName) {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader(fileName));
            jdbcDriver = properties.getProperty("db.driver.class");
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find the driver!");
            ex.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
