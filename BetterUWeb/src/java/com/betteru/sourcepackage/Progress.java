/*
 * Created by Ojas Mhetar on 2016.04.03  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ojmhetar
 */
@Entity
@Table(name = "Progress")
@IdClass(value=ProgressPK.class)
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Progress.findMonth", query = "SELECT p FROM Progress p WHERE p.userId = :userId AND p.logDate BETWEEN :aMonthAgo AND :logDate"),
    @NamedQuery(name = "Progress.findWeek", query = "SELECT p FROM Progress p WHERE p.userId = :userId AND p.logDate BETWEEN :aWeekAgo AND :logDate"),
    @NamedQuery(name = "Progress.findDay", query = "SELECT p FROM Progress p WHERE p.userId = :userId AND p.logDate = :logDate"),
    
    @NamedQuery(name = "Progress.findAll", query = "SELECT p FROM Progress p"),
    @NamedQuery(name = "Progress.findByUserId", query = "SELECT p FROM Progress p WHERE p.userId = :userId"),
    @NamedQuery(name = "Progress.findByLogDate", query = "SELECT p FROM Progress p WHERE p.logDate = :logDate"),
    @NamedQuery(name = "Progress.findByCaloriesIn", query = "SELECT p FROM Progress p WHERE p.caloriesIn = :caloriesIn"),
    @NamedQuery(name = "Progress.findByCaloriesOut", query = "SELECT p FROM Progress p WHERE p.caloriesOut = :caloriesOut"),
    @NamedQuery(name = "Progress.findByWeight", query = "SELECT p FROM Progress p WHERE p.weight = :weight"),
    @NamedQuery(name = "Progress.findByMiles", query = "SELECT p FROM Progress p WHERE p.miles = :miles"),
    @NamedQuery(name = "Progress.findBySteps", query = "SELECT p FROM Progress p WHERE p.steps = :steps")})
public class Progress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private Integer userId;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LogDate")
    private Integer logDate;
    @Column(name = "CaloriesIn")
    private Integer caloriesIn;
    @Column(name = "CaloriesOut")
    private Integer caloriesOut;
    @Column(name = "Weight")
    private Integer weight;
    @Column(name = "Miles")
    private Integer miles;
    @Column(name = "Steps")
    private Integer steps;
    //@JoinColumn(name = "user_id", referencedColumnName = "UserId")
    //@ManyToOne
    //private User user;
    
    public Progress() {
    }

    public Progress(Integer userId) {
        this.userId = userId;
    }

    public Progress(Integer userId, Integer logDate) {
        this.userId = userId;
        this.logDate = logDate;
    }

    /*
    public User getUser() {
            return user;
    }
    
    public void setUser(User user) {
            this.user = user;
    }
    */
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLogDate() {
        return logDate;
    }

    public void setLogDate(Integer logDate) {
        this.logDate = logDate;
    }

    public Integer getCaloriesIn() {
        return caloriesIn;
    }

    public void setCaloriesIn(Integer caloriesIn) {
        this.caloriesIn = caloriesIn;
    }

    public Integer getCaloriesOut() {
        return caloriesOut;
    }

    public void setCaloriesOut(Integer caloriesOut) {
        this.caloriesOut = caloriesOut;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progress)) {
            return false;
        }
        Progress other = (Progress) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.Progress[ userId=" + userId + " ]";
    }

    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getDayString() {
        Date date = new Date((long) this.getLogDate()* 1000);
        Format format = new SimpleDateFormat("yyy-MM-dd");
        return format.format(date);
    }
    
}