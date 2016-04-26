/*
 * Created by Ben Robohn on 2016.04.25  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.betteru.webutils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;
 

/**
 *
 * @author Ben
 */
@Named(value = "chartBean")
@RequestScoped
public class ChartView implements Serializable {
 
    private LineChartModel weightModel;
     
    public ChartView() {
        buildWeightModel();
    }
    
    private void buildWeightModel() {
        weightModel = new LineChartModel();
        
        LineChartSeries weightSeries = new LineChartSeries();
        weightSeries.setLabel("Weight");
        weightSeries.set("2016-04-24", 205);
        weightSeries.set("2016-04-25", 200);
        
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
        return weightModel;
    }
 
}