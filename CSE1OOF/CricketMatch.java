import java.util.*;import java.io.*;

public class CricketMatch {

	private Team[ ] teams;
	private final int MAX_TEAMS = 6;
	private int numberOfTeams;
	private Scanner keyboard;

	public static void main(String[] args) throws IOException {
		CricketMatch cM = new CricketMatch();
		System.out.println("***Name StudentNumber***");
		System.out.println("Cricket Match");
		cM.run();
	}

	public CricketMatch() {
		keyboard = new Scanner(System.in);
		teams = new Team[MAX_TEAMS];
		numberOfTeams = 0;
		for(int i = 0; i < MAX_TEAMS; i ++) teams[i] = null;
	}

	public void run() throws IOException {
			int choice = -1;
			while( choice != 10 ) {
			displayMenu( );
			System.out.print( "Enter choice >> ");
			choice = keyboard.nextInt();
			System.out.println();
			keyboard.nextLine();
			process(choice);	
		}
	}
	private void displayMenu() {
		System.out.println("\n\n\t*** MENU ***");
		System.out.println("\t1. Add player");
		System.out.println("\t2. Add team");
		System.out.println("\t3. Add team from File");
		System.out.println("\t4. Remove team");
		System.out.println("\t5. Display team");
		System.out.println("\t6. Display player");
		System.out.println("\t7. Display all teams");
		System.out.println("\t8. Set batting order");
		System.out.println("\t9. Play game");
		System.out.println("\t10. Exit");

	}

	private void process( int choice ) throws IOException { 
		switch( choice ) {
			case 1 :
			addPlayer( );
			break;
			case 2 :
			addTeam();
			break;
			case 3:
			addTeamFromFile();
			break;
			case 4:
			removeTeam();
			break;
			case 5:
			displayTeam();
			break;
			case 6:
			displayPlayer();
			break;
			case 7:
			displayAllTeams();
			break;
			case 8:
			setTeamBattingOrder();
			break;
			case 9:
			match();
			break;
			case 10:
			break;
			default:
			System.out.println("That is not a valid choice");
		}
	}

	private Team searchTeam(String teamName) {
		for(int i = 0; i < numberOfTeams; i ++)
			if(teams[i].getTeamName().equals(teamName))
				return teams[i];
		return null;

	}

	private int searchTeamIndex(String teamName) {
		for(int i = 0; i < numberOfTeams; i ++)
			if(teams[i].getTeamName().equals(teamName))
				return i;
		return -1;
	}

	public void addTeam() {
		if (this.numberOfTeams == MAX_TEAMS) {
			System.out.println("Maximum teams reached");
			return;
		}
		System.out.println("Enter team name: ");
		String name = this.keyboard.nextLine();
		if (searchTeam(name) != null) {
			System.out.println("Team already exists");
			return;
		}
		this.teams[this.numberOfTeams++] = new Team(name);
	}

	private Player searchPlayer(String playerName, String playerCountry) {
		for(int i = 0; i < numberOfTeams; i ++)
			for(Player teamPlayer:teams[i].getPlayers())
				if(teamPlayer != null && teamPlayer.getName().equals(playerName) && teamPlayer.getCountry().equals(playerCountry))
					return teamPlayer;
		return null;
	}



	public void addPlayer() {
		if (numberOfTeams == 0) {
			System.out.println("No teams exist to add players");
			return;
		}
		System.out.println("Enter player's name: ");
		String name = this.keyboard.nextLine();
		System.out.println("Enter player's country: ");
		String country = this.keyboard.nextLine();
		if (searchPlayer(name, country) != null) {
			System.out.println("Player already exists");
			return;
		}
		System.out.println("Enter player's team: ");
		String teamName = this.keyboard.nextLine();
		Team team = searchTeam(teamName);
		if (team == null) {
			System.out.println("Team does not exists");
			return;
		}
		if (team.getPlayers()[team.MAX_NUMBER_OF_PLAYERS - 1] != null) {
			System.out.println("Team already has a "+team.MAX_NUMBER_OF_PLAYERS+"players");
			return;
		}
		System.out.println("Enter player's batting skill 0-1: ");
		double battingSkill = this.keyboard.nextDouble();
		while (battingSkill < 0 || battingSkill > 1) {
			System.out.println("Invalid value, please try again: ");
			battingSkill = this.keyboard.nextDouble();
		}
		team.addPlayer(new Player(name, country, battingSkill));
	}

