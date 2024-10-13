package org.example;

public class Cell{
    private int cellNearbyMines;
    private boolean isMine;
    private boolean isRevealed;

    public Cell(){
        this.cellNearbyMines = 0;
        this.isMine = false;
        this.isRevealed = false;
    }

    public void setMine(boolean mine){
        isMine = mine;
    }

    public void setCellNearbyMines(int cellNearbyMines){
        this.cellNearbyMines = cellNearbyMines;
    }

    public int getCellNearbyMines(){
        return cellNearbyMines;
    }

    public boolean isMine(){
        return isMine;
    }

    public boolean isRevealed(){
        return isRevealed;
    }

    public void reveal(){
        isRevealed = true;
    }
}

