/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juliabinger
 */
@Entity
@Table(name = "Progress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Progress.findMonth", query = "SELECT p FROM Progress p WHERE p.progressPK.userId = :userId AND p.progressPK.logDate BETWEEN :aMonthAgo AND :logDate"),
    @NamedQuery(name = "Progress.findWeek", query = "SELECT p FROM Progress p WHERE p.progressPK.userId = :userId AND p.progressPK.logDate BETWEEN :aWeekAgo AND :logDate"),
    @NamedQuery(name = "Progress.findAll", query = "SELECT p FROM Progress p"),
    @NamedQuery(name = "Progress.findByUserId", query = "SELECT p FROM Progress p WHERE p.progressPK.userId = :userId"),
    @NamedQuery(name = "Progress.findByLogDate", query = "SELECT p FROM Progress p WHERE p.progressPK.logDate = :logDate"),
    @NamedQuery(name = "Progress.findByCaloriesIn", query = "SELECT p FROM Progress p WHERE p.caloriesIn = :caloriesIn"),
    @NamedQuery(name = "Progress.findByCaloriesOut", query = "SELECT p FROM Progress p WHERE p.caloriesOut = :caloriesOut"),
    @NamedQuery(name = "Progress.findByMiles", query = "SELECT p FROM Progress p WHERE p.miles = :miles"),
    @NamedQuery(name = "Progress.findBySteps", query = "SELECT p FROM Progress p WHERE p.steps = :steps"),
    @NamedQuery(name = "Progress.findByWeight", query = "SELECT p FROM Progress p WHERE p.weight = :weight")})
public class Progress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgressPK progressPK;
    @Column(name = "CaloriesIn")
    private Integer caloriesIn;
    @Column(name = "CaloriesOut")
    private Integer caloriesOut;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Miles")
    private Double miles;
    @Column(name = "Steps")
    private Integer steps;
    @Column(name = "Weight")
    private Double weight;
    @JoinColumn(name = "UserId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Progress() {
    }

    public Progress(ProgressPK progressPK) {
        this.progressPK = progressPK;
    }

    public Progress(int userId, int logDate) {
        this.progressPK = new ProgressPK(userId, logDate);
    }

    public ProgressPK getProgressPK() {
        return progressPK;
    }

    public void setProgressPK(ProgressPK progressPK) {
        this.progressPK = progressPK;
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

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        user.setWeight((int)weight);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progressPK != null ? progressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progress)) {
            return false;
        }
        Progress other = (Progress) object;
        if ((this.progressPK == null && other.progressPK != null) || (this.progressPK != null && !this.progressPK.equals(other.progressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.Progress[ progressPK=" + progressPK + " ]";
    }
    
    public String getDayString() {
        Date date = new Date((long) this.getProgressPK().getLogDate()* 1000);
        Format format = new SimpleDateFormat("yyy-MM-dd");
        return format.format(date);
    }
}
