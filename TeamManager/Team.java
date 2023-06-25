/* Kenny Cao
114859358
kenny.cao.1@stonybrook.edu
HW1 
CSE 214
Recitation Section 02: Jamieson Barkume, Steven Secreti
The Team file creates the teams and allows for the manipulation and cloning of the teams.
*/

public class Team {
    public static final int MAX_PLAYERS = 40;
    private Player[] players;
    private int sizeOfTeam;

    /**
     * creates a Team class of size MAX_PLAYERS with no players in it
     */
    public Team() {
        players = new Player[MAX_PLAYERS];
    }

    public Player[] getPlayers() {
        return players;
    }

    /**
     * @return a clone of the team
     */
    public Object clone(){
        Team newTeam = new Team();
        for (int i = 0; i < MAX_PLAYERS; i++) {
            if (players[i] == null) {
                break;
            }
            try {
                newTeam.addPlayer(players[i], i+1);
            } catch (FullTeamException e) {
                System.out.println("FullTeamException Error");
                e.printStackTrace();
            }
        }
        return newTeam;
    }

    /**
     * 
     * @return false if the teams aren't the same and true if the teams are the same
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Team)) {
            return false;
        }
        Team temp = (Team) obj;
        Player[] temparr = temp.getPlayers();
        for (int i = 0; i < sizeOfTeam; i++) {
            if (temparr[i] != null) {
                if (!(temparr[i].getName().equals(players[i].getName()))) {
                    return false;
                }
                if (temparr[i].getNumHits() != (players[i].getNumHits())) {
                    return false;
                }
                if (temparr[i].getNumErrors() != (players[i].getNumErrors())) {
                    return false;
                }
            }    
        }
        return true;
    }

    /**
     * precondition: the Team object has been instantiated.
     * 
     * @return the size of the team
     */
    public int size() {
        return sizeOfTeam;
    }

    /**
     * Preconditions: This Team object has been instantiated
     * 1 ≤ position ≤ players_currently_in_team + 1.
     * The number of Player objects in this Team is less than MAX_PLAYERS.
     * 
     * adds a player to the team at indicated position
     * 
     * @param p a player
     * @param position the player will be placed at
     * @throws FullTeamException
     */
    public void addPlayer(Player p, int position) throws FullTeamException {
        if (players[MAX_PLAYERS-1] != null) {
            throw new FullTeamException("The team is full");
        }
        if (position < 1 || position > sizeOfTeam + 1) {
            throw new IllegalArgumentException("The position is not within the valid range");
        }
        for (int i = MAX_PLAYERS; i > position; i--) {
            players[i-1] = players[i-2];
        }
        players[position-1] = p;
        sizeOfTeam += 1;
    }

    /**
     * Preconditions: The Team object has been instantiated
     * 1 ≤ position ≤ players_currently_in_team 
     * 
     * removes a player from a specified position
     * 
     * @param position that is specified
     */
    public void removePlayer(int position) {
        if (position < 1 || position > sizeOfTeam + 1) {
            throw new IllegalArgumentException("The position is not within the valid range");
        }
        for (int i = position-1; i < MAX_PLAYERS-1; i++) {
                players[i] = players[i+1];
        }
        sizeOfTeam-=1;
    }

    /**
     * Preconditions: The Team object has been instantiated.
     * 
     * @param position
     * @return player at the position
     * 
     */
    public Player getPlayer(int position) {
        return players[position-1];
    }


    /**
     * Preconditions: The Team object has been instantiated.
     * 
     * Return the Player with the best value in the given statistic ("hits" or "errors").
     * 
     * @param stat (either hits or errors (1:hits 0:errors))
     * @return the Player with the highest number in the specific stat
     */
    public Player getLeader(String stat) {
        if (!(stat.equals("hits") || stat.equals("errors"))) {
            throw new IllegalArgumentException("The indicated stat was neither \"errors\" or \"hits\" (not 0 or 1)");
        }
        Player temp = players[0];
        if (stat.equals("errors")) { 
            for (int i = 0; i < sizeOfTeam; i++) {
                if (players[i].getNumErrors() < temp.getNumErrors()) {
                    temp = players[i];
                }
            }
        }
        else if (stat.equals("hits")) { 
            for (int i = 0; i < sizeOfTeam; i++) {
                if (players[i].getNumHits() > temp.getNumHits()) {
                    temp = players[i];
                }
            }
        }
        return temp;
    }

    /**
     * Precondition: The Team object has been instantiated.
     * 
     * Prints out a neatly formatted table with a list of all the players
     */
    public void printAllPlayers() {
        System.out.println(toString());
    }

    /**
     * @return Strings with formatted table
     */
    public String toString() {
        String news = "";
        news += "Player#   Name               Hits       Errors\n";
        news += "------------------------------------------------\n";
        for (int i = 0; i < MAX_PLAYERS; i++) {
            if (players[i] == null) break;
            news += String.format("%-10d%-19s%-11d%-8d \n", (i+1), players[i].getName(), players[i].getNumHits(), players[i].getNumErrors());
        }
        return news;
    }

}
