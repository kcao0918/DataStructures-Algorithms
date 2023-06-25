/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW4
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The VehicleQueue class simulates a line of cars using an ArrayList and give us accessors for the specific cars
as well as allowing us to enqueue and dequeue from the queue
*/

import java.util.ArrayList;

public class VehicleQueue extends ArrayList<Vehicle>{
    private ArrayList<Vehicle> vehicleQueue = new ArrayList<>();

    /**
     * Creates an Arraylist to stimulate a queue of vehicles
     */
    public VehicleQueue() {
        vehicleQueue = new ArrayList<Vehicle>();
    }

    /**
     * 
     * @param v the vehicle to be added
     */
    public void enqueue(Vehicle v) {
        vehicleQueue.add(v);
    }

    /**
     * 
     * @return the vehicle that got dequeued
     */
    public Vehicle dequeue() {
        if (!isEmpty()) {
            return (vehicleQueue.remove(0));
        }
        return null;
    }

    /**
     * 
     * @return the size of the queue
     */
    public int size() {
        return vehicleQueue.size();
    }

    /**
     * 
     * @return true if the queue is empty, false if not
     */
    public boolean isEmpty() {
        return (vehicleQueue.size() == 0);
    }

    /**
     * 
     * @return a formatted string of the queue 
     */
    public String printQueueBackString() {
        String temp = "";
        for (int i = (vehicleQueue.size()-1); i >= 0; i--) {
            temp += String.format("[%02d]", vehicleQueue.get(i).getSerialID());
        }
        return temp;
    }

    public String printQueue() {
        String temp = "";
        for (int i = 0; i < vehicleQueue.size(); i++) {
            temp += String.format("[%02d]", vehicleQueue.get(i).getSerialID());
        }
        return temp;
    }

}