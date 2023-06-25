/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The NearEarthObject class is responsible for making the near earth object and providing the getter and setters
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class NearEarthObject {
    private int referenceID;
    private String name;
    private double absoluteMagnitude;
    private double averageDiameter;
    private boolean isDangerous;
    private Date closestApproachDate;
    private double missDistance;
    private String orbitingBody;

    /**
     * 
     * @param referenceID
     * @param name
     * @param absoluteMagnitude
     * @param minDiameter
     * @param maxDiameter
     * @param isDangerous
     * @param closestDateTimestamp
     * @param missDistance
     * @param orbitingBody
     * Postconditions: Creates the NearEarthObject 
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter, double 
    maxDiameter, boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody) {
        this.referenceID = referenceID;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.averageDiameter = ((minDiameter+maxDiameter)/2);
        this.isDangerous = isDangerous;
        this.closestApproachDate = new Date(closestDateTimestamp);
        this.missDistance = missDistance;
        this.orbitingBody = orbitingBody;
    }

    /**
     * 
     * @return the referenceID
     */
    public int getReferenceID() {
        return referenceID;
    }

    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return the absolute magnitude
     */
    public double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    /**
     * 
     * @return the average diameter
     */
    public double getAverageDiameter() {
        return averageDiameter;
    }

    /**
     * 
     * @return true if dangerous, false if not
     */
    public boolean getIsDangerous() {
        return isDangerous;
    }

    /**
     * 
     * @return the closest approach date
     */
    public Date getClosestApproachDate() {
        return closestApproachDate;
    }
    
    /**
     * 
     * @return the closest date in a MM_dd-yyyy format
     */
    public String closestDateHelper() {
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
        return s.format(getClosestApproachDate());
    }

    /**
     * 
     * @return the miss distance
     */
    public double getMissDistance() {
        return missDistance;
    }

    /**
     * 
     * @return the orbiting body
     */
    public String getOrbitingBody() {
        return orbitingBody;
    }

    /**
     * 
     * @param referenceID that the referenceID should be set to
     */
    public void setReferenceID(int referenceID) {
        this.referenceID = referenceID;
    }

    /**
     * 
     * @param name that the name should be set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @param absoluteMagnitude that the absoluteMagnitude should be set to
     */
    public void setAbsoluteMagnitude(double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    /**
     * 
     * @param averageDiameter that the averageDiameter should be set to
     */
    public void setAverageDiameter(double averageDiameter) {
        this.averageDiameter = averageDiameter;
    }

    /**
     * 
     * @param isDangerous that the isDangerous variable should be set to
     */
    public void setIsDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    /**
     * 
     * @param closestApproachDate that the closestApproachDate should be set to
     */
    public void setClosestApproachDate(Date closestApproachDate) {
        this.closestApproachDate = closestApproachDate;
    }

    /**
     * 
     * @param missDistance that the missDistance should be set to
     */
    public void setMissDistance(double missDistance) {
        this.missDistance = missDistance;
    }

    /**
     * 
     * @param orbitingBody that the orbitingBody should be set to
     */
    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }
}