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
    
    private Integer calories; 
    private Integer fat;
    private Integer protein; 
    private Integer carbs;
    
    public RecipeEntry(String name, Integer calories) {
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
    
    public Integer getCalories() {
        return calories;  
    }
    
    public void setCalories(Integer calories){
        this.calories = calories; 
    }
    
    public Integer getFat() {
        return fat;  
    }
    
    public void setFat(Integer fat){
        this.fat = fat; 
    }
    
    public Integer getCarbs() {
        return carbs;  
    }
    
    public void setCarbs(Integer carbs){
        this.carbs = carbs; 
    }
    
    public Integer getProtein() {
        return protein;  
    }
    
    public void setProtein(Integer protein){
        this.protein = protein; 
    }
    
}