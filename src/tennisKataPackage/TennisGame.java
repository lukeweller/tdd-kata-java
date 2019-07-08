package tennisKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TennisGame {

	private Map<String, String> playerScores = new LinkedHashMap<String, String>();
	private final List<String> possibleScores = new ArrayList<String>(Arrays.asList("love", "15", "30", "40", "game point"));
	private String currentlyServing;
	private String currentlyReceiving;

	public void incrementScore(String playerName)
	{	
		String currentScore = playerScores.get(playerName);
		if (currentScore.equals("game point"))
		{
			throw new ScoringErrorException("Tried to record point for player that has already won");
		}
		else
		{
			int nextScoreIndex = possibleScores.indexOf(currentScore) + 1;
			String nextScore = possibleScores.get(nextScoreIndex);
			playerScores.put(playerName, nextScore);
		}
	}

	public String reportScore() {
		StringBuilder scoreReport = new StringBuilder();
		
		scoreReport.append(playerScores.get(currentlyServing));
		scoreReport.append("-");
		scoreReport.append(playerScores.get(currentlyReceiving));

		return scoreReport.toString();
	}
	
	private void changeServer()
	{
		String holdCurrentlyServing = currentlyServing;
		currentlyServing = currentlyReceiving;
		currentlyReceiving = holdCurrentlyServing;
	}

	public TennisGame(String playerOneName, String playerOneScore, String playerTwoName, String playerTwoScore) {
		playerScores.put(playerOneName, playerOneScore);
		playerScores.put(playerTwoName, playerTwoScore);
		currentlyServing = playerOneName;
		currentlyReceiving = playerTwoName;
	}

}
