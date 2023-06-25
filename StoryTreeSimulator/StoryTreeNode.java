/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW5
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The StoryTreeNode class simulates the creation of a single node in a story tree. It allows for the adding of "children" in the tree and
is also responsible for checking to see if a specific node is winning or not
*/


public class StoryTreeNode {
    public static String WIN_MESSAGE = "YOU WIN";
    public static String LOSE_MESSAGE = "YOU LOSE";
    private String position;
    private String option;
    private String message;
    private StoryTreeNode leftChild;
    private StoryTreeNode middleChild;
    private StoryTreeNode rightChild;

    /**
     * 
     * @param position 
     * @param option 
     * @param message
     * Postcondition:
     * Creates a StoryTreeNode with a specified position, option, and message
     */
    public StoryTreeNode(String position, String option, String message) {
        this.position = position;
        this.option = option;
        this.message = message;
        this.leftChild = null;
        this.middleChild = null;
        this.rightChild = null;
    }
    
    /**
     * 
     * @return the position of the StoryTreeNode
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * 
     * @return the option of the StoryTreeNode
     */
    public String getOption() {
        return this.option;
    }

    /**
     * 
     * @return the message of the StoryTreeNode
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 
     * @return the leftChild of the StoryTreeNode
     */
    public StoryTreeNode getLeftChild() {
        return this.leftChild;
    }

    /**
     * 
     * @return the middleChild of the StoryTreeNode
     */
    public StoryTreeNode getMiddleChild() {
        return this.middleChild;
    }

    /**
     * 
     * @return the rightChild of the StoryTreeNode
     */
    public StoryTreeNode getRightChild() {
        return this.rightChild;
    }

    /**
     * 
     * @param newPosition the new position
     * Postcondition:
     * Assigns the position to a new position
     */
    public void setPosition(String newPosition) {
        this.position = newPosition;
    }

    /**
     * 
     * @param newPosition the new option
     * Postcondition:
     * Assigns the option to a new option
     */
    public void setOption(String newOption) {
        this.option = newOption;
    }

    /**
     * 
     * @param newPosition the new message
     * Postcondition:
     * Assigns the message to a new message
     */
    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    /**
     * 
     * @param position
     * @param option
     * @param message
     * Postcondition:
     * Assigns the left child to a new StoryTreeNode
     */
    public void setLeftChild(String position, String option, String message) {
        this.leftChild = new StoryTreeNode(position, option, message);
    }

    /**
     * 
     * @param position
     * @param option
     * @param message
     * Postcondition:
     * Assigns the middle child to a new StoryTreeNode
     */
    public void setMiddleChild(String position, String option, String message) {
        this.middleChild = new StoryTreeNode(position, option, message);
    }

    /**
     * 
     * @param position
     * @param option
     * @param message
     * Postcondition:
     * Assigns the right child to a new StoryTreeNode
     */
    public void setRightChild(String position, String option, String message) {
        this.rightChild = new StoryTreeNode(position, option, message);
    }

    /**
     * 
     * @return true if the StoryTreeNode is a leaf, false if not
     */
    public boolean isLeaf() {
        if ((leftChild == null) && (middleChild == null) && (rightChild == null)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @return true if the message is a winning message, false if not
     */
    public boolean isWinning() {
        if (message.contains(WIN_MESSAGE)) {
                return true;
        }
        return false;
    }

    /**
     * 
     * @return true if the message is a losing message, false if not
     */
    public boolean isLosing() {
        if (message.contains(LOSE_MESSAGE)) {
                return true;
        }
        return false;
    }

    /**
     * 
     * @param pos to remove child node from
     */
    public void removeChildNode(String pos) {
        switch (pos) {
            case "left":
                this.leftChild = null;
                break;
            case "middle": 
                this.middleChild = null;
                break;
            case "right":
                this.rightChild = null;
            default:
                break;
        }
    }
}