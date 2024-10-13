package org.example;

import java.util.Random;

public class Board{
    private static final int SIZE = 10;
    private static final int MINES = 10;
    private final Cell[][] board = new Cell[SIZE][SIZE];
    private static Board instance;

    private Board(){
        initializeBoard();
        placeMines();
        calculateNearbyMines();
    }

    public static Board getInstance(){
        if (instance == null){
            instance = new Board();
        }
        return instance;
    }

    public static void killInstance(){
        instance = null;
    }

    public Cell getCell(int row, int col){
        return board[row][col];
    }

    public int getBoardSize(){
        return SIZE;
    }

    public void initializeBoard(){
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                board[row][col] = new Cell();
            }
        }
    }

    public void placeMines(){
        Random random = new Random();
        for (int placedMines = 0; placedMines < MINES; placedMines++){
            board[random.nextInt(SIZE)][random.nextInt(SIZE)].setMine(true);
        }
    }

    public void calculateNearbyMines(){
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                if (!board[row][col].isMine()){
                    board[row][col].setCellNearbyMines(countCellNearbyMines(row, col));
                }
            }
        }
    }

    public int countCellNearbyMines(int row, int col){
        int count = 0;
        for (int nextRow = -1; nextRow <= 1; nextRow++){
            for (int nextCol = -1; nextCol <= 1; nextCol++){
                int nearbyRow = row + nextRow;
                int nearbyCol = col + nextCol;
                if (nearbyRow >= 0 && nearbyRow < SIZE &&
                        nearbyCol >= 0 && nearbyCol < SIZE &&
                        board[nearbyRow][nearbyCol].isMine()){
                            count++;
                }
            }
        }
        return count;
    }
}
