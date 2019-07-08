package tennisKataPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisGameTest {

	private TennisGame tennisGame;
	private final String testServerName = "server";
	private final String testOpponentName = "opponent";
	
	private void advanceGame(int incrementServer, int incrementOpponent)
	{
		for (int i = 0; i < incrementServer; i++) tennisGame.incrementScore(testServerName);
		for (int i = 0; i < incrementOpponent; i++) tennisGame.incrementScore(testOpponentName);
	}

	@BeforeEach
	public void instantiateTestObject()
	{
		tennisGame = new TennisGame(testServerName, testOpponentName);
	}

	@Test
	public void reportTheCurentScore()
	{
		assertEquals("love-love", tennisGame.reportScore());
	}

	@Test
	public void incrementPlayerScore()
	{
		tennisGame.incrementScore(testServerName);
		assertEquals("15-love", tennisGame.reportScore());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("15-15", tennisGame.reportScore());

		advanceGame(2,0);
		assertEquals("40-15", tennisGame.reportScore());
	}

	@Test
	public void exceptionOnIncrementPastGamePoint()
	{
		advanceGame(4,0);
		assertThrows(GameOverException.class, () -> {
			tennisGame.incrementScore(testServerName);
		});
	}

	@Test
	public void tieAtFortyReturnsDeuce()
	{
		advanceGame(3,3);
		assertEquals("deuce", tennisGame.reportScore());
	}

	@Test
	public void pointAfterDeuceReturnsAdvantage()
	{
		advanceGame(3,3);

		tennisGame.incrementScore(testServerName);
		assertEquals("advantage " + testServerName, tennisGame.reportScore());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("deuce", tennisGame.reportScore());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("advantage " + testOpponentName, tennisGame.reportScore());
	}

	@Test
	public void checkIfGameOverWorksAsExpected()
	{
		advanceGame(3,3);
		assertEquals(false, tennisGame.isGameOver());

		tennisGame.incrementScore(testServerName);
		assertEquals(false, tennisGame.isGameOver());

		tennisGame.incrementScore(testServerName);
		assertEquals(true, tennisGame.isGameOver());
	}

	@Test
	public void returnServerWinsAfterGameOver()
	{
		advanceGame(4, 0);
		assertEquals(testServerName + " wins", tennisGame.reportScore());
	}

	@Test
	public void returnOpponentWinsAfterGameOver()
	{
		advanceGame(0,4);
		assertEquals(testOpponentName + " wins", tennisGame.reportScore());
	}

	@Test
	public void returnServerWinsAfterComplexGame()
	{
		advanceGame(3,3);
		assertEquals("deuce", tennisGame.reportScore());

		tennisGame.incrementScore(testServerName);
		assertEquals("advantage " + testServerName, tennisGame.reportScore());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("deuce", tennisGame.reportScore());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("advantage " + testOpponentName, tennisGame.reportScore());

		tennisGame.incrementScore(testServerName);
		assertEquals("deuce", tennisGame.reportScore());

		tennisGame.incrementScore(testServerName);
		assertEquals("advantage " + testServerName, tennisGame.reportScore());

		tennisGame.incrementScore(testServerName);
		assertEquals(testServerName + " wins", tennisGame.reportScore());
	}

	@Test
	public void returnCorrectScoreHistory()
	{
		for (int i = 0; i < 3; i++) {
			tennisGame.incrementScore(testServerName);
			tennisGame.incrementScore(testOpponentName);
		}
		assertEquals("love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce", tennisGame.getScoreHistory());

		tennisGame.incrementScore(testServerName);
		assertEquals("love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce\nadvantage " + testServerName,
				tennisGame.getScoreHistory());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce\nadvantage " + testServerName +"\ndeuce",
				tennisGame.getScoreHistory());

		tennisGame.incrementScore(testOpponentName);
		assertEquals("love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce\nadvantage " + testServerName +"\ndeuce" + "\nadvantage " + testOpponentName,
				tennisGame.getScoreHistory());

		tennisGame.incrementScore(testServerName);
		assertEquals(
				"love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce\nadvantage " + testServerName +"\ndeuce" + "\nadvantage " + testOpponentName + "\ndeuce",
				tennisGame.getScoreHistory());

		tennisGame.incrementScore(testServerName);
		assertEquals(
				"love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce\nadvantage " + testServerName +"\ndeuce" + "\nadvantage " + testOpponentName + "\ndeuce" + "\nadvantage " + testServerName,
				tennisGame.getScoreHistory());

		tennisGame.incrementScore(testServerName);
		assertEquals(
				"love-love\n15-love\n15-15\n30-15\n30-30\n40-30\ndeuce\nadvantage " + testServerName +"\ndeuce" + "\nadvantage " + testOpponentName + "\ndeuce" + "\nadvantage " + testServerName + "\n" + testServerName + " wins",
				tennisGame.getScoreHistory());
	}
}
