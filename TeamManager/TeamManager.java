/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW1 
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Team Manager file shows the menu and allows the user to select options
*/

import java.util.Scanner;

public class TeamManager {
    public static final int MAX_TEAMS = 5;

    public static void main(String[] args) {
        Scanner sk = new Scanner(System.in);
        Team[] teams = new Team[MAX_TEAMS];
        for(int i = 0; i < MAX_TEAMS; i++){
            teams[i] = new Team();
        }
        
        Player temp;
        int selectedTeam = 1;
        String stat, name;
        int numberOfHits, numberOfErrors, positionOnTeam, newSelectedTeam;
        int cFrom, cTo;
        String changeStat;
        int newStatValue;

        while (true) {
            System.out.println(
            "\nA) Add Player. <Name> <Hits> <Errors> <Position> \n" + 
            "G) Get Player stats. <Position> \n" + 
            "L) Get leader in a stat. <Stat> \n" +
            "R) Remove a player. <Position> \n" +
            "P) Print all players. <Team> \n" +
            "S) Size of team. <Team> \n" +
            "T) Select team <Index> \n" +
            "C) Clone team <From> <To> \n" +
            "E) Team equals \n" +
            "U) Update stat. \n" +
            "Q) Quit. \n");

            String response = sk.nextLine();

            switch(response) {
                case "A": //Adds Player to Team
                    System.out.println("Enter the player's name: ");
                    name = sk.nextLine();
                    System.out.println("Enter the number of hits: ");
                    numberOfHits = sk.nextInt();
                    sk.nextLine();
                    System.out.println("Enter the number of errors: ");
                    numberOfErrors = sk.nextInt();
                    sk.nextLine();
                    System.out.println("Enter the position: ");
                    positionOnTeam = sk.nextInt();
                    sk.nextLine();
                    if (positionOnTeam > teams[selectedTeam-1].size()+1){
                        System.out.println("Invalid position for adding the new player");
                    }
                    else {
                        Player playerToAdd = new Player(name, numberOfHits, numberOfErrors);
                        try {
                            teams[selectedTeam-1].addPlayer(playerToAdd, positionOnTeam);
                        } catch (FullTeamException e) {
                            e.printStackTrace();
                        }
                        System.out.print("Player added: ");
                        playerToAdd.toString(name, numberOfHits, numberOfErrors);
                    } 
                    break;
                case "G": //Gets a player's stat
                    System.out.println("Enter the position");
                    positionOnTeam = sk.nextInt();
                    sk.nextLine();
                    if (positionOnTeam <= teams[selectedTeam-1].size()) {
                        temp = (teams[selectedTeam-1].getPlayer(positionOnTeam));
                        temp.toString(temp.getName(), temp.getNumHits(), temp.getNumErrors());
                    }
                    else {
                        System.out.println("Invalid position number");
                    }
                    break;
                case "L": //Gets the leader
                    System.out.println("Enter a stat (hits or errors): ");
                    stat = sk.nextLine();
                    stat.toLowerCase();
                    if (!(stat.equals("hits") || stat.equals("errors"))) {
                        System.out.println("No such statistic");
                    }
                    else {
                        try {
                            temp = teams[selectedTeam-1].getLeader(stat);
                            if (stat.equals("hits")) {
                                System.out.print("Leader in hits: ") ;
                                temp.toString(temp.getName(), temp.getNumHits(), temp.getNumErrors());
                            }
                            else if (stat.equals("errors")) {
                                System.out.print("Leader in errors: ");
                                temp.toString(temp.getName(), temp.getNumHits(), temp.getNumErrors());
                            }
                        } catch (Exception e) {
                            System.out.println("No such statistic");
                        }
                    }
                    break;
                case "R": //Removes a player
                    System.out.println("Enter the position: ");
                    positionOnTeam = sk.nextInt();
                    sk.nextLine();
                    try {
                        name = teams[selectedTeam-1].getPlayer(positionOnTeam).getName();
                        teams[selectedTeam-1].removePlayer(positionOnTeam);
                        System.out.printf("Player Removed at position %d\n", positionOnTeam);
                        System.out.printf("%s has been removed from the team\n", name);
                    } catch (Exception e) {
                        System.out.printf("No player at position %d to remove\n", positionOnTeam);
                    }
                    break;
                case "P": //Prints entire team
                    System.out.println("Select Team Index: ");
                    newSelectedTeam = sk.nextInt();
                    sk.nextLine();
                    teams[newSelectedTeam-1].printAllPlayers();
                    break;
                case "S":
                    System.out.printf("There are %d players(s) in the current Team\n", teams[selectedTeam-1].size());
                    break;
                case "T": //Selects new Team
                    System.out.println("Enter team index to select: ");
                    newSelectedTeam = sk.nextInt();
                    sk.nextLine();
                    if (newSelectedTeam > MAX_TEAMS) {
                        System.out.println("Invalid index for team");
                    }
                    else {
                        selectedTeam = newSelectedTeam;
                        System.out.printf("Team %d has been selected.\n", newSelectedTeam);
                    }
                    break;
                case "C": //Clones a team
                    System.out.println("Select the team to clone from: ");
                    cFrom = sk.nextInt();
                    sk.nextLine();
                    System.out.println("Select the team to clone to: ");
                    cTo = sk.nextInt();
                    sk.nextLine();
                    teams[cTo-1] = (Team) teams[cFrom-1].clone();
                    System.out.printf("Team %d has been copied to Team %d\n", cFrom, cTo);
                    break;
                case "E": //Checks Team Equals
                    System.out.println("Select first team index: ");
                    cFrom = sk.nextInt();
                    sk.nextLine();
                    System.out.println("Select second team index: ");
                    cTo = sk.nextInt();
                    sk.nextLine();
                    if(teams[cFrom-1].equals(teams[cTo-1])) {
                        System.out.println("These teams are equal.");
                    }
                    else {
                        System.out.println("These teams are not equal");
                    }
                    break;
                case "U": //Update Stat
                    System.out.println("Enter the player to update: ");
                    name = sk.nextLine();
                    System.out.println("Enter the stat to update (hits or errors): ");
                    changeStat = sk.nextLine();
                    changeStat.toLowerCase();
                    System.out.printf("Enter the new number of %s\n", changeStat);
                    newStatValue = sk.nextInt();
                    sk.nextLine();
                    boolean inTeam = false;
                    for (int i = 0; i < teams[selectedTeam-1].size(); i++) {
                        if (teams[selectedTeam-1].getPlayer(i+1).getName().equals(name)) {
                            inTeam = true;
                            if (changeStat.equals("hits")) {
                                try {
                                    teams[selectedTeam-1].getPlayer(i+1).setNumHits(newStatValue);
                                    System.out.printf("Updated %s hits\n", teams[selectedTeam-1].getPlayer(i+1).getName());
                                } catch (Exception e) {
                                    System.out.println("Number of hits must be positive");
                                    // TODO: handle exception
                                }
                            }
                            if (changeStat.equals("errors")) {
                                try {
                                    teams[selectedTeam-1].getPlayer(i+1).setNumErrors(newStatValue);
                                    System.out.printf("Updated %s errors\n", teams[selectedTeam-1].getPlayer(i+1).getName());
                                } catch (Exception e) {
                                    System.out.println("Number of errors must be positive");
                                    // TODO: handle exception
                                }
                                
                            }   
                        }
                    }
                    if (!inTeam) {
                        System.out.println("Player not found");
                    }
                    break;
                case "Q": //Exits out of program
                    System.out.println("Program terminating normally");
                    sk.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error Invalid Input");
                    break;
            }
        }
    }
}