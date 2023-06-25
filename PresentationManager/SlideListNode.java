/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW2
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The SlideListNode file stimulates an ArrayList and connects all of the slides to each other
Allows for the selecting of previous and next slides linked
*/


public class SlideListNode {
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;
    
    /**
     * Preconditions: initData is not null
     * 
     * @param initData 
     * SlideListNode is initialized to wrap the indicated Slide, prev/next are set to null
     */
    public SlideListNode(Slide initData) {
        if (initData == null) throw new IllegalArgumentException("initdata is null");
        this.data = initData;
        this.prev = null;
        this.next = null;
    }

    /**
     * 
     * @return the reference of the data member variable
     */
    public Slide getData() {
        return this.data;  
    }

    /**
     * 
     * @return the next member variable
     */
    public SlideListNode getNext() {
        return this.next;
    }

    /**
     * 
     * @return the prev member variable
     */
    public SlideListNode getPrev() {
        return this.prev;
    }

    /**
     * Preconditions: newData is not null
     * 
     * @param newData
     * set the Slide member object to the newData 
     */
    public void setData(Slide newData) {
        if (newData == null) throw new IllegalArgumentException("newData is null");
        else {
            this.data = newData;
        }
    }

    /**
     * @param newNext the new SlideListNode object that next member variable is updated to
     * updates the next member varibale with newNext
     */
    public void setNext(SlideListNode newNext) {
        this.next = newNext;
    }

    /**
     * 
     * @param newPrev
     * @return the new SlideListNode object that prev member variable is updated to
     */
    public void setPrev(SlideListNode newPrev) {
        this.prev = newPrev;
    }
}
