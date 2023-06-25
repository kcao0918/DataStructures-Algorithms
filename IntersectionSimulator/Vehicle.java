/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW4
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Vehicle class simualates the creation of the vehicle and its properties
*/

public class Vehicle {
    private static int serialCounter = 0;
    private int serialID;
    private int timeArrived;

    /**
     * Preconditions: 
     * initTimeArrived > 0
     * @param initTimeArrived
     * Postconditions: 
     * creates a vehicle object with an id number and a time arrived
     */
    public Vehicle(int initTimeArrived) {
        if (initTimeArrived <= 0) throw new IllegalArgumentException();
        this.timeArrived = initTimeArrived;
        serialCounter++;
        serialID = serialCounter;
    }

    /**
     * 
     * @return the serial id of the vehicle
     */
    public int getSerialID() {
        return this.serialID;
    }

    /**
     * 
     * @return the time the vehicle arrived
     */
    public int getTimeArrived() {
        return this.timeArrived;
    }
}
