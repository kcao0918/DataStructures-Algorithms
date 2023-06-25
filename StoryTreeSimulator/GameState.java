/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW5
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
*]
The GameState file allows us to determine the game is currently won, lost, or still continuing through the use of enum
*/

public enum GameState {
    GAME_OVER_WIN(1), GAME_OVER_LOSE(0), GAME_NOT_OVER(2);
    private final int value;

    private GameState(int value) {
        this.value = value;
    }

    /**
     * 
     * @return the value of the enum
     */
    public int getValue() {
        return this.value;
    }
}