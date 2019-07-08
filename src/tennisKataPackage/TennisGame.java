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
			throw new ScoringErrorException("Tried to advance game that is already complete");
		}
		else if (currentlyDeuce)
		{
			if (currentlyHoldsAdvantage == null)
			{
				currentlyHoldsAdvantage = playerName;
			}
			else if (currentlyHoldsAdvantage.equals(playerName))
			{
				gameOver = true;
			}
			else
			{
				currentlyHoldsAdvantage = null;
			}
		}
		else
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
				currentlyDeuce = setIsDeuce();
			}
		}
	}

	public String reportScore() {
		String serverScore = playerScores.get(server);
		String opponentScore = playerScores.get(opponent);
		if (currentlyDeuce)
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
	
	private boolean setIsDeuce()
	{
		String serverScore = playerScores.get(server);
		String opponentScore = playerScores.get(opponent);
		if (serverScore.equals("40") && opponentScore.equals("40"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
			
	public TennisGame(String serverName, String opponentName)
	{
		server = serverName;
		opponent = opponentName;
		
		playerScores.put(server, "love");
		playerScores.put(opponent, "love");
	}

}
