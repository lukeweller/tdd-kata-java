package tddKataPackage;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TDDKataTest {
	
	private TDDKata tddKata;
	
	@BeforeEach
	public void instantiateTestObject()
	{
		tddKata = new TDDKata();
	}
	
	@Test
	public void handlesEmptyString()
	{
		Assert.assertEquals(0, tddKata.Add(""));
	}
	
	@Test
	public void handlesOneNumber()
	{
		Assert.assertEquals(1, tddKata.Add("1"));
	}
	
	@Test
	public void handlesTwoNumbers()
	{
		Assert.assertEquals(3, tddKata.Add("1,2"));
	}
	
	@Test
	public void handlesUnknownAmountOfNumbers()
	{
		Assert.assertEquals(3, tddKata.Add("1,1,1"));
	}
	
	@Test
	public void handlesNewLinesBetweenNumbers()
	{
		Assert.assertEquals(6, tddKata.Add("1\n2,3"));
	}
	
	@Test
	public void handlesDifferentDelimiters()
	{
		Assert.assertEquals(3, tddKata.Add("//;\n1;2"));
	}
}
