package tddKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TDDKata {
	
	private ArrayList<Integer> returnAllNegatives(String numbers)
	{
		ArrayList<Integer> negatives = new ArrayList<Integer>();
		for (int i = 0; i < numbers.length(); i++)
		{
			if (numbers.charAt(i) == '-')
			{
				negatives.add(-1 * Character.getNumericValue(numbers.charAt(i+1)));
			}
		}
		return negatives;	
	}
	
	private void throwNegativesException(ArrayList<Integer> negatives)
	{
		String exceptionMessage = "negatives not allowed, found:";
		for (int negative : negatives)
		{
			exceptionMessage += " " + Integer.toString(negative);
		}
		throw new RuntimeException(exceptionMessage);
	}
	
	private int returnSumOfNumberString(String numbers, HashSet<Character> delimiters)
	{
		String delimiterString = "";
		for (Character delimiter : delimiters)
		{
			delimiterString += String.valueOf(delimiter) + String.valueOf('|');
		}
		// Cuts off trailing '|'
		delimiterString = delimiterString.substring(0, delimiterString.length() - 1);
		
		String[] numbersArray = numbers.split(delimiterString);
		
		int sum = 0;
		for (String number : numbersArray)
		{
			int numberValue = Integer.parseInt(number);
			sum += (numberValue <= 1000) ? numberValue : 0;
		}
		return sum;
	}
	
	public int Add(String numbers)
	{
		ArrayList<Integer> negatives = returnAllNegatives(numbers);
		
		if (negatives.size() != 0) throwNegativesException(negatives);
		
		if (numbers.equals("")) return 0;
		
		HashSet<Character> delimiters = new HashSet<Character>(Arrays.asList(',','\n'));
		if (numbers.charAt(0) == '/')
		{
			delimiters.add(numbers.charAt(2));
			numbers = numbers.substring(4);
		}
		
		return returnSumOfNumberString(numbers, delimiters);
	}
}
