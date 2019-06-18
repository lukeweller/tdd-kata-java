package tddKataPackage;

import org.junit.jupiter.api.Assertions;
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
		Assertions.assertEquals(0, tddKata.Add(""));
	}
	
	@Test
	public void handlesOneNumber()
	{
		Assertions.assertEquals(1, tddKata.Add("1"));
	}
	
	@Test
	public void handlesTwoNumbers()
	{
		Assertions.assertEquals(3, tddKata.Add("1,2"));
	}
	
	@Test
	public void handlesUnknownAmountOfNumbers()
	{
		Assertions.assertEquals(3, tddKata.Add("1,1,1"));
	}
	
	@Test
	public void handlesNewLinesBetweenNumbers()
	{
		Assertions.assertEquals(6, tddKata.Add("1\n2,3"));
	}
	
	@Test
	public void handlesDifferentDelimiters()
	{
		Assertions.assertEquals(3, tddKata.Add("//;\n1;2"));
	}
	
	@Test
	public void handlesNegativesNotAllowed()
	{
		Assertions.assertThrows(RuntimeException.class, () -> {
			tddKata.Add("-1,-2,3");
		});
	}
}
