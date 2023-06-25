/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW2
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The SlideList file stimulates a list of slides and gives you options such as insert/removing slides
and gives you data such as the number of slides and duration. It also lets you control a cursor to 
choose between different slides
*/


public class SlideList {
    private SlideListNode head;
    private SlideListNode tail;
    private SlideListNode cursor;
    private int countSlides = 0;

    /**
     * Initializes the SlideList with head, tail, and cursor set to null
     */
    public SlideList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    /**
     * 
     * @return the total number of slides
     */
    public int size() { 
        return countSlides;
    }

    /**
     * 
     * @return the sum of the total durations in the slide
     */
    public double duration() {
        double countDuration = 0;
        SlideListNode tempCursor = head;
        while (tempCursor != null) {
            countDuration += tempCursor.getData().getDuration();
            if (tempCursor.getNext() != null) {
                tempCursor = tempCursor.getNext();
            }
            else {
                break;
            }
        }
        return countDuration;
    }

    
    /**
     * 
     * @return the total number of bullet points for all the slides
     */
    public int numBullets() {
        int countBullets = 0;
        SlideListNode tempCursor = head;
        while (tempCursor != null) {
            countBullets += tempCursor.getData().getNumBullets();
            if (tempCursor.getNext() != null) {
                tempCursor = tempCursor.getNext();
            }
            else {
                break;
            }
        }
        return countBullets;
    }

    /**
     * 
     * @return the reference to the slide at the current cursor
     */
    public Slide getCursorSlide() {
        return cursor.getData();
    }

    /**
     * if head isn't null, cursor is equal to head
     * if it is null, cursor is null
     */
    public void resetCursorToHead() {
        cursor = head;
    }

    /**
     * @throws EndOfListException if cursor is at tail, or if cursor and tail are both null
     * moves the cursor forward 
     */
    public void cursorForward() throws EndOfListException {
        if ((cursor == tail) || ((cursor == null) && (tail == null))) {
            throw new EndOfListException("The cursor is equal to the tail");
        }
        else {
            cursor = cursor.getNext();
        }
    }

    /**
     * @throws EndOfListException if cursor is at head, or if cursor and head are both null
     * moves the cursor backwards
     */
    public void cursorBackwards() throws EndOfListException {
        if ((cursor == head) || ((cursor == null) && (head == null))) {
            throw new EndOfListException("The cursor is equal to the head");
        }
        else {
            cursor = cursor.getPrev();
        }
    }

    /**
     * Preconditions: newSlide is not null
     * 
     * @param newSlide the slide that is inserted before the cursor
     * adds slide before cursor
     */
    public void insertBeforeCursor(Slide newSlide) {
        if (newSlide == null) throw new IllegalArgumentException("newSlide is equal to null");
        SlideListNode temp = new SlideListNode(newSlide);
        SlideListNode idxchecker = head;
        countSlides++;
        if (tail == null) {
            head = temp;
            tail = temp;
            cursor = temp;
        }
        if (cursor == head) {
            head.setPrev(temp);
            temp.setNext(head);
            head = temp;
            cursor = head;
        }
        else {
            for (int i = 0; i < countSlides; i++) {
                if (idxchecker.getNext() == cursor) {
                    idxchecker.setNext(temp);
                    cursor.setPrev(temp);
                    temp.setNext(cursor);
                    temp.setPrev(idxchecker);
                    cursor = temp;
                    break;
                }
                else {
                    temp = temp.getNext();
                }
            }
        }
    }

    /**
     * Preconditions: newSlide is not null
     * 
     * @param newSlide the slide that is added after the tail
     * adds a slide 
     */
    public void appendToTail(Slide newSlide) {
        if (newSlide == null) throw new IllegalArgumentException("newSlide is equal to null");
        SlideListNode temp = new SlideListNode(newSlide);
        if (tail == null) {
            head = temp;
            tail = temp;
            cursor = temp;
            countSlides++;
        }
        else {
            temp.setPrev(tail);
            tail.setNext(temp);
            tail = tail.getNext();
            countSlides++;
        }
    }

    /**
     * Precondiitons: cursor is not null
     * 
     * @return the reference to the slide that was at the SlideListNode that was just removed
     * @throws EndOfListException
     */
    public Slide removeCursor() throws EndOfListException {
        if (cursor == null) throw new EndOfListException("cursor is null");
        Slide removedSlide = cursor.getData();
        if ((cursor == head) && (head != null)) {
            cursor = head.getNext();
            head = cursor;
        }
        else if (cursor == tail) {
            tail = tail.getPrev();          
            cursor = tail;
            tail.setNext(null);  
            cursor.setNext(null);
        }
        else {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();
        }
        countSlides--;
        return removedSlide;

    }

    /**
     * 
     * @return a string with a formmated list of all the slides
     */
    public String printSlides() {
        String empty = "";
        SlideListNode tempCursor = head;
        for (int i = 0; i < countSlides; i++) {
            if (tempCursor.getData() == cursor.getData()) {
                empty += String.format("-> %d       %s", i+1, tempCursor.getData().toString());
            }
            else {
                empty += String.format("   %d       %s", i+1, tempCursor.getData().toString());
            }
            tempCursor = tempCursor.getNext();
        }
        return empty;
    }
}
