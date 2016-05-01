/*
 * Created by Ojas Mhetar on 2016.04.28  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sessionbeanpackage.ProgressFacade;
import com.betteru.sessionbeanpackage.UserFacade;
import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.ProgressPK;
import com.betteru.sourcepackage.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

@ManagedBean(name = "recommendationManager")
@SessionScoped
/**
 *
 * @author ojmhetar
 */
public class RecommendationManager implements Serializable{
    
    
    private int caloriesMin = 50; 
    private int caloriesMax = 1000;
    private int calorieIntake; 
    private String[] selectedAllergy;
    
    private String statusMessage; 
    
    private static final String YUMMLY_ID = "f6004e71";
    private static final String YUMMLY_KEY = "57e811ae63c25bd48802742327682e7d";
    private static final String YUMMLY_URL = "http://api.yummly.com/v1/api/recipes?_app_id=" + YUMMLY_ID + "&_app_key=" + YUMMLY_KEY;
    
    List<RecipeEntry> yummlyRecommendations; 
    
    @EJB
    private ProgressFacade progressFacade;
    
    public RecommendationManager(){  
        caloriesMin = 50; 
        caloriesMax = 1000; 
        statusMessage = "Status message for testing";
        
    }
    
    public List<RecipeEntry> getYummlyRecommendations() throws IOException{
         String min = "&nutrition.ENERC_KCAL.min=" + caloriesMin; 
         String max = "&nutrition.ENERC_KCAL.max=" + caloriesMax; 
         
         System.out.println("\n\n\n\n\n\n\n\nHERE\n");
         System.out.println(caloriesMin);
         System.out.println(caloriesMax);
         String strURL = YUMMLY_URL + min + max + "&maxResult=5";
          
         
         //Handle allergy
         if(selectedAllergy != null) {
            for (int i = 0; i < selectedAllergy.length; i++) {
                if(selectedAllergy[i] != null) {
                    strURL = strURL + "&allowedAllergy[]=" + selectedAllergy[i];
                }
            }
         }
         
         URL url = new URL(strURL);
         
         List<RecipeEntry> recipeResults = new ArrayList(); 
         
         
         try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                JsonArray results = obj.getJsonArray("matches");
                for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                    
                    RecipeEntry tmpName = new RecipeEntry(result.getString("id"), result.getString("recipeName"));    
                    recipeResults.add(tmpName); 
                    System.out.println(result.getString("recipeName") + "\n");
               }
            }
         
         for (int i = 0; i < recipeResults.size(); i++) {
            RecipeEntry entry = recipeResults.get(i);
            String recipeId = entry.getRecipeId();
            URL urlRecipe = new URL("http://api.yummly.com/v1/api/recipe/"+ recipeId +"?_app_id=" + YUMMLY_ID +"&_app_key=" + YUMMLY_KEY);
            try (InputStream is = urlRecipe.openStream(); JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                JsonArray results = obj.getJsonArray("nutritionEstimates");
                for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                    
                    String tmpName = result.getString("attribute");    
                    if (tmpName.equals("ENERC_KCAL")) {
                        int calorie = result.getJsonNumber("value").intValue();
                        entry.setCalories(calorie);  
                    }
                    else if(tmpName.equals("FAT")) {
                        int fat = result.getJsonNumber("value").intValue();
                        entry.setFat(fat);  
                    }
                    else if(tmpName.equals("PROCNT")) {
                        int protein = result.getJsonNumber("value").intValue();
                        entry.setProtein(protein);  
                    }       
                    else if(tmpName.equals("CHOCDF")) {
                        int carbs = result.getJsonNumber("value").intValue();
                        entry.setCarbs(carbs);  
                    }} 
            }
         }
         
         return recipeResults;
         
    }
    
    
    public String enterDailyIntake(){
          
        //Get user Id  
        Integer user = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");

        if(user == null) {
            statusMessage = "Oops. You're not logged in!";
            return "";
        }
        
        int user_id = user.intValue();
        
        //Get today @ Midnight in epoch 
        int LOGTIME_HARDCODE = 1461744000;
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        
        //Create primary comp key with userId + time 
        ProgressPK proPK = new ProgressPK();
        proPK.setDay(todayMidnight.getSecond());
        proPK.setId(user_id);
        
        //connect to find progress entry
        Progress entry = progressFacade.getProgressEntry(proPK);
        
        if(entry != null) {
            //update progress entry
            try {
                //Update the value instead of reseting
                entry.setCaloriesIn(entry.getCaloriesIn() + calorieIntake);

                progressFacade.edit(entry);
            } catch (EJBException e) {

                statusMessage = "Something went wrong while editing your profile!";
                return "";
            }
        }
        else {
            statusMessage = "Progress entry not found.";
            return "";
        }
        
        //return appropriate page 
        return "DailyProgress";
    }
    
    public int getCaloriesMin() {
        return caloriesMin; 
    }
    
    public void setCaloriesMin(int caloriesMin){
        this.caloriesMin = caloriesMin; 
    }
    
    public int getCaloriesMax() { 
        return caloriesMax;  
    }
    
    public void setCaloriesMax(int caloriesMax) { 
        this.caloriesMax = caloriesMax; 
    }
    
    public int getCalorieIntake(){
        return calorieIntake;
    }
    
    public void setCalorieIntake(int calorieIntake){
        this.calorieIntake = calorieIntake; 
    }
    
    public String[] getSelectedAllergy(){
        return selectedAllergy; 
    }
    
    public void setSelectedAllergy(String[] allergy) {
        this.selectedAllergy = allergy;
    }
    
    public String getStatusMessage(){
        return statusMessage; 
    }
    
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    
    
    
    public String refresh(){
        return "";
    }
    
}

