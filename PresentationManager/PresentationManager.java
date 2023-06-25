/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW2
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Presentation manager shows you the menu and lets you manage slides and the bullets
*/


import java.util.Scanner;

public class PresentationManager {
    private static SlideList listOfSlides = new SlideList();
    public static void main(String[] args) {
        Scanner sk = new Scanner(System.in);
        String title = "";
        double duration;
        String bulletToAdd;
        String choice;
        String selection;
        String dore;
        Slide temp;
        String tempString;
        while (true) {
            System.out.println(
            "\nWelcome to PresentationManager!\n\n" +
            "Please select an option:\n" +
            "F) Move cursor forward \n" + 
            "B) Move cursor backward \n" + 
            "D) Display cursor slide \n" +
            "E) Edit cursor slide \n" +
            "P) Print presentation summary \n" +
            "A) Append new slide to tail \n" +
            "I) Insert new slide before cursor \n" +
            "R) Remove slide at cursor\n" +
            "H) Reset cursor to head \n" +
            "Q) Quit \n" +
            "\nSelect a menu option: ");

            String response = sk.nextLine();
            boolean cont = true;
            boolean add = true;
            switch(response) {
                case "F":
                    try {
                        listOfSlides.cursorForward();
                        System.out.printf("\nThe cursor moved forward to slide \"%s\"\n", listOfSlides.getCursorSlide().getTitle());
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move forward\n");
                    }
                    break;
                case "B":
                    try {
                        listOfSlides.cursorBackwards();
                        System.out.printf("\nThe cursor moved backward to slide \"%s\"\n", listOfSlides.getCursorSlide().getTitle());
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move backward\n");
                    }
                    break;
                case "D":
                    if (listOfSlides.size() > 0) {
                        System.out.printf(" ==============================================\n" +
                        "  %s\n" + " ==============================================\n", listOfSlides.getCursorSlide().getTitle());
                        for (int i = 1; i < listOfSlides.getCursorSlide().getNumBullets()+1; i++) {
                            if (listOfSlides.getCursorSlide().getBullet(i) != null) {
                                System.out.printf(" %d. %s\n", i, listOfSlides.getCursorSlide().getBullet(i));
                            }
                        }
                        System.out.println(" ==============================================\n");
                    }
                    else {
                        System.out.println("Empty slideshow");
                    }
                    break;
                case "E":
                    if (listOfSlides.size() > 0) {
                        System.out.println("Edit title, duration, bullets? (t/d/b) ");
                        selection = sk.nextLine();
                        if (selection.equals("b")) {
                            System.out.print("Bullet index: ");
                            int idx = sk.nextInt();
                            sk.nextLine();
                            try {
                                tempString = listOfSlides.getCursorSlide().getBullet(idx);
                            } catch (Exception e) {
                                System.out.println("Invalid index");
                                break;
                            }
                            System.out.println("Delete or edit? (d/e) ");
                            dore = sk.nextLine();
                            if (dore.equals("e")) {
                                System.out.println("What do you want to edit the bullet to? ");
                                String newbullet = sk.nextLine();
                                listOfSlides.getCursorSlide().setBullet(newbullet, idx);
                                System.out.printf("Bullet %d has been edited \n", idx);
                            }
                            else if (dore.equals("d")) {
                                listOfSlides.getCursorSlide().setBullet(null, idx);
                                System.out.printf("Bullet %d has been deleted \n", idx);
                            }
                            else {
                                System.out.println("Invalid option");
                            }
                        }
                        else if (selection.equals("t")) {
                            System.out.println("What do you want to edit the Slide to? ");
                            String newTitle = sk.nextLine();
                            listOfSlides.getCursorSlide().setTitle(newTitle);
                            System.out.printf("Title has been edited to %s \n", newTitle);
                        }
                        else if (selection.equals("d")) {
                            try {
                                System.out.println("What do you want to edit the duration to");
                                double newDuration = sk.nextDouble();
                                sk.nextLine();
                                listOfSlides.getCursorSlide().setDuration(newDuration);
                                System.out.printf("Duration has been edited to %f \n", newDuration);
                            } catch (Exception e) {
                                System.out.println("Invalid duration");
                                break;
                            }
                        }
                        else {
                            System.out.println("Invalid option");
                        }
                    }
                    else {
                        System.out.println("Empty slideshow");
                    }
                    break;
                case "P":  
                    if (listOfSlides.size() == 0) {
                        System.out.println("==============================================\n" +
                        "Total: 0 slide(s), 0.0 minute(s), 0 bullet(s)");
                    }
                    else {
                        System.out.println("Slideshow Summary:\n" 
                        + "==============================================\n"
                        + "  Slide    Title         Duration   Bullets\n"
                        + "----------------------------------------------");
                        System.out.print(listOfSlides.printSlides());
                        System.out.println("==============================================");
                        System.out.printf("Total: %d slide(s), %.1f minute(s), %d bullet(s)", 
                        listOfSlides.size(), listOfSlides.duration(), listOfSlides.numBullets());
                        System.out.println("\n==============================================\n");
                    }
                    break;
                case "A":
                    temp = new Slide();
                    try {
                        System.out.println("Enter the slide title: ");
                        title = sk.nextLine();
                        temp.setTitle(title);
                    } catch (Exception e) {
                        System.out.println("Invalid title");
                        break;
                    }
                    try {
                        System.out.println("Enter the slide duration: ");
                        duration = sk.nextDouble();
                        temp.setDuration(duration);
                        sk.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid duration");
                        break;
                    }
                    for (int i = 0; i < Slide.MAX_BULLETS; i++) {
                        System.out.printf("Bullet %d: ", temp.getNumBullets()+1);
                        bulletToAdd = sk.nextLine();
                        temp.setBullet(bulletToAdd, temp.getNumBullets()+1);
                        while (i < 4) {
                            System.out.println("Add another bullet point? (y/n) ");
                            choice = sk.nextLine();
                            if (choice.equals("y")) {
                                break;
                            }
                            else if (choice.equals("n")) {
                                cont = false;
                                break;
                            }
                            else {
                                System.out.println("Invalid option");
                                add = false;
                                cont = false;
                                break;
                            }
                        }
                        if (cont == false) {
                            break;
                        }
                    }
                    if (add == true) {
                        listOfSlides.appendToTail(temp);
                        if (temp.getNumBullets() == 5) {
                            System.out.println("No more bullets allowed. Slide is full.");
                        }
                        System.out.printf("Slide \"%s\" added to presentation\n", title);
                    }

                    break;
                case "I":
                    temp = new Slide();
                    try {
                        System.out.println("Enter the slide title: ");
                        title = sk.nextLine();
                        temp.setTitle(title);
                    } catch (Exception e) {
                        System.out.println("Invalid title");
                        break;
                    }
                    try {
                        System.out.println("Enter the slide duration: ");
                        duration = sk.nextDouble();
                        temp.setDuration(duration);
                        sk.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid duration");
                        break;
                    }
                    for (int i = 0; i < Slide.MAX_BULLETS; i++) {
                        System.out.printf("Bullet %d: ", temp.getNumBullets()+1);
                        bulletToAdd = sk.nextLine();
                        temp.setBullet(bulletToAdd, temp.getNumBullets()+1);
                        while (i < 4) {
                            System.out.println("Add another bullet point? (y/n) ");
                            choice = sk.nextLine();
                            if (choice.equals("y")) {
                                break;
                            }
                            else if (choice.equals("n")) {
                                cont = false;
                                break;
                            }
                            else {
                                System.out.println("Invalid option");
                                add = false;
                                cont = false;
                                break;
                            }
                        }
                        if (cont == false) {
                            break;
                        }
                    }
                    if (add == true) {
                        listOfSlides.insertBeforeCursor(temp);
                        if (temp.getNumBullets() == 5) {
                            System.out.println("No more bullets allowed. Slide is full.");
                        }
                        System.out.printf("Slide \"%s\" added to presentation\n", title);
                    }
                    break;
                case "R":
                    if (listOfSlides.size() == 0) {
                        System.out.println("Empty slideshow");
                    }
                    else {
                        try {
                            System.out.println("Slide \"" + listOfSlides.removeCursor().getTitle() + "\" has been removed");
                        } catch (Exception e) {
                            System.out.println("Cursor is null");
                        }
                    }
                    break;
                case "H":
                    if (listOfSlides.size() == 0) {
                        System.out.println("Empty slideshow");
                    }
                    else {
                        listOfSlides.resetCursorToHead();
                        System.out.println("Cursor has been reset to the head");
                    }
                    break;
                case "Q":
                    System.out.println("Program terminating normally...");
                    sk.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;

            }
        }
    }
}

