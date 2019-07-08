package tddKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TDDKata {
	
	private HashSet<String> returnNewDelimiters(String addDelimiterLine)
	{
		HashSet<String> newDelimiters = new HashSet<String>();
		int index = 0;
		while (index < addDelimiterLine.length())
		{
			StringBuilder newDelimiterString = new StringBuilder();
			
			while (addDelimiterLine.charAt(index) != '[')
			{
				index++;
			}
			index++;
			while (addDelimiterLine.charAt(index) != ']')
			{	
				String newRegexCharacter = String.valueOf(addDelimiterLine.charAt(index));
				if (isRegexMetaCharacter(newRegexCharacter))
				{
					newRegexCharacter = "\\" + newRegexCharacter;
				}
				newDelimiterString.append(newRegexCharacter);
				index++;
			}
			newDelimiters.add(newDelimiterString.toString());
			index++;
		}
		
		return newDelimiters;
	}
	
	private boolean isRegexMetaCharacter(String s)
	{
		HashSet<String> metaCharacters = new HashSet<String>(Arrays.asList("|", "?", "*", "+", "."));
		
		return (metaCharacters.contains(s));
	}
	
	private int returnSumOfNumberString(String numbers, HashSet<String> delimiters)
	{
		// Creates delimiter string for use by String.split() method
		StringBuilder delimiterString = new StringBuilder();
		for (String delimiter : delimiters)
		{
			delimiterString.append(delimiter + "|");
		}
		// Cuts off trailing '|'
		delimiterString.setLength(delimiterString.length() - 1);
		
		String[] numbersArray = numbers.split(delimiterString.toString());
		
		List<Integer> negatives = returnAllNegatives(numbersArray);
		if (negatives.size() != 0) throwNegativesException(negatives);
			
		int sum = 0;
		for (String number : numbersArray)
		{
			int numberValue = Integer.parseInt(number);
			sum += (numberValue <= 1000) ? numberValue : 0;
		}
		return sum;
	}
	
	private List<Integer> returnAllNegatives(String [] numbers)
	{
		List<Integer> negatives = new ArrayList<Integer>();
		for (String number : numbers)
		{
			int intNumber = Integer.parseInt(number);
			if ( intNumber < 0)
			{
				negatives.add(intNumber);
			}
		}
		return negatives;	
	}
	
	private void throwNegativesException(List<Integer> negatives)
	{
		StringBuilder exceptionMessage = new StringBuilder("negatives not allowed, found:");
		for (int negative : negatives)
		{
			exceptionMessage.append(" ");
			exceptionMessage.append(Integer.toString(negative));
		}
		throw new NegativeNumberException(exceptionMessage.toString());
	}
	
	public int Add(String numbers)
	{	
		if (numbers.equals("")) return 0;
		
		HashSet<String> delimiters = new HashSet<String>(Arrays.asList(",","\n"));

		if (numbers.length() > 1 && numbers.substring(0, 2).equals("//")) 
		{
			String [] splitNumberString = numbers.split("\n");
			String addDelimiterLine = splitNumberString[0];
			numbers = splitNumberString[1];
			
			delimiters.addAll(returnNewDelimiters(addDelimiterLine));
		}
		
		return returnSumOfNumberString(numbers, delimiters);
	}
}
