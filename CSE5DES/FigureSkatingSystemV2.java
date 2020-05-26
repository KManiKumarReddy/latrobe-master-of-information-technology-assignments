import java.util.ArrayList;
import java.util.List;

public class FigureSkatingSystemV2
{
	private List<Skater> skaterList;
	private List<Judge> judgeList;
	private List<Entry> entryList;
	private List<Session> sessionList;
	private List<Performance> performanceList;
	private List<Score> scoreList; 	// the scores for performances

	public FigureSkatingSystemV2()
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
		// pre: id is new, i.e. no existing person has this id
		Skater skater = Helper.search(skaterList, id);
		Judge judge = Helper.search(judgeList, id);
		boolean pre = skater == null && judge == null;
		if(!pre)
			throw new Exception("ERROR: Id is not new!");

		skater = new Skater(id, name, nationality, gender, age);
		skaterList.add(skater);
	}

	// Atomic use case 2
	public void addJudge(String id, String name, String nationality) throws Exception
	{
		// pre: id is new
		Skater skater = Helper.search(skaterList, id);
		Judge judge = Helper.search(judgeList, id);
		boolean pre = skater == null && judge == null;
		if(!pre)
			throw new Exception("ERROR: Id is not new!");

		judge = new Judge(id, name, nationality);
		judgeList.add(judge);
	}

	// Atomic use case 3
	public void addSingleEntry(String entryId, String skaterId)
	throws Exception
	{
		// pre: entry id is new
		Entry entry = Helper.search(entryList, entryId);
		boolean pre = entry == null || entry.getClass() == SingleEntry.class;
		if(!pre)
		{
			throw new Exception("ERROR: Entry with this id already exists!");
		}

		// pre: skater exists
		Skater skater = Helper.search(skaterList, skaterId);
		pre = skater != null;
		if(!pre)
		{
			throw new Exception("ERROR: Skater does not exist!");
		}

		// pre: skater has not registered in any single entry
		pre = true;
		for(Entry e: entryList)
		{
			if(e.getClass() == SingleEntry.class &&
				((SingleEntry) e).getSkater().getId().equals(skaterId))
			{
				pre = false;
			}
		}
		if(!pre)
		{
			throw new Exception("ERROR: The skater has registered in a single entry!");
		}

		// Determine competition discipline
		Discipline discipline =
			skater.getGender() == Gender.FEMALE? Discipline.LADIES_SINGLE : Discipline.MENS_SINGLE;

		// Create and add the new entry
		entry = new SingleEntry(entryId, skater, discipline);
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
		// pre: session id is new
		Session session = Helper.search(sessionList, sessionId);
		boolean pre = session == null;
		if(! pre)
		{
			throw new Exception("ERROR: Session id is not new!");
		}

		session = new Session(sessionId, discipline, program);
		sessionList.add(session);
	}

	// Atomic use case 5
	public void assignPerformanceToSession(String entryId, Program program, String sessionId) throws Exception
	{
		// pre: Performance exists
		List<String> key = new ArrayList<String>();
		key.add(entryId);
		key.add(program.name());
		Performance performance = Helper.search(performanceList, key);
		boolean pre = performance != null;
		if(!pre)
		{
			throw new Exception("ERROR: The performance does not exists!");
		}

		// pre: Performance has not been assigned to a session
		pre = performance.getSession() == null;
		if(!pre)
		{
			throw new Exception("ERROR: The performance has been assigned to a session!");
		}

		// Pre: session exists
		Session session = Helper.search(sessionList, sessionId);
		pre = session != null;
		if(!pre)
		{
			throw new Exception("ERROR: Session does not exist!");
		}

		// pre: performance's discipline and program match those of session
		pre = performance.getEntry().getDiscipline() == session.getDiscipline() &&
				performance.getProgram() == session.getProgram();


		if (!pre)
		{
			throw new Exception("Error: Performance and session do not match!");
		}

		// Post: set session of performance
		performance.setSession(session);
	}

	// Atomic use case 6
	public void assignJudgeToSession(String judgeId, String sessionId) throws Exception
	{
		// judge exists
		Judge judge = Helper.search(judgeList, judgeId);

		// session exists
		Session session = Helper.search(sessionList, sessionId);

		// session has less than 9 judges
        boolean pre = session.getJudges().size() < 9;
        if(!pre) {
            throw new Exception("ERROR: Judges are already full.");
        }

		// judge has not been assigned to session
		pre = (Helper.search(session.getJudges(), judgeId)) == null;
        if(!pre) {
            throw new Exception("ERROR: Judge is already assigned to session.");
        }

		// The judge's nationality is new for the session
		for(Judge existingJudge : session.getJudges()) {
            pre = judge.getNationality() != existingJudge.getNationality();
            if(!pre) {
                throw new Exception("ERROR: Judge's nationality is not new.");
            }
        }

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

		// Performance and judge are assigned to the same session
		boolean pre =
			(Helper.search(performance.getSession().getJudges(), judgeId)) != null;

		// Judge has not given a score to this performance
		// That is, the score does not exist
		key = new ArrayList<String>();
		key.add(entryId);
		key.add(program.name());		// name() is the same as toString in our case
		key.add(judgeId);
		Score score = Helper.search(scoreList, key);
		pre = score == null;
		if(!pre)
		{
			throw new Exception("ERROR: The performance's score by the judge already exists!");
		}


		// Post: Create and add score
		score = new Score(performance, judge, value);
		scoreList.add(score);
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

		// Nine Judge Ids are provided
		boolean pre = judgeIds.size() == 9;
		if(!pre)
		{
			throw new Exception("ERROR: Number of Judges is not 9.");
		}

		// Nine scores are provided
		pre = scores.size() == 9;
		if(!pre)
		{
			throw new Exception("ERROR: Number of Judges' scores is not 9.");
		}

		Judge judge; int counter = 0;

		for(String judgeId : judgeIds) {

			// Judge exists
			judge = Helper.search(judgeList, judgeId);
			pre = judge != null;
			if(!pre)
			{
				throw new Exception("ERROR: Judge does not exist");
			}
			// Performance and judge are assigned to the same session
			pre = (Helper.search(performance.getSession().getJudges(), judgeId)) != null;
			if(!pre)
			{
				throw new Exception("ERROR: Performance and Judge are not assigned to same session");
			}
			
			key = new ArrayList<String>();
			key.add(entryId);
			key.add(program.name());		// name() is the same as toString in our case
			key.add(judgeId);
			Score score = Helper.search(scoreList, key);
			pre = score == null;
			if(!pre)
			{
				throw new Exception("ERROR: The performance's score by the judge already exists!");
			}
			
			// Post: Create and add score
			score = new Score(performance, judge, scores.get(counter ++));
			scoreList.add(score);
		}

	}

}