	public void removeTeam() {

		if (this.numberOfTeams == 0) {
			System.out.println("No team to remove");
		}
		else {
			this.teams[this.numberOfTeams--] = null;
		}

	}

	public void displayTeam() {
		if (this.numberOfTeams == 0) {
			System.out.println("No teams exist");
			return;
		}
		System.out.println("Enter team name: ");
		String name = this.keyboard.nextLine();
		Team team = searchTeam(name);
		System.out.println(team == null ? "Team does not exist" : team.toString());
	}

	public void displayAllTeams() {
		if (this.numberOfTeams == 0) {
			System.out.println("No teams exist");
			return;
		}
		for(int i = 0; i < numberOfTeams; i ++)
			System.out.println(teams[i].toString());

	}

	public void displayPlayer() {
		if (this.numberOfTeams == 0) {
			System.out.println("No teams exist");
			return;
		}
		System.out.println("Enter player's name: ");
		String name = this.keyboard.nextLine();
		System.out.println("Enter player's country: ");
		String country = this.keyboard.nextLine();
		Player p = searchPlayer(name, country);
		System.out.println(p == null ? "Player does not exist" : p.toString());
	}

	public void setTeamBattingOrder() {
		if (this.numberOfTeams == 0) {
			System.out.println("No teams exist");
			return;
		}
		System.out.println("Enter team name: ");
		String name = this.keyboard.nextLine();
		Team team = searchTeam(name);
		Player[] teamPlayers = team.getPlayers();
		if (teamPlayers[team.MAX_NUMBER_OF_PLAYERS - 1] == null) {
			System.out.println("Team does not have "+team.MAX_NUMBER_OF_PLAYERS+" players");
			return;
		}
		for(int i = 0; i < team.MAX_NUMBER_OF_PLAYERS; i ++)
			System.out.println((i + 1) + ". " + teamPlayers[i].getName());
		int[] battingOrder = new int[team.MAX_NUMBER_OF_PLAYERS];
		int[] duplicatesTracker = new int[team.MAX_NUMBER_OF_PLAYERS];
		boolean duplicates = false;
		for(int i = 0; i < team.MAX_NUMBER_OF_PLAYERS; i ++) duplicatesTracker[i] = -1; 
		for(int i = 0; i < team.MAX_NUMBER_OF_PLAYERS; i ++) {
			System.out.println("\tEnter batter number for order "+(i+1)+": ");
			battingOrder[i] = (keyboard.nextInt() - 1);
			if(duplicatesTracker[battingOrder[i]] != -1)
				duplicates = true;
			duplicatesTracker[battingOrder[i]] = i;
		}
		if(duplicates) {
			System.out.println("You have given an invalid order");
			return;
		}
		for (int i : battingOrder) {
			if(i < 0 || i > team.MAX_NUMBER_OF_PLAYERS - 1){
				System.out.println("You have given an invalid order");
				return;
			}
		}
		team.setBattingOrder(battingOrder);
	}

