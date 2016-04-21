/*
 * Created by Jared Schwalbe on 2016.04.05  * 
 * Copyright © 2016 Osman Balci. All rights reserved. * 
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jared
 */
@Embeddable
public class UsersProgressPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pid")
    private int pid;

    public UsersProgressPK() {
    }

    public UsersProgressPK(int uid, int pid) {
        this.uid = uid;
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) uid;
        hash += (int) pid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersProgressPK)) {
            return false;
        }
        UsersProgressPK other = (UsersProgressPK) object;
        if (this.uid != other.uid) {
            return false;
        }
        if (this.pid != other.pid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entitypackage.UsersProgressPK[ uid=" + uid + ", pid=" + pid + " ]";
    }
    
}
