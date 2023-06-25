/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The CargoStrength class is responsible for assignment specific int values to the strength of cargo
*/

public enum CargoStrength {
    FRAGILE(1), MODERATE(2), STURDY(3);
    private final int value;
    private CargoStrength(int value) {
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
