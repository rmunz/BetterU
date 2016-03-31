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
@Table(name = "Goal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g"),
    @NamedQuery(name = "Goal.findById", query = "SELECT g FROM Goal g WHERE g.id = :id"),
    @NamedQuery(name = "Goal.findByGoalWeight", query = "SELECT g FROM Goal g WHERE g.goalWeight = :goalWeight"),
    @NamedQuery(name = "Goal.findByActivityGoal", query = "SELECT g FROM Goal g WHERE g.activityGoal = :activityGoal"),
    @NamedQuery(name = "Goal.findByMuscleGroup", query = "SELECT g FROM Goal g WHERE g.muscleGroup = :muscleGroup")})
public class Goal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GoalWeight")
    private int goalWeight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ActivityGoal")
    private String activityGoal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MuscleGroup")
    private String muscleGroup;

    public Goal() {
    }

    public Goal(Integer id) {
        this.id = id;
    }

    public Goal(Integer id, int goalWeight, String activityGoal, String muscleGroup) {
        this.id = id;
        this.goalWeight = goalWeight;
        this.activityGoal = activityGoal;
        this.muscleGroup = muscleGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
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
        if (!(object instanceof Goal)) {
            return false;
        }
        Goal other = (Goal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.betterurest.Goal[ id=" + id + " ]";
    }
    
}
