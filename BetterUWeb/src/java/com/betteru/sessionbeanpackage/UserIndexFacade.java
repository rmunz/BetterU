/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.UserIndex;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tim
 */
@Stateless
public class UserIndexFacade extends AbstractFacade<UserIndex> {

    @PersistenceContext(unitName = "BetterU-WebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserIndexFacade() {
        super(UserIndex.class);
    }
    
    public List<UserIndex> retrieveEntriesForUserId(int uid) {
        if(em.createQuery("SELECT ui FROM UserIndex ui WHERE ui.userIndexPK.userID = :id")
                .setParameter("id", uid).getResultList().isEmpty()) {
            return null;
        }
        else {
            return (List<UserIndex>) em.createQuery("SELECT ui FROM UserIndex ui WHERE ui.userIndexPK.userID = :id")
                .setParameter("id", uid).getResultList();
        }      
    }
}
