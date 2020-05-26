public class Skater extends Person
{
	private Gender gender;
	private int age;

	public Skater(String id, String name, String nationality, Gender gender, int age)
	{
		super(id, name, nationality);
		this.gender = gender;
		this.age = age;
	}

	public Gender getGender()
	{
		return gender;
	}

	public int getAge()
	{
		return age;
	}

	public String toString()
	{
		return getClass().getName()
			+ "[id : " + getId() + ", name: " + getName()
			+ ", nationality: " + getNationality()
			+ ", gender: " + gender + ", age: " + age + "]";
	}

	public static void main(String [] args)
	{
		Person skater = new Skater("P1", "Bob", "AUS", Gender.MALE, 22);
		System.out.println(skater);
	}
}