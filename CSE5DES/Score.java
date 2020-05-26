import java.util.*;

public class Score implements CompositeKey
{
	private Performance performance;
	private Judge judge;
	private double value;

	public Score( Performance performance, Judge judge,double value)
	{
		this.performance = performance;
		this.judge = judge;
		this.value = value;
	}

	public List<String> getKey()
	{
		List<String> key = performance.getKey();
		key.add(judge.getKey());
		return key;
	}

	public String toString()
	{
		return getClass().getName() + "[performance: " + performance
			+ ", judge: " + judge
			+ ", value: " + value + "]";
	}

	public Performance getPerformance()
	{
		return performance;
	}

	public Judge getJudge()
	{
		return judge;
	}

	public double getValue()
	{
		return value;
	}
}