
package com.betteru.managers;

/**
 *
 * @author Duke Forsyth
 */
public class FoodEntry {
    private int offset;
    private String foodGroup;
    private String name;
    private String ndbno;
    private int kcal;
    private int protien;
    private int carbs;
    private int fat;

    
    public FoodEntry(int offset,String foodGroup,String name,String ndbno)
    {
        this.offset = offset;
        this.foodGroup = foodGroup;
        this.name = name;
        this.ndbno = ndbno;
        this.kcal = 0;
        this.protien =0;
        this.carbs = 0;
        this.fat = 0;
        
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the foodGroup
     */
    public String getFoodGroup() {
        return foodGroup;
    }

    /**
     * @param foodGroup the foodGroup to set
     */
    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
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
     * @return the ndbno
     */
    public String getNdbno() {
        return ndbno;
    }

    /**
     * @param ndbno the ndbno to set
     */
    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

    /**
     * @return the kcal
     */
    public int getKcal() {
        return kcal;
    }

    /**
     * @param kcal the kcal to set
     */
    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    /**
     * @return the protien
     */
    public int getProtien() {
        return protien;
    }

    /**
     * @param protien the protien to set
     */
    public void setProtien(int protien) {
        this.protien = protien;
    }

    /**
     * @return the carbs
     */
    public int getCarbs() {
        return carbs;
    }

    /**
     * @param carbs the carbs to set
     */
    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    /**
     * @return the fat
     */
    public int getFat() {
        return fat;
    }

    /**
     * @param fat the fat to set
     */
    public void setFat(int fat) {
        this.fat = fat;
    }
    
   
}
