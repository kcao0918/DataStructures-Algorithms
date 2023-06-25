/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW4
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Intersection Class simulates an intersection which allows us methods to change the light index, the light value.
and other methods such as the total number of cars recieved and passed
*/

public class Intersection {
    private TwoWayRoad[] roads;
    private int lightIndex; 
    private int countdownTimer;
    private int waitTimes = 0;
    private int totalReturned = 0;
    private int longestWait = 0;
    private final int MAX_ROADS = 4;

    /**
     * Preconditions:
     * initRoads is not null
     * all indexes of initRoads is not null
     * initrRoads.length > MAX_ROADS
     * @param initRoads the array of roads
     * Postconditions: 
     * Creates an intersection object of roads
     */
    public Intersection(TwoWayRoad[] initRoads) {
        if ((initRoads == null) || (initRoads.length > MAX_ROADS)) throw new IllegalArgumentException();
        roads = new TwoWayRoad[initRoads.length];
        for (int i = 0; i < initRoads.length; i++) {
            if (initRoads[i] == null) throw new IllegalArgumentException();
            roads[i] = initRoads[i];
        }
        this.lightIndex = 0;
        this.countdownTimer = roads[0].getGreenTime();
    }

    /**
     * 
     * @return the number of roads
     */
    public int getNumRoads() {
        return roads.length;
    }
    
    /**
     * 
     * @return the index of the road with active light
     */
    public int getLightIndex() {
        return this.lightIndex;
    }

    /**
     * 
     * @return the current countdown
     */
    public int getCountdownTimer() {
        return this.countdownTimer;
    }

    /**
     * 
     * @return the current light value
     */
    public LightValue getCurrentLightValue() {
        return roads[lightIndex].getLightValue();
    }

    /**
     * 
     * @return the array of vehicles that have passed
     * Postconditions:
     * Dequeues all lanes with a green light and returns the passed vehicles array
     */
    public Vehicle[] timestep() {
        if (countdownTimer == 0) {
            lightIndex++;
            if (lightIndex == (getNumRoads())) {
                lightIndex = 0;
            }
            countdownTimer = roads[lightIndex].getGreenTime();
        }
        countdownTimer--;
        Vehicle[] arr = roads[lightIndex].proceed(countdownTimer+1);
        return arr;
    }

    public int checkCountDown() {
        return roads[lightIndex].getGreenTime();
    }
    /**
     * Preconditions:
     * roadIndex is between 0 (inclusive) and 1 (exclusive)
     * wayIndex is between 0 (inclusive) and TwoWayRoad.NUM_WAYS(exclusive)
     * laneIndex is between 0 (inclusive) and TwoWayRoad.NUM_LANES (exclusive)
     * @param roadIndex the index of the specific road
     * @param wayIndex the direction of the lane
     * @param laneIndex the index of the lane
     * @param vehicle the vehicle to be queued in
     */
    public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle) {
        if ((vehicle == null)) {
            throw new IllegalArgumentException();
        }
        if ((roadIndex < 0) || (roadIndex >= roads.length)) {
            throw new IllegalArgumentException();
        }
        if ((wayIndex < 0) || (roadIndex >= roads[0].NUM_WAYS)) {
            throw new IllegalArgumentException();
        }
        if ((laneIndex < 0) || (roadIndex >= roads[0].NUM_LANES)) {
            throw new IllegalArgumentException();
        }
        roads[roadIndex].enqueueVehicle(wayIndex, laneIndex, vehicle);
        System.out.println(roads[roadIndex].printHelper(wayIndex, laneIndex));
    }

    /**
     * Prints the terminal in a formatted manner showing the roads and the casrs
     */
    public void display() {
        for (int i = 0; i < getNumRoads(); i++) {
            System.out.println();
            if (i == lightIndex) {
                roads[i].printForwardBackward();
            }
            else {
                roads[i].printForwardBackwardOther();
            }
        }
    }

    /**
     * 
     * @return the current number of all vehicles on the road
     */
    public int getFullSize() {
        int counter = 0;
        for (int i = 0; i < getNumRoads(); i++) {
            counter += roads[i].countVehiclesOnRoad();
        }
        return counter;
    }

    /**
     * 
     * @param passedCars the array of cars that got passed
     * @param currentTime the current step
     */
    public void printPassed(Vehicle[] passedCars, int currentTime) {
        for (int i = 0; i < passedCars.length; i++) {
            if (passedCars[i] != null) {
                System.out.printf("Car[%d] passes through. Wait time of %d.\n", passedCars[i].getSerialID(), (currentTime)-passedCars[i].getTimeArrived());
                waitTimes += (currentTime - passedCars[i].getTimeArrived());
                totalReturned ++;
            }
        }
    }

    /**
     * 
     * @return the total amount of wait time for all the passed cars
     */
    public int getTotalWaitTime() {
        return waitTimes;
    }

    /**
     * 
     * @return the total number of cars that got passed
     */
    public int getTotalReturned() {
        return totalReturned;
    }

    public void checkMidRightIntersection() {
        roads[lightIndex].checkMidRight();
    }

    public boolean isEmptyIntersection() {
        boolean emp = true;
        for (int i = 0; i < roads.length; i++) {
            if (!roads[i].roadEmpty()) {
                emp = false;
            }
        }
        return emp;
    }

    public void longestWeightTimeCalc(Vehicle[] arr, int countdownTimer) {
        for (int i = 0; i < arr.length; i++) {
            if ((countdownTimer - arr[i].getTimeArrived()) > longestWait) {
                if (arr[i] != null) {
                    longestWait = countdownTimer - arr[i].getTimeArrived();
                }
            }
        }
    }

    public int longestWeightTime() {
        return longestWait;
    }
}