package com.quocphaminc.bowlingscorecalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Runner class for the calculator
 */
public class BowlingCalculator {
       
    public static void main(String[] args){
        System.out.println("Ten-pin Bowling Score Calculator");
        System.out.println("--------------------------------");
        System.out.println("Please enter the results of each bowling roll "
                + "separated by a comma.");
        System.out.println("Valid values are [0-10] which represent the pins "
                + "knocked down at each throw.");
        System.out.println("E.g. a perfect game would entered be as follows:");
        System.out.println("10,10,10,10,10,10,10,10,10,10,10,10");
        
        Scanner sc = new Scanner(System.in);
        String[] bowlingLine = sc.nextLine().split(",");
        
        List<Integer> rolls = new ArrayList<>();
        for(String s : bowlingLine){
            rolls.add(Integer.parseInt(s.trim()));
        }
        
        BowlingGame game = new BowlingGame();
        game.addRolls(rolls);
        
        System.out.println("Total Score: " + game.getTotal());
    }
}
