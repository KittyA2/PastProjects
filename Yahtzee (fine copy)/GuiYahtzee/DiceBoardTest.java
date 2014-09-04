/**
 * The test class DiceBoardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiceBoardTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class DiceBoardTest
     */
    public DiceBoardTest()
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
		DiceBoard diceBoar1 = new DiceBoard();
		assertEquals("Failed to initialise roll number", 1, diceBoar1.getNextRollNum());
	}

	/** Test that the constructor creates five Die objects, and that they can be 
	 * retrieved using getDie.
	 */
	public void testGetDie()
	{
		DiceBoard diceBoar1 = new DiceBoard();		
		assertEquals("Failed to retrieve an initialised value from the 0th dice", 0, diceBoar1.getDie(0).getValue());
		assertEquals("Failed to retrieve an initialised value from the final dice",  0, diceBoar1.getDie(4).getValue());
    }
    
   	/** Test that the roll number advances correctly.
	 */
	public void testNextRollNum()
	{
		DiceBoard diceBoar1 = new DiceBoard();		
		assertEquals("Failed to initialise correct value for roll number", 1, diceBoar1.getNextRollNum());
		diceBoar1.rollBoard();
		assertEquals("Failed to advance to second roll", 2, diceBoar1.getNextRollNum());
        diceBoar1.rollBoard();
		assertEquals("Failed to advance to final roll", 3, diceBoar1.getNextRollNum()); 
		diceBoar1.rollBoard();
		assertEquals("Failed to return to first roll", 1, diceBoar1.getNextRollNum());
    } 

     /** Test rollBoard rolls all of the Dice.
	 */
	public void testRollBoard()
	{
		DiceBoard diceBoar1 = new DiceBoard();	
		boolean[] dieHasChanged = {false, false, false, false, false};
		diceBoar1.rollBoard();
		int[] lastVals = {diceBoar1.getDie(0).getValue(), 
		                  diceBoar1.getDie(1).getValue(),
		                  diceBoar1.getDie(2).getValue(),
		                  diceBoar1.getDie(3).getValue(),
		                  diceBoar1.getDie(4).getValue()};
		for (int i = 0; i<100; i++) {
		    diceBoar1.rollBoard();
		    for (int j=0; j<DiceBoard.NUMDIE; j++) {
		        if (lastVals[j] != diceBoar1.getDie(j).getValue()) {
		            dieHasChanged[j] = true;
		        }
		    }
	    }
		assertEquals("The 0th die failed to roll", true, dieHasChanged[0]);
	    assertEquals("The 1st die failed to roll", true, dieHasChanged[1]);
		assertEquals("The 2nd die failed to roll", true, dieHasChanged[2]);
		assertEquals("The 3rd die failed to roll", true, dieHasChanged[3]);
		assertEquals("The final die failed to roll", true, dieHasChanged[4]);
    } 
    
    public void testResetBoard()
	{
		DiceBoard diceBoar1 = new DiceBoard();	
		diceBoar1.rollBoard();
		diceBoar1.resetBoard();
	
		assertEquals("Failed to reset roll number", 1, diceBoar1.getNextRollNum());
	    assertEquals("Failed to reset die value", 0, diceBoar1.getDie(3).getValue());
    } 
}
