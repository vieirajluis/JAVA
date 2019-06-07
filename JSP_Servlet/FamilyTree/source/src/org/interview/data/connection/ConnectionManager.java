package org.interview.data.connection;

import java.sql.Connection;
import java.sql.SQLException;

//Database Connection Utility class
public class ConnectionManager {
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {


        return SqlConnection.getSqlConnection();

    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
