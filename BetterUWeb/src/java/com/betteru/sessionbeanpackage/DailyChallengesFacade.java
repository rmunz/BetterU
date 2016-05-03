/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.DailyChallenges;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tim
 */
@Stateless
public class DailyChallengesFacade extends AbstractFacade<DailyChallenges> {

    @PersistenceContext(unitName = "BetterU-WebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DailyChallengesFacade() {
        super(DailyChallenges.class);
    }

    /* Methods added to auto-generated code. */
    public DailyChallenges getChallengeAtIndWithType(int index, String challengeType) {
        if (em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :ind AND dc.challengeType = :ctype")
                .setParameter("ind", index)
                .setParameter("ctype", challengeType)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (DailyChallenges) em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :ind AND dc.challengeType = :ctype")
                    .setParameter("ind", index)
                    .setParameter("ctype", challengeType)
                    .getSingleResult();
        }
    }

}
