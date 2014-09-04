/** Capabilities and attributes of one Die. At any instant, the
    Die has two attributes:
    <UL>
    <LI> a value which is in the range from 0 to 6.  The zero value is
    used to denote an unassigned value.
    
    <LI> a locked status which is either true of false.
    </UL>*/



/**
  * The Die class represents all the properties and behaviours of a die in the
  * Yahtzee game. This class keeps a track of each die's values, its rolling, 
  * and also determines a die's locked state. 
  * 
  * @author Kitty Jasika Miranda 
  * @version 11/10/2007
  */

public class Die

{
    /** initialises the value of a die.*/
    private int dieValue;

    /** initialises lock to control the die*/
    private boolean locked;


    /** Die constructor.  Initialises the Die to the unassigned (zero)
        value and unlocked state.*/

    /**
      * Constructor 1:
      * Instantiates a die with a zero value and defines die to be unlocked.
      */
    public Die()
    {
        dieValue = 0;
        locked = false;
    }




    /** Instance method which returns the value currently held by the Die.*/

    /**
      * @return the value of a die when it is rolled. 
      */
    public int getValue()
    {
        return dieValue;
    }



    /** Instance method for setting the Die's value to a random
        value in the range 1 to 6.  Note that following the expression
        returns an integer value in the range 0 to 5.  <P>
        <PRE>
        (int) Math.floor(Math.random()*6)
        </PRE>
        <P>*/

    /**
      * This method rolls the five dice randomly. If a die is locked 
      * it does not roll again and thus the value of that die is preserved.
      * Only the unlocked die is rolled each time when this method is called.
      */
    public void roll()
    {
        if (!isLocked())
        {
            int range = (int)Math.floor(Math.random() * 6);
            dieValue = range + 1;
        }
    }

    /** Instance method which returns true when the Die is locked, and
        false when it is not. */

    /**
      * This boolean method returns true if and only if the die is 
      * locked otherwise return false as defined in the constructor. 
      */
    public boolean isLocked()
    {
        return locked;
    }

    /** Instance method to set the locked status of the Die.  */

    /**
      * @param lock sets the locked state of the die (to true or false)
      * This mutator (setter) method changes the locked status of a die. 
      */
    public void setLock(boolean lock)
    {
        locked = lock;
    }

    /** Instance method to reset the Die to the zero (unassigned) value and
        the unlocked state. */

    /**
      * This method changes the values of a die to its initial values as 
      * defined in the constructor so that the next player can have his turn. 
      */
    public void resetDie()                      /// do i nt have to tell it when
    {
        dieValue = 0;
        locked = false;
    }



    /** Instance method to set a specific value for the Die.  
        Note that the value is set regardless of the locked state of the
        die.
        <P>
        @param val the new value for the die.*/

    /**
      * The value of a die to set to a new value. (from 1 to 6) 
      */
    public void setDie(int val)
    {
        dieValue = val;
    }

}

