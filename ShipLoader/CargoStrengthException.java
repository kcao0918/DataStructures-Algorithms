/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The CargoStrengthException class is responsible for throwing an exception when the 
cargo on the bottom has a weaker strength than the top or potential top cargo
*/

public class CargoStrengthException extends Exception{
    public CargoStrengthException(String m) {
        super(m);
    }
}
