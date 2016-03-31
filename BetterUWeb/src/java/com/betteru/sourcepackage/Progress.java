/*
 * Created by Ojas Mhetar on 2016.03.30  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ojmhetar
 */
@Entity
@Table(name = "Progress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Progress.findAll", query = "SELECT p FROM Progress p"),
    @NamedQuery(name = "Progress.findById", query = "SELECT p FROM Progress p WHERE p.id = :id"),
    @NamedQuery(name = "Progress.findByDay", query = "SELECT p FROM Progress p WHERE p.day = :day"),
    @NamedQuery(name = "Progress.findByCaloriesIn", query = "SELECT p FROM Progress p WHERE p.caloriesIn = :caloriesIn"),
    @NamedQuery(name = "Progress.findByCaloriesOut", query = "SELECT p FROM Progress p WHERE p.caloriesOut = :caloriesOut"),
    @NamedQuery(name = "Progress.findByWeight", query = "SELECT p FROM Progress p WHERE p.weight = :weight"),
    @NamedQuery(name = "Progress.findByMiles", query = "SELECT p FROM Progress p WHERE p.miles = :miles"),
    @NamedQuery(name = "Progress.findBySteps", query = "SELECT p FROM Progress p WHERE p.steps = :steps")})
public class Progress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Day")
    @Temporal(TemporalType.DATE)
    private Date day;
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

    public Progress() {
    }

    public Progress(Integer id) {
        this.id = id;
    }

    public Progress(Integer id, Date day) {
        this.id = id;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progress)) {
            return false;
        }
        Progress other = (Progress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.Progress[ id=" + id + " ]";
    }
    
}
