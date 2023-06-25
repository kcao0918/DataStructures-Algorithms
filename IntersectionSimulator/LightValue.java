/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW4
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Light Value gives us the values for the lights that we would use in the other classe
*/

public enum LightValue {
    GREEN(1), RED(0), LEFT_SIGNAL(2);
    private final int value;
    private LightValue(int value) {
        this.value = value;
    }
    /**
     * 
     * @return the specific value with the strength
     */
    public int getValue() {
        return value;
    }
}
