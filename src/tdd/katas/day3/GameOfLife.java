// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

public class GameOfLife {
    
    enum GAME_STATUS {
        STASIS, OSCILLATOR, SPACESHIP 
    }
    
    private GameBoard board;
    private GameBoard refreshBoard;

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public GameBoard getBoard() {
        return this.board;
    }
    
    public void playOneRoundAndSetBoard() {
        playOneRound();
        this.setBoard(refreshBoard);
    }

    private void playOneRound() {
        refreshBoard = new GameBoard(board.getWidth(), board.getHeight());
        iterateBoard(board.getWidth(), board.getHeight());
    }

    private void iterateBoard(int width, int height) {
        for (int x=0; x<width; x++) {
            for(int y=0; y<height;y++) {
                refreshCellOnBoard(x, y);
            }
        }
    }
    
    private void refreshCellOnBoard(int x, int y) {
        if (board.isToTurnCellOnOrOff(x, y)) refreshBoard.setCellOn(x, y);
    }

    public void play(int rounds) {    
        while (rounds > 0) {
            this.playOneRound();
            if (this.isInStasis())  {
                gameState = GAME_STATUS.STASIS;
                break;
            }
            rounds--;
            this.setBoard(refreshBoard);
        }
        
    }
    
    private GAME_STATUS gameState;

    private boolean isInStasis() {
        boolean state = true;
        for (int k=0; k<board.getWidth(); k++) {
            for(int l=0; l<board.getHeight();l++) {
                state = !(refreshBoard.isCellOn(k, l) ^ board.isCellOn(k, l));
            }
        }
        return state;
    }

    public GAME_STATUS getGameType() {
        return gameState;
    }
}