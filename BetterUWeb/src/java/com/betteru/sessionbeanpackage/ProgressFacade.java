/*
 * Created by Jared Schwalbe on 2016.04.08  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.Progress;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jared
 */
@Stateless
public class ProgressFacade extends AbstractFacade<Progress> {

    @PersistenceContext(unitName = "BetterU-WebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgressFacade() {
        super(Progress.class);
    }
    
    public List<Progress> findAllProgressEntriesByUid(int userId) {
        String query = "SELECT p FROM Progress p WHERE p.userId = :userId";
        
        if (em.createQuery(query)
                .setParameter("userId", userId)
                .getResultList().isEmpty()) {
            return null;
        }
        else {
            return (List<Progress>) (em.createQuery(query)
                .setParameter("userId", userId)
                .getResultList());        
        }
    }
    
    public List<Progress> findWeekByUid(int userId, long logDate) {
        long aWeekAgo = logDate - 604800 + 1;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findWeek", Progress.class)
                                        .setParameter("logDate", logDate).setParameter("userId", userId).setParameter("aWeekAgo", aWeekAgo);               
        return query.getResultList().isEmpty() ? null : query.getResultList();        
    }
    
    public List<Progress> findMonthByUid(int userId, long logDate) {
        long aMonthAgo = logDate - 2628000 + 1;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findMonth", Progress.class)
                                        .setParameter("logDate", logDate).setParameter("userId", userId).setParameter("aMonthAgo", aMonthAgo);               
        return query.getResultList().isEmpty() ? null : query.getResultList();        
    }
    
}
