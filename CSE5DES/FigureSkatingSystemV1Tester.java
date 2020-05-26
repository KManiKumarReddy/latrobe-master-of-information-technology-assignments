import java.util.*;

public class FigureSkatingSystemV1Tester
{
	public static void main(String [] args) throws Exception
	{
		int count = 0;

		FigureSkatingSystemV1 fss = new FigureSkatingSystemV1();
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addSkater("SK10", "Anne Ashley", "AUS", Gender.FEMALE, 18);
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addSkater("SK20", "Bob Brown", "AUS", Gender.MALE, 20);
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addJudge("J30", "Jane Austen", "CAN");
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addSingleEntry("E10", "SK10");
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addSingleEntry("E20", "SK20");
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addSession("Session1", Discipline.LADIES_SINGLE, Program.TECHNICAL);
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addSession("Session2", Discipline.LADIES_SINGLE, Program.FREE);
		System.out.println("\n\nTest " + (++count) + fss);

		fss.assignPerformanceToSession("E10", Program.TECHNICAL, "Session1");
		System.out.println("\n" + (++count) + fss);

		fss.assignJudgeToSession("J30", "Session1");
		System.out.println("\n\nTest " + (++count) + fss);

		fss.addScore("E10", Program.TECHNICAL, "J30", 5);
		System.out.println("\n\nTest " + (++count) + fss);

		// test use case 8
		//
		System.out.println("\n\nTest Use Case 8");
		testUseCase8();
	}

	public static void testUseCase8() throws Exception
	{
		FigureSkatingSystemV1 fss = new FigureSkatingSystemV1();

		// Add skater, entry and 9 judges
		fss.addSkater("SK1", "Anne Ashley", "AUS", Gender.FEMALE, 18);
		fss.addSingleEntry("E1", "SK1");

		fss.addJudge("J1", "Judge 1", "AAA");
		fss.addJudge("J2", "Judge 2", "BBB");
		fss.addJudge("J3", "Judge 3", "CCC");
		fss.addJudge("J4", "Judge 4", "DDD");
		fss.addJudge("J5", "Judge 5", "EEE");
		fss.addJudge("J6", "Judge 6", "FFF");
		fss.addJudge("J7", "Judge 7", "GGG");
		fss.addJudge("J8", "Judge 8", "HHH");
		fss.addJudge("J9", "Judge 9", "III");

		// add session
		fss.addSession("Session1", Discipline.LADIES_SINGLE, Program.TECHNICAL);

		// assign performance to session
		fss.assignPerformanceToSession("E1", Program.TECHNICAL, "Session1");

		// assign judges to session
		fss.assignJudgeToSession("J1", "Session1");
		fss.assignJudgeToSession("J2", "Session1");
		fss.assignJudgeToSession("J3", "Session1");
		fss.assignJudgeToSession("J4", "Session1");
		fss.assignJudgeToSession("J5", "Session1");
		fss.assignJudgeToSession("J6", "Session1");
		fss.assignJudgeToSession("J7", "Session1");
		fss.assignJudgeToSession("J8", "Session1");
		fss.assignJudgeToSession("J9", "Session1");

		System.out.println("\n\nInitial set up: " + fss);

		// record scores of 9 judges for the performance
		String[] idArray = {"J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9"};
		List<String> judgeIds = new ArrayList<String>();
		for(String s: idArray)
			judgeIds.add(s);

		double[] scoreArray = {4, 5, 6, 4, 5, 6, 4, 5, 6};
		List<Double> scores = new ArrayList<Double>();
		for(double s: scoreArray)
			scores.add(s);

		fss.addNineScoresForPerformance("E1", Program.TECHNICAL, judgeIds, scores);

		System.out.println("\n\nAfter adding scores: " + fss);
	}
}
