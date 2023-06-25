/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW6
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Storage class helps create the storage that will be added. The storage has specified qualities such as an id, client, and content
*/

import java.io.Serializable;

public class Storage implements Serializable {
    static long serialVersionUID;
    private int id;
    private String client;
    private String contents;

    /**
     * 
     * @param id for the storage
     * @param client for the storage
     * @param contents for the storage
     */
    public Storage(int id, String client, String contents) {
        this.id = id;
        this.client = client;
        this.contents = contents;
    }

    /**
     * 
     * @return the id of the storage
     */
    public int getId() {
        return this.id;
    }

     /**
     * 
     * @return the client of the storage
     */
    public String getClient() {
        return this.client;
    }

    /**
     * 
     * @return the content of the storage
     */
    public String getContents() {
        return this.contents;
    }

    /**
     * 
     * @param newid the new id the storage id is set to
     */
    public void setId(int newid) {
        id = newid;
    }

    /**
     * 
     * @param newClient the new client the storage client is set to 
     */
    public void setClient(String newClient) {
        client = newClient;
    }

    /**
     * 
     * @param newContents the new content the storage content is set to
     */
    public void setContents(String newContents) {
        contents = newContents;
    }
}