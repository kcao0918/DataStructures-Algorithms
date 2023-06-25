/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The ShipOverweightException class is responsible for throwing an exception when the 
cargo to be added will cause the ship to go past the maximum weight limit
*/

public class ShipOverweightException extends Exception{
    public ShipOverweightException(String m) {
        super(m);
    }
}
