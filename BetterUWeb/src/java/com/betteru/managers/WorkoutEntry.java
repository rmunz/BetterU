/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betteru.managers;

/**
 *
 * @author Patron
 */
public class WorkoutEntry {
    
    private String name;
    private String Description;
    private String muscles_to_workout;
    private String equipment;

    public WorkoutEntry()
    {
        
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the muscles_to_workout
     */
    public String getMuscles_to_workout() {
        return muscles_to_workout;
    }

    /**
     * @param muscles_to_workout the muscles_to_workout to set
     */
    public void setMuscles_to_workout(String muscles_to_workout) {
        this.muscles_to_workout = muscles_to_workout;
    }

    /**
     * @return the equipment
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
    
}
