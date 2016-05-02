/*
 * Created by Ojas Mhetar on 2016.04.02  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.entitypackage.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ojmhetar
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.betteru.entitypackage.service.PhotoFacadeREST.class);
        resources.add(com.betteru.entitypackage.service.ProgressFacadeREST.class);
        resources.add(com.betteru.entitypackage.service.UserFacadeREST.class);
        resources.add(com.betteru.entitypackage.service.UsersProgressFacadeREST.class);
        resources.add(com.betteru.entitypackage.service.challenges.DailyChallengesFacadeREST.class);
        resources.add(com.betteru.entitypackage.service.challenges.UserIndexFacadeREST.class);
        resources.add(com.betteru.entitypackage.service.challenges.WeeklyChallengesFacadeREST.class);
    }
    
}
