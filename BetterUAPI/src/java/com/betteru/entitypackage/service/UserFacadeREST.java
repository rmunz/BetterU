/*
 * Created by Ojas Mhetar on 2016.04.02  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.entitypackage.service;

import com.betteru.entitypackage.Progress;
import com.betteru.entitypackage.User;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.Timer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ojmhetar
 */
@Stateless
@Path("com.betteru.entitypackage.user")
public class UserFacadeREST extends AbstractFacade<User> {
    @EJB
    private ProgressFacadeREST pf;
    
    @PersistenceContext(unitName = "BetterUAPIPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
        
        SendGrid sendgrid = new SendGrid("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");
        
        SendGrid.Email email = new SendGrid.Email();
        //Sets up the email format to be sent.
        email.addTo(entity.getEmail());
        email.setFrom("BetterU");
        email.setSubject("TEMPORARY EMAIL: Welcome to BetterU.");
        email.setHtml("Thanks for signing up!");

        //Send the email to the user using SendGrid, 
        //if it fails print the error statement
        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());
        }
        catch (SendGridException e) {
            System.err.println(e);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, User entity) {
        //manually update user fields
        User user = super.find(id);
        user.setActivityGoal(entity.getActivityGoal());
        user.setActivityLevel(entity.getActivityLevel());
        user.setAge(entity.getAge());
        user.setBmr(user.getBmr());
        user.setBreakfast(entity.getBreakfast());
        user.setDCSkipped(entity.getDCSkipped());
        user.setDailyChallengeIndex(entity.getDailyChallengeIndex());
        user.setDinner(entity.getDinner());
        user.setEmail(user.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setGender(entity.getGender());
        user.setGoalType(entity.getGoalType());
        user.setGoalWeight(entity.getGoalWeight());
        user.setHeight(entity.getHeight());
        user.setId(entity.getId());
        user.setLastName(entity.getLastName());
        user.setLunch(entity.getLunch());
        user.setPassword(entity.getPassword());
        user.setPhoto(entity.getPhoto());
        user.setPhotoCollection(entity.getPhotoCollection());
        user.setPoints(entity.getPoints());
        user.setSecurityAnswer(entity.getSecurityAnswer());
        user.setSecurityQuestion(entity.getSecurityQuestion());
        user.setSnack(entity.getSnack());
        user.setTargetCalories(entity.getTargetCalories());
        user.setUnits(entity.getUnits());
        user.setUsername(entity.getUsername());
        user.setWCSkipped(entity.getWCSkipped());
        user.setWeeklyChallengeIndex(entity.getWeeklyChallengeIndex());
        
        //update weight entry for that day in the progress db
        int weight = entity.getWeight();
        if (weight > 0) {
            user.setWeight(weight);
            Calendar c = Calendar.getInstance();
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
            Progress prog = pf.find(id, (int)(c.getTimeInMillis()/1000));
            prog.setWeight((double)weight);
        }
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}