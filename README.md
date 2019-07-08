# tdd-kata-java
Simple Kata exercises to practice test-driven development in Java

**Exercise 1**: [Link](https://osherove.com/tdd-kata-1)

**Exercise 2**: 

Tennis Game Kata
-----------------

Write a TennisGame class that will keep score of a tennis game. The rules for scoring in tennis are as follows:

1. Game starts with the score tied at zero (in tennis terms, zero is called "love").
2. A player's score progresses through the following sequence as they win a point: love, 15, 30, 40, and game point (game point means the player has won the game).
3. If both players reach 40, this is called "deuce" (a tie). From deuce, a player must score TWO consecutive points to win.
4. From deuce, if player A scores the next point, it is called "advantage A".
5. If the player with advantage wins the next point, that is game point, and the game is over.
6. If the player with advantage loses the next point, the score returns to deuce.
7. Thus (putting it all together), the only valid scores for a player in a tennis game are: love, 15, 30, 40, deuce, advantage, and game point

#### Your TennisGame class should do the following:

1. Provide a way to report the current score.
2. Provide a way to record a point for either of the players (server or opponent)
3. When reporting any score before deuce, it should be displayed in the following format: server's score, then hyphen, then opponent's score (e.g. "love-15" if the server has not scored and the opponent has 15).
4. When reporting deuce, it should be displayed as "deuce"
5. When reporting advantage, it should be displayed as "advantage server" or "advantage opponent", depending on who scored the point after deuce.
6. Provide a method to indicate if the game is over.
7. When reporting a score after the game is over, it should display either "server wins" or "opponent wins" (depending on who won)
8. After someone wins, the score should not be allowed to further advance. 
9. Variation on #8: If an attempt to advance the score occurs after a game is complete, the class should throw a GameOverException.
10. Let the user specify names for the server and opponent. When reporting advantage, or victory, the names should be used instead of "server" and "opponent".
11. Keep track of the entire history of how a game was scored, point by point, and provide a method to display all the scores up to the current one. Each point should be displayed per the display rules above, with each point on a new line.
