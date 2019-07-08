package tennisKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TennisGame {

	private String server, opponent, currentlyHoldsAdvantage;
	private Map<String, String> playerScores = new LinkedHashMap<String, String>();
	private final List<String> possibleScores = new ArrayList<String>(Arrays.asList("love", "15", "30", "40", "game point"));
	private boolean currentlyDeuce, gameOver = false;

	public void incrementScore(String playerName)
	{	
		String currentScore = playerScores.get(playerName);
		
		if (gameOver)
		{
			throw new GameOverException("Tried to advance game that is already complete");
		}
		else if (currentlyDeuce)
		{
			if (currentlyHoldsAdvantage == null)
			{
				currentlyHoldsAdvantage = playerName;
				return;
			}
			else if (currentlyHoldsAdvantage.equals(playerName));
			else
			{
				currentlyHoldsAdvantage = null;
				return;
			}
		}
		changePlayerScore(playerName, currentScore);
	}

	public String reportScore() {
		String serverScore = playerScores.get(server);
		String opponentScore = playerScores.get(opponent);
		if (gameOver)
		{
			if (serverScore.equals("game point"))
			{
				return server + " wins";
			}
			else
			{
				return opponent + " wins";
			}
		}
		else if (currentlyDeuce)
		{
			if (currentlyHoldsAdvantage == null)
			{
				return "deuce";
			}
			else
			{
				return "advantage " + currentlyHoldsAdvantage;
			}
		}
		else
		{
			return serverScore + "-" + opponentScore;				
		}
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	private void changePlayerScore(String playerName, String currentScore)
	{
		int nextScoreIndex = possibleScores.indexOf(currentScore) + 1;
		String nextScore = possibleScores.get(nextScoreIndex);
		playerScores.put(playerName, nextScore);
			
		if (nextScore.equals("game point"))
		{
			gameOver = true;
		}
		else
		{
			currentlyDeuce = isCurrentlyDeuce();
		}
	}
	
	private boolean isCurrentlyDeuce()
	{
		String serverScore = playerScores.get(server);
		String opponentScore = playerScores.get(opponent);
		return (serverScore.equals("40") && opponentScore.equals("40"));
	}
			
	public TennisGame(String serverName, String opponentName)
	{
		server = serverName;
		opponent = opponentName;
		
		playerScores.put(server, "love");
		playerScores.put(opponent, "love");
	}

}
