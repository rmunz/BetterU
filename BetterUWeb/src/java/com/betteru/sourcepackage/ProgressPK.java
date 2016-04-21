/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betteru.sourcepackage;
import java.io.Serializable;

/**
 *
 * @author juliabinger
 */
public class ProgressPK implements Serializable {
 
 
    private Integer userId;
    private Integer logDate;
 
    public ProgressPK(){
        // Your class must have a no-arq constructor
    }
 
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProgressPK){
            ProgressPK progressPK = (ProgressPK) obj;
 
            if(!progressPK.getId().equals(userId)){
                return false;
            }
 
            if(!progressPK.getDay().equals(logDate)){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return userId.hashCode() + logDate.hashCode();
    }
 
    public Integer getId() {
        return userId;
    }
 
    public void setId(Integer userId) {
        this.userId = userId;
    }
 
    public Integer getDay() {
        return logDate;
    }
 
    public void setDay(Integer logDate) {
        this.logDate = logDate;
    }
}

