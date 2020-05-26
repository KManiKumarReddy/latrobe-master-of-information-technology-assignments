class Competitor {

    private String competitorName, country;
    private double skill;
    private int numberOfGoldMedals;

    public Competitor(String competitorName, String country, double skill) {
        this.competitorName = competitorName;
        this.country = country;
        this.skill = skill;
        this.numberOfGoldMedals = 0;
    }

    public String getCompetitorName() {
        return this.competitorName;
    }

    public String getCountry() {
        return this.country;
    }

    public double getSkill() {
        return this.skill;
    }

    public void incrementNumberOfGoldMedals() {
        this.numberOfGoldMedals ++;
    }

    public int getNumberOfGoldMedals() {
        return this.numberOfGoldMedals;
    }

    public String toString() {
        return "Competitor{competitorName="+this.competitorName+", country="+this.country+", skill="+this.skill+"\nnumberOfGoldMedals="+this.numberOfGoldMedals+"}";
    }
}