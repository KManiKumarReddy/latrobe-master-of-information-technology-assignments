import java.util.*;

public class Session implements SimpleKey
{
	private String sessionId;
	private Discipline discipline;	// LADIES_SINGLE, etc.
	private Program program;			// TECHNICAL, FREE
	private List<Judge> judges;

	public Session(String sessionId, Discipline discipline, Program program)
	{
		this.sessionId = sessionId;
		this.discipline = discipline;
		this.program = program;
		judges = new ArrayList<Judge>();
	}

	public String getKey()
	{
		return sessionId;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public Discipline getDiscipline()
	{
		return discipline;
	}

	public Program getProgram()
	{
		return program;
	}

	public List<Judge> getJudges()
	{
		return judges;
	}
	public String toString()
	{
		return getClass().getName()
			+ "[sessionId : " + sessionId  + ", program: " + program
			+ ", discipline: " + discipline + "]";
	}

	public static void main(String [] args)
	{
		Session session  = new Session("S01", Discipline.LADIES_SINGLE, Program.TECHNICAL);
		System.out.println(session);
	}
}