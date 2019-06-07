package org.interview.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Database Connection Utility class
public class SqlConnection {

    public static Connection getSqlConnection()
            throws ClassNotFoundException, SQLException {
        // Note: Change the connection parameters accordingly.
        String hostName = "jdbc:postgresql://localhost:5432/";
        String dbName = "postgres";
        String userName = "postgres";//Encrypt
        String password = "postgres";//Encrypt
        return getSqlConnection(hostName, dbName, userName, password);
    }

    public static Connection getSqlConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        // URL Connection for Postgresql:
        // jdbc:postgresql://localhost:5432/postgres
        String connectionURL = hostName + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
