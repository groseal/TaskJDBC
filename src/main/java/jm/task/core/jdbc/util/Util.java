package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jm_base";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "159159";

    public static Connection getConnectBD() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Ошибка соединения с БД");
            e.printStackTrace();
        }
        return connection;
    }
}
