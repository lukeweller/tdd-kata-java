package tddKataPackage;

public class TDDKata {
	
	public int Add(String numbers)
	{
		if (numbers.equals(""))
		{
			return 0;
		}
		else if (numbers.length() == 1)
		{
			return Character.getNumericValue(numbers.charAt(0));
		}
		else
		{
			return Character.getNumericValue(numbers.charAt(0)) + Character.getNumericValue(numbers.charAt(2));
		}
	}
}
