// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

public class Cell {
    
    private boolean cellState; 

    public void on() {
        cellState = true;
    }

    public boolean isOn() {
        return cellState;
    }

    public void off() {
        cellState = false;
    }

    public boolean isOff() {
        return cellState;
    }
    
}
