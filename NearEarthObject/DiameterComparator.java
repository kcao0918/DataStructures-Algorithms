/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The DiameterComparator class is responsible for sorting the arraylist by average diameter
*/

public class DiameterComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * compares two earth objects by their average diameter. Helps sort the arraylist based on the average diameters
     */
    public int compare(NearEarthObject e1, NearEarthObject e2) {
        if (e1.getAverageDiameter() == e2.getAverageDiameter())
            return 0;
        else if (e1.getAverageDiameter() > e2.getAverageDiameter())
            return 1;
        else
            return -1;
    }
}
