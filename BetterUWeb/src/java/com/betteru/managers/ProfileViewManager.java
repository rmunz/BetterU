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
    private double minWeight = Integer.MAX_VALUE;
    private double maxWeight = 0;
    private int minSteps = Integer.MAX_VALUE;
    private int maxSteps = 0;
    private int minMiles = Integer.MAX_VALUE;
    private int maxMiles = 0;
    private int minCalories = Integer.MAX_VALUE;
    private int maxCalories = 0;
    
    private int numTicks = 7;
    private int numDaysInMonth = 30;
    private boolean weekly = true;
    private String interval = "Weekly";
    
    private long referenceTime;
    private final long secondsPerDay = 60*60*24;

    public String getInterval() {
        return interval;
    }

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
        referenceTime = System.currentTimeMillis();
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
    
    public void refreshCharts() {
        interval = weekly ? "Weekly" : "Monthly";
        
        resetMinMax();
        
        buildWeightModel();
        buildCalorieModel();
        buildStepModel();
        buildMileModel();
    }
    
    private void resetMinMax() {
        minWeight = Integer.MAX_VALUE;
        maxWeight = 0;
        minSteps = Integer.MAX_VALUE;
        maxSteps = 0;
        minMiles = Integer.MAX_VALUE;
        maxMiles = 0;
        minCalories = Integer.MAX_VALUE;
        maxCalories = 0;
    
    }
    
    public void changeInterval() {
        weekly = !weekly;
        
        refreshCharts();
    }
    
    public void today() {
        referenceTime = System.currentTimeMillis();
        
        refreshCharts();
    }
    
    public void prev(){
        //decrement time
        if (weekly) {
            referenceTime -= (7*secondsPerDay*1000);
        } else {
            referenceTime -= (30*secondsPerDay*1000);
        }
        
        refreshCharts();
    }
    
    public void next(){
        //increment time
        if (weekly) {
            referenceTime += (7*secondsPerDay*1000);
        } else {
            referenceTime += (30*secondsPerDay*1000);
        }
        
        refreshCharts();
    }
    
    public List<Progress> getLoggedInUsersProgress() {
        List<Progress> progressList;
        
        if (weekly) {
            progressList = progressFacade.findWeekByUid(getLoggedInUser().getId(), getEndOfWeek(referenceTime));
        } else {
            progressList = progressFacade.findMonthByUid(getLoggedInUser().getId(), getEndOfMonth(referenceTime), numDaysInMonth);
        }
        
        if (progressList != null) {
            numTicks = progressList.size();
        }
        
        return progressList;
    }
    
    private long getEndOfWeek(long time) {
        Calendar c = Calendar.getInstance();
        
        Date now = new Date(time);
        c.setTime(now);
        c.getFirstDayOfWeek();
        
        // set to end of week
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTimeInMillis()/1000;
    }
    
    private long getEndOfMonth(long time) {
        Calendar c = Calendar.getInstance();
        
        Date now = new Date(time);
        c.setTime(now);
        
        // set to end of month
        numDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, numDaysInMonth);
        c.set(Calendar.AM_PM, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTimeInMillis()/1000;
    }
    
    private LineChartModel buildWeightModel() {
        int padding = 10;
        weightModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries weightSeries = new LineChartSeries();
        weightSeries.setLabel("Weight");
        
        if (progressList == null) {
            return null;
        }
        
        for (Progress p : progressList) {
            weightSeries.set(p.getDayString(), p.getWeight());

            if (p.getWeight() < minWeight) {
                minWeight = p.getWeight();
            }
            if (p.getWeight() > maxWeight) {
                maxWeight = p.getWeight();
            }
        }
        
        weightModel.addSeries(weightSeries);
        weightModel.setTitle("Weight");
        
        Axis yAxis = weightModel.getAxis(AxisType.Y);
        yAxis.setMin((minWeight - padding) < 0 ? 0 : (minWeight - padding));
        yAxis.setMax(maxWeight + padding);
        yAxis.setLabel("Pounds");
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(numTicks);
         
        weightModel.getAxes().put(AxisType.X, axis);
        
        return weightModel;
    }
    
    private LineChartModel buildStepModel() {
        int padding = 1000;
        stepModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries stepSeries = new LineChartSeries();
        stepSeries.setLabel("Steps");
        
        if (progressList == null) {
            return null;
        }
        
        for (Progress p : progressList) {
            stepSeries.set(p.getDayString(), p.getSteps());

            if (p.getSteps()< minSteps) {
                minSteps = p.getSteps();
            }
            if (p.getSteps() > maxSteps) {
                maxSteps = p.getSteps();
            }
        }
        
        stepModel.addSeries(stepSeries);
        stepModel.setTitle("Steps");
        
        Axis yAxis = stepModel.getAxis(AxisType.Y);
        yAxis.setMin((minSteps - padding) < 0 ? 0 : (minSteps - padding));
        yAxis.setMax(maxSteps + padding);
        yAxis.setLabel("Steps");
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(numTicks);
         
        stepModel.getAxes().put(AxisType.X, axis);
        
        return stepModel;
    }
    
    private LineChartModel buildMileModel() {
        int padding = 1;
        mileModel = new LineChartModel();
        List<Progress> progressList = getLoggedInUsersProgress();
        
        LineChartSeries mileSeries = new LineChartSeries();
        mileSeries.setLabel("Miles");
        
        if (progressList == null) {
            return null;
        }
        
        for (Progress p : progressList) {
            mileSeries.set(p.getDayString(), p.getMiles());

            if (p.getMiles() < minMiles) {
                minMiles = p.getMiles();
            }
            if (p.getMiles() > maxMiles) {
                maxMiles = p.getMiles();
            }
        }
        
        mileModel.addSeries(mileSeries);
        mileModel.setTitle("Miles");
        
        Axis yAxis = mileModel.getAxis(AxisType.Y);
        yAxis.setMin((minMiles - padding) < 0 ? 0 : (minMiles - padding));
        yAxis.setMax(maxMiles + padding);
        yAxis.setLabel("Miles");
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(numTicks);
         
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
        
        if (progressList == null) {
            return null;
        }
        
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
        
        calorieModel.addSeries(calorieInSeries);
        calorieModel.addSeries(calorieOutSeries);
        calorieModel.setTitle("Calories");
        
        calorieModel.setLegendPosition("ne");
        
        Axis yAxis = calorieModel.getAxis(AxisType.Y);
        yAxis.setMin((minCalories - padding) < 0 ? 0 : (minCalories - padding));
        yAxis.setMax(maxCalories + padding);
        yAxis.setLabel("Calories");
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-45);
        axis.setMin(progressList.get(0).getDayString());
        axis.setMax(progressList.get(progressList.size()-1).getDayString());
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(numTicks);
                 
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
    
    public String getBreakfast() {
        return getLoggedInUser().getBreakfast();
    }
    
    public String getLunch() {
        return getLoggedInUser().getLunch();
    }
    
    public String getDinner() {
        return getLoggedInUser().getDinner();
    }
    
    public String getSnack() {
        return getLoggedInUser().getSnack();
    }
}