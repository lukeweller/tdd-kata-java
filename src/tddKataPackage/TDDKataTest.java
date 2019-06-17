package tddKataPackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TDDKataTest {
	
	@SuppressWarnings("unused")
	private TDDKata tddKata;
	
	@Before
	public void instantiateTestObject()
	{
		tddKata = new TDDKata();
	}
	
	@Test
	public void handlesEmptyString()
	{
		TDDKata tddKata = new TDDKata();
		Assert.assertEquals(0, tddKata.Add(""));
	}
	
	@Test
	public void handlesOneNumber()
	{
		TDDKata tddKata = new TDDKata();
		Assert.assertEquals(1, tddKata.Add("1"));
	}
	
	@Test
	public void handlesTwoNumbers()
	{
		TDDKata tddKata = new TDDKata();
		Assert.assertEquals(3, tddKata.Add("1,2"));
	}
}
