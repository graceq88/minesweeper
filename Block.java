/* Name: Grace Qian
 * Pennkey: graceq
 * Recitation: 202
 * Execution: N/A
 * 
 * Program Description: this program creates instances of the block object 
 *                      and defines its fields and methods; a block is created 
 *                      for each square in the minesweeper game
 */

public class Block {

    // fields
    private int value;
    private double x;
    private double y;
    private double width;
    private boolean revealed;
    private boolean flagged;
    
    // methods
    
    /* Constructor: creates a basic blank block in its unclicked state, 
     *              with value set to 0; size of the block is calculated from 
     *              size of canvas / num, which represents the number of blocks
     *              in the game
     * Input: double num; number of blocks we want in the array is used to 
     *        calculate the size of the blocks
     */
    public Block(double num) {
        this.value = 0;
        this.x = 1 / (num * 2);
        this.y = 1 - (1 / (num * 2));
        this.width = 1 / num;
        this.revealed = false;
        this.flagged = false;
    }
    
    // Description: changes the status of revealed if a block has been clicked
    public void clicked() {
        revealed = true;
    }
    
    // Description: changes the states of flagged back and forth
    public void flag() {
        flagged = !flagged;
    }
    
    /* Description: draws blocks, based off the states of its fields; uses PennDraw
     *              functions for blank and numbered blocks, and pulls images for 
     *              drawing flags and mines
     */
    public void draw() {
        // drawing flagged blocks
        if (this.flagged) {
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.square(x, y, width / 2);
            PennDraw.picture(x, y, "flag.png", width * 512, width * 512);
            
        // not revealed, blank state
        } else if (!this.revealed) {
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.square(x, y, width / 2);
            
        // revealed blank (0 mines nearbye)
        } else if (this.value == 0) {
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
            PennDraw.filledSquare(x, y, width / 2);
            
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.square(x, y, width / 2);
            
        // drawing mines; only occurs when player loses
        } else if (this.value == 50) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledSquare(x, y, width / 2);
            
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.square(x, y, width / 2);
            PennDraw.picture(x, y, "mine.png", width * 512, width * 512);
            
        //drawing number blocks
        } else {
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
            PennDraw.filledSquare(x, y, width / 2);
            
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.square(x, y, width / 2);
            
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(x, y, "" + this.value);
        }
    }
    
    // getters 
    
    /* Description: gets the field x, which is the x coordinate of the center 
     *              of a block
     * output: double x
     */
    public double getX() {
        double x = this.x;
        return x;
    }
    
    /* Description: gets the field y, which is the y coordinate of the center 
     *              of a block
     * output: double y
     */
    public double getY() {
        double y = this.y;
        return y;
    }
    
    /* Description: gets the field value, which is the value a block holds; 
     *              0 for blank blocks, 50 for mines, and the values of the 
     *              numbered blocks are the numbers
     * Output: int val
     */
    public int getVal() {
        int val = this.value;
        return val;
    }
    
    // Description: gets the field width, which is the sidelength of a block 
    public double getWidth() {
        double width = this.width;
        return width;
    }
    
    /* Description: gets the field x, which is the x coordinate of the center 
     *              of a block
     */
    public boolean getRev() {
        boolean rev = this.revealed;
        return rev;
    }
    
    // setters
    
    /* Description: setter for x
     * Input: double x
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /* Description: setter for y
     * Input: double y
     */
    public void setY(double y) {
        this.y = y; 
    }
    
    /* Description: setter for value
     * Input: int val
     */
    public void setVal(int val) {
        this.value = val;
    }
    
    /* Description: setter for revealed
     * Input: boolean rev
     */
    public void setRev(boolean rev) {
        this.revealed = rev;
    }
}