public class Team {

	private String teamName;
	private Player[] players;
	private int[] battingOrder;
	private int numberOfPlayers;
	private int gamesWon;
	private int gamesLost;
	private int gamesTied;

	public static final int MAX_NUMBER_OF_PLAYERS = 11;

	public Team(String teamName) {
		this.teamName = teamName;
		this.players = new Player[MAX_NUMBER_OF_PLAYERS];
		this.battingOrder = new int[MAX_NUMBER_OF_PLAYERS];
		this.gamesWon = 0;
		this.gamesLost = 0;
		this.gamesTied = 0;
		this.numberOfPlayers = 0;
		for (int i = 0; i < MAX_NUMBER_OF_PLAYERS; i ++) {
			this.players[i] = null;
			this.battingOrder[i] = i;
		}

	}
	public Team(String teamName, Player[] players, int[] battingOrder, int gamesWon, int gamesLost, int gamesTied) {
		this.teamName = teamName;
		this.players = players;
		this.battingOrder = battingOrder;
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
		this.gamesTied = gamesTied;
		this.numberOfPlayers = MAX_NUMBER_OF_PLAYERS;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void addPlayer(Player player) {
		this.players[this.numberOfPlayers++] = player;
	}

	public Player[] getPlayers() {
		Player[] p = this.players;
		return p;
	}

	public void setBattingOrder(int[] battingOrder) {
		this.battingOrder = battingOrder;
	}

	public int[] getBattingOrder() {
		return this.battingOrder;
	}

	public int getGamesWon() {
		return this.gamesWon;
	}

	public int getGamesLost() {
		return this.gamesLost;
	}

	public int getGamesTied() {
		return this.gamesTied;
	}

	public void incrementGamesWon() {
		this.gamesWon ++;
	}

	public void incrementGamesLost() {
		this.gamesLost ++;
	}

	public void incrementGamesTied() {
		this.gamesTied ++;
	}

	public String toString() {
		String s = "Team{\n\tteamName="+this.teamName
		+"\n\tnumberOfPlayers="+this.numberOfPlayers
		+"\n\tgamesWon="+this.gamesWon
		+"\n\tgamesLost="+this.gamesLost
		+"\n\tgamesTied="+this.gamesTied
		+"\n\tplayers= [";
		for (int i = 0; i < numberOfPlayers; i ++)
			s += "\n\t\t" + players[i].toString();
		return s + "\n\t]\n}";
	}
}