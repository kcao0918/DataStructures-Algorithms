/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The MissDistanceComparator class is responsible for sorting the arraylist by miss distance
*/

public class MissDistanceComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * compares two earth objects by their miss distances. Helps sort the arraylist based on miss distances
     */
    public int compare(NearEarthObject e1, NearEarthObject e2) {
        if (e1.getMissDistance() == e2.getMissDistance())
            return 0;
        else if (e1.getMissDistance() > e2.getMissDistance())
            return 1;
        else
            return -1;
    }
}
