/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The ReferenceIDComparator class is responsible for sorting the arraylist by reference ID
*/

public class ReferenceIDComparator implements java.util.Comparator<NearEarthObject>{
    /**
     * compares two earth objects by their referenceID. Helps sort the arraylist based on referenceIDs
     */
    public int compare(NearEarthObject e1, NearEarthObject e2) {
        if (e1.getReferenceID() == e2.getReferenceID())
            return 0;
        else if (e1.getReferenceID() > e2.getReferenceID())
            return 1;
        else
            return -1;
    }
}
