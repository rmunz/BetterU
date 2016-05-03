/*
 * Created by Ojas Mhetar on 2016.02.27  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sourcepackage.User;
import com.betteru.sessionbeanpackage.UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "testManager")
@SessionScoped
/**
 *
 * @author Mhetar
 */
public class TestManager implements Serializable {

    // Instance Variables (Properties)
    private String firstName;
    private String lastName;
    private User selected;

    @EJB
    private UserFacade userFacade;

    public TestManager() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getSelected() {
        if (selected == null) {
            selected = userFacade.find(FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("user_id"));
        }
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public String updateProfile() {
        int user_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");
        User editUser = userFacade.getUser(user_id);
        try {
            editUser.setFirstName(this.selected.getFirstName());
            editUser.setLastName(this.selected.getLastName());
            userFacade.edit(editUser);
        } catch (EJBException e) {
            String msg = e.getMessage();
            return "";
        }
        return "MyAccount?faces-redirect=true";
    }

}
