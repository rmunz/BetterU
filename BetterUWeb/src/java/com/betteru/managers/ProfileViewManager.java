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
        // TODO return whole list
        return progressFacade.findAllProgressEntriesByUid(getLoggedInUser().getId());
    }
    
    private void buildWeightModel() {
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
        yAxis.setMin(0);
        yAxis.setMax(210);
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2016-04-25");
        axis.setTickFormat("%b %#d, %y");
        axis.setTickCount(2);
         
        weightModel.getAxes().put(AxisType.X, axis);
    }
    
    public LineChartModel getWeightModel() {
        buildWeightModel();
        return weightModel;
    }

}