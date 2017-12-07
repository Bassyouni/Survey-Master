/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;

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
    
    public HashMap<String, String> getAttributes(){
        //`is_administrator`, `name`, `password`, `email`, `is_suspended`
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("is_administrator", isAdministrator);
        attributes.put("name", name);
        attributes.put("password", password);
        attributes.put("email", email);
        attributes.put("is_suspended", isSuspended);
        
        return attributes;
    }
}
