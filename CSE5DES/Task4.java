import java.util.*;

public class Task4
{
	public static void main(String [] args) throws Exception
	{
        validAssigningPerformancetoSession();
        // invalidEntryTest();
        // invalidSessionTest();
        // performanceAlreadyHasSessionTest();
        // differentEntryTest();
	}

	public static void validAssigningPerformancetoSession() throws Exception
	{
        FigureSkatingSystemV2 fss = new FigureSkatingSystemV2();

		fss.addSkater("SK10", "Anne Ashley", "AUS", Gender.FEMALE, 18);
		fss.addSession("Session1", Discipline.LADIES_SINGLE, Program.TECHNICAL);
        fss.addSingleEntry("E10", "SK10");

		fss.assignPerformanceToSession("E10", Program.TECHNICAL, "Session1");
        
    }
    

	public static void invalidSessionTest() throws Exception
	{
        FigureSkatingSystemV2 fss = new FigureSkatingSystemV2();

		fss.addSkater("SK10", "Anne Ashley", "AUS", Gender.FEMALE, 18);

        // Assigning a session that does not exist in the system
		fss.assignPerformanceToSession("E10", Program.TECHNICAL, "Session1");
        
    }
    

	public static void invalidEntryTest() throws Exception
	{
        FigureSkatingSystemV2 fss = new FigureSkatingSystemV2();
        
        fss.addSkater("SK10", "Anne Ashley", "AUS", Gender.FEMALE, 18);
		fss.addSession("Session1", Discipline.LADIES_SINGLE, Program.TECHNICAL);

        // Assigning a invalid entry that does not exist in the system
		fss.assignPerformanceToSession("E40", Program.TECHNICAL, "Session1");
        
    }
    

	public static void performanceAlreadyHasSessionTest() throws Exception
	{
        FigureSkatingSystemV2 fss = new FigureSkatingSystemV2();
        
        fss.addSkater("SK10", "Anne Ashley", "AUS", Gender.FEMALE, 18);
        fss.addSingleEntry("E10", "SK10");


		fss.addSession("Session1", Discipline.LADIES_SINGLE, Program.TECHNICAL);
        fss.addSession("Session2", Discipline.LADIES_SINGLE, Program.FREE);

        fss.assignPerformanceToSession("E10", Program.TECHNICAL, "Session1");
        
        // Assigning an Entry that has already been assigned Session 1
		fss.assignPerformanceToSession("E10", Program.TECHNICAL, "Session2");
        
    }
    
    
	public static void differentEntryTest() throws Exception
	{
        FigureSkatingSystemV2 fss = new FigureSkatingSystemV2();
        
        fss.addSkater("SK10", "Anne Ashley", "AUS", Gender.FEMALE, 18);
		fss.addSession("Session1", Discipline.LADIES_SINGLE, Program.TECHNICAL);
        fss.addSingleEntry("E10", "SK10");

        fss.addSkater("SK20", "Bob Brown", "AUS", Gender.MALE, 20);
        fss.addSingleEntry("E20", "SK20");
        fss.addSession("Session2", Discipline.LADIES_SINGLE, Program.FREE);

        // Assigning a session and entry of different categories
		fss.assignPerformanceToSession("E10", Program.TECHNICAL, "Session2");
        
	}
}
