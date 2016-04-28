/*
 * Created by Ojas Mhetar on 2016.02.27  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.User;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
    
    private String dateMin = "2016-04-01";
    private String dateMax = "2016-04-30";
    
    
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
        List<Progress> progressList;// = progressFacade.findAllProgressEntriesByUid(getLoggedInUser().getId());
        
        //Get current week
        progressList = progressFacade.findWeekByUid(getLoggedInUser().getId(), 1461988800);
        
        //Get current month
        
        return progressList;
    }
    
    private int getEndOfWeek(long currentTime) {
        Calendar c = Calendar.getInstance();
        
        Date now = new Date(currentTime);
        c.setTime(now);
        
        // set to end of week
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return (int)c.getTimeInMillis();
    }
    
    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }
    
    private LineChartModel buildWeightModel() {
        int padding = 10;
        weightModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries weightSeries = new LineChartSeries();
        weightSeries.setLabel("Weight");
        
        if (progressList != null) {
            for (Progress p : progressList) {
                weightSeries.set(p.getDayString(), p.getWeight());

                if (p.getWeight() < minWeight) {
                    minWeight = p.getWeight();
                }
                if (p.getWeight() > maxWeight) {
                    maxWeight = p.getWeight();
                }
            }
        } else {
            return null;
        }
        
        weightModel.addSeries(weightSeries);
        weightModel.setTitle("Weight");
        
        Axis yAxis = weightModel.getAxis(AxisType.Y);
        yAxis.setMin(minWeight - padding);
        yAxis.setMax(maxWeight + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(progressList.size());
         
        weightModel.getAxes().put(AxisType.X, axis);
        
        return weightModel;
    }
    
    private LineChartModel buildStepModel() {
        int padding = 1000;
        stepModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries stepSeries = new LineChartSeries();
        stepSeries.setLabel("Steps");
        
        if (progressList != null) {
            for (Progress p : progressList) {
                stepSeries.set(p.getDayString(), p.getSteps());

                if (p.getSteps()< minSteps) {
                    minSteps = p.getSteps();
                }
                if (p.getSteps() > maxSteps) {
                    maxSteps = p.getSteps();
                }
            }
        } else {
            return null;
        }
        
        stepModel.addSeries(stepSeries);
        stepModel.setTitle("Steps");
        
        Axis yAxis = stepModel.getAxis(AxisType.Y);
        yAxis.setMin(minSteps - padding);
        yAxis.setMax(maxSteps + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(progressList.size());
         
        stepModel.getAxes().put(AxisType.X, axis);
        
        return stepModel;
    }
    
    private LineChartModel buildMileModel() {
        int padding = 1;
        mileModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries mileSeries = new LineChartSeries();
        mileSeries.setLabel("Miles");
        
        if (progressList != null) {
            for (Progress p : progressList) {
                mileSeries.set(p.getDayString(), p.getMiles());

                if (p.getMiles() < minMiles) {
                    minMiles = p.getMiles();
                }
                if (p.getMiles() > maxMiles) {
                    maxMiles = p.getMiles();
                }
            }
        } else {
            return null;
        }
        
        mileModel.addSeries(mileSeries);
        mileModel.setTitle("Miles");
        
        Axis yAxis = mileModel.getAxis(AxisType.Y);
        yAxis.setMin(minMiles - padding);
        yAxis.setMax(maxMiles + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(progressList.size());
         
        mileModel.getAxes().put(AxisType.X, axis);
        
        return mileModel;
    }
    
    private LineChartModel buildCalorieModel() {
        int padding = 500;
        calorieModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries calorieInSeries = new LineChartSeries();
        LineChartSeries calorieOutSeries = new LineChartSeries();
        calorieInSeries.setLabel("Calories In");
        calorieOutSeries.setLabel("Calories Out");
        
        if (progressList != null) {
            for (Progress p : progressList) {
                calorieInSeries.set(p.getDayString(), p.getCaloriesIn());
                calorieOutSeries.set(p.getDayString(), p.getCaloriesOut());

                if (p.getCaloriesIn() < minCalories || p.getCaloriesOut() < minCalories) {
                    minCalories = Math.min(p.getCaloriesIn(), p.getCaloriesOut());
                }
                if (p.getCaloriesIn() > maxCalories || p.getCaloriesOut() > maxCalories) {
                    maxCalories = Math.max(p.getCaloriesIn(), p.getCaloriesOut());
                }
            }
        } else {
            return null;
        }
        
        calorieModel.addSeries(calorieInSeries);
        calorieModel.addSeries(calorieOutSeries);
        calorieModel.setTitle("Calories");
        
        calorieModel.setLegendPosition("ne");
        
        Axis yAxis = calorieModel.getAxis(AxisType.Y);
        yAxis.setMin(minCalories - padding);
        yAxis.setMax(maxCalories + padding);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(progressList.size());
                 
        calorieModel.getAxes().put(AxisType.X, axis);
        
        return calorieModel;
    }
    
    public LineChartModel getWeightModel() {
        LineChartModel m = buildWeightModel();

        return m;
    }
    
    public LineChartModel getStepModel() {
        LineChartModel m = buildStepModel();
        
        return m;
    }
    
    public LineChartModel getMileModel() {
        LineChartModel m = buildMileModel();

        return m;
    }
    
    public LineChartModel getCalorieModel() {
        LineChartModel m = buildCalorieModel();

        return m;
    }
}