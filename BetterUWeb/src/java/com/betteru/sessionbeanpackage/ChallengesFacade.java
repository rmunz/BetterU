/*
 * Created by Ojas Mhetar on 2016.03.30  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.Challenges;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ojmhetar
 */
@Stateless
public class ChallengesFacade extends AbstractFacade<Challenges> {

    @PersistenceContext(unitName = "BetterUWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChallengesFacade() {
        super(Challenges.class);
    }
    
}
