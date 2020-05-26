class Sport {

    private String sportName;
    private Competitor competitor1, competitor2, competitor3;

    public Sport(String sportName ) {
        this.sportName = sportName;
        competitor1 = null;
        competitor2 = null;
        competitor3 = null;
    }

    public void addCompetitor(Competitor competitor) {
        if(competitor1 == null) {
            competitor1 = competitor;
        }
        else if(competitor2 == null){
            competitor2 = competitor;
        }
        else if(competitor3 == null) {
            competitor3 = competitor;
        }
    }

    public Competitor getCompetitor(int competitorNumber) {
        switch (competitorNumber) {
            case 1:
                return competitor1;
        
            case 2:
                return competitor2;
    
            case 3:
                return competitor3;
        
            default:
                return null;
        }
    }

    public String getSportName() {
        return this.sportName;
    }

    public String toString() {
        return "Sport{\nsportName="+this.sportName+"\ncompetitor1="+this.competitor1+"\ncompetitor2="+this.competitor2+"\ncompetitor3="+this.competitor3+"\n}";
    }
}