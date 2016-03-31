/*
 * Created by Ojas Mhetar on 2016.03.29  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.mycompany.betterurest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Challenges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Challenges.findAll", query = "SELECT c FROM Challenges c"),
    @NamedQuery(name = "Challenges.findByName", query = "SELECT c FROM Challenges c WHERE c.name = :name"),
    @NamedQuery(name = "Challenges.findByDescription", query = "SELECT c FROM Challenges c WHERE c.description = :description"),
    @NamedQuery(name = "Challenges.findByChallengeType", query = "SELECT c FROM Challenges c WHERE c.challengeType = :challengeType"),
    @NamedQuery(name = "Challenges.findByPointsAwarded", query = "SELECT c FROM Challenges c WHERE c.pointsAwarded = :pointsAwarded")})
public class Challenges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ChallengeType")
    private String challengeType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PointsAwarded")
    private int pointsAwarded;

    public Challenges() {
    }

    public Challenges(String name) {
        this.name = name;
    }

    public Challenges(String name, String description, String challengeType, int pointsAwarded) {
        this.name = name;
        this.description = description;
        this.challengeType = challengeType;
        this.pointsAwarded = pointsAwarded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(String challengeType) {
        this.challengeType = challengeType;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(int pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Challenges)) {
            return false;
        }
        Challenges other = (Challenges) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.betterurest.Challenges[ name=" + name + " ]";
    }
    
}
