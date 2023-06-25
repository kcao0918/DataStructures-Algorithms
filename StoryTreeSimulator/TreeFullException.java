/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW5
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The TreeFullException checks to see if the all 3 child spots are filled
*/


public class TreeFullException extends Exception{
    public TreeFullException(String m) {
        super(m);
    }
}