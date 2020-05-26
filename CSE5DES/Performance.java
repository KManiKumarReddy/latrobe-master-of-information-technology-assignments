import java.util.*;

public class Performance implements CompositeKey
{
	private Entry entry;
	private Program program;	// TECHNICAL, FREE
	private Session session;	// in which to perform
	private double performanceScore;

	public Performance(Entry entry, Program program)
	{
		this.entry = entry;
		this.program = program;
		this.session = null;
		this.performanceScore = -999;
	}

	public void setSession(Session session)
	{
		this.session = session;
	}

	public void setPerformanceScore(double score)
	{
		this.performanceScore = score;
	}

	public List<String> getKey()
	{
		List<String> key  = new ArrayList<String>();
		key.add(entry.getKey());
		key.add(program.toString());
		return key;
	}

	public Entry getEntry()
	{
		return entry;
	}

	public Program getProgram()
	{
		return program;
	}

	public Session getSession()
	{
		return session;
	}

	public double getPerformanceScore()
	{
		return performanceScore;
	}

	public String toString()
	{
		return getClass().getName() + "[entry: " + entry
		+ ", program: " + program + ", session: " + session
		+ ", performanceScore: " + performanceScore + "]";
	}

	public static void main(String [] args)
	{

	}
}