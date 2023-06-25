/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The EmptyStackException is responsible for throwing an exception when the stack is empty
*/

public class EmptyStackException extends Exception{
    public EmptyStackException(String m) {
        super(m);
    }
}
