package tennisKataPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TennisGame {
	
	private String server, opponent;
	private Map<String, Integer> scores;
	private List<String> scoreHistory;
	private final List<String> tennisScores = new ArrayList<String>(Arrays.asList("love", "15", "30", "40"));

	public void incrementScore(String playerName)
	{	
		if (isGameOver())
		{
			throw new GameOverException("Tried to advance game that is already complete");
		}
		else
		{
			scores.put(playerName, scores.get(playerName) + 1);
			scoreHistory.add(reportScore());
		}
	}
	
	public boolean isGameOver()
	{
		return (scores.get(server) > 3 || scores.get(opponent) > 3) && (Math.abs(scores.get(server) - scores.get(opponent)) > 1);
	}
	
	public String reportScore()
	{
		if (isGameOver())
		{
			if (scores.get(server) > scores.get(opponent))
			{
				return server + " wins";
			}
			else
			{
				return opponent + " wins";
			}
		}
		else if (isDeuce())
		{
			if (scores.get(server) > scores.get(opponent))
			{
				return "advantage " + server;
			}
			else if (scores.get(server) < scores.get(opponent))
			{
				return "advantage " + opponent;
			}
			else
			{
				return "deuce";
			}
		}
		else
		{
			return tennisScores.get(scores.get(server)) + "-" + tennisScores.get(scores.get(opponent));				
		}
	}
	
	private boolean isDeuce()
	{
		return scores.get(server) > 2 && scores.get(opponent) > 2 && !isGameOver();
	}

	public String getScoreHistory()
	{
		StringBuilder historyString = new StringBuilder();
		for (String score : scoreHistory)
		{
			historyString.append(score + "\n");
		}
		// Cuts off trailing '\n'
		return historyString.toString().substring(0, historyString.length() - 1);
	}
			
	public TennisGame(String server, String opponent)
	{	
		this.server = server;
		this.opponent = opponent;
		
		scores = new LinkedHashMap<String, Integer>();
		scores.put(server, 0);
		scores.put(opponent, 0);
		
		scoreHistory = new ArrayList<String>(Arrays.asList("love-love"));
	}
}
