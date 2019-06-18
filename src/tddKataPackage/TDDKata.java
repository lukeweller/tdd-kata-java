package tddKataPackage;

import java.util.Arrays;
import java.util.HashSet;

public class TDDKata {
	
	public int Add(String numbers)
	{
		if (numbers.equals(""))
        {
        	return 0;
        }
		
		HashSet<Character> delimiters = new HashSet<Character>(Arrays.asList(',','\n'));
		
		if (numbers.charAt(0) == '/')
		{
			delimiters.add(numbers.charAt(2));
			numbers = numbers.substring(4);
		}
		
		int sum = 0;
		
		for (int i = 0; i < numbers.length(); i++)
		{
			char charAtIndex = numbers.charAt(i);
			if (!delimiters.contains(charAtIndex))
			{
				sum += Character.getNumericValue(charAtIndex);
			}
		}
		return sum;
	}
}
