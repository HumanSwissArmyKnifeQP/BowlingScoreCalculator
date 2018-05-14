package com.quocphaminc.bowlingscorecalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Computes the total bowling game score for a single player.
 */
public class BowlingGame {
    
    public static final int DEFAULT_PIN_COUNT = 10;
    
    public static final int DEFAULT_FRAME_COUNT = 10;
           
    public static final int DEFAULT_ROLLS_PER_FRAME = 2;
      
    private final List<Frame> frames;
      
    public BowlingGame(){
        frames = new ArrayList<>();
    }
    
    public List<Frame> getFrames(){
        return frames;
    }
    
    /**
     * Parses a list of rolls into the appropriate frame.
     * @param rolls List of pins knocked down during each roll.
     */
    public void addRolls(List<Integer> rolls){
        
        int frameIdx = 1;
        Frame currentFrame = new Frame();
        frames.add(currentFrame);
        
        while(!rolls.isEmpty()){
            
            // Add bonus in frames 1-9.
            if(frameIdx < DEFAULT_FRAME_COUNT && 
                    currentFrame.getTotal() == DEFAULT_PIN_COUNT){
                
                if(currentFrame.getNumRolls() == 1){
                    currentFrame.addBonus(rolls.get(0) + rolls.get(1));
                } else {
                    currentFrame.addBonus(rolls.get(0));
                }
            }
            
            // Open a new frame
            if(frameIdx < DEFAULT_FRAME_COUNT && 
                    (currentFrame.getNumRolls() == DEFAULT_ROLLS_PER_FRAME
                    || currentFrame.getTotal() >= DEFAULT_PIN_COUNT)){
                
                currentFrame = new Frame();
                frames.add(currentFrame);
                frameIdx++;
            }
            
            currentFrame.addRoll(rolls.remove(0));
        }
    }
    
    public int getTotal(){
        return frames.stream().mapToInt(o -> o.getTotal()).sum();
    }
}
