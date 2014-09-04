/** Codes and Constants.
    DO NOT MAKE ANY CHANGES TO THIS CLASS.
    
    This class contains constants that are used in the following
    classes: 
    <UL>
    <LI>GuiYahtzee --- the graphical user interface class that is
	supplied for you uses these values to know into which cell a score
	should be displayed.

    <LI>Player --- this class which stores information about the players
	and their scores uses these values to know into which cell the 
	score should be stored.

    <LI>Evaluator --- this class uses these values in the calculation of
	the value of the diceboard.
    </UL><P>

    HINT: These codes will be used to index the ARRAY of the Player's
    score.  ONES is actually assigned the value zero, and all the other
	constants are numerically ascending from zero (TWOS has the value 1,
	THREES has the value 2, up to GRANDTOTAL which has the value 17.
	So an array of
    scores for one game (see the Player class) could be declared of size 
	[GRANDTOTAL+1] and indexed from [ONES] to [GRANDTOTAL]. <P>

    Constants ONES to SIXES denote the score cells for the Upper section
	of the score-board.<P>

    Constants TOAK to GRANDTOTAL denote the cells in the Lower section
	of the score-board (Three Of A Kind through to the Grand Total).<P>
*/

public class CellCodes {
  /** Code for index zero */
  public static final int ONES = 0;
  /** Code for index one */
  public static final int TWOS = 1;
  /** Code for index two */
  public static final int THREES = 2;
  /** Code for index three */
  public static final int FOURS = 3;
  /** Code for index four */
  public static final int FIVES = 4;
  /** Code for index five */
  public static final int SIXES = 5;

  /** Code for index six */
  public static final int TOAK = 6;	
  /** Code for index seven */
  public static final int FOAK = 7;	
  /** Code for index eight */
  public static final int FULLH = 8;    
  /** Code for index nine */
  public static final int LOWS = 9;
  /** Code for index ten */
  public static final int HIGHS = 10;
  /** Code for index eleven */
  public static final int YAH = 11;
  /** Code for index  twelve*/
  public static final int CHANCE = 12;
  /** Code for index thirteen */
  public static final int USBONUS = 13;
  /** Code for index fourteen */
  public static final int TOTALUS = 14;
  /** Code for index fifteen */
  public static final int TOTALLS = 15;
  /** Code for index sixteen */
  public static final int YAHBONUS = 16;
  /** Code for index seventeen */
  public static final int GRANDTOTAL = 17;

  /** Class method which returns the numerical equivalent to one of 
	the constants ONES through to SIXES in this class.<P>

        For example, the int value 3 would be returned by the expression
	CellCodes.codeToNumber(CellCodes.THREES).<P>

        @param code a cell code (should be one of the Constants in this
	class).
  */
  public static final int codeToNumber(int code) {
    int result = 0;
    if ((code < ONES) || (code > SIXES)) {
      System.err.println("Illegal code passed to `codeToNumber': " +
		"Should be ine range ONES to SIXES");
    } else {
      result = code + 1;
    }
    return result;
  }
}





