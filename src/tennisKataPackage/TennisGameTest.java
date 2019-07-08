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
		tennisGame = new TennisGame("Jim", "love", "Dwight", "love");
	}
	
	@Test
	public void reportTheCurentScore()
	{
		assertEquals("Jim : love\nDwight : love", tennisGame.reportScore());
	}
	
	@Test
	public void incrementPlayerScore()
	{
		tennisGame.incrementScore("Jim");
		assertEquals("Jim : 15\nDwight : love", tennisGame.reportScore());
		
		tennisGame.incrementScore("Dwight");
		assertEquals("Jim : 15\nDwight : 15", tennisGame.reportScore());
		
		for (int _i = 0; _i < 3; _i++)
		{
			tennisGame.incrementScore("Jim");
		}
		assertEquals("Jim : game point\nDwight : 15", tennisGame.reportScore());
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
		assertEquals("Tried to record point for player that has already won", exception.getMessage());
	}
}
