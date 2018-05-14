package com.quocphaminc.bowlingscorecalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BowlingGameNGTest {
    
    public BowlingGameNGTest() {
    }
    
    /**
     * Test of parseRolls method with all strikes. Check each frame has correct 
     * number of points and rolls.
     */
    @Test
    public void testParseAllStrikes(){
        int numFrames = 10;
        int numStrikes = numFrames + 2; // 2 bonus for last frame
        int strikeValue = BowlingGame.DEFAULT_PIN_COUNT * 3;

        List<Integer> rolls = new ArrayList<>();
        for(int i = 0; i < numStrikes; i++){
            rolls.add(BowlingGame.DEFAULT_PIN_COUNT);
        }
               
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(frames.size(), numFrames, "Incorrect number of frames.");
        
        int frameIdx = 1;
        for(Frame frame : frames){
            Assert.assertEquals(frame.getTotal(), strikeValue, "Incorrect point total.");
            
            int expectedRolls = (frameIdx == 10) ? 3 : 1; 
            Assert.assertEquals(frame.getNumRolls(), expectedRolls, "Incorrect number of rolls.");
            frameIdx++;
        }
        
    }
    
    /**
     * Test of parseRolls method with no strikes. Check each frame has correct 
     * number of points and rolls.
     */
    @Test
    public void testParseAllOpenFrames(){
        int numFrames = 10;
        int firstRoll = 3;
        int secondRoll = 6;
        
        List<Integer> rolls = new ArrayList<>();
        for(int i = 0; i < numFrames; i++){
            rolls.add(firstRoll);
            rolls.add(secondRoll);
        }
        
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(frames.size(), numFrames, "Incorrect number of frames.");
        
        for(Frame frame : frames){
            Assert.assertEquals(frame.getTotal(), firstRoll + secondRoll, "Incorrect point total.");
            Assert.assertEquals(frame.getNumRolls(), 2, "Incorrect number of rolls.");
        }
        
    }
    
    /**
     * Test of parseRolls method, with different Frame types.
     */
    @Test
    public void testParseMixedSFrames() {
        List<Integer> rolls = new ArrayList<>(
                Arrays.asList(BowlingGame.DEFAULT_PIN_COUNT, 3, 7, 4, 5));
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(frames.size(), 3, "Incorrect number of frames.");
        
        Frame firstFrame = frames.get(0);
        Assert.assertEquals(firstFrame.getTotal(), 20, "Incorrect point total.");
        Assert.assertEquals(firstFrame.getNumRolls(), 1, "Incorrect number of rolls.");
        
        Frame secondFrame = frames.get(1);
        Assert.assertEquals(secondFrame.getTotal(), 14, "Incorrect point total.");
        Assert.assertEquals(secondFrame.getNumRolls(), 2, "Incorrect number of rolls.");
        
        Frame thirdFrame = frames.get(2);
        Assert.assertEquals(thirdFrame.getTotal(), 9, "Incorrect point total.");
        Assert.assertEquals(thirdFrame.getNumRolls(), 2, "Incorrect number of rolls.");
    }

    /**
     * Test of computeTotal for a perfect bowling game.
     */
    @Test
    public void testComputeTotalAllStrikes() {
        int numFrames = 10;
        int numStrikes = 10 + 2; // 2 bonus for last frame
        int strikeValue = BowlingGame.DEFAULT_PIN_COUNT * 3;
        
        List<Integer> rolls = new ArrayList<>();
        for(int i = 0; i < numStrikes; i++){
            rolls.add(BowlingGame.DEFAULT_PIN_COUNT);
        }
        
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        Assert.assertEquals(game.getTotal(), strikeValue * numFrames, "Incorrect point total.");
    }
    
    /**
     * Test of computeTotal for all spares with alternating roll values.
     */
    @Test
    public void testComputeAllSpares() {
        int numFrames = 10;
        int firstRoll = 3;
        int secondRoll = 7;
        
        // Alternate between firstRoll and secondRoll
        int spareValue = firstRoll + secondRoll + firstRoll;
        List<Integer> rolls = new ArrayList<>();
        for(int i = 0; i < numFrames; i++){
            rolls.add(firstRoll);
            rolls.add(secondRoll);
        }

        // Add one bonus for last frame.
        rolls.add(firstRoll);
        
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        Assert.assertEquals(game.getTotal(), spareValue * numFrames, "Incorrect point total.");
    }
    
    
    /**
     * Test of computeTotal for all open frames with alternating roll values.
     */
    @Test
    public void testComputeAllOpenFrames(){
        int numFrames = 10;
        int firstRoll = 4;
        int secondRoll = 5;
        
        
        // Alternate between firstRoll and secondRoll
        int openFrameValue = firstRoll + secondRoll;
        List<Integer> rolls = new ArrayList<>();
        for(int i = 0; i < numFrames; i++){
            rolls.add(firstRoll);
            rolls.add(secondRoll);
        }
        
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        Assert.assertEquals(game.getTotal(), openFrameValue * numFrames, "Incorrect point total.");
    }
    
}
