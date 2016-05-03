/*
 * Created by Ojas Mhetar on 2016.04.02  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.entitypackage;

import com.betteru.entitypackage.service.ProgressFacadeREST;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ojmhetar
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAge", query = "SELECT u FROM User u WHERE u.age = :age"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByHeight", query = "SELECT u FROM User u WHERE u.height = :height"),
    @NamedQuery(name = "User.findByWeight", query = "SELECT u FROM User u WHERE u.weight = :weight"),
    @NamedQuery(name = "User.findByUnits", query = "SELECT u FROM User u WHERE u.units = :units"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPoints", query = "SELECT u FROM User u WHERE u.points = :points"),
    @NamedQuery(name = "User.findByActivityLevel", query = "SELECT u FROM User u WHERE u.activityLevel = :activityLevel"),
    @NamedQuery(name = "User.findByBmr", query = "SELECT u FROM User u WHERE u.bmr = :bmr"),
    @NamedQuery(name = "User.findByGoalType", query = "SELECT u FROM User u WHERE u.goalType = :goalType"),
    @NamedQuery(name = "User.findByGoalWeight", query = "SELECT u FROM User u WHERE u.goalWeight = :goalWeight"),
    @NamedQuery(name = "User.findByActivityGoal", query = "SELECT u FROM User u WHERE u.activityGoal = :activityGoal"),
    @NamedQuery(name = "User.findByDailyChallengeIndex", query = "SELECT u FROM User u WHERE u.dailyChallengeIndex = :dailyChallengeIndex"),
    @NamedQuery(name = "User.findByDCSkipped", query = "SELECT u FROM User u WHERE u.dCSkipped = :dCSkipped"),
    @NamedQuery(name = "User.findByWeeklyChallengeIndex", query = "SELECT u FROM User u WHERE u.weeklyChallengeIndex = :weeklyChallengeIndex"),
    @NamedQuery(name = "User.findByWCSkipped", query = "SELECT u FROM User u WHERE u.wCSkipped = :wCSkipped"),
    @NamedQuery(name = "User.findBySecurityQuestion", query = "SELECT u FROM User u WHERE u.securityQuestion = :securityQuestion"),
    @NamedQuery(name = "User.findBySecurityAnswer", query = "SELECT u FROM User u WHERE u.securityAnswer = :securityAnswer")})
public class User implements Serializable {

    @EJB
    ProgressFacadeREST pf;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Gender")
    private Character gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Height")
    private int height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Weight")
    private int weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Units")
    private Character units;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Points")
    private int points;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ActivityLevel")
    private int activityLevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BMR")
    private int bmr;
    @Column(name = "GoalType")
    private Integer goalType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GoalWeight")
    private int goalWeight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ActivityGoal")
    private String activityGoal;
    @Column(name = "DailyChallengeIndex")
    private Integer dailyChallengeIndex;
    @Size(max = 255)
    @Column(name = "DCSkipped")
    private String dCSkipped;
    @Column(name = "WeeklyChallengeIndex")
    private Integer weeklyChallengeIndex;
    @Column(name = "WCSkipped")
    private Integer wCSkipped;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SecurityQuestion")
    private int securityQuestion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SecurityAnswer")
    private String securityAnswer;
    
    @Size(max = 255)
    @Column(name = "Breakfast")
    private String breakfast;
    @Size(max = 255)
    @Column(name = "Lunch")
    private String lunch;
    @Size(max = 255)
    @Column(name = "Dinner")
    private String dinner;
    @Size(max = 255)
    @Column(name = "Snack")
    private String snack;
    @Size(max = 255)
    @Column(name = "Photo")
    private String photo;
    @Column(name = "TargetCalories")
    private Integer targetCalories;
    @OneToMany(mappedBy = "userId")
    private Collection<Photo> photoCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String firstName, String lastName, String username, 
            String password, int age, Character gender, int height, int weight, 
            Character units, String email, int points, int activityLevel, int bmr, 
            int goalWeight, String activityGoal, int securityQuestion, String securityAnswer,
            String breakfast, String lunch, String dinner, String snack, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.units = units;
        this.email = email;
        this.points = points;
        this.activityLevel = activityLevel;
        this.bmr = bmr;
        this.goalWeight = goalWeight;
        this.activityGoal = activityGoal;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snack = snack;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        Progress p = pf.find(id, (int)(c.getTimeInMillis()/1000));
        p.setWeight((double)weight);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Character getUnits() {
        return units;
    }

    public void setUnits(Character units) {
        this.units = units;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    public Integer getGoalType() {
        return goalType;
    }

    public void setGoalType(Integer goalType) {
        this.goalType = goalType;
    }

    public int getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getActivityGoal() {
        return activityGoal;
    }

    public void setActivityGoal(String activityGoal) {
        this.activityGoal = activityGoal;
    }

    public Integer getDailyChallengeIndex() {
        return dailyChallengeIndex;
    }

    public void setDailyChallengeIndex(Integer dailyChallengeIndex) {
        this.dailyChallengeIndex = dailyChallengeIndex;
    }

    public String getDCSkipped() {
        return dCSkipped;
    }

    public void setDCSkipped(String dCSkipped) {
        this.dCSkipped = dCSkipped;
    }

    public Integer getWeeklyChallengeIndex() {
        return weeklyChallengeIndex;
    }

    public void setWeeklyChallengeIndex(Integer weeklyChallengeIndex) {
        this.weeklyChallengeIndex = weeklyChallengeIndex;
    }

    public Integer getWCSkipped() {
        return wCSkipped;
    }

    public void setWCSkipped(Integer wCSkipped) {
        this.wCSkipped = wCSkipped;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
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

    public void setDinner(String dinner) {
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
    
    @XmlTransient
    public Collection<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public void setPhotoCollection(Collection<Photo> photoCollection) {
        this.photoCollection = photoCollection;
    }

    public Integer getTargetCalories() {
        return targetCalories;
    }
    
    public void setTargetCalories(Integer targetCalories) {
        this.targetCalories = targetCalories;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.entitypackage.User[ id=" + id + " ]";
    }

    private ProgressFacadeREST lookupProgressFacadeRESTBean() {
        try {
            Context c = new InitialContext();
            return (ProgressFacadeREST) c.lookup("java:global/BetterUAPI/ProgressFacadeREST!com.betteru.entitypackage.service.ProgressFacadeREST");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
