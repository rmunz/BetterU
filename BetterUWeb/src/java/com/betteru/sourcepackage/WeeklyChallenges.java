/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.sourcepackage;

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
 * @author Tim
 */
@Entity
@Table(name = "WeeklyChallenges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WeeklyChallenges.findAll", query = "SELECT w FROM WeeklyChallenges w"),
    @NamedQuery(name = "WeeklyChallenges.findByChallengeName", query = "SELECT w FROM WeeklyChallenges w WHERE w.challengeName = :challengeName"),
    @NamedQuery(name = "WeeklyChallenges.findByDescription", query = "SELECT w FROM WeeklyChallenges w WHERE w.description = :description"),
    @NamedQuery(name = "WeeklyChallenges.findByPointsAwarded", query = "SELECT w FROM WeeklyChallenges w WHERE w.pointsAwarded = :pointsAwarded"),
    @NamedQuery(name = "WeeklyChallenges.findByInd", query = "SELECT w FROM WeeklyChallenges w WHERE w.ind = :ind")})
public class WeeklyChallenges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ChallengeName")
    private String challengeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PointsAwarded")
    private int pointsAwarded;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ind")
    private int ind;

    public WeeklyChallenges() {
    }

    public WeeklyChallenges(String challengeName) {
        this.challengeName = challengeName;
    }

    public WeeklyChallenges(String challengeName, String description, int pointsAwarded, int ind) {
        this.challengeName = challengeName;
        this.description = description;
        this.pointsAwarded = pointsAwarded;
        this.ind = ind;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(int pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (challengeName != null ? challengeName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeeklyChallenges)) {
            return false;
        }
        WeeklyChallenges other = (WeeklyChallenges) object;
        if ((this.challengeName == null && other.challengeName != null) || (this.challengeName != null && !this.challengeName.equals(other.challengeName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.WeeklyChallenges[ challengeName=" + challengeName + " ]";
    }
    
}
