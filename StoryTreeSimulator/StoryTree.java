/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW5
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The StoryTree class simulates a storytree and allows read and saving trees to files. Also allows for adding, removing, and going through 
the nodes of the story tree
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class StoryTree {
    private StoryTreeNode root;
    private StoryTreeNode cursor;
    private GameState state;
    static Scanner lineReader;

    /**
     * The constructor for the StoryTree. Creates a new StoryTreeNode
     */
    public StoryTree() {
        root = new StoryTreeNode("root", "root", "Hello, and Welcome to Zork!");
        cursor = root;
        state = GameState.GAME_NOT_OVER;
    }

    /**
     * Preconditions:
     * filename is a non-null, non-empty String that points to a file that exists that is readable, and is valid.
     * @param filename the file to be read from
     * @return a new StoryTree from the specified text document
     * @throws DataFormatException if the filename isn't a txt file
     * @throws FileNotFoundException
     * @throws TreeFullException
     */
    public static StoryTree readTree(String filename) throws DataFormatException, FileNotFoundException {
        if ((filename == null) || (filename.equals(""))) {
            throw new IllegalArgumentException();
        }
        if (!(filename.endsWith(".txt"))) {
            throw new DataFormatException();
        }
        StoryTree tempTree = new StoryTree();
        try {
            File sfile = new File(filename);
            lineReader = new Scanner(sfile);
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException(null);
        }
        while(lineReader.hasNextLine()) {
            String[] token = lineReader.nextLine().split("\\| ");
            token[0] = token[0].trim();
            if ((token[0].length() == 1)) {
                try { 
                    tempTree.addChildHelper(token[0], token[1], token[2]);
                } catch (TreeFullException e) {
                    e.printStackTrace();
                }
            }
            else {
                tempTree.resetCursor();
                try {
                    tempTree.selectChild("1");
                } catch (NodeNotPresentException e) {
                    e.printStackTrace();
                }
                for (int i = 2; i < token[0].length(); i += 2) {
                    if (i == token[0].length()-1) {
                        try {
                            tempTree.addChildHelper(token[0], token[1], token[2]);
                        } catch (Exception e) {

                        }
                    }
                    else {
                        try {
                            tempTree.selectChild(token[0].substring(i, i+1));
                        } catch (Exception e) {
                            System.out.println("Null");
                        }   
                    }      
                }
            }
        }
        lineReader.close();
        return tempTree;
    }

    /**
     * Preconditions:
     * tree is non-null
     * file name is non-null, non-empty String
     * @param filename the file to be read from
     * @param tree to be saved to file
     */
    public static void saveTree(String filename, StoryTree tree) {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        if ((filename == null) || (filename.equals(""))) {
            throw new IllegalArgumentException();
        }
        try {
            PrintWriter fileOut = new PrintWriter(filename);
            tree.resetCursor();
            treeWriterHelper(tree.root.getLeftChild(), fileOut);
            fileOut.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("An error occured.");
        }
    }

    /**
     * 
     * @return the game state
     */
    public GameState getGameState() {
        return this.state;
    }

    /**
     * 
     * @return the position of the cursor
     */
    public String getCursorPosition() {
        return cursor.getPosition();
    }

    /**
     * 
     * @return the message of the cursor
     */
    public String getCursorMessage() {
        return cursor.getMessage();
    }

    /**
     * 
     * @return the option of the cursor
     */
    public String getCursorOption() {
        return cursor.getOption();
    }

    /**
     * 
     * @param childPos position
     * @return checks to see if cursor has child at location
     */
    public boolean hasChild(String childPos) {
        if (childPos.equals("left")) {
            if (cursor.getLeftChild() != null) {
                return true;
            }
            return false;
        }
        else if (childPos.equals("middle")) {
            if (cursor.getMiddleChild() != null) {
                return true;
            }
            return false;
        }
        else if (childPos.equals("right")) {
            if (cursor.getRightChild() != null) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 
     * @return an array of a String pair {position, option}
     */
    public String[][] getOptions() {
        String[][] option = new String[3][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    switch (i) {
                        case 0:
                            if (cursor.getLeftChild() != null) {
                                option[i][j] = cursor.getLeftChild().getPosition();
                            }
                            break;
                        case 1:
                            if (cursor.getMiddleChild() != null) {
                                option[i][j] = cursor.getMiddleChild().getPosition();
                            }
                            break;
                        case 2:
                            if (cursor.getRightChild() != null) {
                                option[i][j] = cursor.getRightChild().getPosition();
                            }
                            break;
                        default:
                            break;
                    }
                }
                else {
                    switch (i) {
                        case 0:
                            if (cursor.getLeftChild() != null) {
                                option[i][j] = cursor.getLeftChild().getOption();
                            }
                            break;
                        case 1:
                            if (cursor.getMiddleChild() != null) {
                                option[i][j] = cursor.getMiddleChild().getOption();
                            }
                            break;
                        case 2:
                            if (cursor.getRightChild() != null) {
                                option[i][j] = cursor.getRightChild().getOption();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return option;
    }

    /**
     * 
     * @param message that cursor should be set to 
     */
    public void setCursorMessage(String message) {
        cursor.setMessage(message);
    }

    /**
     * 
     * @param option that cursor should be set to
     */
    public void setCursorOption(String option) {
        cursor.setOption(option);
    }

    /**
     * returns the cursor to the root
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * Preconditions:
     * The child with the indicated position member variable exists as a direct child of the cursor.
     * @param position of the child
     * @throws NodeNotPresentException if a node with the position is not present
     * Postconditions:
     * Cursor references node indicated by position.
     */
    public void selectChild(String position) throws NodeNotPresentException {
        if ((position == null) || position.equals("")) {
            throw new IllegalArgumentException();
        }
        if ((Integer.parseInt(position) != 1 && Integer.parseInt(position) != 2 && Integer.parseInt(position) != 3)) {
            throw new IllegalArgumentException();
        }
        else {
            if (position.equals("1")) {
                if (cursor.getLeftChild() != null) {
                    cursor = cursor.getLeftChild();
                }
                else {
                    throw new NodeNotPresentException(null);
                }
            }
            if (position.equals("2")) {
                if (cursor.getMiddleChild() != null) {
                    cursor = cursor.getMiddleChild();
                }
                else {
                    throw new NodeNotPresentException(null);
                }
            }
            if (position.equals("3")) {
                if (cursor.getRightChild() != null) {
                    cursor = cursor.getRightChild();
                }
                else {
                    throw new NodeNotPresentException("Node with indicated position variable was not found. Custom exception.");
                }
            }
        }
    }

    /**
     * 
     * @param option string to set as the option of the new child.
     * @param message string to set as the message of the new child.
     * @throws TreeFullException if the three child spots are filled
     * Postconditions:
     * Cursor has new child, with specified message and option.
     */
    public void addChild(String option, String message) throws TreeFullException {
        if ((option == null) || option.equals("")) {
            throw new IllegalArgumentException();
        }
        if ((message == null) || message.equals("")) {
            throw new IllegalArgumentException();
        }
        if (cursor == root) {
            cursor.setLeftChild("1", option, message);
        }
        else if (cursor.getLeftChild() == null) {
            cursor.setLeftChild(getCursorPosition()+"-1", option, message);
        }
        else if (cursor.getMiddleChild() == null) {
            cursor.setMiddleChild(getCursorPosition()+"-2", option, message);
        }
        else if (cursor.getRightChild() == null) {
            cursor.setRightChild(getCursorPosition()+"-3", option, message);
        }
        else {
            throw new TreeFullException("All three child spots are already full.");
        }
    }

    /**
     * 
     * @param position
     * @param option
     * @param message
     * @throws TreeFullException
     * Helper for add child
     */
    public void addChildHelper(String position, String option, String message) throws TreeFullException {
        if ((option == null) || option.equals("")) {
            throw new IllegalArgumentException();
        }
        if ((message == null) || message.equals("")) {
            throw new IllegalArgumentException();
        }
        if (cursor == root) {
            cursor.setLeftChild("1", option, message);
        }
        else if (cursor.getLeftChild() == null) {
            cursor.setLeftChild(position, option, message);
        }
        else if (cursor.getMiddleChild() == null) {
            cursor.setMiddleChild(position, option, message);
        }
        else if (cursor.getRightChild() == null) {
            cursor.setRightChild(position, option, message);
        }
        else if ((cursor.getLeftChild() == null) && (cursor.getMiddleChild() == null) && (cursor.getRightChild() == null)) {
            throw new TreeFullException("All three child spots are already full.");
        }
    }

    /**
     * 
     * @param position to be removed
     * @return the removed child
     * @throws NodeNotPresentException if the node is not present
     */
    public StoryTreeNode removeChild(String position) throws NodeNotPresentException {
        boolean exists = false;
        StoryTreeNode temp = new StoryTreeNode(position, position, position);
        if (position.equals("1")) {
            if (cursor.getLeftChild() != null) {
                exists = true;
                temp = cursor.getLeftChild();
                if (cursor.getMiddleChild() != null) {
                    cursor.setLeftChild(cursor.getPosition()+"-"+position, cursor.getMiddleChild().getOption(), cursor.getMiddleChild().getMessage());
                    cursor.removeChildNode("middle");
                    if (cursor.getRightChild() != null) {
                        cursor.setMiddleChild(position, cursor.getRightChild().getOption(), cursor.getRightChild().getMessage());
                        cursor.removeChildNode("right");
                    }
                }
                else {
                    cursor.removeChildNode("left");
                }
            }
        }
        if (position.equals("2")) {
            if (cursor.getMiddleChild() != null) {
                exists = true;
                temp = cursor.getMiddleChild();
                if (cursor.getRightChild() != null) {
                    cursor.setMiddleChild(cursor.getPosition()+"-"+position, cursor.getRightChild().getOption(), cursor.getRightChild().getMessage());
                    cursor.removeChildNode("right");
                }
                else {
                    cursor.removeChildNode("middle");
                }
            }
        }
        if (position.equals("3")) {
            if (cursor.getRightChild() != null) {
                exists = true;
                temp = cursor.getRightChild();
                cursor.removeChildNode("right");
            }
        }
        if (!exists) {
            throw new NodeNotPresentException("The node is not present");
        }
        else {
            return temp;
        }
    }

    /**
     * 
     * @param storyTreeNode the node to be start at
     * @param fileWriter the write to test
     * Postconditions:
     * writes the entire tree into the textfile
     */
    public static void treeWriterHelper(StoryTreeNode storyTreeNode, PrintWriter fileWriter) {
        if (storyTreeNode == null) {
            return;
        }
        else {
            fileWriter.println(storyTreeNode.getPosition().trim() + " | " + storyTreeNode.getOption().trim() + " | " + storyTreeNode.getMessage().trim());
            treeWriterHelper(storyTreeNode.getLeftChild(), fileWriter);
            treeWriterHelper(storyTreeNode.getMiddleChild(), fileWriter);
            treeWriterHelper(storyTreeNode.getRightChild(), fileWriter);
        }
    }

    /**
     * checks to see if the node cursor is on is winning
     */
    public void winChecker() {
        if (cursor.isWinning()) {
            state = GameState.GAME_OVER_WIN;
        }
        else if (cursor.isLosing()) {
            state = GameState.GAME_OVER_LOSE;
        }
        else {
            state = GameState.GAME_NOT_OVER;
        }
    }
}