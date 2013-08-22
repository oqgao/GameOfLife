// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdd.katas.day3.GameOfLife.GAME_STATUS;

public class GameOfLifeTest {
    
    private GameOfLife game = null;
    
    @Before
    public void setUp() {
        game = new GameOfLife();
        GameBoard board = new GameBoard(5, 5);
        setBoardOne(board);
        game.setBoard(board);
    }
    
    private void setBoardOne(GameBoard board) {
        board.setCellOn(0, 0);
        board.setCellOn(0, 1);
        board.setCellOn(1, 2);
        
        board.setCellOn(2, 3);
        
        board.setCellOn(3, 2);
        board.setCellOn(3, 3);
        board.setCellOn(3, 4);
    }
    
    @Test
    public void playOneRound() {
        game.playOneRoundAndSetBoard();
        assertFalse("Cell should be set to Off!", game.getBoard().isCellOn(0, 0));
        assertTrue("Cell should be set to On!", game.getBoard().isCellOn(0, 1));
        assertFalse("Cell should be set to Off!", game.getBoard().isCellOn(0, 2));
        assertFalse("Cell should be set to Off!", game.getBoard().isCellOn(1, 0));
        assertTrue("Cell should be set to On!", game.getBoard().isCellOn(1, 2));
        assertFalse("Cell should be set to Off!", game.getBoard().isCellOn(1, 0));
        assertFalse("Cell should be set to Off!", game.getBoard().isCellOn(2, 3));
    }
    
    @Test
    public void playStasis() {
        GameBoard board = new GameBoard(5,5);
        board.setCellOn(1,1);
        board.setCellOn(1,2);
        board.setCellOn(2,1);
        board.setCellOn(2,3);
        board.setCellOn(3,2);
        
        game.setBoard(board);
        game.play(5);
        
        assertEquals(GAME_STATUS.STASIS, game.getGameType());      
    }
}
