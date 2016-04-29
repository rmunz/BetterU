/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betteru.listeners;

/**
 *
 * @author juliabinger
 */
import com.betteru.sessionbeanpackage.ProgressFacade;
import com.betteru.sessionbeanpackage.UserFacade;
import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.swing.Timer;

public class BetterUContextListener implements ServletContextListener{
    @EJB
    private UserFacade uf;
    
    @EJB
    private ProgressFacade pf;
    
   @Override
   public void contextInitialized(ServletContextEvent contextEvent) {
        /* Do Startup stuff. */
        
                
        ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        //...Perform a task...
                       // DateTime dt = new DateTime();                        
                        //dt = dt.withTimeAtStartOfDay();
                        //dt = dt.minusHours(20);
                        
                        int midnight = ((int)(System.currentTimeMillis()/100000)) * 100;
                        for(User entity : uf.findAllUsers()) {
                            Progress progress = new Progress(entity.getId(), midnight);
                            //Progress progress = new Progress(entity.getId(), (int)(dt.getMillis()/1000));
                            progress.setCaloriesIn(0);
                            progress.setCaloriesOut(0);
                            progress.setMiles(0);
                            progress.setWeight(entity.getWeight());
                            progress.setSteps(0);
                           // ProgressFacadeREST pf = new ProgressFacadeREST();
                            pf.create(progress);
                        }
                    }
                };
            
            //set up refresh timer
            Timer timer = new Timer(86400000, taskPerformer);//set delay to 24 hours
            
            //test timer set for every 10 minutes
            //Timer timer = new Timer(6000, taskPerformer);

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            int msToMidnight = (int)(c.getTimeInMillis()-System.currentTimeMillis());
            timer.setInitialDelay(msToMidnight);
            timer.start(); 
   }

   @Override
   public void contextDestroyed(ServletContextEvent contextEvent) {
        /* Do Shutdown stuff. */
   }

}
