/*
 * Created by Ojas Mhetar on 2016.04.29  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sessionbeanpackage.ProgressFacade;
import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.ProgressPK;
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
    
    private String statusMessage; 
    
    @EJB
    private ProgressFacade progressFacade;
    
    public ExerciseManager(){
        
    }
    
    public String enterDailyExercise(){
          
        //Testing: userId = 29, time = 1461744000  
        int user_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");
        if(user_id == 0) {
            statusMessage = "Oops. You're not logged in!";
            return "";
        }
        
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
        return "MyAccount";
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
}
