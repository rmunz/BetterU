/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.WeeklyChallenges;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tim
 */
@Stateless
public class WeeklyChallengesFacade extends AbstractFacade<WeeklyChallenges> {

    @PersistenceContext(unitName = "BetterU-WebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WeeklyChallengesFacade() {
        super(WeeklyChallenges.class);
    }
    
}
