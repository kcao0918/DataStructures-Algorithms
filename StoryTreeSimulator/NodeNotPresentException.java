/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW5
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
*]
The NodeNotPresentException class allows us to determine if a selected node is not present
*/

public class NodeNotPresentException extends Exception{
    public NodeNotPresentException(String m) {
        super(m);
    }
}