public abstract class Entry implements SimpleKey
{
	private String entryId;
	private Discipline discipline;	// LADIES_SINGLE, MENS_SINGLE, PAIR_SKATING
	private double entryScore;

	public Entry(String entryId, Discipline discipline)
	{
		this.entryId = entryId;
		this.discipline = discipline;
		entryScore = -999;
	}

	public String getKey()
	{
		return entryId;
	}

	public String getEntryId()
	{
		return entryId;
	}

	public Discipline getDiscipline()
	{
		return discipline;
	}

	public double entryScore()
	{
		return entryScore;
	}

	public String toString()
	{
		return getClass().getName() + "[discipline: " + discipline + "]";
	}

	public static void main(String [] args)
	{
		// no tests
	}
}