/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The CargoShip class stimulates the Ship. It allows for the pushing and poppiing at specific stacks
and is responsible for printing out the weight of the ship and finding specific cargo
*/

public class CargoShip {
    private CargoStack[] stacks;
    private int maxHeight;
    private double maxWeight;

    /**
     * Preconditions: 
     * numStacks, initMaxHeight, and initMaxWeight all have to be above 0
     * @param numStacks the number of stacks the ship can hold
     * @param initMaxHeight the maximum height of any stack on this ship
     * @param initMaxWeight the maximum weight for all the cargo on the ship
     * Postconditions: Initializes a cargoship with a number of stacks, a maxh eight, and a max weight
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) {
        if ((numStacks <= 0) || (initMaxHeight <= 0) || (initMaxHeight <= 0)) {
            throw new IllegalArgumentException("numStacks, initMaxHeight, or initMaxWeigth is 0 or below");
        }
        stacks = new CargoStack[numStacks];
        this.maxHeight = initMaxHeight;
        this.maxWeight = initMaxWeight;
        for (int i = 0; i < numStacks; i++) {
            stacks[i] = new CargoStack();
        }
    }

    /**
     * Preconditions: 
     * This CargoShip is initialized and not null.
     * Cargo is initialized and not null.
     * 1 ≤ stack ≤ stacks.length.
     * The size of the stack at the desired index is less than maxHeight.
     * The total weight of all Cargo on the ship and cargo.getWeight()is less than maxWeight.
     * @param cargo that is to be added
     * @param stack index of stack
     * @throws FullStackException when the stack is full
     * @throws ShipOverweightException when the cargo will cause the ship to go over the max weight
     * @throws CargoStrengthException when the cargo underneath is more fragile
     * Postconditions: The cargo was successfully pushed unless ran into an error, in which case nothing occurs to the stack
     */
    public void pushCargo(Cargo cargo, int stack) throws FullStackException, ShipOverweightException, CargoStrengthException {
        if ((cargo == null) || ((stack <= 0 || stack > stacks.length))) {
            throw new IllegalArgumentException();
        }
        else if ((stacks[stack-1].size()+1) > maxHeight) {
            throw new FullStackException(null);
        }
        else if ((getWeightOfShip()+cargo.getWeight()) > maxWeight) {
            throw new ShipOverweightException(null);
        }
        else if (!stacks[stack-1].isStackable(cargo)) {
            throw new CargoStrengthException(null);
        }
        else {
            stacks[stack-1].push(cargo);
        }
    }

    /**
     * Preconditions: 
     * This CargoShip is initialized and not null.
     * 1 ≤ stack ≤ stacks.length
     * @param stack to pop from
     * @return the cargo that got popped
     * @throws EmptyStackException if the stack indicated is empty
     * @throws IllegalArgumentException if stack is in invalid range
     * Postconditions: The cargo was successfully popped unless ran into an error, in which case nothing occurs to the stack
     */
    public Cargo popCargo(int stack) throws EmptyStackException {
        if ((stack <= 0 || stack > stacks.length)) {
            throw new IllegalArgumentException();
        }
        else if (stacks[stack-1].size() == 0) {
            throw new EmptyStackException(null);
        }
        else {
            return stacks[stack-1].pop();
        }
    }

    /**
     * A method that helps to print all the cargo in the stacks
     */
    public void printAllStacks() {
        for (int i = 0; i < stacks.length; i++) {
            System.out.printf("Stack %s: ", i+1);
            System.out.print(stacks[i].getStackList());
            System.out.println();
        }
    }

    /**
     * Preconditions:
     * This CargoShip is initialized and not null.
     * @param name to find
     * Postcondiitons:
     * The details of the cargo with the given name have been found and printed, or the user has been notified that no such cargo has been found.
     * If cargo isn't found, then it is printed
     * Cargo Ship is unchanged
     */
    public void findAndPrint(String name) {
        String tempString = "";
        int counter = 0;
        int countWeight = 0;
        for (int i = 0; i < stacks.length; i++) {
            for (int j = 0; j < stacks[i].size(); j++) {
                if ((stacks[i].getCargo(j).getName()).equals(name)) {
                    int tempWeight = (int) stacks[i].getCargo(j).getWeight().intValue();
                    tempString += String.format("   %-4d|   %-4d|    %-6s|  %s  \n"
                    , i+1, stacks[i].size()-j-1, tempWeight, stacks[i].getCargoStrength(j));
                    countWeight += stacks[i].getCargo(j).getWeight();
                    counter++;
                }
            }
        }
        if (!tempString.equals("")) {
            System.out.println(" Stack   Depth   Weight   Strength\n" 
            + "=======+=======+========+==========");
            System.out.println(tempString);
            System.out.printf("Total Count: %d", counter);
            System.out.printf("\nTotal Weight: %s", countWeight);

        }
        else {
            System.out.printf("Cargo '%s' could not be found on the ship\n", name);
        }
    }

    /**
     * 
     * @return the total of all the cargo weights in the ship
     */
    public int getWeightOfShip() {
        int weight = 0;
        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i] != null) {
                weight += stacks[i].getAllWeight();
            }
        }
        return weight;
    }
}
