/**
 * The test class DieTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DieTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class DieTest
     */
    public DieTest()
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
		Die die1 = new Die();
		assertEquals("Failed to initialise Die value", 0, die1.getValue());
		assertEquals("Failed to initialise locked status", false, die1.isLocked());
	}

	public void testSetLock()
	{
		Die die1 = new Die();
		die1.setLock(false);
		assertEquals("Failed to unlock die", false, die1.isLocked());
		die1.setLock(true);
		assertEquals("Failed to lock die", true, die1.isLocked());
	}
	
	// Test that roll produces a value in range 1-6.
	public void testRoll1()
	{
		Die die1 = new Die();
		die1.roll();
		int val=die1.getValue();
		assertTrue("Rolled value outside 1--6", (val>=1 && val<=6));
    } 
	
    // Test that different rolls produce different values
    public void testRoll2()
	{
		Die die1 = new Die();
		int[] dieVals = {0,0,0,0,0,0,0,0};
		int rollResult;
		for (int i=0; i< 500; i++) {
		    die1.roll();
		    rollResult = die1.getValue();
		    dieVals[rollResult]++;
		} 
		assertEquals("Zeros were rolled", 0, dieVals[0]);
		assertTrue("Zero ones were rolled",(dieVals[2] >1));
		assertTrue("Zero twos were rolled",(dieVals[2] >1));
		assertTrue("Zero threes were rolled",(dieVals[3] >1));
		assertTrue("Zero fourss were rolled",(dieVals[4] >1));
		assertTrue("Zero fives were rolled",(dieVals[5] >1));
		assertTrue("Zero sixes were rolled",(dieVals[6] >1));
	    assertEquals("Sevens were rolled", 0, dieVals[7]);
   }
   
   	public void testLockedRoll()
	{
		Die die1 = new Die();
		die1.roll();
		int valBefore = die1.getValue();
		die1.setLock(true);
		boolean allSame = true;
		int thisVal;
		for (int i = 0; i < 10; i++) {
		    die1.roll();
		    thisVal=die1.getValue();
		    if (thisVal!=valBefore) {
		        allSame=false;
		    }
		}
		assertTrue("Locked die produced different values", allSame);
    }
    
  	public void testSetReset()
	{
	    Die die1 = new Die();
	    die1.setDie(5);
	    assertEquals("setValue failed", die1.getValue(), 5);
	    die1.resetDie();
	    assertEquals("Reseting failed to set value to 0", die1.getValue(),0);
	    assertEquals("Reseting failed to set locked to false", die1.isLocked(), false);
	}
}