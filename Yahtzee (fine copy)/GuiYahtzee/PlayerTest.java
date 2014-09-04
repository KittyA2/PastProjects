/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    /** Test the constructor and the getName method */
	public void testConstructor()
	{
		Player player1 = new Player("Andy");
		assertEquals("Failed to construct and retrieve name", "Andy", player1.getName());
	}
	
	/** First test of setting and getting scorecells. */
	public void testScoreCells1()
	{
		Player player1 = new Player("Andy");
		player1.setScoreCell(1,1,4);
		assertEquals("Failed to set a score cell", 4, player1.getScoreCell(1,1));
	}
	
	/** Second test of setting and getting scorecells. */
	public void testScoreCells2()
	{
	    Player player1 = new Player("Andy");
		int[] oneGameScores = {3,6,9,12,15,18, 18, 25, 25, 30, 40, 40, 5};
		for (int i =0; i< oneGameScores.length; i++) {
		    player1.setScoreCell(0, i, oneGameScores[i]);
		    assertEquals("Failed to set a score in all cells of one game", oneGameScores[i], player1.getScoreCell(0, i));
		}
	}
	
		/** Second test of setting and getting scorecells. */
	public void testScoreCells3()
	{
	    Player player1 = new Player("Andy");
		int[] oneGameScores = {3,6,9,12,15,18, 18, 25, 25, 30, 40, 40, 5};
		for (int game =0; game<Match.MAXGAMES; game++) {
		    for (int i =0; i< oneGameScores.length; i++) {
		        player1.setScoreCell(game, i, oneGameScores[i]);
		        assertEquals("Failed to set a score in all cells of several games", oneGameScores[i], player1.getScoreCell(game, i));
		    }
		}
	}
}
