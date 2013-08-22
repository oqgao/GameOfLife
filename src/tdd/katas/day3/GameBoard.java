// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

public class GameBoard {
    
    private static final int THREE_NEIGHBORS = 3;
    private static final int TWO_NEIGHBORS = 2;
    private static final int INNER_CELL_NEIGHBORS = 8;
    private static final int EDGE_CELL_NEIGHBORS = 5;
    private static final int CORNER_CELL_NEIGHBORS = 3;
    private int width;
    private int height;
    
    private Cell[][] cells;
    
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
                
            }
        }
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Cell[][] getCells() {
        return cells;
    }
    
    public void setCellOn(int x, int y) {
        cells[x][y].on();
    }
    
    public void setCellOff(int x, int y) {
        cells[x][y].off();
    }
    
    public boolean isCellOn(int x, int y) {
        return cells[x][y].isOn();
    }
    
    public int getCellNeighbors(int x, int y) {
        int neighbors = 0;
        
        if (isCornerCell(x, y)) {
            neighbors = CORNER_CELL_NEIGHBORS;
        }
        else if (isEdgeCell(x, y)) {
            neighbors = EDGE_CELL_NEIGHBORS;
        }
        else {
            neighbors = INNER_CELL_NEIGHBORS;
        }
        
        return neighbors;
    }
    
    private boolean isEdgeCell(int x, int y) {
        return (x == 0 || y == 0) || (x == cells.length - 1 || y == cells.length - 1);
    }
    
    private boolean isCornerCell(int x, int y) {
        return (x == 0 || x == cells.length - 1) && (y == 0 || y == cells.length - 1);
    }
    
    public int getLiveCellNeighbors(int x, int y) {
        return countTopRowLiveNeighbors(x, y) + countThisRowLiveNeighbors(x, y) + 
                        countBottomRowLiveNeighbors(x, y);
    }
    
    private int countTopRowLiveNeighbors(int x, int y) {
        return getLiveCount(x-1, y-1)+getLiveCount(x-1, y)+getLiveCount(x-1, y+1);
    }
    
    private int countThisRowLiveNeighbors(int x, int y) {
        return getLiveCount(x, y-1)+getLiveCount(x, y+1);
    }

    private int countBottomRowLiveNeighbors(int x, int y) {
        return getLiveCount(x+1, y-1)+getLiveCount(x+1, y)+getLiveCount(x+1, y+1);
    }

    private int getLiveCount(int x, int y) {
        if (x<0 || y<0 ||x>=cells.length||y>=cells.length) return 0;
        return (this.isCellOn(x, y)?1:0);
    }

    public boolean isToTurnCellOnOrOff(int x, int y) {
        int liveCellNeighbors = getLiveCellNeighbors(x,y);
        boolean isToTurnCellOnOrOff = false;
        if(cellDiesOfUnderPopulation(liveCellNeighbors) || cellDiesOfOverPopulation(liveCellNeighbors)) {
            isToTurnCellOnOrOff = false;
        } 
        else if(cellLivesOverReproduction(liveCellNeighbors)) {
            isToTurnCellOnOrOff = true;
        }else {
            isToTurnCellOnOrOff = this.isCellOn(x, y);
        }
        return isToTurnCellOnOrOff;
    }

    private boolean cellLivesOverReproduction(int liveCellNeighbors) {
        return liveCellNeighbors==THREE_NEIGHBORS;
    }

    private boolean cellDiesOfOverPopulation(int liveCellNeighbors) {
        return liveCellNeighbors>THREE_NEIGHBORS;
    }

    private boolean cellDiesOfUnderPopulation(int liveCellNeighbors) {
        return liveCellNeighbors<TWO_NEIGHBORS;
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    } 
    
    public void printBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(cells[i][j].isOn())
                    System.out.print("ON ");
                else
                    System.out.print("OF ");                
            }
            System.out.println();
        }
    }
}
