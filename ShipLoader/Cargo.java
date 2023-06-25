/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Cargo class makes the cargo with specified attributes, 
then provides getter methods for those attributes
*/


public class Cargo {
    private String name;
    private Double weight;
    private CargoStrength strength;

    /**
     * Preconditions: initName is not null and initWeight > 0
     * @param initName
     * @param invalid
     * @param initStrength
     * Postcondition: Initializes a cargo object with name, weight, and strength
     */
    public Cargo(String initName, double initWeight, CargoStrength initStrength) {
        if (initName == null || initWeight <= 0) {
            throw new IllegalArgumentException("initname is null or initweight is 0");
        }
        this.name = initName;
        this.weight = initWeight;
        this.strength = initStrength;
    }

    /**
     * 
     * @return name of Cargo
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return weight of Cargo 
     */
    public Double getWeight() {
        return this.weight;
    }

    /**
     * 
     * @return strength of Cargo
     */
    public CargoStrength getStrength() {
        return this.strength;
    }

    /**
     * 
     * @return strength of Cargo in a shortened format
     */
    public String getStrengthShort() {
        if (this.strength == CargoStrength.FRAGILE) {
            return "F";
        }
        else if (this.strength == CargoStrength.MODERATE) {
            return "M";
        }
        else {
            return "S";
        }
    }
}
