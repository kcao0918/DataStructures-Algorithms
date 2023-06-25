/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW6
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The StorageManager class simulates a list of storages and gives options for what to do with the list. Allows for the putting in and 
removing of boxes. Also allows for the printing of all boxes or boxes of a specified client as well as finding specific boxes based
on specified id. At the beginning the class creates a list from an object file and at the end the class saves the StorageTable 
to an object file or deletes a file
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class StorageManager {
    public static void main(String[] args) throws ClassNotFoundException {
        StorageTable mainTable = new StorageTable();
        Scanner sk = new Scanner(System.in);
        System.out.println("Hello, and welcome to Rocky Stream Storage Manager\n");
        String choice;
        int storageId;
        String storageClient;
        String storageContents;
        Storage tempStorage;
        try {
            FileInputStream filein = new FileInputStream("storage.obj");
            ObjectInputStream inStream = new ObjectInputStream(filein);
            mainTable = (StorageTable) inStream.readObject();
            inStream.close();
        } catch (IOException e) {

        }
        while (true) {
            System.out.println(
            "P - Print all storage boxes\n" + 
            "A - Insert into storage box\n" +
            "R - Remove contents from a storage box\n" +
            "C - Select all boxes owned by a particular client\n" +
            "F - Find a box by ID and display its owner and contents\n" +
            "Q - Quit and save workspace\n" +
            "X - Quit and delete workspace\n");
            System.out.print("Please select an option: ");
            choice = sk.nextLine();

            switch (choice.toUpperCase()) {
                case "P":
                    mainTable.printTable();
                    break;
                case "A":
                    System.out.print("Please enter id: ");
                    storageId = sk.nextInt();
                    sk.nextLine();
                    System.out.print("Please enter client: ");
                    storageClient = sk.nextLine();
                    System.out.print("Please enter contents: ");
                    storageContents = sk.nextLine();
                    tempStorage = new Storage(storageId, storageClient, storageContents);
                    try {
                        mainTable.putStorage(storageId, tempStorage);
                        FileOutputStream fileout = new FileOutputStream("storage.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(fileout);
                        outStream.writeObject(mainTable);
                        outStream.close();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println("\nStorage " + storageId + " set!\n");
                    break;  
                case "R":
                    System.out.print("Please enter ID: ");
                    storageId = sk.nextInt();
                    sk.nextLine();
                    mainTable.removeStorage(storageId);
                    System.out.printf("Box " + storageId + " is now removed.\n\n");
                    break;
                case "C":
                    System.out.print("Please enter the name of the client: ");
                    storageClient = sk.nextLine();
                    mainTable.printTableClient(storageClient);
                    break;
                case "F":
                    System.out.print("Please enter ID: ");
                    storageId = sk.nextInt();
                    sk.nextLine();
                    tempStorage = mainTable.findStorage(storageId);
                    if (tempStorage == null) {
                        System.out.println("The specified storage does not exist");
                    }
                    else {
                        System.out.printf("Box %s\n", tempStorage.getId());
                        System.out.printf("Contents: %s\n", tempStorage.getContents());
                        System.out.printf("Owner: %s\n\n", tempStorage.getClient());
                    }
                    break;
                case "Q":
                    try {
                        FileOutputStream fileout = new FileOutputStream("storage.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(fileout);
                        outStream.writeObject(mainTable);
                        outStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sk.close();
                    System.out.println("Storage Manager is quitting, current storage is saved for next session.\n");
                    System.exit(0);
                    break;
                case "X":
                    File storageFile = new File("storage.obj");
                    storageFile.delete();
                    sk.close();
                    System.out.println("Storage Manager is quitting, all data is being erased.\n");
                    System.exit(0);
                    break;
            }
        }
    }
}
