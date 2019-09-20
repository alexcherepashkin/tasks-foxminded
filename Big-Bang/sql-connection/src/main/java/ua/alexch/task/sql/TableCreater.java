package ua.alexch.task.sql;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreater {
    private DBConnectionBuilder connectionBuilder = new DBConnectionBuilder();

    public void createTable(File fileSql) {
        try (Connection conn = connectionBuilder.getConnection();
                PreparedStatement statement = conn.prepareStatement(readSqlFile(fileSql))) {

            statement.execute();
            System.out.println("Table created!");

        } catch (SQLException e) {
            System.out.println("Error creating table!");
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
