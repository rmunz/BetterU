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

/**
 *
 * @author Tim
 */
@Named(value = "challengesManager")
@SessionScoped
public class ChallengesManager implements Serializable {

    /**
     * The instance variable 'UserIndexFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @SessionScoped session bean UserIndexFacade.
     */
    @EJB
    private UserIndexFacade userIndexFacade;
    
    /**
     * The instance variable 'DailyChallengesFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @SessionScoped session bean DailyChallengesFacade.
     */
    @EJB
    private DailyChallengesFacade dailyChallengesFacade;
    
    /**
     * The instance variable 'WeeklyChallengesFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @SessionScoped session bean WeeklyChallengesFacade.
     */
    @EJB
    private WeeklyChallengesFacade weeklyChallengesFacade;
    
    
    public ChallengesManager() {
    }
       
}