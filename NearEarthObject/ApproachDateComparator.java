/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The ApproachDateComparator class is responsible for sorting the arraylist by approach date
*/

public class ApproachDateComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * compares two earth objects by their approach date. Helps sort the arraylist based on the approach dates
     */
    public int compare(NearEarthObject e1, NearEarthObject e2) {
        if (e1.getClosestApproachDate() == e2.getClosestApproachDate())
            return 0;
        else if (e1.getClosestApproachDate().compareTo(e2.getClosestApproachDate()) > 0)
            return 1;
        else
            return -1;
    }
}
