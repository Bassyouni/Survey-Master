/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdc
 */
public class DatabaseConnection {
    
    private static final String url = "jdbc:mysql://localhost:3306/survey_db";
    private static final String user = "root";
    private static final String password = "root";
    
    private static Connection con;
    
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean connect(){
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    //CRUD Methods
    
    public int insertInto(String tableName, HashMap<String, String> input){
        
        int rows = -1;
        //getting the keys and the attributes in the crossponding Lists
         ArrayList<String> attributes = new ArrayList<>();
         attributes.addAll(input.keySet());
         ArrayList<String> values = new ArrayList<>();
        
         for(int i = 0; i < attributes.size(); i++){
             values.add(  input.get(attributes.get(i))  );
         }
         
         //constructing the Sql statement
        String insertQuery = "INSERT INTO `survey_db`.`" + tableName + "`" + "(";
        for(int i = 0; i < attributes.size(); i++){
            if(i != attributes.size() - 1)
                insertQuery +=  "`" + attributes.get(i) + "`, " ;
            else
                insertQuery +=  "`" + attributes.get(i) + "`) " ;
        }
        
        insertQuery += "VALUES (";
        
        for(int i = 0; i < values.size(); i++){
            if(i != values.size() - 1)
                insertQuery +=  "'" + values.get(i) + "', " ;
            else
                insertQuery +=  "'" + values.get(i) + "'); " ;
        }
        
        //System.out.println(insertQuery);
        //Executing the query
        Statement Stmt;
        try {
            Stmt = con.createStatement();
            rows = Stmt.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            return rows;
        }
        return rows;
        
    }
    
    public ResultSet select(String tableName)
    {
        ResultSet rs = null;
        String selectQuery = "SELECT * FROM " + tableName;
        try {
            Statement statement = con.createStatement();
            rs = statement.executeQuery(selectQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet select(String tableName, HashMap<String, String> input){
        ResultSet rs = null;
        //getting the keys and the attributes in the crossponding Lists
         ArrayList<String> attributes = new ArrayList<>();
         attributes.addAll(input.keySet());
         ArrayList<String> values = new ArrayList<>();
         for(int i = 0; i < attributes.size(); i++){
             values.add(  input.get(attributes.get(i))  );
         }
         
        String insertQuery = "Select * From survey_db." + tableName + " where ";
        for(int i = 0; i < attributes.size(); i++){
            if(i != attributes.size() -1 )
                insertQuery += attributes.get(i) + " = '" + values.get(i) + "' and ";
            else
                insertQuery += attributes.get(i) + " = '" + values.get(i) + "';";
        }
        
        //System.out.println(insertQuery);
        
        Statement Stmt;
        try {
            Stmt = con.createStatement();
            rs = Stmt.executeQuery(insertQuery);
        } catch (SQLException ex) {
            return rs;
        }
        return rs;
        
    }
    
    public int delete(String tableName, int id){
        String sql = "DELETE FROM " + tableName + " Where id = " + id + ";";
        int rowsAffected = -1;
        try {
            Statement statement = con.createStatement();
            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return rowsAffected;
        }
        return rowsAffected;
        
    }
    
    public int update(String tableName, HashMap<String, String> input, int id){
        //getting the keys and the attributes in the crossponding Lists
         ArrayList<String> attributes = new ArrayList<>();
         attributes.addAll(input.keySet());
         ArrayList<String> values = new ArrayList<>();
         for(int i = 0; i < attributes.size(); i++){
             values.add(  input.get(attributes.get(i))  );
         }
         
         //creating this sql statement UPDATE users SET password = '1234' WHERE id ='12';
         String sql = "Update " + tableName + " set ";
         for(int i = 0; i < attributes.size(); i++){
            if(i != attributes.size() -1 )
                sql += attributes.get(i) + " = '" + values.get(i) + "', ";
            else
                sql += attributes.get(i) + " = '" + values.get(i) + "' Where id = " + id + ";" ;
        }
         System.out.println(sql);
        int rowsAffected = -1;
        try {
            Statement statement = con.createStatement();
            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return rowsAffected;
        }
        return rowsAffected;
         
    }
    
}
