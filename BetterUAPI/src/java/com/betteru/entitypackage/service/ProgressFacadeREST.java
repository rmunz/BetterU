/*
 * Created by Ojas Mhetar on 2016.04.02  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.entitypackage.service;

import com.betteru.entitypackage.Progress;
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
import java.util.Date;
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
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Progress entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Progress find(@PathParam("id") Integer id) {
        return super.find(id);
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
    @Path("{id}/caloriesIn/week/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getCaloriesInWeek(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdCaloriesInByWeek", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }

    @GET
    @Path("{id}/caloriesIn/month/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getCaloriesInMonth(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdCaloriesInByMonth", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/caloriesOut/week/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getCaloriesOutWeek(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByCaloriesOutWeek", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/caloriesOut/month/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getCaloriesOutMonth(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByCaloriesOutMonth", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/weight/week/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getWeightWeek(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByWeightWeek", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    
    @GET
    @Path("{id}/weight/month/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getWeightMonth(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByWeightMonth", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/steps/week/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getStepsWeek(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByStepsWeek", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/steps/month/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getStepsMonth(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByStepsMonth", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/miles/week/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getMilesWeek(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByMilesWeek", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @GET
    @Path("{id}/miles/month/{day}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Progress> getMilesMonth(@PathParam("id") Integer id, @PathParam("day") Date day) {
        TypedQuery<Progress> query = em.createNamedQuery("Progress.findIdByMilesMonth", Progress.class);
        List<Progress> results = query.getResultList();
        return results;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
