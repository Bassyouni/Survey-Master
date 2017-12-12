/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdc
 */
public class User {

    private String id;
    private String isAdministrator;
    private String name;
    private String password;
    private String email;
    private String isSuspended;

    
    public User(String isAdministrator, String name, String password, String email, String isSuspended) {
        this.isAdministrator = isAdministrator;
        this.name = name;
        this.password = password;
        this.email = email;
        this.isSuspended = isSuspended;
    }

    private User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(String isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(String isSuspended) {
        this.isSuspended = isSuspended;
    }
    
    
    
    public HashMap<String, String> getAttributes(){
        //`is_administrator`, `name`, `password`, `email`, `is_suspended`
        HashMap<String, String> attributes = new HashMap<>();
        if(isAdministrator != null)
            attributes.put("is_administrator", isAdministrator);
        if(name != null)
            attributes.put("name", name);
        if(password != null)
            attributes.put("password", password);
        if(email != null)
            attributes.put("email", email);
        if(isSuspended != null)
            attributes.put("is_suspended", isSuspended);
        
        return attributes;
    }
    
    public static User selectUser(String id){
        User targetUser = null;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        HashMap<String, String> input = new HashMap<>();
        input.put("id", id);
        ResultSet rs = databaseConnection.select("users", input);
        try {
            while(rs.next()){
                targetUser = new User(
                        rs.getString("is_administrator"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("is_suspended")
                );
                
                targetUser.setId(rs.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return targetUser;
    }
}
