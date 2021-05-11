/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import cristi.server.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crist
 */
public class Person {
    static private List<String> personID = new ArrayList<>();
    private Person(){
        
    }
    public static List<String> getPersons(){
        Connection myConn = DBConnection.getConnection();
        String personQuery = "";
        try {
            myConn.setAutoCommit(false);
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name from person;"); 
            while(rs.next()){
                personQuery = rs.getString("name");
                personID.add(personQuery);
            }
            stmt.close();
            rs.close();
        }
        catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
        return personID;
    }
    public static boolean existID(int ID){
        Connection myConn = DBConnection.getConnection();
        try {
            myConn.setAutoCommit(false);
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM person WHERE id='"+ ID +"';");
            if (rs.next() == false) return false;
            stmt.close();
            rs.close();
        }
        catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
        return true;
    }
    
    public static void createPerson(String personName){
        Connection myConn = DBConnection.getConnection();
        try {
            myConn.setAutoCommit(false);
            Statement stmt = myConn.createStatement();
            String sql = "INSERT INTO person (name) VALUES ('" + personName + "');"; 
            stmt.executeUpdate(sql);
            stmt.close();
            myConn.commit();
        }
        catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
    }
    public static void updatePerson(int id, String name){
        Connection myConn = DBConnection.getConnection();
        try {
            myConn.setAutoCommit(false);
            Statement stmt = myConn.createStatement();
            String sql = "UPDATE person SET name = '" + name + "' WHERE id = '"+ id +"';"; 
            stmt.executeUpdate(sql);
            stmt.close();
            myConn.commit();
        }
        catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
    }
    public static void deletePerson(int id){
        Connection myConn = DBConnection.getConnection();
        try {
            myConn.setAutoCommit(false);
            Statement stmt = myConn.createStatement();
            String sql = "DELETE FROM person WHERE id='" + id + "';"; 
            stmt.executeUpdate(sql);
            stmt.close();
            myConn.commit();
        }
        catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
    }
}
