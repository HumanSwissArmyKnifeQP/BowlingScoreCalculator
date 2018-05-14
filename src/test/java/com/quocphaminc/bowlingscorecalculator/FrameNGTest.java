package com.quocphaminc.bowlingscorecalculator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameNGTest {
    
    public static final int INITIAL_ROLLS = 0;
    public static final int INITIAL_POINTS = 0; 
    public static final String BAD_ATTRIBUTE_MESSAGE 
            = "Unexpected value for'%s'.";
    
    public FrameNGTest() {
    }
    
    /**
     * Test initial state of newly created Frame.
     */
    @Test
    public void testCreateFrame(){
        Frame frame = new Frame();
        
        Assert.assertEquals(frame.getNumRolls(), INITIAL_ROLLS, 
                String.format(BAD_ATTRIBUTE_MESSAGE, "rolls"));
        Assert.assertEquals(frame.getTotal(), INITIAL_POINTS,
                String.format(BAD_ATTRIBUTE_MESSAGE, "points"));
    }

    /**
     * Test addRolls increases points and increments number of rolls.
     */
    @Test
    public void testAddRoll() {
        int firstRoll = 3;
        int secondRoll = 5;
        
        Frame frame = new Frame();
        frame.addRoll(firstRoll);
        Assert.assertEquals(frame.getNumRolls(), 1,
                String.format(BAD_ATTRIBUTE_MESSAGE, "rolls"));
        Assert.assertEquals(frame.getTotal(), firstRoll,
                String.format(BAD_ATTRIBUTE_MESSAGE, "points"));
        
        frame.addRoll(secondRoll);
        Assert.assertEquals(frame.getNumRolls(), 2,
                String.format(BAD_ATTRIBUTE_MESSAGE, "rolls"));
        Assert.assertEquals(frame.getTotal(), firstRoll + secondRoll,
                String.format(BAD_ATTRIBUTE_MESSAGE, "points"));
    }

    /**
     * Test addBonus increases points but NOT number of rolls.
     */
    @Test
    public void testAddBonus() {
        int firstBonus = 3;
        int secondBonus = 5;
        
        Frame frame = new Frame();
        frame.addBonus(firstBonus);
        Assert.assertEquals(frame.getNumRolls(), INITIAL_ROLLS,
                String.format(BAD_ATTRIBUTE_MESSAGE, "rolls"));
        Assert.assertEquals(frame.getTotal(), firstBonus,
                String.format(BAD_ATTRIBUTE_MESSAGE, "points"));
        
        frame.addBonus(secondBonus);
        Assert.assertEquals(frame.getNumRolls(), INITIAL_ROLLS,
                String.format(BAD_ATTRIBUTE_MESSAGE, "rolls"));
        Assert.assertEquals(frame.getTotal(), firstBonus + secondBonus,
                String.format(BAD_ATTRIBUTE_MESSAGE, "points"));
    }  
}
