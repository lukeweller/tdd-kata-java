package tddKataPackage;

public class TDDKata {
	
	public int Add(String numbers)
	{
		int sum = 0;
		for (int i = 0; i < numbers.length(); i++)
		{
			char charAtIndex = numbers.charAt(i);
			if (charAtIndex != ',' && charAtIndex != '\n')
			{
				sum += Character.getNumericValue(charAtIndex);
			}
		}
		return sum;
	}
}
