package tddKataPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tddKataPackage.NegativeNumberException;

public class TDDKataTest {
	
	private TDDKata tddKata;
	
	@BeforeEach
	public void instantiateTestObject()
	{
		tddKata = new TDDKata();
	}
	
	@Test
	public void givenEmptyStringReturnZero()
	{
		assertEquals(0, tddKata.Add(""));
	}

	@Test
	public void givenOneInputReturnSum()
	{
		assertEquals(1, tddKata.Add("1"));
	}
	
	@Test
	public void givenTwoInputsReturnSum()
	{
		assertEquals(3, tddKata.Add("1,2"));
	}
	
	@Test
	public void givenUnlimitedInputsReturnSum()
	{
		assertEquals(3, tddKata.Add("1,1,1"));
		assertEquals(19, tddKata.Add("1,2,3,5,8"));
	}
	
	@Test
	public void interpretsNewlineAsDelimiter()
	{
		assertEquals(6, tddKata.Add("1\n2,3"));
	}
	
	@Test
	public void interpretsNewCustomDelimiter()
	{
		assertEquals(3, tddKata.Add("//[;]\n1;2"));
	}
	
	@Test
	public void givenNegativesThrowException()
	{
		assertThrows(NegativeNumberException.class, () -> {
			tddKata.Add("-1,-2,3");
		});
	}
	
	@Test
	public void givenNegativesThrowCorrectExceptionMessage()
	{
		NegativeNumberException exception = assertThrows(NegativeNumberException.class, () -> {
			tddKata.Add("-1,-2,3");
		});
		assertEquals("negatives not allowed, found: -1 -2", exception.getMessage());
	}
	
	@Test
	public void givenMulitpleDigitNegativesThrowException()
	{
		assertThrows(NegativeNumberException.class, () -> {
			tddKata.Add("-10,-200,-3");
		});
	}
	
	@Test
	public void givenMulitpleDigitNegativesThrowCorrectExceptionMessage()
	{
		NegativeNumberException exception = assertThrows(NegativeNumberException.class, () -> {
			tddKata.Add("-10,-200,-3");
		});
		assertEquals("negatives not allowed, found: -10 -200 -3", exception.getMessage());
	}
	
	@Test
	public void givenDoubleDigitNumbersReturnSum()
	{
		assertEquals(25, tddKata.Add("10,3,12"));
	}
	
	@Test
	public void givenTripleDigitNumbersReturnSum()
	{
		assertEquals(1100, tddKata.Add("100,999,1"));
	}
	
	@Test
	public void ignoreNumbersGreaterThanOneThousand()
	{
		assertEquals(101, tddKata.Add("1001,100,1"));
	}
	
	@Test
	public void interpretsDelimitersOfAnyLength()
	{
		assertEquals(6, tddKata.Add("//[***]\n1***2***3"));
	}
	
	@Test
	public void interpretsMultipleCustomDelimiters()
	{
		assertEquals(6, tddKata.Add("//[*][%]\n1*2%3"));
	}
	
	@Test
	public void interpretsMutipleCustomDelimitersOfAnyLength()
	{
		assertEquals(6, tddKata.Add("//[**][%;]\n1**2%;3"));
	}
	
	@Test
	public void givenIncorrectFormattingThrowsException()
	{
		assertThrows(NumberFormatException.class, () -> {
			tddKata.Add("/[**]\n1**2%;3");
		});
		assertThrows(NumberFormatException.class, () -> {
			tddKata.Add("[**]\n1**2%;3");
		});
		assertThrows(NumberFormatException.class, () -> {
			tddKata.Add("**]\n1**2%;3");
		});
		assertThrows(NumberFormatException.class, () -> {
			tddKata.Add("**\n1**2%;3");
		});
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			tddKata.Add("//\n1**2%;3");
		});
	}	
}
