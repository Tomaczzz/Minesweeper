import org.example.Board;
import org.example.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Tester{
    private Board board;
    private Game game;

    void initializer(){
        board = Board.getInstance();
        game = Game.getInstance();
    }

    void kill(){
        Board.killInstance();
        Game.killInstance();
    }

    @Test
    void testWithNotRevealedCellShouldReturnFalse(){
        initializer();
        assertFalse(board.getCell(0, 0).isRevealed());
        kill();
    }

    @Test
    void testWithRevealedCellsShouldReturnGameWinAsTrue(){
        initializer();
        board.getCell(0, 0).setMine(true);

        game.revealCell(1, 2);

        assertTrue(game.checkWin());
        kill();
    }

    @Test
    void testWithAllRevealedCellsShouldReturnAsRevealedCells(){
        initializer();
        board.getCell(0, 0).setMine(true);

        game.revealCell(1, 2);

        assertTrue(board.getCell(0, 1).isRevealed());
        assertTrue(board.getCell(0, 2).isRevealed());
        assertTrue(board.getCell(1, 0).isRevealed());
        assertTrue(board.getCell(1, 1).isRevealed());
        assertTrue(board.getCell(1, 2).isRevealed());
        assertTrue(board.getCell(2, 0).isRevealed());
        assertTrue(board.getCell(2, 1).isRevealed());
        assertTrue(board.getCell(2, 2).isRevealed());
        kill();
    }
}