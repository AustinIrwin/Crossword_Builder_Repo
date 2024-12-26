package org.crosswordBuilder;

//import org.crosswordBuilder.Model.Block;
import org.junit.Test;
import org.crosswordBuilder.Model.*;
import static org.crosswordBuilder.SamplePuzzle.*;

//import static org.crosswordBuilder.SamplePuzzle.medium_Puzzle;
//import static org.crosswordBuilder.SamplePuzzle.mini_Puzzle;
import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void testTest(){
        assertTrue(true);
    }

    @Test
    public void blockClosedTest(){
        Block block = new Block(-1);
        assertTrue(block.isClosed());
    }

    @Test
    public void blockOpenTest(){
        Block block = new Block('A');
        assertFalse(block.isClosed());
    }

    @Test
    public void blockGuessTest(){
        Block block = new Block('A');
        assertTrue(block.setGuess('A'));
    }

    @Test
    public void blockGuessWrongTest(){
        Block block = new Block('A');
        assertFalse(block.setGuess('B'));
    }

    @Test
    public void blockLowerGuessTest(){
        Block block = new Block('a');
        assertTrue(block.setGuess('A'));
    }

    @Test
    public void blockRandomTest(){
        try{
            new Block(10);
            fail();
        }catch(IllegalArgumentException e){
            //test passed
        }
    }

    @Test
    public void blockSolveTest(){
        Block block = new Block('a');
        block.setGuess('F');
        block.solve();
        assertEquals('A', block.getGuess());
    }

    @Test
    public void blockIsCorrectTest(){
        Block block = new Block('A');
        block.setGuess('B');
        assertTrue(block.isCorrect());
    }


    @Test
    public void constructBoardTest(){
        int[][] mini_Frame = {  { -1 , -1 ,'A','T','E'},
                {'D','I','D','I','T'},
                {'O','B','A','M','A'},
                {'J','A','M','E','S'},
                {'O','R','S', -1 , -1 }};

        new Board(mini_Frame);
    }
    public static IBoard mini_Board(){
        int[][] mini_Frame = {  { -1 , -1 ,'A','T','E'},
                {'D','I','D','I','T'},
                {'O','B','A','M','A'},
                {'J','A','M','E','S'},
                {'O','R','S', -1 , -1 }};

        return new Board(mini_Frame);
    }

    @Test
    public void boardSizeTest(){
        IBoard board = mini_Board();

        assertEquals(5, board.getSize());
    }

    @Test
    public void getBlockTest(){
        IBoard board = mini_Board();

        assertEquals('A', board.getBlock(0,2).getLetter());
        assertTrue(board.getBlock(0,1).isClosed());
    }

    @Test
    public void getBlockOOBTest(){
        IBoard board = mini_Board();
        try{
            board.getBlock(10,10);
            fail();
        }catch (IllegalArgumentException e){
            //test pass
        }
    }

    @Test
    public void solveBoardTest(){
        IBoard board = mini_Board();
        for(int x = 0; x < board.getSize(); x++){
            for(int y = 0; y < board.getSize(); y++){
                try{
                    board.getBlock(x,y).solve();
                }catch(IllegalArgumentException e){
                    System.out.println("Closed block, moving on");
                }
            }
        }

        assertTrue(board.isSolved());
    }

    @Test
    public void resetBoardTest(){
        IBoard board = mini_Board();
        for(int x = 0; x < board.getSize(); x++){
            for(int y = 0; y < board.getSize(); y++){
                try{
                    board.getBlock(x,y).solve();
                }catch(IllegalArgumentException e){
                    System.out.println("Closed block, moving on");
                }
            }
        }
        board.reset();

        assertFalse(board.isSolved());
    }

    public static IClue[] miniClue(){
        IClue[] mini_Clues = new IClue[10];
        IClue A1 = new Clue("Downed", IPuzzle.Direction.ACROSS, 1, false);
        mini_Clues[0] = A1;

        IClue A4 = new Clue("\"Oops, I ___ again\" (Britney Spears lyric)", IPuzzle.Direction.ACROSS, 4, false);
        mini_Clues[1] = A4;

        IClue A6 = new Clue("\"Dreams From My Father\" author", IPuzzle.Direction.ACROSS, 6, false);
        mini_Clues[2] = A6;

        IClue A7 = new Clue("Famous Bond", IPuzzle.Direction.ACROSS, 7, false);
        mini_Clues[3] = A7;

        IClue A8 = new Clue("Surgery sites, for short", IPuzzle.Direction.ACROSS, 8, false);
        mini_Clues[4] = A8;


        IClue D1 = new Clue("Douglas who wrote \"The Hitchhiker's Guide to the Galaxy\"", IPuzzle.Direction.DOWN, 1, false);
        mini_Clues[5] = D1;

        IClue D2 = new Clue("A metronome keeps it", IPuzzle.Direction.DOWN, 2, false);
        mini_Clues[6] = D2;

        IClue D3 = new Clue("Greek H's", IPuzzle.Direction.DOWN, 3, false);
        mini_Clues[7] = D3;

        IClue D4 = new Clue("Martial Arts School", IPuzzle.Direction.DOWN, 4, false);
        mini_Clues[8] = D4;

        IClue D5 = new Clue("Letter Shaped construction beam", IPuzzle.Direction.DOWN, 5, false);
        mini_Clues[9] = D5;

        return mini_Clues;
    }

    @Test
    public void puzzleConstructorTest(){
        IBoard board = mini_Board();
        IClue[] clues = miniClue();

        new Puzzle("Mini", board, clues);
    }
    @Test
    public void puzzleLibraryConstructorTest(){
        IPuzzleLibrary puzzleLibrary = new PuzzleLibrary();

        puzzleLibrary.addPuzzle(mini_Puzzle());
        puzzleLibrary.addPuzzle(medium_Puzzle());
        assertEquals(2, puzzleLibrary.size());
    }

    public static IPuzzleLibrary puzzleLibrary(){
        IPuzzleLibrary puzzleLibrary = new PuzzleLibrary();
        puzzleLibrary.addPuzzle(mini_Puzzle());
        puzzleLibrary.addPuzzle(medium_Puzzle());

       return puzzleLibrary;
    }


    @Test
    public void modelPrevNextTest(){
        IModel model = new Model(puzzleLibrary());
        model.nextPuzzle();
        model.nextPuzzle();
        model.prevPuzzle();

        assertEquals("Mini", model.getActivePuzzle().getTitle());

    }

    @Test
    public void modelBlockAcrossTest(){
        IModel model = new Model(puzzleLibrary());
        assertEquals('A', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('T', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('E', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('I', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('I', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('T', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('O', model.getActiveBlock().getLetter());

        for(int x = 0; x < 100; x++){
            model.nextBlock();
        }

    }

    @Test
    public void modelBlockDownTest(){
        IModel model = new Model(puzzleLibrary());
        model.toggleDirection();
        assertEquals('A', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('A', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('M', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('S', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('T', model.getActiveBlock().getLetter());

        for(int x = 0; x < 100; x++){
            model.nextBlock();
        }

    }

    @Test
    public void modelWordNext(){
        IModel model = new Model(puzzleLibrary());
        assertEquals(IPuzzle.Direction.ACROSS, model.getDirection());
        model.nextWord();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('O', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('J', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('O', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('A', model.getActiveBlock().getLetter());


        model.nextWord();
        assertEquals('T', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('E', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('I', model.getActiveBlock().getLetter());
        model.nextWord();
        assertEquals('A', model.getActiveBlock().getLetter());

    }

    @Test
    public void modelToggleDirectionTest(){
        IModel model = new Model(puzzleLibrary());
        assertEquals(IPuzzle.Direction.ACROSS, model.getDirection());
        //model.toggleDirection();
        model.toggleDirection();
        assertEquals(IPuzzle.Direction.DOWN, model.getDirection());
        model.toggleDirection();
        assertEquals(IPuzzle.Direction.ACROSS, model.getDirection());
    }

    @Test
    public void modelPrevBlock(){
        IModel model = new Model(puzzleLibrary());
        model.toggleDirection();
        assertEquals('A', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('A', model.getActiveBlock().getLetter());
        model.nextBlock();
        assertEquals('M', model.getActiveBlock().getLetter());
        model.prevBlock();
        assertEquals('A', model.getActiveBlock().getLetter());
        model.prevBlock();
        assertEquals('D', model.getActiveBlock().getLetter());
        model.prevBlock();
        assertEquals('A', model.getActiveBlock().getLetter());
        model.prevBlock();
        assertEquals('A', model.getActiveBlock().getLetter());


    }

}
