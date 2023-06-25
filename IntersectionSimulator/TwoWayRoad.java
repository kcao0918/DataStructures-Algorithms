/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW4
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The TwoWayRoad class simulates a backwards and forward road using a 2d array and provides methods to add/remove
cars from the array
*/

public class TwoWayRoad {
    public final int FORWARD_WAY = 0;
    public final int BACKWARD_WAY = 1;
    public final int NUM_WAYS = 2;
    public final int LEFT_LANE = 0;
    public final int MIDDLE_LANE = 1;
    public final int RIGHT_LANE = 2;
    public final int NUM_LANES = 3;
    private final int BLEFT_LANE = 2;
    private final int BRIGHT_LANE = 0;

    private String name;
    private int greenTime;
    private int leftSignalGreenTime;
    private VehicleQueue[][] lanes;
    private LightValue lightValue;

    /**
     * Preconditions:
     * initGreenTime > 0
     * @param initName the name of the road
     * @param initGreenTime the amount of time the green light will be on the road
     * Postconditions:
     * creates a code with the lanes initialized to empty queues
     */
    public TwoWayRoad(String initName, int initGreenTime) {
        if ((initGreenTime <= 0) || (initName == null)) throw new IllegalArgumentException();
        this.name = initName;
        this.greenTime = initGreenTime;
        this.lightValue = LightValue.GREEN;
        leftSignalGreenTime = (int) (1.0/NUM_LANES * initGreenTime);
        lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
        for (int i = 0; i < NUM_WAYS; i++) {
            for (int j = 0; j < NUM_LANES; j++) {
                lanes[i][j] = new VehicleQueue();
            }
        }
    }

    /**
     * 
     * @return the current green time
     */
    public int getGreenTime() {
        return this.greenTime;
    }

    /**
     * 
     * @return the current value of the light
     */
    public LightValue getLightValue() {
        return this.lightValue;
    }
    /**
     * Preconditions:
     * The TwoWayRoad object is instantiated
     * @param timerVal the current timer value
     * @return an array with all dequeued cars
     */
    public Vehicle[] proceed(int timerVal) {
        if (timerVal <= 0) {
            throw new IllegalArgumentException();
        }
        if (timerVal == greenTime) {
            lightValue = LightValue.GREEN;
        }
        else if (timerVal <= leftSignalGreenTime) {
            lightValue = LightValue.LEFT_SIGNAL;
        }
        int countPassedCars = 0;
        Vehicle[] arrPassedCars = new Vehicle[4];
        switch (lightValue) {
            case GREEN:
                if (!lanes[FORWARD_WAY][MIDDLE_LANE].isEmpty()) {
                    arrPassedCars[countPassedCars] = lanes[FORWARD_WAY][MIDDLE_LANE].dequeue();
                    countPassedCars++;
                }
                if (!lanes[FORWARD_WAY][RIGHT_LANE].isEmpty()) {
                    arrPassedCars[countPassedCars] = lanes[FORWARD_WAY][RIGHT_LANE].dequeue();
                    countPassedCars++;
                }
                if (!lanes[BACKWARD_WAY][MIDDLE_LANE].isEmpty()) {
                    arrPassedCars[countPassedCars] = lanes[BACKWARD_WAY][MIDDLE_LANE].dequeue();
                    countPassedCars++;
                }
                if (!lanes[BACKWARD_WAY][BRIGHT_LANE].isEmpty()) {
                    arrPassedCars[countPassedCars] = lanes[BACKWARD_WAY][BRIGHT_LANE].dequeue();
                    countPassedCars++;
                }
                break;
            case LEFT_SIGNAL:
                if (!lanes[FORWARD_WAY][LEFT_LANE].isEmpty()) {
                    arrPassedCars[countPassedCars] = lanes[FORWARD_WAY][LEFT_LANE].dequeue();
                    countPassedCars++;
                }
                if (!lanes[BACKWARD_WAY][BLEFT_LANE].isEmpty()) {
                    arrPassedCars[countPassedCars] = lanes[BACKWARD_WAY][BLEFT_LANE].dequeue();
                    countPassedCars++;
                }
                break;
            default:
                break;
        }
        return arrPassedCars;
    }

