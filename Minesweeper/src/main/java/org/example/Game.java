package org.example;

import java.util.Scanner;

public class Game{
    private final Board board;
    private static Game instance;

    private Game(){
        board = Board.getInstance();
    }

    public static Game getInstance(){
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    public static void killInstance(){
        instance = null;
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            printBoard();

            System.out.print("Row: ");
            int row = scanner.nextInt();

            System.out.print("Column: ");
            int col = scanner.nextInt();

            if (row < 0 || col < 0 ||
                    row > Board.getInstance().getBoardSize() - 1 ||
                    col > Board.getInstance().getBoardSize() - 1){
                        System.out.println("ERROR! Invalid input.");
                        continue;
            }

            if (board.getCell(row, col).isMine()){
                revealAllBombs();
                printBoard();
                System.out.println("GG, you lose.");
                Board.killInstance();
                Game.killInstance();
                break;
            }else{
                revealCell(row, col);
            }

            if (checkWin()){
                revealAllBombs();
                printBoard();
                System.out.println("GG, you WIN!");
                Board.killInstance();
                Game.killInstance();
                break;
            }
        }
    }

    public void printBoard(){
        System.out.print("    ");
        for (int col = 0; col < board.getBoardSize(); col++){
            System.out.print(col + " ");
        }
        System.out.println();

        System.out.print("    ");
        for (int col = 0; col < board.getBoardSize(); col++){
            System.out.print("_ ");
        }
        System.out.println();

        for (int row = 0; row < board.getBoardSize(); row++){
            System.out.print(row + " | ");

            for (int col = 0; col < board.getBoardSize(); col++){
                if (board.getCell(row, col).isRevealed()){
                    System.out.print(board.getCell(row, col).isMine() ? "X " :
                            board.getCell(row, col).getCellNearbyMines() + " ");
                }else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void revealCell(int row, int col){
        if (row < 0 || row >= board.getBoardSize() ||
                col < 0 || col >= board.getBoardSize() ||
                board.getCell(row, col).isRevealed()){
                    return;
        }

        board.getCell(row, col).reveal();

        if (board.getCell(row, col).getCellNearbyMines() == 0){
            for (int nearbyRow = -1; nearbyRow <= 1; nearbyRow++){
                for (int nearbyCol = -1; nearbyCol <= 1; nearbyCol++){
                    revealCell(row + nearbyRow, col + nearbyCol);
                }
            }
        }
    }

    public void revealAllBombs() {
        for (int row = 0; row < board.getBoardSize(); row++){
            for (int col = 0; col < board.getBoardSize(); col++){
                if (board.getCell(row, col).isMine()){
                    board.getCell(row, col).reveal();
                }
            }
        }
    }

    public boolean checkWin(){
        for (int row = 0; row < board.getBoardSize(); row++){
            for (int col = 0; col < board.getBoardSize(); col++){
                if (!board.getCell(row, col).isMine() &&
                        !board.getCell(row, col).isRevealed()){
                            return false;
                }
            }
        }
        return true;
    }
}
