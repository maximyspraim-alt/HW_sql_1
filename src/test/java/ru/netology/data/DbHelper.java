package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/app?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "pass";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static String getVerificationCode(String login) {
        String sql = "SELECT code FROM auth_codes " +
                "JOIN users ON auth_codes.user_id = users.id " +
                "WHERE users.login = ? " +
                "ORDER BY auth_codes.created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            QueryRunner runner = new QueryRunner();
            return runner.query(conn, sql, new ScalarHandler<>(), login);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить код подтверждения из БД", e);
        }
    }
}