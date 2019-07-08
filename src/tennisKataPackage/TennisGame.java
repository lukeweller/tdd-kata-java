package tennisKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TennisGame {

	private Map<String, String> playerScores = new LinkedHashMap<String, String>();
	private List<String> possibleScores = new ArrayList<String>(Arrays.asList("love", "15", "30", "40", "game point"));

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
		for (Map.Entry<String, String> player : playerScores.entrySet()) {
			scoreReport.append(player.getKey() + " : ");
			scoreReport.append(player.getValue() + "\n");
		}
		// Cuts off trailing newline character
		scoreReport.setLength(scoreReport.length() - 1);

		return scoreReport.toString();
	}

	public TennisGame(String playerOneName, String playerOneScore, String playerTwoName, String playerTwoScore) {
		playerScores.put(playerOneName, playerOneScore);
		playerScores.put(playerTwoName, playerTwoScore);
	}

}
