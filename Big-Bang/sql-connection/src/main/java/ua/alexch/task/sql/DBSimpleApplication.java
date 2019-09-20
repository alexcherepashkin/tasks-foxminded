package ua.alexch.task.sql;

import java.io.File;

public class DBSimpleApplication {

    public static void main(String[] args) {
        File startupSql = new File("startup.sql");

        TableCreater creater = new TableCreater();
        creater.createTable(startupSql);

        DBTestDataSetup setupDB = new DBTestDataSetup();
        setupDB.setupTestData();

        DBManager manager = new DBManager();
    }
}
