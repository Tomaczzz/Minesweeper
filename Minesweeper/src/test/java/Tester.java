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
    void testWithInvalidRevealedCellShouldReturnFalseReveal(){
        initializer();
        game.revealCell(-1, 0);
        assertFalse(board.getCell(0, 0).isRevealed());
        kill();
    }

    @Test
    void testWithRevealedBombShouldReturnGameWinAsFalse(){
        initializer();
        board.getCell(0,0).setMine(true);
        game.revealCell(0,0);
        assertFalse(game.checkWin());
        kill();
    }
}