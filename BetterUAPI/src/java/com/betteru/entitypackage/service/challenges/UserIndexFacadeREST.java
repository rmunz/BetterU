/*
 * Created by Timothy Street on 2016.04.23  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.entitypackage.service.challenges;

import com.betteru.entitypackage.DailyChallenges;
import com.betteru.entitypackage.User;
import com.betteru.entitypackage.UserIndex;
import com.betteru.entitypackage.UserIndexPK;
import com.betteru.entitypackage.WeeklyChallenges;
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
import javax.ws.rs.core.PathSegment;
import java.util.Random;
import java.util.Scanner;
import javax.persistence.Query;

/**
 *
 * @author Tim
 */
@Stateless
@Path("com.betteru.entitypackage.service.challenges.userindex")
public class UserIndexFacadeREST extends AbstractFacade<UserIndex> {

    @PersistenceContext(unitName = "BetterUAPIPU")
    private EntityManager em;

    private UserIndexPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userID=userIDValue;challengeType=challengeTypeValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.betteru.entitypackage.UserIndexPK key = new com.betteru.entitypackage.UserIndexPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userID = map.get("userID");
        if (userID != null && !userID.isEmpty()) {
            key.setUserID(new java.lang.Integer(userID.get(0)));
        }
        java.util.List<String> challengeType = map.get("challengeType");
        if (challengeType != null && !challengeType.isEmpty()) {
            key.setChallengeType(challengeType.get(0));
        }
        return key;
    }

    public UserIndexFacadeREST() {
        super(UserIndex.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserIndex entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, UserIndex entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.betteru.entitypackage.UserIndexPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserIndex find(@PathParam("id") PathSegment id) {
        com.betteru.entitypackage.UserIndexPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserIndex> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserIndex> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    /* Added methods */
    @GET
    @Path("createIndicesForUserId={uid}")
    /**
     *
     */
    public void createIndicesForUserId(@PathParam("uid") int uid) {

        for (int i = 0; i < 5; i++) {
            // Init userInd and userIndPK for creation
            UserIndex userInd = new UserIndex();
            UserIndexPK userIndPK = new UserIndexPK();

            // Set the fields for both
            userIndPK.setUserID(uid);
            switch (i) {
                case 0:
                    userIndPK.setChallengeType("Core");
                    break;
                case 1:
                    userIndPK.setChallengeType("Upper");
                    break;
                case 2:
                    userIndPK.setChallengeType("Cardio");
                    break;
                case 3:
                    userIndPK.setChallengeType("Lower");
                    break;
                default:
                    userIndPK.setChallengeType("Weekly");
                    break;
            }
            userInd.setUserIndexPK(userIndPK);
            userInd.setInd(0);

            // Insert into UserIndex table
            this.create(userInd);
        }
    }

    @GET
    @Path("setNextDailyChallengeForUserId={uid}")
    @Produces({MediaType.APPLICATION_JSON})
    public DailyChallenges setNextDailyChallengeForUserId(@PathParam("uid") int uid) {

        // Generate a random number between 0 and 3 (inclusive) to decide
        // what the next daily challenge type should be.
        // 0 - Core, 1 - Upper, 2 - Lower, 3 - Cardio
        Random generator = new Random();
        String challengeType = "";
        int randomChallengeType = generator.nextInt(4);

        switch (randomChallengeType) {
            case 0:
                challengeType = "Core";
                break;
            case 1:
                challengeType = "Upper";
                break;
            case 2:
                challengeType = "Lower";
                break;
            default:
                challengeType = "Cardio";
                break;
        }

        int index;
        if (em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                .setParameter("uid", uid).setParameter("ctype", challengeType)
                .getResultList().isEmpty()) {
            return null;
        } else {
            UserIndex userInd = (UserIndex) em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                    .setParameter("uid", uid).setParameter("ctype", challengeType).getSingleResult();
            // Attempt to increment to next Challenge in Challenges table
            index = userInd.getInd();
        }

        
        if(index < 50) {
            index = 1;
        } else {
            index++;
        }

        if (em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :index AND dc.challengeType = :ctype")
                .setParameter("index", index).setParameter("ctype", challengeType)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (DailyChallenges) em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :index AND dc.challengeType = :ctype")
                    .setParameter("index", index).setParameter("ctype", challengeType).getSingleResult();
        }
    }

    @GET
    @Path("setNextWeeklyChallengeForUserId={uid}")
    @Produces({MediaType.APPLICATION_JSON})
    public WeeklyChallenges setNextWeeklyChallengeForUserId(@PathParam("uid") int uid) {

        int index = 0;
        if (em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                .setParameter("uid", uid).setParameter("ctype", "Weekly")
                .getResultList().isEmpty()) {
            return null;
        } else {
            UserIndex userInd = (UserIndex) em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                    .setParameter("uid", uid).setParameter("ctype", "Weekly").getSingleResult();
            // Attempt to increment to next Challenge in Challenges table
            index = userInd.getInd();
        }
        
        if(index < 7) {
            index++;
        } else {
            index = 1;
        }
        
        if (em.createQuery("SELECT wc FROM WeeklyChallenges wc WHERE wc.ind = :index")
                .setParameter("index", index)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (WeeklyChallenges) em.createQuery("SELECT wc FROM WeeklyChallenges wc WHERE wc.ind = :index")
                    .setParameter("index", index).getSingleResult();
        }
    }

    @GET
    @Path("completeChallengeForUserId={uidAndChallengeType}")
    @Produces(MediaType.TEXT_PLAIN)
    public void completeChallengeForUserId(@PathParam("uidAndChallengeType") String param) {
        
        String[] temp = param.split("_");
        int uid = Integer.parseInt(temp[0]);
        String challengeType = temp[1];
        
        //List<DailyChallenges> dailyChallengesList = em.createQuery("SELECT dc FROM DailyChallenges dc").getResultList();
        List<WeeklyChallenges> weeklyChallengesList = em.createQuery("SELECT dc FROM WeeklyChallenges dc").getResultList();
        //int numDailyChallenges = dailyChallengesList.size();
        int numWeeklyChallenges = weeklyChallengesList.size();

        
        int awardedPoints = 0;
        UserIndex userIndex = (UserIndex) em.createQuery("SELECT ui FROM UserIndex ui WHERE ui.userIndexPK.userID = :uid AND ui.userIndexPK.challengeType = :ct")
                .setParameter("uid", uid).setParameter("ct",challengeType).getSingleResult(); 
        int index = userIndex.getInd() + 1;
        int queryInd = 0;
        if (challengeType.equals("Weekly")) {
            
            if((index) == numWeeklyChallenges ) {
                index = 0;
                queryInd = 1;
            }
            else {
                queryInd = index;
            }
            WeeklyChallenges weeklyChallenge = (WeeklyChallenges) em.createQuery("SELECT wc FROM WeeklyChallenges wc WHERE wc.ind = :index")
                .setParameter("index", queryInd).getSingleResult();
            awardedPoints = weeklyChallenge.getPointsAwarded();
            // Update user index to point to next weekly challenge
            em.createQuery("UPDATE UserIndex u SET u.ind = :index WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                .setParameter("uid", uid).setParameter("ctype", "Weekly").setParameter("index", index).executeUpdate();   
            
        } else {
            
            List<DailyChallenges> dailyChallengesOfCType;
            dailyChallengesOfCType = em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.challengeType = :ctype")
                    .setParameter("ctype", challengeType).getResultList();
            int numCType = dailyChallengesOfCType.size();
            
            // Prevents the user from exceeding the maximum index for a specific challenge type
            if((index) >= numCType) {
                index = 0;
                queryInd = 1;
            } else {
                queryInd = index;
            }
            DailyChallenges dailyChallenge = (DailyChallenges) em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :index AND dc.challengeType = :ctype")
                .setParameter("index", queryInd).setParameter("ctype", challengeType).getSingleResult();
            awardedPoints = dailyChallenge.getPointsAwarded();
            // Update user index to point to next daily challenge
            em.createQuery("UPDATE UserIndex u SET u.ind = :index WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                .setParameter("uid", uid).setParameter("ctype", challengeType).setParameter("index", index).executeUpdate();  
        }
        
        int currentUserPoints = ((User) em.createQuery("SELECT u FROM User u WHERE u.id = :uid")
                    .setParameter("uid", uid).getSingleResult()).getPoints() + awardedPoints;
        
        em.createQuery("UPDATE User u SET u.points = :pts WHERE u.id = :uid")
                    .setParameter("uid", uid).setParameter("pts", currentUserPoints).executeUpdate();
    }
    
    @GET
    @Path("vetoDailyChallengeForUserId={uidAndChallengeType}")
    @Produces(MediaType.APPLICATION_JSON)
    public DailyChallenges vetoChallengeForUserId(@PathParam("uidAndChallengeType") String param) {
        
        String[] temp = param.split("_");
        int uid = Integer.parseInt(temp[0]);
        String oldChallengeType = temp[1];
	DailyChallenges dc;
        Random generator = new Random();
        String challengeType = "";
        int randomChallengeType = generator.nextInt(4);

	do {
        switch (randomChallengeType) {
            case 0:
                challengeType = "Core";
                break;
            case 1:
                challengeType = "Upper";
                break;
            case 2:
                challengeType = "Lower";
                break;
            default:
                challengeType = "Cardio";
                break;
        }
        
        randomChallengeType = generator.nextInt(4);
        }
        while (challengeType.equals(oldChallengeType));
        
        int index;
        if (em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                .setParameter("uid", uid).setParameter("ctype", challengeType)
                .getResultList().isEmpty()) {
            return null;
        } else {
            UserIndex userInd = (UserIndex) em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :ctype AND u.userIndexPK.userID = :uid")
                    .setParameter("uid", uid).setParameter("ctype", challengeType).getSingleResult();
            // Attempt to increment to next Challenge in Challenges table
            index = userInd.getInd();
        }

        index++;

        if (em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :index AND dc.challengeType = :ctype")
                .setParameter("index", index).setParameter("ctype", challengeType)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (DailyChallenges) em.createQuery("SELECT dc FROM DailyChallenges dc WHERE dc.ind = :index AND dc.challengeType = :ctype")
                    .setParameter("index", index).setParameter("ctype", challengeType).getSingleResult();
        }
    }
    
    /**
    @GET
    @Path("updateIndicesTables")
    public void updateIndicesTablesForAllUsers() {
        List<User> userList = em.createQuery("SELECT u FROM User u").getResultList();
        
        for (User u : userList) {
            int uid = u.getId();
            if (em.createQuery("SELECT u FROM UserIndex u WHERE u.userIndexPK.userID = :uid")
                .setParameter("uid", uid).getResultList().isEmpty()) {
                this.createIndicesForUserId(uid);
            }
                
        }
    }*/
}
