// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
        
    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell();
    }
    
    @Test
    public void ensureCellNotAliveByDefault() {
        assertFalse("Cell should be not Alive now!", cell.isOn());
    }
    
    @Test
    public void isCellOn() {
        cell.on();
        assertTrue("Cell should be On now!", cell.isOn());
    }
    
    @Test
    public void isCellOff() {
        cell.off();
        assertFalse("Cell should be Off now!", cell.isOff());
    }
    
}
