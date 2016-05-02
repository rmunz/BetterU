/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.sessionbeanpackage;

import com.betteru.sourcepackage.UserIndex;
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
    
}
