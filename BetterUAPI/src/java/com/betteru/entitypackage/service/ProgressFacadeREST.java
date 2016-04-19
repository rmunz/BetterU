/*
 * Created by Ojas Mhetar on 2016.04.02  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.entitypackage.service;

import com.betteru.entitypackage.Progress;
import com.betteru.entitypackage.ProgressPK;
import java.util.List;
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
    @Path("{id}/{day}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, @PathParam("day") Integer day, Progress entity) {
        ProgressPK pk = new ProgressPK();
        pk.setDay(day);
        pk.setId(id);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}/{day}")
    public void remove(@PathParam("id") Integer id, @PathParam("day") Integer day) {
        ProgressPK pk = new ProgressPK();
        pk.setDay(day);
        pk.setId(id);
        super.remove(super.find(pk));
    }

    @GET
    @Path("{id}/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public Progress find(@PathParam("id") Integer id, @PathParam("day") Integer day) {
        ProgressPK pk = new ProgressPK();
        pk.setDay(day);
        pk.setId(id);
        return super.find(pk);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("{id}/week/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getWeek(@PathParam("id") Integer id, @PathParam("day") Integer day) {
        int aWeekAgo = day - 604800;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findWeek", Progress.class)
                                        .setParameter("date", day).setParameter("id", id).setParameter("aWeekAgo", aWeekAgo);               
        return query.getResultList();
    }
    
    @GET
    @Path("{id}/month/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getMonth(@PathParam("id") Integer id, @PathParam("day") Integer day) {
        int aMonthAgo = day - 2628000;
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findMonth", Progress.class)
                                        .setParameter("date", day).setParameter("id", id).setParameter("aMonthAgo", aMonthAgo);               
        return query.getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
