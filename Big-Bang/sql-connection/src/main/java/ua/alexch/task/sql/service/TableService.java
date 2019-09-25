package ua.alexch.task.sql.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.alexch.task.sql.DBConnectionFactory;

public class TableService {
    private final DBConnectionFactory connectionFactory;

    public TableService(DBConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setupTables(File fileSql) {
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement statement = conn.prepareStatement(readSqlFile(fileSql))) {

            statement.execute();
            System.out.println("Setup tables completed!");

        } catch (SQLException e) {
            System.out.println("Setup error!");
            e.printStackTrace();
        }
    }

    private String readSqlFile(File fileSql) {
        StringBuilder sql = new StringBuilder();

        try {
            Files.readAllLines(fileSql.toPath()).forEach(sql::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sql.toString();
    }
}
