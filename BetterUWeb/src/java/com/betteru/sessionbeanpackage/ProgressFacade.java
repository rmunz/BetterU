/*
 * Created by Jared Schwalbe on 2016.04.08  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.Progress;
import com.betteru.sourcepackage.ProgressPK;
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
    
    public List<Progress> findMonthByUid(int userId, long logDate, int numDaysInMonth) {
        long aMonthAgo = logDate - (24*60*60*numDaysInMonth) + 1;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findMonth", Progress.class)
                                        .setParameter("logDate", logDate).setParameter("userId", userId).setParameter("aMonthAgo", aMonthAgo);               
        return query.getResultList().isEmpty() ? null : query.getResultList();        
    }
    
//    public Progress findDayByUid(int userId, long logDate) {
//        
//        TypedQuery<Progress> query = em.createNamedQuery("Progress.findDay", Progress.class)
//                                        .setParameter("logDate", logDate).setParameter("userId", userId);               
//        return query.getResultList().isEmpty() ? null : query.getSingleResult();        
//    }
    
    public Progress getProgressEntry(Integer user_id, Integer epochTime) {
        
        return (Progress) (em.createQuery("SELECT p FROM Progress p WHERE p.userId = :userId AND p.logDate = :time")
            .setParameter("userId", user_id)
            .setParameter("time", epochTime)
            .getSingleResult());        

    }
    
}
