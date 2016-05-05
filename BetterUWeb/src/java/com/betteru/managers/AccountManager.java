/*
 * Created by Ojas Mhetar on 2016.02.27  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sessionbeanpackage.ProgressFacade;
import com.betteru.sourcepackage.User;
import com.betteru.sessionbeanpackage.UserFacade;
import com.betteru.sourcepackage.Progress;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
 
import static javax.ws.rs.client.Entity.entity;

@Named(value = "accountManager")
@SessionScoped
/**
 *
 * @author Mhetar
 */
public class AccountManager implements Serializable {

    // Instance Variables (Properties)
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private Integer height;
    private Integer heightFeet;
    private Integer heightInches;
    private Integer weight;
    private Integer activityLevel;
    private String activityGoal;
    private Integer goalWeight;
    private Integer targetCalories;
    private char gender;
    private int security_question;
    private String security_answer;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snack;
    private String photo;

    private Map<String, Object> security_questions;
    private String statusMessage = "";
    private String profileStatusMessage;
    private String advancedStatusMessage;
    private User selected;

    /**
     * The instance variable 'userFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject
     * in this instance variable a reference to the @Stateless session bean
     * UserFacade.
     */
    @EJB
    private UserFacade userFacade;

    /**
     * The instance variable 'pf' is annotated with the @EJB annotation. This
     * means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @Stateless session bean
     * UserFacade.
     */
    @EJB
    private ProgressFacade pf;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Integer level) {
        this.activityLevel = level;
    }

    public String getActivityGoal() {
        return this.activityGoal;
    }

    public void setActivityGoal(String goal) {
        this.activityGoal = goal;
    }

    public Integer getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Integer goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Integer getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(Integer targetCalories) {
        this.targetCalories = targetCalories;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Creates a new instance of AccountManager
     */
    public AccountManager() {
    }

    /**
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinenr(String dinner) {
        this.dinner = dinner;
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(int security_question) {
        this.security_question = security_question;
    }

    public String getSecurity_answer() {
        return security_answer;
    }

    public void setSecurity_answer(String security_answer) {
        this.security_answer = security_answer;
    }

    public Map<String, Object> getSecurity_questions() {
        if (security_questions == null) {
            security_questions = new LinkedHashMap<>();
            for (int i = 0; i < Constants.QUESTIONS.length; i++) {
                security_questions.put(Constants.QUESTIONS[i], i);
            }
        }
        return security_questions;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    public String getProfileStatusMessage() {
        return this.profileStatusMessage;
    }
    
    public void setProfileStatusMessage(String statusMessage) {
        this.profileStatusMessage = statusMessage;
    }
    
    public String getAdvancedStatusMessage() {
        return this.advancedStatusMessage;
    }
    
    public void setAdvancedStatusMessage(String statusMessage) {
        this.advancedStatusMessage = statusMessage;
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

    /* Check session map for username to see if anyone is logged in */
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("username") != null;
    }

    public String createAccount() {

        // Check to see if a user already exists with the username given.
        User aUser = userFacade.findByUsername(username);

        if (aUser != null) {
            username = "";
            statusMessage = "Username already exists! Please select a different one!";
            return "";
        }

        if (statusMessage.isEmpty()) {
            try {
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                this.height = (heightFeet * 12) + heightInches;
                user.setHeight(height);
                user.setWeight(weight);
                user.setAge(age);
                user.setSecurityQuestion(security_question);
                user.setSecurityAnswer(security_answer);
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setGender(gender);
                user.calculateBMR();
                user.setActivityLevel(activityLevel);
                user.setActivityGoal(activityGoal);
                user.setGoalWeight(goalWeight);
                user.setTargetCalories(user.calcTargetCals());
                user.setPoints(0);
                user.setUnits('I');
                sendEmail(user, "create");
                userFacade.create(user);    
                Calendar c = Calendar.getInstance();
                c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
                for(int i = 0; i < 30; i++) {
                    //...Perform a task...
                    Progress progress = new Progress(user.getId(), (int) ((c.getTimeInMillis() - i * 86400000) / 1000));
                    progress.setCaloriesIn(0);
                    progress.setCaloriesOut(0);
                    progress.setMiles(0.0);
                    progress.setWeight((double)user.getWeight());
                    progress.setSteps(0);
                    pf.create(progress);
                }

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while creating your account!";
                return "";
            }
            initializeSessionMap();

            return "MyAccount";
        }
        return "";
    }

    public void sendEmail(User user, String emailType) {


        SendGrid sendgrid = new SendGrid("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");

        SendGrid.Email email = new SendGrid.Email();
        //Sets up the email format to be sent.
        email.addTo(user.getEmail());
        email.setFrom("BetterU");
        
        if(emailType.equals("create")) {
            email.setSubject(user.getFirstName() + ", Welcome to BetterU.");
            email.setHtml("Thanks for signing up!");
        }
        else if(emailType.equals("delete")) {       
            email.setSubject("We're sorry to see you go, " + getFirstName());
            email.setHtml("Come back to BetterU any time."); 
        }
        else { 
            
            email.setSubject("BetterU");
            email.setHtml("BetterU."); 
            
        }

        //Send the email to the user using SendGrid, 
        //if it fails print the error statement
        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());
        } catch (SendGridException e) {
            System.err.println(e);
        }

    }

    public String updateAccount() {
        if (statusMessage.isEmpty()) {
            int user_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");
            User editUser = userFacade.getUser(user_id);
            try {
                editUser.setFirstName(this.selected.getFirstName());
                editUser.setLastName(this.selected.getLastName());
                editUser.setHeight(this.selected.getHeight());
                editUser.setWeight(this.selected.getWeight());
                editUser.setEmail(this.selected.getEmail());
                editUser.setPassword(this.selected.getPassword());
                userFacade.edit(editUser);
            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while editing your profile!";
                return "";
            }
            return "Profile";
        }
        return "";
    }
    
    public String updateProfile() {
        return "";
    }
    
    public String updateAdvanced() {
        return "";
    }
    
    public String deleteAccount() {
        if (statusMessage.isEmpty()) {
            int user_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");
            try {
                sendEmail(selected, "delete");
                userFacade.deleteUser(user_id);

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while deleting your account!";
                return "";
            }

            return "/index.xhtml?faces-redirect=true";
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void validateInformation(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();
        // Get password
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String pwd = uiInputPassword.getLocalValue() == null ? ""
                : uiInputPassword.getLocalValue().toString();

        // Get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirm-password");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        if (pwd.isEmpty() || confirmPassword.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }

        if (!pwd.equals(confirmPassword)) {
            statusMessage = "Passwords must match!";
        } else {
            statusMessage = "";
        }
    }
    
    public void validateProfile(ComponentSystemEvent event) {
        
    }
    
    public void validateAdvanced(ComponentSystemEvent event) {
        
    }

    public void initializeSessionMap() {
        User user = userFacade.findByUsername(getUsername());
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("username", username);
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("user_id", user.getId());
    }

    private boolean correctPasswordEntered(UIComponent components) {
        UIInput uiInputVerifyPassword = (UIInput) components.findComponent("confirm-password");
        String verifyPassword = uiInputVerifyPassword.getLocalValue() == null ? ""
                : uiInputVerifyPassword.getLocalValue().toString();
        if (verifyPassword.isEmpty()) {
            statusMessage = "";
            return false;
        } else if (verifyPassword.equals(password)) {
            return true;
        } else {
            statusMessage = "Invalid password entered!";
            return false;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        username = firstName = lastName = password = email = statusMessage = "";
        security_answer = "";
        height = weight = security_question = 0;
        breakfast = lunch = dinner = snack = photo = activityGoal = "";
        targetCalories = goalWeight = activityLevel = 0;

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

}
