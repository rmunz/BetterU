/*
 * Created by Ojas Mhetar on 2016.02.27  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named(value = "profileViewManager")
@SessionScoped
/**
 *
 * @author Ben
 */
public class ProfileViewManager implements Serializable {

    // Instance Variable (Property)
    private User user;
    private LineChartModel weightModel;
    private LineChartModel calorieModel;
    private LineChartModel stepModel;
    private LineChartModel mileModel;
    private int minWeight = Integer.MAX_VALUE;
    private int maxWeight = 0;
    private int minSteps = Integer.MAX_VALUE;
    private int maxSteps = 0;
    private int minMiles = Integer.MAX_VALUE;
    private int maxMiles = 0;
    private int minCalories = Integer.MAX_VALUE;
    private int maxCalories = 0;
    
    
    /**
     * The instance variable 'userFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @Stateless session bean UserFacade.
     */  
    @EJB
    private com.betteru.sessionbeanpackage.UserFacade userFacade;
    
    /**
     * The instance variable 'userFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @Stateless session bean UserFacade.
     */  
    @EJB
    private com.betteru.sessionbeanpackage.ProgressFacade progressFacade;

    public ProfileViewManager() {
        
    }

    public String viewProfile() {
        return "MyAccount";
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    public User getLoggedInUser() {
        return userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id"));
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Progress> getLoggedInUsersProgress() {
        List<Progress> progressList = progressFacade.findAllProgressEntriesByUid(getLoggedInUser().getId());
        for (Progress p : progressList) {
            if (p.getWeight() < minWeight) {
                minWeight = p.getWeight();
            }
            if (p.getWeight() > maxWeight) {
                maxWeight = p.getWeight();
            }
            
            if (p.getSteps()< minSteps) {
                minSteps = p.getSteps();
            }
            if (p.getSteps() > maxSteps) {
                maxSteps = p.getSteps();
            }
            
            if (p.getMiles()< minMiles) {
                minMiles = p.getMiles();
            }
            if (p.getMiles() > maxMiles) {
                maxMiles = p.getMiles();
            }
            
            if (p.getCaloriesIn() < minCalories || p.getCaloriesOut() < minCalories) {
                minCalories = Math.min(p.getCaloriesIn(), p.getCaloriesOut());
            }
            if (p.getCaloriesIn() > maxCalories || p.getCaloriesOut() > maxCalories) {
                maxCalories = Math.max(p.getCaloriesIn(), p.getCaloriesOut());
            }
        }
        return progressList;
    }
    
    private void buildWeightModel() {
        int padding = 10;
        weightModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries weightSeries = new LineChartSeries();
        weightSeries.setLabel("Weight");
        for (Progress p : progressList) {
            weightSeries.set(p.getDayString(), p.getWeight());
        }
        
        weightModel.addSeries(weightSeries);
        
        weightModel.setTitle("Weight");
        //weightModel.setLegendPosition("e");
        
        Axis yAxis = weightModel.getAxis(AxisType.Y);
        yAxis.setMin(minWeight - padding);
        yAxis.setMax(maxWeight + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMax("2016-04-17");
        axis.setMax("2016-04-23");
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(7);
         
        weightModel.getAxes().put(AxisType.X, axis);
    }
    
    private void buildStepModel() {
        int padding = 1000;
        stepModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries stepSeries = new LineChartSeries();
        stepSeries.setLabel("Steps");
        for (Progress p : progressList) {
            stepSeries.set(p.getDayString(), p.getSteps());
        }
        
        stepModel.addSeries(stepSeries);
        
        stepModel.setTitle("Steps");
        //weightModel.setLegendPosition("e");
        
        Axis yAxis = stepModel.getAxis(AxisType.Y);
        yAxis.setMin(minSteps - padding);
        yAxis.setMax(maxSteps + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMax("2016-04-17");
        axis.setMax("2016-04-23");
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(7);
         
        stepModel.getAxes().put(AxisType.X, axis);
    }
    
    private void buildMileModel() {
        int padding = 1;
        mileModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries mileSeries = new LineChartSeries();
        mileSeries.setLabel("Miles");
        for (Progress p : progressList) {
            mileSeries.set(p.getDayString(), p.getMiles());
        }
        
        mileModel.addSeries(mileSeries);
        
        mileModel.setTitle("Miles");
        //weightModel.setLegendPosition("e");
        
        Axis yAxis = mileModel.getAxis(AxisType.Y);
        yAxis.setMin(minMiles - padding);
        yAxis.setMax(maxMiles + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMax("2016-04-17");
        axis.setMax("2016-04-23");
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(7);
         
        mileModel.getAxes().put(AxisType.X, axis);
    }
    
    private void buildCalorieModel() {
        int padding = 500;
        calorieModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries calInSeries = new LineChartSeries();
        LineChartSeries calOutSeries = new LineChartSeries();
        calInSeries.setLabel("Calories In");
        calOutSeries.setLabel("Calories Out");
        for (Progress p : progressList) {
            calInSeries.set(p.getDayString(), p.getCaloriesIn());
            calOutSeries.set(p.getDayString(), p.getCaloriesOut());
        }
        
        calorieModel.addSeries(calInSeries);
        calorieModel.addSeries(calOutSeries);
        
        calorieModel.setTitle("Calories");
        calorieModel.setLegendPosition("ne");
        
        
        Axis yAxis = calorieModel.getAxis(AxisType.Y);
        yAxis.setMin(minCalories - padding);
        yAxis.setMax(maxCalories + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMax("2016-04-17");
        axis.setMax("2016-04-23");
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(7);
         
        calorieModel.getAxes().put(AxisType.X, axis);
    }
    
    public LineChartModel getWeightModel() {
        buildWeightModel();
        return weightModel;
    }
    
    public LineChartModel getStepModel() {
        buildStepModel();
        return stepModel;
    }
    
    public LineChartModel getMileModel() {
        buildMileModel();
        return mileModel;
    }
    
    public LineChartModel getCalorieModel() {
        buildCalorieModel();
        return calorieModel;
    }
}