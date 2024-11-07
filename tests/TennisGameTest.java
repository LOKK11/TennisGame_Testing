import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_Player1ScoresAfterWin() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		assertThrows(TennisGameException.class, () -> game.player1Scored()); 
	}
	
	@Test
	public void testTennisGame_Player2ScoresAfterWin() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		assertThrows(TennisGameException.class, () -> game.player2Scored()); 
	}
	
	@Test
	public void testTennisGame_TestDifferentScores() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		assertEquals("Score '15 - love' incorrect", "15 - love", game.getScore());
		game.player2Scored();
		assertEquals("Score 30 incorrect", "30 - love", game.getScore());
		game.player2Scored();
		assertEquals("Score 40 incorrect", "40 - love", game.getScore());
		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test 
	public void testTennisGame_Player1Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Test getScore when player1 has won
		String score = game.getScore();
		assertEquals("Player 1 victory score incorrect", "player1 wins", score);
	}
	
	@Test 
	public void testTennisGame_Player2Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Test getScore when player1 has won
		String score = game.getScore();
		assertEquals("Player 2 victory score incorrect", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_TestAdvantages() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		assertEquals("Advantage score incorrect", "player1 has advantage", game.getScore());
		// Test advantage for player2 when player1 has at least 4 points
		game.player2Scored();
		game.player2Scored();
		assertEquals("Advantage score incorrect", "player2 has advantage", game.getScore());
		
		
		//Arrange
		game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		assertEquals("Advantage score incorrect", "player2 has advantage", game.getScore());
		// Test advantage for player1 when player2 has at least 4 points
		game.player1Scored();
		game.player1Scored();
		assertEquals("Advantage score incorrect", "player1 has advantage", game.getScore());
	}
			
}
