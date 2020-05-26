public abstract class Person implements SimpleKey
{
	private String id;
	private String name;
	private String nationality;
		// FYI, see
		// http://www.nationsonline.org/oneworld/country_code_list.htm

	public Person(String id, String name, String nationality )
	{
		this.id = id;
		this.name = name;
		this.nationality = nationality;

	}

	public String getKey()
	{
		return getId();
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getNationality()
	{
		return nationality;
	}

	public String toString()
	{
		return getClass().getName()
			+ "[id : " + id + ", name: " + name
			+ ", nationality: " + nationality + "]";
	}

	public static void main(String [] args)
	{
		// no tests
	}
}