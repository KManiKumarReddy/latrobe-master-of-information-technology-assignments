public class Player {

	private String playerName;
	private String country;
	private double battingSkill;

	public Player(String name, String country, double battingSkill) {
		this.playerName = name;
		this.country = country;
		this.battingSkill = battingSkill;
	}

	public String getName() {
		return this.playerName;
	}

	public String getCountry() {
		return this.country;
	}

	public double getBattingSkill() {
		return this.battingSkill;
	}

	public String toString() {
		return "Player{name="+this.playerName+", country="+this.country+", battingSkill="+this.battingSkill+"}";
	}
}