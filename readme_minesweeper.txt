/* Name: Grace Qian
 * Pennkey: graceq
 * Recitation: 202
 * 
 * readme
 */

How to run the program: 
    Run Minesweeper.java with no arguments (java Minesweeper). This
    will run the minesweeper game with 9 rows and columns and 10 
    randomly placed mines
    
Additional features: 
    I implemented a flagging feature, which can be used to mark 
    unrevealed blocks. Blocks are both flagged and unflagged by 
    pressing the "f" key. When the player loses, mines that are
    flagged are not revealed.
    
File Descriptions:
    
    Block.java:
        Creates instances of the Block object, which are the building
        blocks of the minesweeper game. Each block contains fields that
        determine what image they will display in PennDraw, as well as 
        their size, depending on the number of cells in the grid.
    
    Game.java: 
        Creates instances of the Game object, which is essentially used
        to execute one game of minesweeper. The main field of Game is 
        the 2D block array, and it also contains other fields about the
        statuses of the mines and how many blocks have been revealed.
        The two most significant methods in this class are setMines and 
        clickAndFlag. setMines, when called, turns random blank blocks into
        mines, as long as it's not where the player clicks first, protecting
        the first click. It also updates all the block values so that they
        show the number of adjacent mines, and these values are kept for 
        the rest of the game. clickAndFlag senses the user's actions on 
        whether they're clicking or flagging. If clicking, there are 3 
        outcomes, which revSurr and clickMine help to execute.
    
    Minesweeper.java
        Used to run the program. Minesweeper contains 2 main while loops: 
        the inner loop is used to loop through each frame of the game, and
        the outer loop is run each time a game is played. The inner loop 
        runs as long as until the player reveals all blocks but the mines,
        in which case the player would win, or until the player clicks on 
        a mine, in which the loop is broken and the player looses. Mines 
        are set right after the first block is clicked. As this loop 
        continues, it is constantly updatint the images of the blocks, 
        depending on how the fields of each block change. After the game is
        completed, text is displayed, depending on whether the player wins 
        or loses. If the game is replayed, a new game is recreated. 
        
    
    
    
    
