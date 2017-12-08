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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bassyouni
 */
public class Survey {
    
    private int id;
    private int ownerId;
    private String name;
    public ArrayList<Question> questions;
    
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    private static final String surveyTableName = "surveys";

    public Survey(){
        
    }
    public Survey(int id, int owner, String Name) {
        this.id = id;
        this.ownerId = owner;
        this.name = Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    
    public static ArrayList<Survey> getAllSurveys(){
        ArrayList<Survey> surveys = new ArrayList<>();
        ResultSet rs = databaseConnection.select(surveyTableName);
        Survey dummySurvey;
        try {
            while(rs.next()){
                dummySurvey = new Survey();
                dummySurvey.setId(rs.getInt("id"));
                dummySurvey.setOwnerId(rs.getInt("owner"));
                dummySurvey.setName(rs.getString("name"));
                surveys.add(dummySurvey);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Survey.class.getName()).log(Level.SEVERE, null, ex);
            return surveys;
        }
        
        return surveys;
    }

    @Override
    public String toString() {
        return String.format("SurveyName: %s, OwnerId: %d", this.name, this.ownerId);
                
    }
    
    
    
}
