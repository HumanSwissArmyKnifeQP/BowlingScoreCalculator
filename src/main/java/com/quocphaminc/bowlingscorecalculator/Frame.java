package com.quocphaminc.bowlingscorecalculator;

public class Frame {

    private int numRolls = 0;
    private int points = 0;
    
    public Frame(){
    }
    
    /**
     * Adds the given roll value to this Frame.
     * 
     * @param pinsHit The number of pins hit for the roll being add.
     */
    public void addRoll(int pinsHit){
        points += pinsHit;
        numRolls++;
    }
    
    public void addBonus(int bonus){
        points += bonus;
    }
       
    /**
     * Returns the number of rolls in this Frame.
     * 
     * @return 
     */
    public int getNumRolls(){
        return numRolls;
    }
    
    /**
     * Returns the total number of pins hit for this Frame.
     * 
     * @return 
     */
    public int getTotal(){
        return points;
    }
}
