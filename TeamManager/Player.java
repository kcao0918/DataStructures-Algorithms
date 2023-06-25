/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW1 
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Player file creates the player and also provides the mutators and accessor methods for the player stats
*/

public class Player {
    private String name;
    private int numHits;
    private int numErrors;

    /**
     * creates a player with a name, a number of hits, and a number of errors
     * @param name
     * @param numHits
     * @param numErrors
     */
    public Player(String name, int numHits, int numErrors) {
        this.name = name;
        this.numHits = numHits;
        this.numErrors = numErrors;
    }


    /**
     * 
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return the number of hits the player made
     */
    public int getNumHits() {
        return this.numHits;
    }

    /**
     * 
     * @return the number of errors the player made
     */
    public int getNumErrors() {
        return this.numErrors;
    }

    /**
     * sets the name for the player
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the number of hits
     * @param numHits 
     * @throws IllegalArgumentException checks if number of hits is negative
     */
    public void setNumHits(int numHits) throws IllegalArgumentException{
        if (numHits < 0) {
            throw new IllegalArgumentException("Number of hits must be positive");
        }
        else {
            this.numHits = numHits;
        }
    }

    /**
     * sets the number of errors
     * @param numErrors
     * @throws IllegalArgumentException checks if number of errors is negative
     */
    public void setNumErrors(int numErrors) throws IllegalArgumentException{
        if (numErrors < 0) {
            throw new IllegalArgumentException("Number of hits must be positive");
        }
        else {
            this.numErrors = numErrors;
        }
    }

    /**
     * prints out the result as a string
     * @param name
     * @param numHits
     * @param numErrors
     */
    public void toString(String name, int numHits, int numErrors) {
        System.out.printf("%s - %d hits, %d errors \n", name, numHits, numErrors);
    }




}