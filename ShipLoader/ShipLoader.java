/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW3
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The ShipLoader class is responsible for stimulating the port with the ship and dock. 
It allows you to load, remove, make new cargo, and many more options
*/

import java.util.Scanner;

public class ShipLoader {
    public static void main(String[] args) {
        String response;
        String nameOfCargo;
        int weightOfCargo;
        String strengthOfContainer;
        int loadDestination;
        int sourceidx;
        int destinationidx;
        CargoStack dock = new CargoStack();

        Scanner sk = new Scanner(System.in);
        System.out.println("// Build ship first.\n" +
        "Cargo Ship Parameters\n" +
        "--------------------------------------------------");
        System.out.print("Number of stacks: ");
        int numOfStacks = sk.nextInt();
        sk.nextLine();
        System.out.print("Maximum height of stacks: ");
        int maxHeightOfStacks = sk.nextInt();
        sk.nextLine();
        System.out.print("Maximum total cargo weight: ");
        int maxTotalCargoWeight = sk.nextInt();
        sk.nextLine();
        CargoShip stackShip = new CargoShip(numOfStacks, maxHeightOfStacks, maxTotalCargoWeight);
        System.out.println("\nCargo ship created." +
        "\nPulling ship in to dock...\n" +
        "Cargo ship ready to be loaded.");
    
        while (true) {
            System.out.println(
                "\n// menu \n" +
                "Please select an option: \n" +
                "C) Create new cargo\n" + 
                "L) Load cargo from dock \n" + 
                "U) Unload cargo from ship \n" +
                "M) Move cargo between stacks \n" +
                "K) Clear dock \n" +
                "P) Print ship stacks \n" +
                "S) Search for cargo \n" +
                "Q) Quit. \n");
            
            response = sk.nextLine();
            Cargo tempCargo = null;

            switch (response) {
                case "C":
                    System.out.print("Enter the name of the cargo: ");
                    nameOfCargo = sk.nextLine();
                    System.out.print("Enter the weight of the cargo: ");  
                    weightOfCargo = sk.nextInt();
                    sk.nextLine();
                    System.out.print("Enter the container strength (F/M/S): ");
                    strengthOfContainer = sk.nextLine();
                    System.out.println();
                    switch (strengthOfContainer) {
                        case "F":
                            tempCargo = new Cargo(nameOfCargo, weightOfCargo, CargoStrength.FRAGILE);
                            break;
                        case "M":
                            tempCargo = new Cargo(nameOfCargo, weightOfCargo, CargoStrength.MODERATE);
                            break;
                        case "S":
                            tempCargo = new Cargo(nameOfCargo, weightOfCargo, CargoStrength.STURDY);
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    if (dock.isStackable(tempCargo)) {
                        dock.push(tempCargo);
                        System.out.printf("\nCargo '%s' pushed onto the dock.\n\n", nameOfCargo);
                        printShipAndDock(stackShip, dock, maxTotalCargoWeight);
                    }
                    else {
                        System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                    }
                    break;
                case "L":
                    try {
                        System.out.print("Select the load destination stack index: ");
                        loadDestination = sk.nextInt();
                        sk.nextLine();
                        stackShip.pushCargo(dock.peek(), loadDestination);
                        System.out.printf("\nCargo '%s' moved from dock to stack %d.\n\n", dock.peek().getName(), loadDestination);
                        dock.pop();
                        printShipAndDock(stackShip, dock, maxTotalCargoWeight);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid index");
                    } catch (FullStackException f) {
                        System.out.println("Operation failed! Cargo stack is at maximum height. ");
                    } catch (ShipOverweightException s) {
                        System.out.println("Operation failed! Cargo would put ship overweight. ");
                    } catch (CargoStrengthException c) {
                        System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                    }
                    break;
                case "U":
                    System.out.println("Select the unload source stack index: \n");
                    sourceidx = sk.nextInt();
                    sk.nextLine();
                    try {
                        tempCargo = stackShip.popCargo(sourceidx);
                        dock.push(tempCargo);
                        System.out.printf("\nCargo '%s' moved from stack %d to dock.\n\n", tempCargo.getName(), sourceidx);
                        printShipAndDock(stackShip, dock, maxTotalCargoWeight);
                    } catch (EmptyStackException e) {
                        System.out.println("The stack is empty");
                        e.printStackTrace();
                    }
                    break;
                case "M":
                    System.out.print("Source stack index: ");
                    sourceidx = sk.nextInt();
                    sk.nextLine();
                    System.out.print("\nDestination stack index: ");
                    destinationidx = sk.nextInt();
                    sk.nextLine();
                    try {
                        tempCargo = stackShip.popCargo(sourceidx);
                        stackShip.pushCargo(tempCargo, destinationidx);
                        System.out.printf("\nCargo '%s' moved from stack %d to stack %d.\n\n",tempCargo.getName(), sourceidx, destinationidx);
                        printShipAndDock(stackShip, dock, maxTotalCargoWeight);
                    } catch (FullStackException f) {
                        System.out.println("Operation failed! Cargo stack is at maximum height. ");
                    } catch (CargoStrengthException c) {
                        System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                    } catch (EmptyStackException e) {
                        System.out.println("The source stack is empty");
                    } catch (Exception e) {
                        System.out.println("Invalid Index");
                    }
                    break;
                case "K":
                    dock = new CargoStack();
                    System.out.println("\nDock cleared.\n");
                    printShipAndDock(stackShip, dock, maxTotalCargoWeight);
                    break;
                case "P":
                    printShipAndDock(stackShip, dock, maxTotalCargoWeight);
                    break;
                case "S":
                    System.out.print("Enter the name of the cargo: ");
                    nameOfCargo = sk.nextLine();
                    System.out.println();
                    stackShip.findAndPrint(nameOfCargo);
                    break;
                case "Q": //Exits out of program
                    System.out.println("Program terminating normally");
                    sk.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error Invalid Input");
                    break;

            }
        }
    }

    /**
     * 
     * @param stackShip 
     * @param dock
     * @param maxTotalCargoWeight
     * Postcondition: Prints out the stacks and dock, as well as the dock
     */
    public static void printShipAndDock(CargoShip stackShip, CargoStack dock, int maxTotalCargoWeight){
        stackShip.printAllStacks();
        System.out.println("Dock: " + dock.getStackList()); 
        System.out.printf("\nTotal Weight = %s / %s\n", stackShip.getWeightOfShip(), maxTotalCargoWeight);   
    }
}

