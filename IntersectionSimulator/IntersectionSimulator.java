/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW4
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The IntersectionSimulator class takes in the inputs from the user and shows us the steps for all the cars to be 
queued in and passed. As well as shows us a model of the intersection
*/

import java.util.Scanner;

public class IntersectionSimulator {
    public static Intersection mainIntersection;
    public static BooleanSourceHW4 probab;
    public static void main(String[] args) {
        Scanner sk = new Scanner(System.in);
        int simTime = 0;
        double prob;
        int numRoads;
        String[] names;
        int[] times;
        String roadName;
        int countRoads = 0;
        int timeRoads = 0;
        boolean nameInArr = false;
        if (args.length >= 5) {
            simTime    = Integer.parseInt(args[0]);
            prob    = Double.parseDouble(args[1]);
            numRoads   = Integer.parseInt(args[2]);
            names = new String[numRoads];
            times = new int[numRoads];
            for (int i = 0; i < numRoads; ++i) {
                names[i] = args[3 + i];
                times[i] = Integer.parseInt(args[3 + numRoads + i]);
            }
            // process args in simTime, prob, numRoads, names, times.
        }
        else {
            System.out.print("Input the simulation time: ");
            simTime = sk.nextInt();
            sk.nextLine();
            System.out.print("Input the arrival probability: ");
            prob = sk.nextDouble();
            probab = new BooleanSourceHW4(prob);
            sk.nextLine();
            System.out.print("Input number of Streets: ");
            numRoads = sk.nextInt();
            sk.nextLine();
            names = new String[numRoads];
            times = new int[numRoads];
            while (countRoads < numRoads) { //Adds roads name into string array
                nameInArr = false;
                System.out.printf("Input Street %d name: ", countRoads+1);
                roadName = sk.nextLine();
                for (int i = 0; i < numRoads; i++) {
                    if (names[i] != null) {
                        if (names[i].equals(roadName)) {
                            nameInArr = true;
                            System.out.println("Duplicate Detected");
                        }
                    }
                }
                if (!nameInArr) {
                    names[countRoads] = roadName;
                    countRoads++;
                }
            }
            for (int j = 0; j < numRoads; j++) {
                System.out.printf("Input max green time for %s: ", names[j]);
                timeRoads = sk.nextInt();
                sk.nextLine();
                times[j] = timeRoads;
            }
            System.out.println("\nStarting Simulation...\n");
            System.out.println("################################################################################");
            simulate(simTime, prob, names, times);
            sk.close();
        }
    }

    public static void simulate(int simulationTime, double arrivalProbability, String[] roadNames, int[] maxGreenTimes) {
        TwoWayRoad[] temp = new TwoWayRoad[roadNames.length];
        for (int i = 0; i < roadNames.length; i++) {
            temp[i] = new TwoWayRoad(roadNames[i], maxGreenTimes[i]);
        }
        mainIntersection = new Intersection(temp);
        int countSteps = 1;
        Vehicle[] returnedarr = null;
        while (true) {
            if (mainIntersection.isEmptyIntersection() && (countSteps > simulationTime)) {
                System.out.println(
                    "################################################################################" + 
                    "################################################################################" +
                    "################################################################################");
                System.out.println("\nSIMULATION SUMMARY:");
                System.out.printf("Total Time: %d steps\n", countSteps-1);
                System.out.printf("Total cars passed: %d cars passed\n", mainIntersection.getTotalReturned());
                System.out.printf("Longest wait time: %d", mainIntersection.longestWeightTime());
                System.out.printf("Total wait time: %d turns\n", mainIntersection.getTotalWaitTime());
                System.out.printf("Average wait time: %.3f turns\n", (1.0*mainIntersection.getTotalWaitTime())/(mainIntersection.getTotalReturned()));
                System.exit(0);
            }
            System.out.printf("Time Step %d: \n", countSteps);
            if (countSteps > simulationTime) {
                mainIntersection.checkMidRightIntersection();
            }
            System.out.printf("%s Light for %s.\n", mainIntersection.getCurrentLightValue(), roadNames[mainIntersection.getLightIndex()]);
            if (mainIntersection.getCountdownTimer() == 0) {
                System.out.printf("Timer = %d\n", mainIntersection.checkCountDown());
            }
            else {
                System.out.printf("Timer = %d\n", mainIntersection.getCountdownTimer());
            }
            System.out.println("Arriving Cars: ");
            if (simulationTime >= countSteps) {
                for (int x = 0; x < roadNames.length; x++) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (probab.occursHW4()) {
                                Vehicle vehicle = new Vehicle(countSteps);
                                System.out.printf("Car[%s] entered %s, ", vehicle.getSerialID(), roadNames[x]);
                                mainIntersection.enqueueVehicle(x, i, j, vehicle);

                            }
                        }
                    }
                }
            }
            else {
                System.out.println("\nCars no longer arriving");
            }
            returnedarr = mainIntersection.timestep();
            System.out.println("\nPassing Cars: ");
            mainIntersection.printPassed(returnedarr, countSteps);
            mainIntersection.longestWeightTimeCalc(returnedarr, countSteps);
            System.out.println();
            mainIntersection.display();
            System.out.println("\nStatistics: ");
            System.out.printf("Cars currently waiting: %d cars\n", mainIntersection.getFullSize());
            System.out.printf("Total cars passed: %d cars passed\n", mainIntersection.getTotalReturned());
            System.out.printf("Total wait time: %d turns\n", mainIntersection.getTotalWaitTime());
            System.out.printf("Average wait time: %.3f turns\n", (1.0*mainIntersection.getTotalWaitTime())/(mainIntersection.getTotalReturned()));
            System.out.println("################################################################################\n");
            countSteps++;
        }
    }
}