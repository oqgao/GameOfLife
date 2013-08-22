// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
    
    private GameBoard board;
    
    @Before
    public void setUp() {
        board = new GameBoard(5,5);  
        setBoardOne();
    }
    
    private void setBoardOne() {
        board.setCellOn(0, 0);
        board.setCellOn(0, 1);
        board.setCellOn(1, 2);
        
        board.setCellOn(2, 3);
        
        board.setCellOn(3, 2);
        board.setCellOn(3, 3);
        board.setCellOn(3, 4); 
    }
    
    @Test
    public void checkBoardExist() {
        assertEquals(5, board.getHeight());
        assertEquals(5, board.getWidth());        
        assertNotNull(board.getCells());
    }
    
    @Test
    public void checkCellAlive() {
        assertFalse("Cell should be Off!", board.isCellOn(0,4));
        board.setCellOn(0,4);
        assertTrue("Cell should be On!", board.isCellOn(0,4));        
    }

    
    @Test
    public void cornerCellNeighbors() {
        int neighbors = board.getCellNeighbors(0, 0);
        assertEquals(3, neighbors);
        neighbors = board.getCellNeighbors(0, board.getHeight()-1);
        assertEquals(3, neighbors);
        neighbors = board.getCellNeighbors(board.getWidth()-1, 0);
        assertEquals(3, neighbors);
        neighbors = board.getCellNeighbors(board.getWidth()-1, board.getHeight()-1);
        assertEquals(3, neighbors);
    }

    @Test
    public void borderCellNeighbors() {
        int neighbors = board.getCellNeighbors(0, 1);
        assertEquals(5, neighbors);
        neighbors = board.getCellNeighbors(board.getWidth()-1, 1);
        assertEquals(5, neighbors);
    }

    @Test
    public void innerCellNeighbors() {
        int neighbors = board.getCellNeighbors(1, 1);
        assertEquals(8, neighbors);
    }
    
    @Test
    public void liveCellNeighbor() {
        int liveNeighbors = board.getLiveCellNeighbors(0, 0);
        assertEquals(1, liveNeighbors);
        board.setCellOn(0, 1);
        assertEquals(1, board.getLiveCellNeighbors(0, 0));
    }
    

    
    @Test
    public void cellDiesOfUnderPopulation() {
        int x=0,y=3;
        assertFalse("Cell should be set to Off!",board.isToTurnCellOnOrOff(x,y));
    }
   
    @Test
    public void cellLivesToNextGeneration() {
        int x=1,y=1;
        assertTrue("Cell should be set to On!",board.isToTurnCellOnOrOff(x,y));
    }  
    
    @Test
    public void cellDiesOfOverPopulation() {  
        int x=2,y=3;
        assertFalse("Cell should be set to Off!",board.isToTurnCellOnOrOff(x,y));
    }
    
    @Test
    public void cellBecomesAliveOfReproduction() {  
        int x=2,y=4;    
        assertTrue("Cell should be set to On!",board.isToTurnCellOnOrOff(x,y));
    }
    
    @Test
    public void cellRemainsUnchanged() {  
        int x=0,y=2;    
        assertFalse("Cell should be set to Off!",board.isToTurnCellOnOrOff(x,y));
    }
    
}
