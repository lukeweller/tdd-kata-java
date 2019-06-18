package tddKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class TDDKata {
	
	private class Tuple<X, Y> 
	{ 
		private final X numbers; 
		private final Y delimiters; 
		private Tuple(X numbers, Y delimiters)
		{ 
			this.numbers = numbers; 
			this.delimiters = delimiters; 
		 } 
	}
	
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
	
	private Tuple<String, HashSet<String>> addNewDelimiter(String numbers, HashSet<String> delimiters)
	{
		String newDelimiterString = "";
		int index = 3;
		while (numbers.charAt(index) != ']')
		{
			String newRegexCharacter = String.valueOf(numbers.charAt(index));
			if (isRegexMetaCharacter(newRegexCharacter))
			{
				newRegexCharacter = "\\" + newRegexCharacter;
			}
			newDelimiterString += newRegexCharacter;
			index++;
		}
		delimiters.add(newDelimiterString);
		numbers = numbers.substring(index + 2);
		
		Tuple<String, HashSet<String>> returnTuple = new Tuple<String, HashSet<String>>(numbers, delimiters);
		return returnTuple;
	}
	
	private boolean isRegexMetaCharacter(String s)
	{
		HashSet<String> metaCharacters = new HashSet<String>(Arrays.asList("|", "?", "*", "+", "."));
		
		return (metaCharacters.contains(s));
	}
	
	private int returnSumOfNumberString(String numbers, HashSet<String> delimiters)
	{
		// Creates delimiter string for use by String.split() method
		String delimiterString = "";
		for (String delimiter : delimiters)
		{
			delimiterString += delimiter + "|";
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
		
		HashSet<String> delimiters = new HashSet<String>(Arrays.asList(",","\n"));

		if (numbers.charAt(0) == '/') 
		{
			// Adds new delimiter to delimiters
			// Pops the front part off of the numbers string so that it starts with the first digit
			// Tuple class used to return both values at once
			Tuple<String, HashSet<String>> holderTuple = addNewDelimiter(numbers, delimiters);
			
			numbers = holderTuple.numbers;
			delimiters = holderTuple.delimiters;
		}
		
		return returnSumOfNumberString(numbers, delimiters);
	}
}
