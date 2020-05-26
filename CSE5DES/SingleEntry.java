public class SingleEntry extends Entry
{
	private Skater skater;

	public SingleEntry(String entryId, Skater skater, Discipline discipline)
	{
		super(entryId, discipline);
		this.skater = skater;
	}
	// The caller of this constructor must pass the appropriate discipline
	// to the constructor. This is a consequence of having discipline in
	// the super class.
	// Note that strictly speaking, we should check that the gender of the skater
	// is consistent with that of the discipline, and throw an exception if
	// this is violated. For the sake of simplicity, we will not implement
	// this check here.

	public Skater getSkater()
	{
		return skater;
	}

	public String toString()
	{
		return getClass().getName() + "[entryId: " + getEntryId() + ", skater: "
			+ skater + ", discipline: " + getDiscipline() + "]";
	}

	public static void main(String [] args)
	{

	}
}