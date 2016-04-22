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
    
    public List<Progress> findAllProgressEntriesByUid(int uid) {
        String query = "SELECT p FROM Progress p WHERE p.uid = :uid";
        
        if (em.createQuery(query)
                .setParameter("uid", uid)
                .getResultList().isEmpty()) {
            return null;
        }
        else {
            return (List<Progress>) (em.createQuery(query)
                .setParameter("uid", uid)
                .getResultList());        
        }
    }
    
    public List<Progress> findWeekByUid(int userId, int logDate) {
        int aWeekAgo = logDate - 604800;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findWeek", Progress.class)
                                        .setParameter("LogDate", logDate).setParameter("userId", userId).setParameter("aWeekAgo", aWeekAgo);               
        return query.getResultList();        
    }
    
    public List<Progress> findMonthByUid(int userId, int logDate) {
        int aMonthAgo = logDate - 2628000;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findMonth", Progress.class)
                                        .setParameter("LogDate", logDate).setParameter("userId", userId).setParameter("aMonthAgo", aMonthAgo);               
        return query.getResultList();
    }
    
}
