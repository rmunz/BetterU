/*
 * Created by Ojas Mhetar on 2016.03.29  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.mycompany.betterurest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "User.findByGoalID", query = "SELECT u FROM User u WHERE u.goalID = :goalID"),
    @NamedQuery(name = "User.findByUnits", query = "SELECT u FROM User u WHERE u.units = :units"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPoints", query = "SELECT u FROM User u WHERE u.points = :points"),
    @NamedQuery(name = "User.findByActivityLevel", query = "SELECT u FROM User u WHERE u.activityLevel = :activityLevel"),
    @NamedQuery(name = "User.findByBmr", query = "SELECT u FROM User u WHERE u.bmr = :bmr"),
    @NamedQuery(name = "User.findByCurrentDailyChallenge", query = "SELECT u FROM User u WHERE u.currentDailyChallenge = :currentDailyChallenge"),
    @NamedQuery(name = "User.findByCurrentWeeklyChallenge", query = "SELECT u FROM User u WHERE u.currentWeeklyChallenge = :currentWeeklyChallenge")})
public class User implements Serializable {

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
    @Column(name = "GoalID")
    private int goalID;
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
    @Size(max = 255)
    @Column(name = "CurrentDailyChallenge")
    private String currentDailyChallenge;
    @Size(max = 255)
    @Column(name = "CurrentWeeklyChallenge")
    private String currentWeeklyChallenge;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String firstName, String lastName, String username, String password, int age, Character gender, int height, int weight, int goalID, Character units, String email, int points, int activityLevel, int bmr) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.goalID = goalID;
        this.units = units;
        this.email = email;
        this.points = points;
        this.activityLevel = activityLevel;
        this.bmr = bmr;
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
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
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

    public String getCurrentDailyChallenge() {
        return currentDailyChallenge;
    }

    public void setCurrentDailyChallenge(String currentDailyChallenge) {
        this.currentDailyChallenge = currentDailyChallenge;
    }

    public String getCurrentWeeklyChallenge() {
        return currentWeeklyChallenge;
    }

    public void setCurrentWeeklyChallenge(String currentWeeklyChallenge) {
        this.currentWeeklyChallenge = currentWeeklyChallenge;
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
        return "com.mycompany.betterurest.User[ id=" + id + " ]";
    }
    
}
