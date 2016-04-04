/*
 * Created by Ojas Mhetar on 2016.04.03  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.sourcepackage;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ojmhetar
 */
@Entity
@Table(name = "UsersProgress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersProgress.findAll", query = "SELECT u FROM UsersProgress u"),
    @NamedQuery(name = "UsersProgress.findByUid", query = "SELECT u FROM UsersProgress u WHERE u.usersProgressPK.uid = :uid"),
    @NamedQuery(name = "UsersProgress.findByPid", query = "SELECT u FROM UsersProgress u WHERE u.usersProgressPK.pid = :pid")})
public class UsersProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersProgressPK usersProgressPK;

    public UsersProgress() {
    }

    public UsersProgress(UsersProgressPK usersProgressPK) {
        this.usersProgressPK = usersProgressPK;
    }

    public UsersProgress(int uid, int pid) {
        this.usersProgressPK = new UsersProgressPK(uid, pid);
    }

    public UsersProgressPK getUsersProgressPK() {
        return usersProgressPK;
    }

    public void setUsersProgressPK(UsersProgressPK usersProgressPK) {
        this.usersProgressPK = usersProgressPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersProgressPK != null ? usersProgressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersProgress)) {
            return false;
        }
        UsersProgress other = (UsersProgress) object;
        if ((this.usersProgressPK == null && other.usersProgressPK != null) || (this.usersProgressPK != null && !this.usersProgressPK.equals(other.usersProgressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.betteru.sourcepackage.UsersProgress[ usersProgressPK=" + usersProgressPK + " ]";
    }
    
}