    /**
     * Preconditions:
     * The TwoWayRoad obejct should be instantiated 
     * @param wayIndex the direction of the car
     * @param laneIndex the lane the car is in
     * @param vehicle to be enqueued
     * Postconditions:
     * Given vehicle is not null, and the position is invalid, 
     * the car should be added to the end of the queue
     */
    public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle) {
        if ((wayIndex > 1) || (wayIndex < 0) || (laneIndex < 0) || (laneIndex > 2) || (vehicle == null)) {
            throw new IllegalArgumentException();
        }
        if (wayIndex == 0) {
            lanes[wayIndex][laneIndex].enqueue(vehicle);
        }
        else {
            if (laneIndex == LEFT_LANE) {
                lanes[wayIndex][BRIGHT_LANE].enqueue(vehicle);
            }
            else if (laneIndex == RIGHT_LANE) {
                lanes[wayIndex][BLEFT_LANE].enqueue(vehicle);
            }
            else {
                lanes[wayIndex][MIDDLE_LANE].enqueue(vehicle);
            }
        }
    }

    /**
     * 
     * @param wayIndex the direction of the lane    
     * @param laneIndex the index of the lane
     * @return true if the lane is empty, false if the lane has a car
     */
    public boolean isLaneEmpty(int wayIndex, int laneIndex) {
        if ((wayIndex > 1) || (wayIndex < 0) || (laneIndex < 0) || (laneIndex > 2)) {
            throw new IllegalArgumentException();
        }
        return (lanes[wayIndex][laneIndex].size() == 0);
    }

    /**
     * 
     * @return the number of vehicles currently on the road
     */
    public int countVehiclesOnRoad() {
        int counter = 0;
        for (int i = 0; i < NUM_WAYS; i++) {
            for (int j = 0; j < NUM_LANES; j++) {
                counter += lanes[i][j].size();
            }
        }
        return counter;
    }

    /**
     * 
     * @return if the road is empty
     */
    public boolean roadEmpty() {
        for (int i = 0; i < NUM_WAYS; i++) {
            for (int j = 0; j < NUM_LANES; j++) {
                if (lanes[i][j].isEmpty() == false) {
                    return false;
                }
            }
        }
        return true;
     }

    /**
     * Print helper method
     */
    public void printForwardBackward() {
        System.out.println(name + ":");
        System.out.println("                       FORWARD               BACKWARD");
        System.out.println("==============================               ===============================");
        for (int j = 0; j < NUM_LANES; j++) {
            for (int i = 0; i < NUM_WAYS; i++) {
                if (i == FORWARD_WAY) {
                    System.out.printf("%30s", lanes[i][j].printQueueBackString());
                    if (j == LEFT_LANE) {
                        System.out.print(" [L] ");
                    }
                    else if (j == MIDDLE_LANE) {
                        System.out.print(" [M] ");
                    }
                    else if (j == RIGHT_LANE) {
                        System.out.print(" [R] ");
                    }
                    if (lightValue == LightValue.GREEN) {
                        if (j == 0) {
                            System.out.print("x");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                    else if (lightValue == LightValue.LEFT_SIGNAL) {
                        if ((j == 1) || (j == 2)) {
                            System.out.print("x");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                    else {
                        System.out.print("x");
                    }
                    System.out.print("   ");
                }
                else {
                    if (lightValue == LightValue.GREEN) {
                        if (j == 2) {
                            System.out.print("x");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                    else if (lightValue == LightValue.LEFT_SIGNAL) {
                        if ((j == 1) || (j == 0)) {
                            System.out.print("x");
                        }
                        else {
                            System.out.print(" ");
                        }
                    }
                    else {
                        System.out.print("x");
                    }
                    if (j == LEFT_LANE) {
                        System.out.printf(" [R] %s", lanes[i][BRIGHT_LANE].printQueue());
                    }
                    else if (j == MIDDLE_LANE) {
                        System.out.printf(" [M] %s", lanes[i][MIDDLE_LANE].printQueue());
                    }
                    else if (j == RIGHT_LANE) {
                        System.out.printf(" [L] %s", lanes[i][BLEFT_LANE].printQueue());
                    }
                }
            }
            System.out.println("\n==============================               ===============================");
        }
    }

        /**
     * Print helper method
     */
    public void printForwardBackwardOther() {
        System.out.println(name + ":");
        System.out.println("                       FORWARD               BACKWARD");
        System.out.println("==============================               ===============================");
        for (int j = 0; j < NUM_LANES; j++) {
            for (int i = 0; i < NUM_WAYS; i++) {
                if (i == FORWARD_WAY) {
                    System.out.printf("%30s", lanes[i][j].printQueueBackString());
                    if (j == LEFT_LANE) {
                        System.out.print(" [L] ");
                    }
                    else if (j == MIDDLE_LANE) {
                        System.out.print(" [M] ");
                    }
                    else if (j == RIGHT_LANE) {
                        System.out.print(" [R] ");
                    }
                    System.out.print("x   x");
                }
                else {
                    if (j == LEFT_LANE) {
                        System.out.printf(" [R] %s", lanes[i][BRIGHT_LANE].printQueue());
                    }
                    else if (j == MIDDLE_LANE) {
                        System.out.printf(" [M] %s", lanes[i][MIDDLE_LANE].printQueue());
                    }
                    else if (j == RIGHT_LANE) {
                        System.out.printf(" [L] %s", lanes[i][BLEFT_LANE].printQueue());
                    }
                }
            }
            System.out.println("\n==============================               ===============================");
        }
    }

    /**
     * 
     * @param wayIndex the direction of the lane
     * @param laneIndex the index of the lane
     * @param vehicle the vehicle to be added in
     * @return a formmated string showing the car that got added
     */
    public String printHelper(int wayIndex, int laneIndex) {
        String direct = "";
        String way = "";
        switch(wayIndex) {
            case 0:
                direct = "FORWARD";
                break;
            case 1:
                direct = "BACKWARD";
                break;
            default:
                break;
        }
        if (direct.equals("FORWARD")) {
            switch (laneIndex) {
                case LEFT_LANE:
                    way = "LEFT";
                    break;
                case MIDDLE_LANE:
                    way = "MIDDLE";
                    break;
                case RIGHT_LANE:
                    way = "RIGHT";
                    break;
                default:
                    break;
            }
        }
        if (direct.equals("BACKWARD")) {
            switch (laneIndex) {
                case BLEFT_LANE:
                    way = "LEFT";
                    break;
                case MIDDLE_LANE:
                    way = "MIDDLE";
                    break;
                case BRIGHT_LANE:
                    way = "RIGHT";
                    break;
                default:
                    break;
            }
        }
        String temp = "";
        temp += String.format("going %s in %s lane", direct, way);
        return temp;
    }

    /**
     * checks if the middle and right are empty or not
     */
    public void checkMidRight() {
        if (lanes[FORWARD_WAY][MIDDLE_LANE].isEmpty() && lanes[FORWARD_WAY][RIGHT_LANE].isEmpty()) {
            if (lanes[BACKWARD_WAY][MIDDLE_LANE].isEmpty() && lanes[BACKWARD_WAY][BRIGHT_LANE].isEmpty()) {
                lightValue = LightValue.LEFT_SIGNAL;
            }
        }
    }
}
