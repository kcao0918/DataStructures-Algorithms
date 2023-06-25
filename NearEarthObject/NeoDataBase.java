/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The NeoDataBase class is responsible for adding all of the objects into the arraylist from the database 
and printing the table. It also gives the method for sorting the list
*/

import java.util.Comparator;
import big.data.DataSource;
import java.util.ArrayList;
import java.util.Collections;

public class NeoDataBase {
    public static final String API_KEY = "7JZcJugLFEmPDKPKVMSux0nlbhYbf6dtaEk2ke9X";
    public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";
    private ArrayList<NearEarthObject> arrList;

    /**
     * initializes the empty arraylist
     */
    public NeoDataBase() {
        arrList = new ArrayList<>();
    }

    /**
     * Preconditions:
     * 0 ≤ page ≤ 715.
     * @param pageNumber 
     * @return the String of the URL
     * @throws IllegalArgumentException If pageNumber is not in the valid range.
     * Postconditions:
     * Builds a query URL given a page number. This should be a simple method which returns (API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY)
     */
    public String buildQueryURL(int pageNumber) throws IllegalArgumentException {
        if ((pageNumber < 0) || (pageNumber > 715)) {
            throw new IllegalArgumentException();
        }
        return API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY;
    }

    /**
     * Preconditions:
     * queryURL is a non-null string representing a valid API request to the NASA NeoW service.
     * @param queryURL String containing the URL requesting a dataset from the NASA NeoW service 
     * @throws IllegalArgumentException If queryURL is null or cound not be resolved by the server.
     * Postconditions: 
     * All NearEarthObject records returned have been added to the database, or else a IllegalArgumentException has been thrown.
     */
    public void addAll(String queryURL) throws IllegalArgumentException {
        if (queryURL == null) {
            throw new IllegalArgumentException();
        }
        DataSource ds = DataSource.connectJSON(queryURL);
        ds.load();
        NearEarthObject[] arr = ds.fetchArray(
            "NearEarthObject",                    
            "near_earth_objects/neo_reference_id",   
            "near_earth_objects/name",
            "near_earth_objects/absolute_magnitude_h",
            "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min",
            "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max",
            "near_earth_objects/is_potentially_hazardous_asteroid",
            "near_earth_objects/close_approach_data/epoch_date_close_approach",
            "near_earth_objects/close_approach_data/miss_distance/kilometers",
            "near_earth_objects/close_approach_data/orbiting_body" 
        );
        for (NearEarthObject x : arr) {
            arrList.add(x);
        }
    }

    /**
     * Preconditions:
     * comp is not null
     * @param comp Comparator of NearEarthObjects which will be used to sort the database
     * @throws IllegalArgumentException If comp is null.
     * Postconditions:
     * The database has been sorted based on the order specified by the inidcated Comparator of NearEarthObjects.
     */
    public void sort(Comparator<NearEarthObject> comp) throws IllegalArgumentException {
        Collections.sort(arrList, comp);
    }

    /**
     * Preconditions:
     * This NeoDatabase is initialized and not null.
     * Postconditions:
     * The table has been printed to the console but remains unchanged.
     */
    public void printTable() {
        System.out.println("  ID   |           Name            | Mag. | Diameter | Danger | Close Date | Miss Dist | Orbits");
        System.out.println("================================================================================================");
        for (int i = 0; i < arrList.size(); i++) {
            NearEarthObject temp = arrList.get(i);
            System.out.printf("%-9d%-28.26s%-8.1f%-10.3f%-10b%-12s%-12.0f%s\n", temp.getReferenceID(), temp.getName(), temp.getAbsoluteMagnitude(),
            temp.getAverageDiameter(), temp.getIsDangerous(), temp.closestDateHelper(), temp.getMissDistance(), temp.getOrbitingBody());
        }
    }
}