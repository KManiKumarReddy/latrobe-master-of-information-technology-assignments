public class PairEntry extends Entry
{
	private Skater femaleSkater;
	private Skater maleSkater;

	public PairEntry(String entryId, Skater femaleSkater, Skater maleSkater)
	{
		super(entryId, Discipline.PAIR_SKATING);
		this.femaleSkater = femaleSkater;
		this.maleSkater = maleSkater;
	}

	public String toString()
	{
		return getClass().getName() + "[entryId: " + getEntryId() + ", femaleSkater: " + femaleSkater
			+ ", maleSkater: " + maleSkater + ",discipline: " + getDiscipline() + "]";
	}

	public Skater getFemaleSkater()
	{
		return femaleSkater;
	}

	public Skater getMaleSkater()
	{
		return maleSkater;
	}


	public static void main(String [] args)
	{

	}
}