/*
 * Created by Ojas Mhetar on 2016.04.29  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sessionbeanpackage.ProgressFacade;
import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.ProgressPK;
import com.betteru.sourcepackage.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "exerciseManager")
@SessionScoped
/**
 *
 * @author ojmhetar
 */
public class ExerciseManager {
    
    private int caloriesOut; 
    private int intensity; 
    private int duration;
    private int weight; 
    
    private String statusMessage; 
    
    @EJB
    private ProgressFacade progressFacade;
    
    public ExerciseManager(){
        
    }
    
    public String enterDailyExercise(){
          
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
        //proPK.setDay(todayMidnight.getSecond());
        proPK.setDay(LOGTIME_HARDCODE); //for testing purposes
        proPK.setId(user_id);
        
        //connect to find progress entry
        Progress entry = progressFacade.getProgressEntry(proPK);
        
        if(entry != null) {
            //update progress entry
            try {
                caloriesOut = ((int)(intensity * 3.5 * 120)/200) * duration;
                entry.setCaloriesOut(caloriesOut);
                entry.setWeight(weight);

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
    
    public void setCaloriesOut(int caloriesOut){
        this.caloriesOut = caloriesOut;
    }
    
    public int getCaloriesOut(){
        return caloriesOut; 
    }
    
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage; 
    }
    
    public String getStatusMessage(){
        return statusMessage; 
    }
    
    public int getIntensity(){ 
        return intensity;
    }
    
    public void setIntensity(int intensity){
        this.intensity = intensity;
    }
    
    public int getDuration() { 
        return duration; 
    }
    
    public void setDuration(int duration) {
        this.duration = duration; 
    }
    
    public int getWeight() { 
        return weight; 
    }
    
    public void setWeight(int weight) {
        this.weight = weight; 
    }
}
