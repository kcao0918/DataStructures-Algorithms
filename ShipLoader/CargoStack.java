/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The CargoStack class is responsible for stimulating a stack that holds cargo. 
It also contains methods such as push() and pop() which are used in the CargoShip Class,
as well as other methods such as peek() and getting a print of all the Cargo in the Stack
*/

import java.util.ArrayList;

public class CargoStack {
    private ArrayList<Cargo> cargoStack;

    /**
     * Creates an Arraylist to stimulate a stack
     */
    public CargoStack() {
        cargoStack = new ArrayList<Cargo>();
    }

    /**
     * 
     * @param cargo to be added
     * Adds the cargo to the end of the stack
     */
    public void push(Cargo cargo) {
        cargoStack.add(cargo);
    }

    /**
     * 
     * @return the cargo that was removed
     * Removes the cargo at the very top of the stack
     */
    public Cargo pop() {
        return cargoStack.remove(cargoStack.size()-1);

    }

    /**
     * 
     * @return the cargo at the very top of the stack
     */
    public Cargo peek() {
        return cargoStack.get(cargoStack.size()-1);
    }

    /**
     * 
     * @return true if stack is empty, false if stack has an object in it
     */
    public boolean isEmpty() {
        return (cargoStack.size() == 0);
    }

    /**
     * 
     * @return the current number of cargo in the stack
     */
    public int size() {
        return cargoStack.size();
    }

    /**
     * 
     * @return the weight of all the cargo in the stack
     */
    public int getAllWeight() {
        int weight = 0;
        for (int i = 0; i < cargoStack.size(); i++) {
            weight += cargoStack.get(i).getWeight();
        }
        return weight;
    }

    /**
     * 
     * @param idx to be checked
     * @return the strength of the cargo
     */
    public CargoStrength getCargoStrength(int idx) {
        return cargoStack.get(idx).getStrength();
    }

    /**
     * 
     * @return a string that helps print out the stacks and all their cargo inside
     */
    public String getStackList() {
        String temp = "";
        for (int i = 0; i < cargoStack.size(); i++) {
            if (i == cargoStack.size()-1) {
                temp += cargoStack.get(i).getStrengthShort();
            }
            else {
                temp += cargoStack.get(i).getStrengthShort();
                temp += ", ";
            }
        }
        return temp;
    }

    /**
     * 
     * @param cargo
     * @return true if the cargo can be inserted on top, false if not
     */
    public boolean isStackable(Cargo cargo) {
        if (cargoStack.size() == 0) {
            return true;
        }
        if (cargoStack.get(cargoStack.size()-1).getStrength().getValue() >= cargo.getStrength().getValue()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * @param idx
     * @return the cargo at the index 
     */
    public Cargo getCargo(int idx) {
        return cargoStack.get(idx);
    }
}

