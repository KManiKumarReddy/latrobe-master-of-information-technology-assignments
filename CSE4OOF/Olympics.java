import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Mani Kumar Reddy Kancharla
 */
public class Olympics 
{
    private Scanner keyboard;
    private Sport sport1;
    private Sport sport2;
    private Sport sport3;
    
    //STUDENTS GIVEN THIS METHOD - Update with your student details
    public static void main(String[] args) throws IOException
    {
        Olympics ol = new Olympics();
        System.out.println("***Mani Kumar Reddy Kancharla 18976404**");
        System.out.println("Olympics 1");
        ol.run();
    }
    
    //STUDENTS GIVEN THIS METHOD - Do not edit this method
    public Olympics()
    {
        keyboard = new Scanner(System.in);
        sport1=null;
        sport2=null;
        sport3=null;
    }

    //STUDENTS GIVEN THIS METHOD - Do not edit this method
    public void run( ) throws IOException
    {
        int choice = -1;
        while( choice != 10 )
        {
            displayMenu( );
            System.out.print( "Enter choice >> ");
            choice = keyboard.nextInt( );
            System.out.println( );
            keyboard.nextLine( );
            process( choice );
        }
    }
    
    //STUDENTS GIVEN THIS METHOD - Do not edit this method until Assignment D
    private void displayMenu()
    {
        System.out.println("\n\n\t*** MENU ***");
        System.out.println("\t1. Add competitor");
        System.out.println("\t2. Add sport");
       // System.out.println("\t3. Add sport from File");
        System.out.println("\t4. Remove sport");
        System.out.println("\t5. Display sport");
        //System.out.println("\t6. Display competitor");
        System.out.println("\t7. Display all sports");
      //  System.out.println("\t8. Display medals for country"); 
        System.out.println("\t9. Compete");
        System.out.println("\t10. Exit");
    }
    
    //STUDENTS GIVEN THIS METHOD
    private void process( int choice ) throws IOException
    {
        switch( choice )
        {
            case 1 :
                addCompetitor( );
                break;
            case 2 :
                addSport( );
                break;
            /*case 3 :
                addSportFromFile( );
                break;*/
            case 4 :
                removeSport( );
                break;
            case 5 :
                displaySport( );
                break;
           /* case 6 :
                displayCompetitor( );
                break;*/
            case 7 :
                displayAllSports();
                break;
            /*case 8 :
                displayMedals();
                break;*/
            case 9 :
                compete();
                break;
            case 10:
                //exit - just capture this choice
                break;
            default:
                System.out.println("That is not a valid choice");
                break;
        }
    }

    //Important concepts - moderate
    public void addCompetitor( )
    {
        if((sport3 == null || sport3.getCompetitor(3) != null) && (sport2 == null || sport2.getCompetitor(3) != null) && (sport1 == null || sport1.getCompetitor(3) != null)) {
            System.out.println("No sport available to add competitors");
            return;
        }
        System.out.print("Enter players name: ");
        String name = keyboard.next();
        System.out.print("Enter players country: ");
        String country = keyboard.next();
        System.out.print("Enter players sport: ");
        String sportName = keyboard.next();
        Sport sport = searchSport(sportName);
        if( sport == null) {
            System.out.println("Sport does not exist");
            return;
        }
        System.out.print("Enter competitors skill 0-1: ");
        double skill = keyboard.nextDouble();
		while (skill < 0 || skill > 1) {
			System.out.print("Invalid value, please try again: ");
			skill = this.keyboard.nextDouble();
		}
        if(sport.getCompetitor(3) != null) {
            System.out.println("Sports competitors are full");
            return;
        }
        sport.addCompetitor(new Competitor(name, country, skill));

    }
     
    public void addSport( )
    {
        if (sport1 != null && sport2 != null && sport3 != null) {
            System.out.print("Maximum sports reached");
            return;
        }

        System.out.print("Enter sport name: ");
        String sportName = keyboard.next();

        if(searchSport(sportName) != null) {
            System.out.println("Sport already exists");
            return;
        }
        Sport sport = new Sport(sportName);
        if(sport1 == null) {
            sport1 = sport;
        }
        else if(sport2 == null) {
            sport2 = sport;
        }
        else if(sport3 == null) {
            sport3 = sport;
        }
    }

    private Sport searchSport(String sportName)
    {
        Sport tempSport = null;
        if(sport1 != null && sport1.getSportName().equals(sportName))
            tempSport = sport1;
        else if(sport2 != null && sport2.getSportName().equals(sportName))
            tempSport = sport2;
        else if(sport3 != null && sport3.getSportName().equals(sportName))
            tempSport = sport3;
        return tempSport;
    }

   
    //Important concepts - moderate
    public void removeSport( )
    {
        if(sport3 != null) {
            sport3 = null;
        }
        else if(sport2 != null) {
            sport2 = null;
        }
        else if(sport1 != null) {
            sport1 = null;
        }
        else {
            System.out.println("No sport to remove");
        }
    }
    
    //Important concepts - simple
    public void displaySport( )
    {
        if(sport1 == null) {
            System.out.println("No sports exist");
            return;
        }
        System.out.print("Enter Sport: ");
        Sport sport = searchSport(keyboard.next());
        System.out.println(sport == null ? "Sport does not exist" : sport);
    }
    
    //Important concepts - simple
    public void displayAllSports( )
    {
        if (sport1 != null) {
            System.out.println(sport1);
        }  else {
            System.out.println("No sports exist");
        }
        if (sport2 != null) {
            System.out.println(sport2);
        }
        if (sport3 != null) {
            System.out.println(sport3);
        }
    }
    
   
    
    //Important concepts - moderate
    public void compete( )
    {
        System.out.print("Enter the name of the sport: ");
        Sport sport = searchSport(keyboard.next());
        if(sport == null) {
            System.out.println("Sport does not exist");
            return;
        }
        if(sport.getCompetitor(3) == null) {
            System.out.println("Sport does not have three players");
            return;
        }
        System.out.println("*** "+sport.getSportName()+" ***");
        int score1, score2, score3;
        score1 = (int)(sport.getCompetitor(1).getSkill() * 50 * Math.random());
        score2 = (int)(sport.getCompetitor(2).getSkill() * 50 * Math.random());
        score3 = (int)(sport.getCompetitor(3).getSkill() * 50 * Math.random());
        Competitor competitor = null;
        if(score1 > score2) {
            if (score3 > score1 ) {
                competitor = sport.getCompetitor(3);
            }
            else {
                competitor = sport.getCompetitor(1);
            }
        }
        else {
            if (score3 > score2 ) {
                competitor = sport.getCompetitor(3);
            }
            else {
                competitor = sport.getCompetitor(2);
            }
        }
        System.out.println("GOLD: "+competitor.getCompetitorName()+", "+competitor.getCountry());
        competitor.incrementNumberOfGoldMedals();
	}
}