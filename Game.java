/* Name: Grace Qian
 * Pennkey: graceq
 * Recitation: 202
 * Execution: N/A
 * 
 * Program Description: this program creates instances of the game object and defines
 *                      its fields and methods; the field of game is the 2D array of 
 *                      blocks
 */

public class Game {
    // fields
    private Block[][] game;
    private boolean minesSet;
    private boolean mineClicked;
    private int blocksRev;
    
    // methods
    
    /* Constructor: creates a 2D array of blocks, iterates through the 
     *              array to create an instance of a block at each entry, 
     *              and sets their location on the PennDraw canvas; also 
     *              sets the beginning states of the other fields
     * Input: double num; used to create the size of the square array
     */
    public Game(int num) {
        game = new Block[num][num];
        for (int row = 0; row < game.length; row++) {
            for (int col = 0; col < game[row].length; col++) {
                game[row][col] = new Block(num);
                
                // set block location
                game[row][col].setX(game[row][col].getX() + col * 
                                    game[row][col].getWidth());
                game[row][col].setY(game[row][col].getY() - row * 
                                    game[row][col].getWidth());
            }
        }
        this.minesSet = false;
        this.mineClicked = false;
        this.blocksRev = 0;
    }
    
    /* Description: sets a certain number of mines at random places in the
     *              array, without repeating spots or setting at the first - 
     *              clicked spot; each time a mine is put, 1 is added to the 
     *              value of the adjacent blocks
     * Input: double num; number of mines to set
     */
    public void setMines(int num) {
        // protecting first click and making sure not to repeat
        int mines = 0;
        while (mines < num) {
            int i = (int) (Math.random() * game.length);
            int j = (int) (Math.random() * game[0].length);
            
            if (!game[i][j].getRev() && game[i][j].getVal() == 0) {
                game[i][j].setVal(50);
                mines++;
                
                // setting values of blocks bordering mines
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (i + k >= 0 && i + k < game.length && j + l >= 0 && 
                                j + l < game[i].length && 
                                game[i + k][j + l].getVal() != 50) {
                            int val = game[i + k][j + l].getVal() + 1;
                            game[i + k][j + l].setVal(val);
                        }
                    }
                }
            }
        }
        this.minesSet = true;
    }
    
    /* Description: draws the game by iterating through the array and calling
     *              draw on each block
     */
    public void draw() {
        for (int row = 0; row < game.length; row++) {
            for (int col = 0; col < game[row].length; col++) {
                game[row][col].draw();
            }
        }
    } 
    
    /* Description: this function is used to calculate the value of revealed 
     *              blocks, rather than redoing blocksRev++ in specific parts
     *              of multiple functions
     */
    public void blocksRevealed() {
        int blocks = 0;
        for (int row = 0; row < game.length; row++) {
            for (int col = 0; col < game[row].length; col++) {
                if (game[row][col].getRev()) {
                    blocks++;
                }
            }
        }
        this.blocksRev = blocks;
    }
    
    /* Description: locates the index of the array the mouse is in; if the f key
     *              is pressed, it will flag / unflag a block, only if the block 
     *              is not already revealed; if a block is clicked, it will show
     *              the block differently, depending on if the value of the block
     *              is 0 (blank), 50 (mine), or another number
     */
    public void clickAndFlag() {
        double x = PennDraw.mouseX();
        double y = PennDraw.mouseY();
        
        // calculates indices mouse is in
        int j = (int) (x * game.length);
        int i = (game[0].length - 1) - (int) (y * game[0].length);
        
        // flagging and unflagging
        if (PennDraw.hasNextKeyTyped()) {
            if (PennDraw.nextKeyTyped() == 'f' && !game[i][j].getRev()) {
                game[i][j].flag();
            }
        }
        
        // click
        if (PennDraw.mousePressed()) {
            game[i][j].clicked();
            
            // if mine
            if (game[i][j].getVal() == 50) {
                this.clickMine();
            }
            
            // if blank
            if (game[i][j].getVal() == 0 && this.minesSet) {
                this.revSurr(i, j);
            } 
        } 
    }
    
    /* Description: helper function for clickAndFlag; if a mine is clicked,
     *              this function is called to reveal all of the mines in 
     *              the array, unless they are flagged
     */
    public void clickMine() {
        for (int row = 0; row < game.length; row++) {
            for (int col = 0; col < game[row].length; col++) {
                if (game[row][col].getVal() == 50) {
                    game[row][col].setRev(true);
                }
            }
        }
        this.mineClicked = true;
    }
    
    /* Description: recursive helper function for clickAndFlag; if a blank 
     *              is clicked, this function reveals surrounding blocks
     *              and calls the function on them, if the indices are in 
     *              bounds and if the block is not already revealed. The 
     *              recursion is stopped once the block it is called on 
     *              is not blank
     * Inputs: int i, int j; these are the indices of the clicked blank
     */
    public void revSurr(int i, int j) {
        if (game[i][j].getVal() > 0) {
            return;
        }
        
        // uses loops to reach adjacent blocks
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if (i + a >= 0 && i + a < game.length && j + b >= 0 && 
                        j + b < game[0].length) {
                    if (!(a == 0 && b == 0)) {
                        if (!game[i + a][j + b].getRev()) {
                            game[i + a][j + b].setRev(true);
                            revSurr(i + a, j + b);
                        }
                    }
                }
            }
        }
    }
    
    // getters
    
    /* Description: gets the field minesSet, which is used to determine 
     *              whether the mines have already been set
     * Output: boolean mineState
     */
    public boolean getMineState() {
        boolean mineState = this.minesSet;
        return mineState;
    }
    
    /* Description: gets the field mineClick, which is used to determine
     *              if a mine has been clicked; if true, would lead to a 
     *              losing game
     * Output: boolean mineClick
     */
    public boolean getMineClick() {
        boolean mineClick = this.mineClicked;
        return mineClick;
    }
    
    /* Description: gets the field blocksRev, which is used to determine
     *              if a game is finished, ie all the blocks except for 
     *              the mines are clicked
     * Output: int blocks
     */
    public int getBlocksRev() {
        int blocks = blocksRev;
        return blocks;
    }
}