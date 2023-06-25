/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW7
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The NeoViewer class is responsible for giving the user options to add from the database to the list, which sorting method
to use, to print the list, and to exit out of the program
*/

import java.util.Scanner;

public class NeoViewer {
    public static void main(String[] args) {
        Scanner sk = new Scanner(System.in);
        System.out.println("Welcome to NEO Viewer!\n");
        int pageNumber;
        String choice;
        String sortOption;
        NeoDataBase dataBase = new NeoDataBase();
        while (true) {
            System.out.println("Option Menu: \n" + 
                "A) Add a page to the database\n" +
                "S) Sort the database\n" + 
                "P) Print the database as a table.\n" + 
                "Q) Quit\n");
            choice = sk.nextLine();
            switch (choice) {
                case "A":
                    System.out.print("Enter page to load: ");
                    pageNumber = sk.nextInt();
                    sk.nextLine();
                    dataBase.addAll(dataBase.buildQueryURL(pageNumber));
                    System.out.println();
                    break;
                case "S":
                    System.out.println("\nR) Sort by referenceID\n" +
                    "D) Sort by diameter\n" +
                    "A) Sort by approach date\n" +
                    "M) Sort by miss distance\n");
                    sortOption = sk.nextLine();
                    if (sortOption.equals("R")) {
                        dataBase.sort(new ReferenceIDComparator());
                        System.out.printf("Table sorted on referenceID.\n\n");
                    }
                    else if (sortOption.equals("D")) {
                        dataBase.sort(new DiameterComparator());
                        System.out.printf("Table sorted on diameter.\n\n");
                    }
                    else if (sortOption.equals("A")) {
                        dataBase.sort(new ApproachDateComparator());
                        System.out.printf("Table sorted on approach date.\n\n");
                    }
                    else if (sortOption.equals("M")) {
                        dataBase.sort(new MissDistanceComparator());
                        System.out.printf("Table sorted on miss distance.\n\n");
                    }
                    break;
                case "P":
                    dataBase.printTable();
                    System.out.println();
                    break;
                case "Q":
                    sk.close();
                    System.exit(0);
                    break;
            }
        }
    }
}