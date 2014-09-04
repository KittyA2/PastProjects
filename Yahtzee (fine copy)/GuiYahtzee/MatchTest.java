

/**
 * The test class MatchTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MatchTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class MatchTest
     */
    public MatchTest()
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

	public void testConstructor()
	{
		Match match1 = new Match();
		assertEquals(0, match1.getNumPlayers());
	}
	
    public void testAddPlayer()
	{
		Match match1 = new Match();
		match1.addPlayer("Andy");
		match1.addPlayer("Sara");
		match1.addPlayer("Orry");
		assertEquals("Failed to add players", 3, match1.getNumPlayers());
	}

	public void testNextPlayer()
	{
		Match match1 = new Match();
		match1.addPlayer("Andy");
		match1.addPlayer("Sara");
		assertEquals("Failed to track first player", "Andy", match1.getCurrentPlayer().getName());
		match1.nextPlayer();
		assertEquals("Failed to advance to second player", "Sara", match1.getCurrentPlayer().getName());
		match1.nextPlayer();
		assertEquals("Failed to return to first player", "Andy", match1.getCurrentPlayer().getName());
	}

	public void testRoundNumber1()
	{
		Match match1 = new Match();
		match1.addPlayer("Andy");
		assertEquals("Incorrect initialisation of game number", 0, match1.getGameNum());
		assertEquals("Incorrect initialisation of round numer", 0, match1.getRoundNum());
        match1.nextPlayer();
		assertEquals("Failed to advance round", 1, match1.getRoundNum());
		for (int i = 0; i<13; i++) {
		    match1.nextPlayer();
		}
		assertEquals("Failed to advance game", 1, match1.getGameNum());
		assertEquals("Failed to track round number correctly", 1, match1.getRoundNum());
	}
	
    public void testRoundNumber2()
	{
		Match match1 = new Match();
		match1.addPlayer("Andy");
		match1.addPlayer("Orry");
        match1.nextPlayer();
        match1.nextPlayer();        
		assertEquals("Incrementing match round failed", 1, match1.getRoundNum());
		for (int i = 0; i<26; i++) {
		    match1.nextPlayer();
		}
		assertEquals("Incrementing game number failed", 1, match1.getGameNum());
		assertEquals("Round number test failed", 1, match1.getRoundNum());
		assertEquals("Incorrect player tracking", "Andy", match1.getCurrentPlayer().getName());
		
		for (int i = 0; i<26; i++) {
		    match1.nextPlayer();
		}
		assertEquals("Game number increment incorrect", 2, match1.getGameNum());
		assertEquals("Incorrect tracking of round number", 1, match1.getRoundNum());
		match1.nextPlayer();
		assertEquals("Incorrect player tracking", "Orry", match1.getCurrentPlayer().getName());
	}

}