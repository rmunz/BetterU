/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.managers;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.betteru.sessionbeanpackage.*;
import com.betteru.sourcepackage.DailyChallenges;
import com.betteru.sourcepackage.User;
import com.betteru.sourcepackage.UserIndex;
import com.betteru.sourcepackage.WeeklyChallenges;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tim
 */
@Named(value = "challengesManager")
@SessionScoped
public class ChallengesManager implements Serializable {

    private User selected;
    private List<DailyChallenges> completedUserDailyChallenges;
    private List<WeeklyChallenges>  completedUserWeeklyChallenges;
    private boolean weeklyChallengeCompleted;

    /**
     * The instance variable 'UserIndexFacade' is annotated with the @EJB
     * annotation. This means that the GlassFish application server, at runtime,
     * will inject in this instance variable a reference to the @SessionScoped
     * session bean UserIndexFacade.
     */
    @EJB
    private UserIndexFacade userIndexFacade;

    /**
     * The instance variable 'DailyChallengesFacade' is annotated with the @EJB
     * annotation. This means that the GlassFish application server, at runtime,
     * will inject in this instance variable a reference to the @SessionScoped
     * session bean DailyChallengesFacade.
     */
    @EJB
    private DailyChallengesFacade dailyChallengesFacade;

    /**
     * The instance variable 'WeeklyChallengesFacade' is annotated with the @EJB
     * annotation. This means that the GlassFish application server, at runtime,
     * will inject in this instance variable a reference to the @SessionScoped
     * session bean WeeklyChallengesFacade.
     */
    @EJB
    private WeeklyChallengesFacade weeklyChallengesFacade;

    /**
     * The instance variable 'UserFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject
     * in this instance variable a reference to the @SessionScoped session bean
     * UserFacade.
     */
    @EJB
    private UserFacade userFacade;

    public ChallengesManager() {
        weeklyChallengeCompleted = false;
    }
    
    /**
     * Make this display previously completed challenges along with points awarded
     */
    public void setChallengesDisplay() {
        User selectedUser = this.getSelected();
        if(selected != null) {
            List<UserIndex> userIndices = userIndexFacade.
                    retrieveEntriesForUserId(selectedUser.getId());
            this.completedUserDailyChallenges = new ArrayList<DailyChallenges>();
            this.completedUserWeeklyChallenges = new ArrayList<WeeklyChallenges>();
            
            for(UserIndex u: userIndices) {
                String currChallengeType = u.getUserIndexPK().getChallengeType();
                int ind = u.getInd();
                if(!currChallengeType.equals("Weekly")) {
                    //List<DailyChallenges> temp = dailyChallengesFacade.getChallengesWithType(currChallengeType);
                    for(int i = 1; i <= ind; i++) {                       
                        completedUserDailyChallenges.add(dailyChallengesFacade.getChallengeAtIndWithType(i, currChallengeType));
                    }
                } else {
                    for(int i = 1; i <= ind; i++) {
                        completedUserWeeklyChallenges.add(weeklyChallengesFacade.getWeeklyChallengeAtInd(i));
                    }
                    //this.weeklyChallengeCompleted = true;
                    //currentUserWeeklyChallenges.add(weeklyChallengesFacade.findWeeklyChallengeWithInd(u.getInd()));
                }
            }
        }
    }

    public User getSelected() {
        if(selected == null) {
            selected = userFacade.find(FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("user_id"));
        }
        return selected;
    }

    public boolean isWeeklyChallengeCompleted() {
        return weeklyChallengeCompleted;
    }

    public void setWeeklyChallengeCompleted(boolean weeklyChallengeCompleted) {
        this.weeklyChallengeCompleted = weeklyChallengeCompleted;
    } 

    public UserIndexFacade getUserIndexFacade() {
        return userIndexFacade;
    }

    public void setUserIndexFacade(UserIndexFacade userIndexFacade) {
        this.userIndexFacade = userIndexFacade;
    }

    public DailyChallengesFacade getDailyChallengesFacade() {
        return dailyChallengesFacade;
    }

    public void setDailyChallengesFacade(DailyChallengesFacade dailyChallengesFacade) {
        this.dailyChallengesFacade = dailyChallengesFacade;
    }

    public WeeklyChallengesFacade getWeeklyChallengesFacade() {
        return weeklyChallengesFacade;
    }

    public void setWeeklyChallengesFacade(WeeklyChallengesFacade weeklyChallengesFacade) {
        this.weeklyChallengesFacade = weeklyChallengesFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public List<DailyChallenges> getCompletedUserDailyChallenges() {
        return completedUserDailyChallenges;
    }

    public void setCompletedUserDailyChallenges(List<DailyChallenges> completedUserDailyChallenges) {
        this.completedUserDailyChallenges = completedUserDailyChallenges;
    }

    public List<WeeklyChallenges> getCompletedUserWeeklyChallenges() {
        return completedUserWeeklyChallenges;
    }

    public void setCompletedUserWeeklyChallenges(List<WeeklyChallenges> completedUserWeeklyChallenges) {
        this.completedUserWeeklyChallenges = completedUserWeeklyChallenges;
    }    
}