	public void addTeamFromFile() throws FileNotFoundException{
		if(this.numberOfTeams == MAX_TEAMS)
		{
			System.out.println("Maximum teams reached");
			return;
		}
		System.out.println("Enter file name: ");
		Scanner sc = null;
		String fileName=this.keyboard.nextLine();
		sc = new Scanner(new File(fileName));
		String teamName = sc.nextLine();
		if (searchTeam(teamName) != null) {
			System.out.println("Team already exists");
			sc.close();
			return;
		}
		int numberOfPlayers = sc.nextInt();
		if(numberOfPlayers != Team.MAX_NUMBER_OF_PLAYERS) {
			System.out.println("Partial teams are not allowed to be added by file");
			sc.close();
			return;
		}
		int battingOrder[] = new int[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i ++)
			battingOrder[i] = sc.nextInt();
		int gamesWon = sc.nextInt();
		int gamesLost = sc.nextInt();
		int gamesTied = sc.nextInt();
		Player[] players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i ++) {
			sc.nextLine();
			String name = sc.nextLine();
			System.out.println(name);
			String country = sc.nextLine();
			System.out.println(country);
			if(searchPlayer(name, country) != null) {
				System.out.println("Player already exists");
				sc.close();
				return;
			}
			double battingSkill = sc.nextDouble();
			if (battingSkill < 0 || battingSkill > 1) {
				System.out.println("Invalid batting skill for " + name + ": " + battingSkill);
				sc.close();
				return;
			}
			players[i] = new Player(name, country, battingSkill);
		}
		this.teams[this.numberOfTeams++] = new Team(teamName, players, battingOrder, gamesWon, gamesLost, gamesTied);
	}

	public void match() {
		System.out.println("Enter the name of team 1: ");
		String team1Name = this.keyboard.nextLine();
		System.out.println("Enter the name of team 2: ");
		String team2Name = this.keyboard.nextLine();
		Team team1 = searchTeam(team1Name);
		if (team1 == null) {
			System.out.println("Team1 does not exists");
			return;
		}
		Team team2 = searchTeam(team2Name);
		if (team2 == null) {
			System.out.println("Team2 does not exists");
			return;
		}
		Player[] team1Players = team1.getPlayers(), team2Players = team2.getPlayers();
		if (team1Players[team1.MAX_NUMBER_OF_PLAYERS - 1] == null) {
			System.out.println("Team 1 does not have enough player");
			return;
		}
		if (team2Players[team2.MAX_NUMBER_OF_PLAYERS - 1] == null) {
			System.out.println("Team 2 does not have enough player");
			return;
		}
		int team1Score = 0, team2Score = 0, battingOrder[];
		Player player;
		System.out.println("\nPress \"Enter\" to advance the game\n");
		keyboard.nextLine();
		battingOrder = team1.getBattingOrder();
		System.out.println("Innings 1 - "+team1.getTeamName()+" batting");
		for (int i = 0; i < team1.MAX_NUMBER_OF_PLAYERS - 1; i ++) {
			player = team1Players[battingOrder[i] - 1];
			int score = (int)(player.getBattingSkill() * 50 * Math.random());
			System.out.println("\n\t" + player.getName() + " : " + score + " runs");
			team1Score += score;
		}
		System.out.println("\nPress \"Enter\" to advance the game\n");
		keyboard.nextLine();
		battingOrder = team2.getBattingOrder();
		System.out.println("Innings 2 - "+team2.getTeamName()+" batting");
		for (int i = 0; i < team2.MAX_NUMBER_OF_PLAYERS - 1; i ++) {
			player = team2Players[battingOrder[i] - 1];
			int score = (int)(player.getBattingSkill() * 50 * Math.random());
			System.out.println("\n\t" + player.getName() + " : " + score + " runs");
			team2Score += score;
		}
		if(team1Score > team2Score) {
			team1.incrementGamesWon();
			team2.incrementGamesLost();
			System.out.println("\n***"+team1.getTeamName()+" won by "+(team1Score - team2Score)+" runs ***\n");
		}
		else if (team1Score < team2Score) {
			team2.incrementGamesWon();
			team1.incrementGamesLost();
			System.out.println("\n***"+team2.getTeamName()+" won by "+(team2Score - team1Score)+" runs ***\n");
		} else {
			team1.incrementGamesTied();
			team2.incrementGamesTied();
			System.out.println("\n***Game Tied at "+team1Score+" runs***\n");
			System.out.println(team1);
			System.out.println(team2);
		}

	}

}