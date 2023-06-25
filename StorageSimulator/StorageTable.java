/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW6
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The StorageTable class provides methods for the list used in the StorageManager class. Some of these methods include adding and removing storage,
printing out a table of the storage, finding storage based on id or clients, and checking if a storage with a storage id exists already
*/

import java.io.Serializable;
import java.util.ArrayList;

public class StorageTable extends ArrayList<Storage> implements Serializable {
    static long serialVersionUID;
    private ArrayList<Storage> storageList = new ArrayList<Storage>();

    /**
     * Preconditions:
     * storageId ≥ 0 and does not already exist in the table.
     * Storage ≠ null
     * @param storageId the id of the storage
     * @param storage the storage object
     * @throws IllegalArgumentException if any of the preconditions aren't met
     * Postconditions:
     * The Storage has been inserted into the table with the indicated key.
     */
    public void putStorage(int storageId, Storage storage) throws IllegalArgumentException {
        if (storage == null) throw new IllegalArgumentException();
        if (existsInTable(storageId) || storageId < 0) throw new IllegalArgumentException();
        if (storageList.isEmpty()) {
            storageList.add(storage);
        }
        else {
            for (int i = 0; i < storageList.size(); i++) {
                if (i == storageList.size()-1) {
                    if (storageId < storageList.get(i).getId())
                        storageList.add(i, storage);
                    else {
                        storageList.add(storage);
                    }
                    break;
                }
                if (storageId < storageList.get(i).getId()) {
                    storageList.add(i, storage);
                    break;
                }
            }
        }
    }

    /**
     * 
     * @param storageId the id of the specified storage
     * @return the storage
     */
    public Storage getStorage(int storageId) {
        Storage temp = null;
        for (Storage x : storageList) {
                if (x.getId() == storageId) {
                    temp = x;
                }
            }
        return temp;
    }

    /**
     * 
     * @param storageId the id of the specified storage
     * @return true if it exists in the table, false if it doesn't
     */
    public boolean existsInTable(int storageId) {
        for (Storage x : storageList) {
            if (x.getId() == storageId) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * helps print out the table witht the box#, content, and owner
     */
    public void printTable() {
        System.out.printf("\n%-14s%-31s%-31s\n", "Box#", "Contents", "Owner");
        System.out.println("----------------------------------------------------------------");
        for (Storage x : storageList) {
            System.out.printf("%-14d%-31s%s\n", x.getId(), x.getContents(), x.getClient());
        }
        System.out.println();
    }

    /**
     * helps print out the table witht the box#, content, and owner for the specified client
     */
    public void printTableClient(String client) {
        System.out.printf("\n%-14s%-31s%-31s\n", "Box#", "Contents", "Owner");
        System.out.println("----------------------------------------------------------------");
        for (Storage x : storageList) {
            if (x.getClient().equals(client)) {
                System.out.printf("%-14d%-31s%s\n", x.getId(), x.getContents(), x.getClient());
            }
        }
        System.out.println();
    }

    /**
     * 
     * @param storageId the id of the specified storage
     * Postconditions:
     * removes the storage with the id
     */
    public void removeStorage(int storageId) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getId() == storageId) {
                storageList.remove(i);
            }
        }
    }

    /**
     * 
     * @param storageId the id of the specified storage
     * @return the storage if it has the storage id, null if a storage with the storage number doesn't exist
     */
    public Storage findStorage(int storageId) {
        for (Storage x : storageList) {
            if (x.getId() == storageId) {
                return x;
            }
        }
        return null;
    }


}