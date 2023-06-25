/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW5
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Zork class allows for the playing and editing of the tree. It is also responsible for calling the methods to taking in the file and
create a tree from the lines. For the editing portion, you are able to add, edit, and remove node from the story.
*/

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Zork {
    public static StoryTree mainTree = new StoryTree();
    static Scanner sk = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Hello and welcome to Zork!\n");
        System.out.print("Please enter the file name: ");
        String filename = sk.nextLine();
        System.out.println("\nLoading game from file... ");
        try {
            mainTree = StoryTree.readTree(filename);
            System.out.println("File loaded!");
        } catch (DataFormatException e) {
            System.out.println("Error");
        } catch (FileNotFoundException x) {
            System.out.printf("The specified file '%s' could not be found or opened.\n", filename);
            mainTree = new StoryTree();
        }
        while (true) {
            System.out.print("Would you like to edit (E), play (P) or quit (Q)? ");
            String choice = sk.nextLine().toUpperCase();
            switch (choice) {
                case "E":
                    mainTree.resetCursor();
                    if (mainTree.hasChild("left")) {
                        try {
                            mainTree.selectChild("1");
                        } catch (Exception e) {
                            System.out.println("Unexpected Error: 1");
                        }
                    }
                    editGame(mainTree);
                    break;
                case "P":
                    mainTree.resetCursor();
                    System.out.println("\n" + mainTree.getCursorOption()+"\n");
                    if (mainTree.hasChild("left")) {
                        try {
                            mainTree.selectChild("1");
                        } catch (NodeNotPresentException e) {
                            System.out.println("Unexpected Error: 2");
                        }
                    }
                    playGame(mainTree);
                    break;
                case "Q":
                    System.out.printf("Game being saved to %s...\n\n", filename);
                    StoryTree.saveTree(filename, mainTree);
                    System.out.println("Save successful!\n"); 
                    System.out.println("Program terminating normally.");
                    sk.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 
     * @param tree to play the game from
     */
    public static void playGame(StoryTree tree) {
        String selectedChoice;
        while (tree.getGameState() == GameState.GAME_NOT_OVER) {
            System.out.println(mainTree.getCursorMessage());
            String[][] tempOptions = mainTree.getOptions();
            tree.winChecker();
            if (mainTree.getGameState() != GameState.GAME_NOT_OVER) {
                System.out.println("\nThanks for playing.\n");
                break;
            }
            for (int i = 0; i < 3; i++) {
                if (tempOptions[i][1] != null) {
                    System.out.print(i+1 + ") ");
                    System.out.print(tempOptions[i][1] + " ");
                    System.out.println();
                }
            }
            System.out.print("Please make a choice. ");
            selectedChoice = sk.nextLine();
            System.out.println();
            try {
                mainTree.selectChild(selectedChoice);
            } catch (NodeNotPresentException e) {
                System.out.println("Node not present");
            }
        }
    }

    /**
     * 
     * @param tree to edit the game from
     */
    public static void editGame(StoryTree tree) {
        String choice = "";
        String newOp = "";
        String newMess = "";
        boolean cont = true;
        while(cont) {
            int test = 0;
            System.out.println("\nZork Editor: ");
            System.out.println(
            "   V: View the cursor's position, option and message. \n" +
            "   S: Select a child of this cursor (options are 1, 2, and 3). \n" + 
            "   O: Set the option of the cursor. \n" +
            "   M: Set the message of the cursor. \n" +
            "   A: Add a child StoryNode to the cursor. \n" +
            "   D: Delete one of the cursor's children and all its descendants. \n" +
            "   R: Move the cursor to the root of the tree. \n" +
            "   Q: Quit editing and return to main menu.\n");
            System.out.print("Please select an option: ");
            choice = sk.nextLine();
            System.out.println();
            
            switch (choice.toUpperCase()) {
                case "V":
                    System.out.println("Position: " + tree.getCursorPosition());
                    System.out.println("Option: " + tree.getCursorOption());
                    System.out.println("Message: " + tree.getCursorMessage());
                    break;
                case "S":
                    String pos = "";
                    System.out.print("Please select a child: "); 
                    System.out.print("[");
                    if (mainTree.hasChild("left")) {
                        System.out.print("1");
                        if (mainTree.hasChild("middle")) {
                            System.out.print(",2");
                            if (mainTree.hasChild("right")) {
                                System.out.print(",3");
                                
                            }
                        }
                    }
                    System.out.print("] ");
                    pos = sk.nextLine();
                    if (!("1234567890").contains(pos)) {
                        System.out.println("no child");
                        cont = false;
                        break;
                    }
                    else {
                        try {
                            mainTree.selectChild(pos);
                        } catch (NodeNotPresentException e) {
                            System.out.printf("\nError. No child %s for the current node.\n", pos);
                        } catch (IllegalArgumentException a) {
                            System.out.printf("Error. No child %s for the current node.", pos);
                        }
                    }
                    break;
                case "O":
                    System.out.printf("Please enter a new option: ");
                    newOp = sk.nextLine();
                    mainTree.setCursorOption(newOp);
                    System.out.println("Option set. \n");
                    break;
                case "M":
                    System.out.printf("Please enter a new message: ");
                    newMess = sk.nextLine();
                    mainTree.setCursorMessage(newMess);
                    mainTree.winChecker();
                    System.out.println("Message set. \n");
                    break;
                case "A":
                    System.out.print("Enter an option: ");
                    newOp = sk.nextLine();
                    System.out.print("Enter a message: ");
                    newMess = sk.nextLine();
                    mainTree.winChecker();
                    if ((mainTree.getGameState() == GameState.GAME_OVER_LOSE) || (mainTree.getGameState() == GameState.GAME_OVER_WIN)) {
                        System.out.printf("Error. No child %s for the current node.\n", mainTree.getCursorPosition().substring(mainTree.getCursorPosition().length()-1, mainTree.getCursorPosition().length()));
                    }
                    else {
                        try {
                            mainTree.addChild(newOp, newMess);
                            System.out.println("\nChild added");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Input");
                        } catch (TreeFullException t) {
                            System.out.println("Error");
                        }
                    }
                    break;
                case "D":
                    String deletedPos = "";
                    System.out.print("Please select a child: "); 
                    System.out.print("[");
                    if (mainTree.hasChild("left")) {
                        System.out.print("1");
                        if (mainTree.hasChild("middle")) {
                            System.out.print(",2");
                            if (mainTree.hasChild("right")) {
                                System.out.print(",3");
                                
                            }
                        }
                    }
                    System.out.print("] ");
                    deletedPos = sk.nextLine();
                    if (!("1234567890").contains(deletedPos)) {
                        System.out.println("no child");
                        cont = false;
                        break;
                    }
                    try {
                        mainTree.removeChild(deletedPos);
                        System.out.println("Subtree deleted");
                    } catch (NodeNotPresentException e) {
                        System.out.printf("Error. No child %s for the current node.", deletedPos);
                    } catch (NumberFormatException n) {
                        System.out.println("no child");
                    } 
                    break;
                case "R":
                    tree.resetCursor();
                    if (mainTree.hasChild("left")) {
                        try {
                            tree.selectChild("1");
                        } catch (Exception e) {

                        }
                    }
                    System.out.println("Cursor moved to root.");
                    break;
                case "Q":
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
}
