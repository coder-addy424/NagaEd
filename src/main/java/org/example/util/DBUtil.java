package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

        private static Connection conn=null;

        private DBUtil() {
        }
        public static Connection provideConnection(){

            try {
                if(conn == null || conn.isClosed()){
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    String cs="jdbc:mysql://localhost:3306/project";

                    try {
                        conn= DriverManager.getConnection(cs,"root","Aditya@123");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return conn;
        }


}

