/*
 * Created by Ojas Mhetar on 2016.04.28  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sessionbeanpackage.ProgressFacade;
import com.betteru.sessionbeanpackage.UserFacade;
import com.betteru.sourcepackage.Progress;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
    
//--------------------------------------------------------------
    //For Usda Search url is built in descending order q+sort+max...
    private String foodToSearchForUSDA; //q term for usda food search
    private String ndbno; //the number for the food to search for
    private String sort = "&sort=n";
    private String max = "&max=5";
    private String offset = "&offset=0";
    private static final String USDA_KEY = "&api_key=lmng23Wvez10CHDEqiWE90dL1qWhJrkXlqIIXRmN";
    private static final String USDA_KEY_DEMO = "&api_key=DEMO_KEY";
    private static final String USDA_URL = "http://api.nal.usda.gov/ndb/search/?format=json&q=";
   //----------------------------------------------------------------
   //WGER Stuff below 
    private static final String WGER_Key = "fae3c283d251f4797c4338e8782236d6de49512b";
   //-----------------------------------------------------------------   
    
//Created by ojas/corey ask about later how they do stuff
    private int caloriesMin = 50; 
    private int caloriesMax = 1000;

    private int calorieIntake; 
    private String[] selectedAllergy;
    
    private String statusMessage; 
    

    private static final String YUMMLY_ID = "f6004e71";
    private static final String YUMMLY_KEY = "57e811ae63c25bd48802742327682e7d";
    private static final String YUMMLY_URL = "http://api.yummly.com/v1/api/recipes?_app_id=" + YUMMLY_ID + "&_app_key=" + YUMMLY_KEY;
    
    List<RecipeEntry> yummlyRecommendations; 

    List<FoodEntry> usdaRecommendations;
    

    @EJB
    private ProgressFacade progressFacade;
    
    public RecommendationManager(){  
        caloriesMin = 50; 
        caloriesMax = 1000; 
        statusMessage = "Status message for testing";   
    }
    
  
   
    /**
     * To get the list of USDA foods 
     * @return
     * @throws IOException 
     */
    public List<FoodEntry> getUSDAEntries() throws IOException{
        
        System.out.println("Yo");//self test
        
        List<FoodEntry> usdaResults = new ArrayList();
        
        URL usdaURL = new URL(USDA_URL + getFoodToSearchForUSDA()+sort+max+offset+ USDA_KEY);
        
        try (InputStream is = usdaURL.openStream(); JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                
                JsonObject newObj = obj.getJsonObject("list");
                
                JsonArray results = newObj.getJsonArray("item");
                
                for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                    FoodEntry tmpName = new FoodEntry(result.getInt("offset"), result.getString("group"),result.getString("name"),result.getString("ndbno"));    
                    usdaResults.add(tmpName); 
                    
               }
            }
            this.usdaRecommendations = usdaResults;
            
        return usdaResults;    
    }
    
    /**
     * To get Yummly Recipe recommendations
     * @return list of recipe's
     * @throws IOException ?
     */
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
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        Integer epochMidnight =  (int)(c.getTimeInMillis()/1000);
 
        //connect to find progress entry
        Progress entry = progressFacade.getProgressEntry(user, epochMidnight);
        System.out.println("\n\n\n\n\n\n\n\nHERE");
        System.out.println("\n\n USER" + user);
        System.out.println("\n\n Midnight" + epochMidnight);
        
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
            
            //Create new entry 
            try {   
                createProgressEntry(user_id, calorieIntake);

            } catch (EJBException e) {

                statusMessage = "Something went wrong while editing your profile!";
                return "";
            }
        }
        
        //return appropriate page 
        return "DailyProgress";
    }
    
    public void createProgressEntry(int user_id, int caloriesIn) {
        
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);

        Progress progress = new Progress(user_id, (int)((c.getTimeInMillis()/1000)));
        progress.setCaloriesIn(caloriesIn);
        progress.setCaloriesOut(0);
        progress.setMiles(777);
        progress.setWeight(0);
        progress.setSteps(0);
        
        progressFacade.create(progress);
        
    }
    
    public Progress getToday(){
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        Integer epochMidnight =  (int)(c.getTimeInMillis()/1000);
        
        //Get user Id  
        Integer user = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");

        if(user == null) {
            statusMessage = "Oops. You're not logged in!";
        }
        
        int user_id = user.intValue();
        
        Progress entry = progressFacade.getProgressEntry(user_id, epochMidnight);
        return entry;    
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

    /**
     * @return the usdaRecommendations
     */
    public List<FoodEntry> getUsdaRecommendations() {
        return usdaRecommendations;
    }

    /**
     * @param usdaRecommendations the usdaRecommendations to set
     */
    public void setUsdaRecommendations(List<FoodEntry> usdaRecommendations) {
        this.usdaRecommendations = usdaRecommendations;
    }

    /**
     * @return the foodToSearchForUSDA
     */
    public String getFoodToSearchForUSDA() {
        return foodToSearchForUSDA;
    }

    /**
     * @param foodToSearchForUSDA the foodToSearchForUSDA to set
     */
    public void setFoodToSearchForUSDA(String foodToSearchForUSDA) {
        this.foodToSearchForUSDA = foodToSearchForUSDA;
    }

    /**
     * @return the ndbno
     */
    public String getNdbno() {
        return ndbno;
    }

    /**
     * @param ndbno the ndbno to set
     */
    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }
    
}

