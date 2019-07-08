package tennisKataPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisGameTest {
	
	private TennisGame tennisGame;
	
	@BeforeEach
	public void instantiateTestObject()
	{
		tennisGame = new TennisGame("Jim", "Dwight");
	}
	
	@Test
	public void reportTheCurentScore()
	{
		assertEquals("love-love", tennisGame.reportScore());
	}
	
	@Test
	public void incrementPlayerScore()
	{
		tennisGame.incrementScore("Jim");
		assertEquals("15-love", tennisGame.reportScore());
		
		tennisGame.incrementScore("Dwight");
		assertEquals("15-15", tennisGame.reportScore());
		
		for (int _i = 0; _i < 2; _i++)
		{
			tennisGame.incrementScore("Jim");
		}
		assertEquals("40-15", tennisGame.reportScore());
	}
	
	@Test
	public void exceptionOnIncrementPastGamePoint()
	{
		for (int _i = 0; _i < 4; _i++)
		{
			tennisGame.incrementScore("Jim");
		}
		assertThrows(ScoringErrorException.class, () -> {
			tennisGame.incrementScore("Jim");
		});
	}
	
	@Test
	public void exceptionOnIncrementPastGamePointCorrectErrorMessage()
	{
		for (int _i = 0; _i < 4; _i++)
		{
			tennisGame.incrementScore("Jim");
		}
		ScoringErrorException exception = assertThrows(ScoringErrorException.class, () -> {
			tennisGame.incrementScore("Jim");
		});
		assertEquals("Tried to advance game that is already complete", exception.getMessage());
	}
	
	@Test
	public void tieAtFortyReturnsDeuce()
	{
		for (int _i = 0; _i < 3; _i++)
		{
			tennisGame.incrementScore("Jim");
			tennisGame.incrementScore("Dwight");
		}
		assertEquals("deuce", tennisGame.reportScore());
	}
	
	@Test
	public void pointAfterDeuceReturnsAdvantage()
	{
		for (int _i = 0; _i < 3; _i++)
		{
			tennisGame.incrementScore("Jim");
			tennisGame.incrementScore("Dwight");
		}
		
		tennisGame.incrementScore("Jim");
		assertEquals("advantage Jim", tennisGame.reportScore());
		
		tennisGame.incrementScore("Dwight");
		assertEquals("deuce", tennisGame.reportScore());
		
		tennisGame.incrementScore("Dwight");
		assertEquals("advantage Dwight", tennisGame.reportScore());
	}
	
	@Test
	public void checkIfGameOverWorksAsExpected()
	{
		for (int _i = 0; _i < 3; _i++)
		{
			tennisGame.incrementScore("Jim");
			tennisGame.incrementScore("Dwight");
		}	
		assertEquals(false, tennisGame.isGameOver());
		
		tennisGame.incrementScore("Jim");
		assertEquals(false, tennisGame.isGameOver());
		
		tennisGame.incrementScore("Jim");
		assertEquals(true, tennisGame.isGameOver());
	}
	
	@Test
	public void returnServerWinsAfterGameOver()
	{
		for (int _i = 0; _i < 4; _i++)
		{
			tennisGame.incrementScore("Jim");
		}
		assertEquals("Jim wins", tennisGame.reportScore());
	}
	
	@Test
	public void returnOpponentWinsAfterGameOver()
	{
		for (int _i = 0; _i < 4; _i++)
		{
			tennisGame.incrementScore("Dwight");
		}
		assertEquals("Dwight wins", tennisGame.reportScore());
	}
	
	@Test
	public void returnServerWinsAfterComplexGame()
	{
		for (int _i = 0; _i < 3; _i++)
		{
			tennisGame.incrementScore("Jim");
			tennisGame.incrementScore("Dwight");
		}
		assertEquals("deuce", tennisGame.reportScore());
		
		tennisGame.incrementScore("Jim");
		assertEquals("advantage Jim", tennisGame.reportScore());
		
		tennisGame.incrementScore("Dwight");
		assertEquals("deuce", tennisGame.reportScore());
		
		tennisGame.incrementScore("Dwight");
		assertEquals("advantage Dwight", tennisGame.reportScore());
		
		tennisGame.incrementScore("Jim");
		assertEquals("deuce", tennisGame.reportScore());
		
		tennisGame.incrementScore("Jim");
		assertEquals("advantage Jim", tennisGame.reportScore());
		
		tennisGame.incrementScore("Jim");
		assertEquals("Jim wins", tennisGame.reportScore());
	}
}
