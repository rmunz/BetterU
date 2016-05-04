/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betteru.sessionbeanpackage;

/**
 *
 * @author juliabinger
 */
import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.swing.Timer;

@Singleton
@Startup
public class LifecycleBean {
    @EJB
    private ProgressFacade pf;  
    
    @EJB
    private UserFacade uf;
  @PostConstruct
  public void init() {
        ActionListener taskPerformer = (ActionEvent evt) -> {
            //...Perform a task...
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
            for(User entity : uf.findAllUsers()) {
                Progress progress = new Progress(entity.getId(), (int)((cal.getTimeInMillis())/1000));
                progress.setCaloriesIn(0);
                progress.setCaloriesOut(0);
                progress.setMiles(0);
                progress.setWeight((double)entity.getWeight());
                progress.setSteps(0);
                pf.create(progress);
            }
        };
            
        //set up refresh timer
        Timer timer = new Timer(86400000, taskPerformer);//set delay to 24 hours

        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        int msToMidnight = (int)(c.getTimeInMillis() + 86400000 - System.currentTimeMillis());
        timer.setInitialDelay(msToMidnight);
        
        timer.start(); 
       
    }

  @PreDestroy
  public void destroy() {
    /* Shutdown stuff here */
  }

}