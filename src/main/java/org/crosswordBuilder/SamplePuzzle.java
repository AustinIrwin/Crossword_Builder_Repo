package org.crosswordBuilder;

import org.crosswordBuilder.Model.*;

public class SamplePuzzle {
    /* Puzzles follow the convention below:
    -1 is a blacked out space
    0 is an empty space (this will on appear in these solved puzzles)

    Do I store both a solution set and an empty puzzle
    Or do I store an unsolved puzzle and then the clues that it follows
    how do I store the clues?
    across clues
    down clues
    nope easier to just store a solved puzzle
    so this puzzle library will store copies of solved puzzles that will then be "cleared"
    when they are loaded in



     */

    public static IPuzzle PUZZLE_01(){
        //initialize an IBoard
        //initialize a list of across clues
        //initialize a list of down clues

        //Initialize a puzzle with the board and clues

        int[][] frame_01 = {{'C','L','U','E', 0 ,'T','H','A','T', 0 ,'T','A','B'},
                            {'B','A','R','N', 0 ,'O','I','L','Y', 0 ,'I','L','L'},
                            {'S','P','A','S', 0 ,'O','P','E','R','E','T','T','A'},
                            { 0 , 0 ,'N','N','E', 0 , 0 , 0 ,'A','T','L','A','S'},
                            {'A','S','I','A','N', 0 ,'S','I','N','C','E','R','E'},
                            {'S','T','U','R','D','I','E','S','T', 0 , 0, 0 , 0 },
                            {'S','A','M','E', 0 ,'C','A','L', 0 ,'M','E','S','H'},
                            { 0 , 0 , 0 , 0 ,'S','E','R','E','N','A','D','E','S'},
                            {'E','R','A','S','E','R','S', 0 ,'E','D','I','C','T'},
                            {'S','E','D','A','N', 0 , 0 , 0 ,'T','O','T', 0 , 0 },
                            {'S','E','A','P','O','R','T','S', 0 ,'N','I','P','S'},
                            {'E','S','P', 0 ,'R','A','H','S', 0 ,'N','O','A','H'},
                            {'S','E','T', 0 ,'A','N','E','W', 0 ,'A','N','T','E'}};

        IBoard board_01 = new Board(frame_01);

        IClue[] clues_01 = new IClue[29+33];

        //ACROSS clues
        IClue A1 = new Clue("Detective's find", IPuzzle.Direction.ACROSS, 1, false);
        clues_01[0] = A1;

        return new Puzzle("Grande", board_01, clues_01);
    }


    public static IPuzzle mini_Puzzle(){
        //Copyright the New York Times mini Crossword
        int[][] mini_Frame = {  { -1, -1,'A','T','E'},
                                {'D','I','D','I','T'},
                                {'O','B','A','M','A'},
                                {'J','A','M','E','S'},
                                {'O','R','S', -1 , -1 }};

        IBoard mini_Board = new Board(mini_Frame);


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

        return new Puzzle("Mini", mini_Board, mini_Clues);
    }

    public static IPuzzle medium_Puzzle(){
        int[][] medium_Frame = {{-1 ,-1 ,'L','A','S','E',-1 },
                                {-1 ,'W','I','N','T','E','R'},
                                {'C','E','N','T','U','R','Y'},
                                {'A','G','E',-1 ,'D','I','E'},
                                {'R','O','O','K','I','E','S'},
                                {'L','O','N','G','E','R',-1 },
                                {-1 ,'D','E','B','S',-1 ,-1 }};

        IBoard medium_Board = new Board(medium_Frame);

        IClue[] medium_clues = new IClue[16];
        for(int x = 0; x < 16; x++){
            medium_clues[x] = new Clue("Placeholder Clue, good luck guessing", IPuzzle.Direction.ACROSS, x, false);
        }

        return new Puzzle("Midi", medium_Board, medium_clues);
    }
}

