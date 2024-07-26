package org.example;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String [] args){
        for (int i = 0; i < new Random().nextInt(); i++)
        {
            System.out.println("hi");

        }
    }
}

/* Feature: Add Products

  Scenario : Add Available Product
Given this product is available<>
And this product has items
When enter or add is pressed
Then the new product should be added
*/



