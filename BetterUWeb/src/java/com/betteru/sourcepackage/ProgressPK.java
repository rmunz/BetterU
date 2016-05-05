/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author juliabinger
 */
@Embeddable
public class ProgressPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LogDate")
    private int logDate;

    public ProgressPK() {
    }

    public ProgressPK(int userId, int logDate) {
        this.userId = userId;
        this.logDate = logDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLogDate() {
        return logDate;
    }

    public void setLogDate(int logDate) {
        this.logDate = logDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) logDate;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgressPK)) {
            return false;
        }
        ProgressPK other = (ProgressPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.logDate != other.logDate) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.ProgressPK[ userId=" + userId + ", logDate=" + logDate + " ]";
    }
    
}
