/* Name: Grace Qian
 * Pennkey: graceq
 * Recitation: 202
 * Execution: java Minesweeper
 * 
 * Program Description: this program is run to run the game minesweeper. It creates
 *                      an instance of the game class and uses an inner while loop 
 *                      to run each frame of a game, and an outer while loop to 
 *                      repeat the game.
 */

public class Minesweeper {

    public static void main(String[] args) {
        
        // this loop runs for each game
        while (true) {
            // create game with 9 boxes
            Game minesweeper = new Game(9);
            minesweeper.draw();

            PennDraw.enableAnimation(10);
            
            /* when 71 blocks are revealed, all blocks except for the mines are
             * revealed, so the player wins the game
             */
            while (minesweeper.getBlocksRev() < 71) {
                PennDraw.clear();
                
                // checks for clicks and flags, and implements the function
                minesweeper.clickAndFlag();
                
                // before first click, show this message
                if (minesweeper.getBlocksRev() == 0) {
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.text(0.5, 0.25, "Press F To Flag, Press Again To " + 
                                      "Unflag");
                }
                
                // first click, sets mines
                if (!PennDraw.mousePressed() && !minesweeper.getMineState()) {
                    minesweeper.setMines(10);
                }
                
                minesweeper.blocksRevealed();
                
                // draws next iteration of game
                minesweeper.draw();
                
                // exits loop if mine is clicked to go into lose state
                if (minesweeper.getMineClick()) {
                    break;
                }
                
                PennDraw.advance();
            }
            
            PennDraw.disableAnimation();
            
            while (!PennDraw.hasNextKeyTyped()) {
                // lose game
                if (minesweeper.getMineClick()) {
                    PennDraw.picture(0.5, 0.5, "You-died.png");
                
                // win game
                } else {
                    PennDraw.picture(0.5, 0.5, "YOU-WIN.png");
                }
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.25, "Press Any Key To Replay");
            }
            PennDraw.clear();
        }
    }

}