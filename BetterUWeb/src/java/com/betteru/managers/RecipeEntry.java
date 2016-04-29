/*
 * Created by Ojas Mhetar on 2016.04.29  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

/**
 *
 * @author ojmhetar
 */
public class RecipeEntry {
    
    private String name; 
    private String recipeId; 
    private int calories; 
    
    public RecipeEntry(String name, int calories) {
        this.name = name; 
        this.calories = calories; 
    }
    
    public RecipeEntry(String recipeId, String name) {
        this.name = name;
        this.recipeId = recipeId; 
        this.calories = 0; 
    }
    
    public String getRecipeId(){
        return recipeId; 
    }
    
    public void setRecipeId(String recipeId){
        this.recipeId = recipeId; 
    }
    
    public String getName() {
        return name; 
    }
    
    public void setName(String name){
        this.name = name; 
    }
    
    public int getCalories() {
        return calories;  
    }
    
    public void setCalories(int calories){
        this.calories = calories; 
    }
    
    
}