package ua.alexch.task.sql;

import java.io.File;

public class DBSimpleApplication {

    public static void main(String[] args) {
        File startupSql = new File("startup.sql");
        File db_config = new File("db_config.properties");

        DBConnectionFactory connectionFactory = new DBConnectionFactory(db_config);

        TableCreater creater = new TableCreater(connectionFactory);
        creater.createTable(startupSql);

        DBTestDataSetup setupDB = new DBTestDataSetup(connectionFactory);
        setupDB.setupTestData();

        UniversityFacade universityFacade = new UniversityFacade(connectionFactory);
    }
}
