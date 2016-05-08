/*
 * Created by Ojas Mhetar on 2016.04.02  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.entitypackage.service;

import com.betteru.entitypackage.Progress;
import com.betteru.entitypackage.ProgressPK;
import com.betteru.entitypackage.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.persistence.TypedQuery;

/**
 *
 * @author ojmhetar
 */
@Stateless
@Path("com.betteru.entitypackage.progress")
public class ProgressFacadeREST extends AbstractFacade<Progress> {
    @EJB
    private UserFacadeREST uf;
    
    @PersistenceContext(unitName = "BetterUAPIPU")
    private EntityManager em;

    public ProgressFacadeREST() {
        super(Progress.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Progress entity) {
        super.create(entity);
    }
    
    @PUT
    @Path("{UserId}/{LogDate}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("UserId") Integer userId, @PathParam("LogDate") Integer logDate, Progress entity) {
        ProgressPK pk = new ProgressPK();
        pk.setDay(logDate);
        pk.setId(userId);
        //super.edit(entity);
        
        //manually add all of the fields
        Progress prog = (Progress) super.find(pk);
        prog.setMiles(entity.getMiles());
        double weight = entity.getWeight();
        //update user profile weight in accordance with new progress entry
        if (weight > 0) {
            prog.setWeight(weight);
            User user = uf.find(userId);
            user.setWeight((int)weight);
        }
        
        prog.setCaloriesIn(entity.getCaloriesIn());
        prog.setCaloriesOut(entity.getCaloriesOut());
        prog.setSteps(entity.getSteps());
         
    }

    @DELETE
    @Path("{UserId}/{LogDate}")
    public void remove(@PathParam("UserId") Integer userId, @PathParam("LogDate") Integer logDate) {
        ProgressPK pk = new ProgressPK();
        pk.setDay(logDate);
        pk.setId(userId);
        super.remove(super.find(pk));
    }

    @GET
    @Path("{UserId}/{LogDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Progress find(@PathParam("UserId") Integer userId, @PathParam("LogDate") Integer logDate) {
        ProgressPK pk = new ProgressPK();
        pk.setDay(logDate);
        pk.setId(userId);
        return super.find(pk);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> findAll() {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    /**
     * returns a week of progress entries as JSON
     * @param userId - user we want
     * @param logDate - date of the last day of the week we want
     * @return JSON of the last week of progress
     */
    @GET
    @Path("{UserId}/week/{LogDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getWeek(@PathParam("UserId") Integer userId, @PathParam("LogDate") Integer logDate) {
        int aWeekAgo = logDate - 604800;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findWeek", Progress.class)
                                        .setParameter("LogDate", logDate).setParameter("userId", userId).setParameter("aWeekAgo", aWeekAgo);               
        return query.getResultList();
    }
    
    /**
     * returns a month of progress entries as JSON
     * @param userId - user we want
     * @param logDate - date of the last day of the month we want
     * @return JSON of the last month of progress
     */
    @GET
    @Path("{UserId}/month/{LogDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getMonth(@PathParam("UserId") Integer userId, @PathParam("LogDate") Integer logDate) {
        int aMonthAgo = logDate - 2628000;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findMonth", Progress.class)
                                        .setParameter("LogDate", logDate).setParameter("userId", userId).setParameter("aMonthAgo", aMonthAgo);               
        return query.getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
