/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristi.server;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author crist
 */
public class DBConnection {

    private static Connection connection_instance = null;
    
    private DBConnection() {
        
    }
    
    public static Connection getConnection(){
        if(connection_instance != null)
            return connection_instance;
        try {
            Class.forName("org.sqlite.JDBC");
            connection_instance = DriverManager.getConnection("jdbc:sqlite:E:/Sqlite3/lab11_PA.db");
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection_instance;
    }
}
