import java.util.*;

public class FigureSkatingSystemV1
{
	private List<Skater> skaterList;
	private List<Judge> judgeList;
	private List<Entry> entryList;
	private List<Session> sessionList;
	private List<Performance> performanceList;
	private List<Score> scoreList; 	// the scores for performances

	public FigureSkatingSystemV1()
	{
		skaterList = new ArrayList<Skater>();
		judgeList = new ArrayList<Judge>();
		entryList = new ArrayList<Entry>();
		sessionList = new ArrayList<Session>();
		performanceList = new ArrayList<Performance>();
		scoreList = new ArrayList<Score>();
	}

	public String toString()
	{
		return
			"\n---------- FSC System ----------\n"
			+ "skaterList: " 			+ Helper.getDisplayList(skaterList)+ "\n\n"
			+ "judgeList: " 			+ Helper.getDisplayList(judgeList) + "\n\n"
			+ "entryList: " 			+ Helper.getDisplayList(entryList) + "\n\n"
			+ "sessionList: " 		+ Helper.getDisplayList(sessionList) + "\n\n"
			+ "performanceList: " 	+ Helper.getDisplayList(performanceList) + "\n\n"
			+ "scoreList: " 			+ Helper.getDisplayList(scoreList) + "]";
	}

	// Atomic use case 1
	public void addSkater(String id, String name, String nationality, Gender gender,
	int age) throws Exception
	{
		skaterList.add(new Skater(id, name, nationality, gender, age));
	}

	// Atomic use case 2
	public void addJudge(String id, String name, String nationality) throws Exception
	{
		judgeList.add(new Judge(id, name, nationality));
	}

	// Atomic use case 3
	public void addSingleEntry(String entryId, String skaterId)
	throws Exception
	{

		Skater skater = Helper.search(skaterList, skaterId);

		// Determine competition discipline
		Discipline discipline =
			skater.getGender() == Gender.FEMALE? Discipline.LADIES_SINGLE : Discipline.MENS_SINGLE;

		// Create and add the new entry
		Entry entry = new SingleEntry(entryId, skater, discipline);
		entryList.add(entry);

		// Create and add performances (with missing score and session)
		Performance technicalPerformance = new Performance(entry, Program.TECHNICAL);
		Performance freeStylePerformance = new Performance(entry, Program.FREE);
		performanceList.add(technicalPerformance);
		performanceList.add(freeStylePerformance);
	}

	// Atomic use case 4
	public void addSession(String sessionId, Discipline discipline, Program program)
	throws Exception
	{
		sessionList.add(new Session(sessionId, discipline, program));
	}

	// Atomic use case 5
	public void assignPerformanceToSession(String entryId, Program program, String sessionId) throws Exception
	{

		// Post: set session of performance
		List<String> key = new ArrayList<String>();
		key.add(entryId);
		key.add(program.name());
		Performance performance = Helper.search(performanceList, key);
		performance.setSession(Helper.search(sessionList, sessionId));
	}

	// Atomic use case 6
	public void assignJudgeToSession(String judgeId, String sessionId) throws Exception
	{
		// judge exists
		Judge judge = Helper.search(judgeList, judgeId);

		// session exists
		Session session = Helper.search(sessionList, sessionId);

		// post
		session.getJudges().add(judge);
	}

	// Atomic use case 7
	public void addScore(String entryId, Program program, String judgeId, double value)
	throws Exception
	{
		// Performance exists
		List<String> key = new ArrayList<String>();
		key.add(entryId);
		key.add(program.name());
		Performance performance = Helper.search(performanceList, key);

		// Judge exists
		Judge judge  = Helper.search(judgeList, judgeId);


		// Post: Create and add score
		scoreList.add(new Score(performance, judge, value));
	}

	// Atomic use case 8
	public void addNineScoresForPerformance(String entryId, Program program, List<String> judgeIds, List<Double> scores)
	throws Exception
	{
		// Performance exists
		List<String> key = new ArrayList<String>();
		key.add(entryId);
		key.add(program.name());
		Performance performance = Helper.search(performanceList, key);

		Judge judge; int counter = 0;

		for(String judgeId : judgeIds) {

			// Judge exists
			judge = Helper.search(judgeList, judgeId);

			// Post: Create and add score
			scoreList.add(new Score(performance, judge, scores.get(counter ++)));
		}

	}

	public double calculatePerformanceScore(Performance performance) {
		
		int performanceScore = 0;

		for (Score score : scoreList) {
			if(score.getPerformance().equals(performance) && score.getValue() != -999 ) {
				performanceScore += score.getValue();
			}
		}

		return performanceScore;
		
	}

}
