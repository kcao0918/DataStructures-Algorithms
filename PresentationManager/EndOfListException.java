/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW2
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The EndOfListException file lets you know when you are at either end of the list, beginning or end
*/



public class EndOfListException extends Exception{
    public EndOfListException(String m) {
        super(m);
    }
}
