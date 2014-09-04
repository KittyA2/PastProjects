/** This class maintains the capabilities and attributes of Yahtzee 
    diceboards which are constructed out of a number of Die (usually 5). <P>
    
    Each Die within the DiceBoard can be retrieved through the ``getDie'' 
    method.<P>
    
    The DiceBoard class is responsible for controlling the roll sequence
    within the game (each player's turn consists of up to three rolls of
    the DiceBoard). It does so by keeping a count of the next roll
    number (first 1, then 2, then 3, then back to 1). <P>
    */


/**
  * The Diceboard class keeps a record of all the five dice and their duties 
  * in the Yahtzee game.
  * 
  * @author Kitty Jasika Miranda
  * @version 11/10/07
  */

public class DiceBoard

{
    /** Class constant showing the number of die in the dice board. */
    public final static int NUMDIE = 5;

    /** initialises an array for the five dice.*/
    private Die[] diceSet;

    /** initialises the number of rolls.*/
    private int rollNum;


    /** Constructor for the DiceBoard. Creates a DiceBoard with NUMDIE
        Die and initialises the next roll number to 1.*/


    /**
      * Constructor 1: 
      * Instantiates an array for each of teh five dice storing every die in 
      * each of an array element.
      */
    public DiceBoard()
    {
        rollNum = 1;
        diceSet = new Die[NUMDIE];
        for (int i = 0; i <= NUMDIE - 1; i++)
        {
            diceSet[i] = new Die();
        }
    }


    /** Instance method to return a specific Die in the DiceBoard.  
        @param num identifies which die in the DiceBoard is to be
        returned.  For diceboard with NUMDIE die, numdie must be in the 
        range 0 to (NUMDIE-1).*/


    /**
      * @return the specific die in the diceboard is returned.
      */
    public Die getDie(int num)
    {
        return diceSet[num];
    }


    /** Instance method to return the number of the next roll-number.
        Each player's turn consists of up to three rolls of the DiceBoard.
        The value returned by the method must be in the range 1 to 3.*/


    /**
      * @return the next roll number. 
      */
    public int getNextRollNum()
    {
        return rollNum;
    }


    /** Instance method to roll all of the unlocked Die in the DiceBoard.
        Note that this method updates the roll number to ensure that it
        always shows the next roll number (1, then 2, then 3, then back to
        1).*/


    /**
      * This method determines the roll state of every die in the created 
      * array. If a die in the array is not locked it is rolled and finally, 
      * the roll numberis updated.
      */
    public void rollBoard()
    {
        for (int i = 0; i <= NUMDIE - 1; i++)
        {
            if (!diceSet[i].isLocked())
            {
                diceSet[i].roll();
            }
        }
        rollNum = rollNum % 3 + 1;
    }


    /** Instance method called when a new player is ready to start their
        turn.  Each of the Die in the DiceBoard is reset and the 
        roll number is set to show that the new player is about to make their
        first roll. */


    /**
      * This method resets the board to the initial conditions for the next 
      * player to have his turn.
      */
    public void resetBoard()
    {
        rollNum = 1;
        for (int i = 0; i <= NUMDIE - 1; i++)
        {
            diceSet[i].resetDie();
        }
    }

}





