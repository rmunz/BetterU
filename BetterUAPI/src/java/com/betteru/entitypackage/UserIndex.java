/*
 * Created by Timothy Street on 2016.04.23  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.entitypackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tim
 */
@Entity
@Table(name = "UserIndex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserIndex.findAll", query = "SELECT u FROM UserIndex u"),
    @NamedQuery(name = "UserIndex.findByUserID", query = "SELECT u FROM UserIndex u WHERE u.userIndexPK.userID = :userID"),
    @NamedQuery(name = "UserIndex.findByChallengeType", query = "SELECT u FROM UserIndex u WHERE u.userIndexPK.challengeType = :challengeType"),
    @NamedQuery(name = "UserIndex.findByInd", query = "SELECT u FROM UserIndex u WHERE u.ind = :ind")})
public class UserIndex implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserIndexPK userIndexPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ind")
    private int ind;

    public UserIndex() {
    }

    public UserIndex(UserIndexPK userIndexPK) {
        this.userIndexPK = userIndexPK;
    }

    public UserIndex(UserIndexPK userIndexPK, int ind) {
        this.userIndexPK = userIndexPK;
        this.ind = ind;
    }

    public UserIndex(int userID, String challengeType) {
        this.userIndexPK = new UserIndexPK(userID, challengeType);
    }

    public UserIndexPK getUserIndexPK() {
        return userIndexPK;
    }

    public void setUserIndexPK(UserIndexPK userIndexPK) {
        this.userIndexPK = userIndexPK;
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
        hash += (userIndexPK != null ? userIndexPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserIndex)) {
            return false;
        }
        UserIndex other = (UserIndex) object;
        if ((this.userIndexPK == null && other.userIndexPK != null) || (this.userIndexPK != null && !this.userIndexPK.equals(other.userIndexPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.entitypackage.service.UserIndex[ userIndexPK=" + userIndexPK + " ]";
    }
    
}
