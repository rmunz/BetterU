/*
 * Created by Timothy Street on 2016.05.02  * 
 * Copyright © 2016 Timothy Street. All rights reserved. * 
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tim
 */
@Embeddable
public class UserIndexPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "UserID")
    private int userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ChallengeType")
    private String challengeType;

    public UserIndexPK() {
    }

    public UserIndexPK(int userID, String challengeType) {
        this.userID = userID;
        this.challengeType = challengeType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(String challengeType) {
        this.challengeType = challengeType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userID;
        hash += (challengeType != null ? challengeType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserIndexPK)) {
            return false;
        }
        UserIndexPK other = (UserIndexPK) object;
        if (this.userID != other.userID) {
            return false;
        }
        if ((this.challengeType == null && other.challengeType != null) || (this.challengeType != null && !this.challengeType.equals(other.challengeType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.UserIndexPK[ userID=" + userID + ", challengeType=" + challengeType + " ]";
    }
    
}
