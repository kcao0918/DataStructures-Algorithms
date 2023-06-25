/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The FullStackException class is responsible for throwing an exception when the stack is already full
*/

public class FullStackException extends Exception{
    public FullStackException(String m) {
        super(m);
    }
}
