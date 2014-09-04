/** This class is to manipulate the information about each Player in a
in a game of Yahtzee.<P>

Each Player has a name and a score-sheet.  The score sheet stores all
information about the Player's game scores: it consists of score cells
for Match.MAXGAMES games and CellCodes.GRANDTOTAL+1 cells within each
game (from CellCodes.ONES to CellCodes.GRANDTOTAL).  <P>

The Evaluator class is responsible for calculating the values that get
put into the Player's score cells. <P>

HINT: the score-sheet is best implemented as a two-dimensional array,
with the game number  as the first index and the cell code as the second
index.  Recall from the CellCodes class that the CellCodes.ONES constant
has the value zero, and that all other CellCodes constants are
numerically ascending from zero to seventeen (CellCodes.GRANDTOTAL).
Hopefully, this will make creating a score-sheet array easier.
*/



/**
 * The Player class records the details and scores of a player achieved
 * in each round. 
 * 
 * @author Kitty Jasika Miranda
 * @version 11/10/07
 */

public class Player 

{
   /** initialises each player's name*/
   private String name;
   
   /** initialises the score sheet as a 2D array*/
   private int[][] scoreSheet;    
    
 
  /** Constructor for the Player.  
      <UL>
      <LI> All of the selected category score cells (from CellCodes.ONES to 
    CellCodes.CHANCE) must be initialised to a negative value.<P>

      <LI> All of the total and bonus cells (from CellCodes.TOTALUS to
    CellCodes.GRANDTOTAL) must be initialised to zero.
      </UL>
      @param name the name of the player.
  */
 
 
   /**
    * Constructor 1: 
    * This constructor contains the player's name as a parameter.
    * It instantiates the 2D score sheet array. The for loop initialises the columns (maximum
    * number of games) and rows (Cellcodes) assigning them to  -1 as expected to highlight the 
    * categoires defined in the scoresheet.
    */ 
   public Player (String name) 
   {
             
       this.name = name;
       scoreSheet = new int  [CellCodes.GRANDTOTAL+1][Match.MAXGAMES] ;
       
       for(int currentCellCode  = CellCodes.ONES;  currentCellCode <= CellCodes.CHANCE; currentCellCode++)
            for(int currentGame = 0 ; currentGame < Match.MAXGAMES; currentGame++)
       {
           scoreSheet   [currentCellCode] [currentGame]= -1;
       }
   }

  /** Instance method to return the name of the Player. */
  
  
   /**
    * @return name of the current player
    */
   public String getName () 
   {
       return name;
   }

  /** Instance method to retrieve the value from a
    particular score cell. 
      @param game the game number must be in the range from 0 to 
        Match.MAXGAMES-1.
      @param cell the CellCode for the desired category in the
        score-sheet: must be in the range from CellCodes.ONES 
        to CellCodes.GRANDTOTAL.
  */
 
 
   /**
    * @return the number of the cell and the number of the game in which the scores are present. 
    */
   public int getScoreCell (int game, int cell) 
   {
       return scoreSheet [cell] [game];
   }

  /** Instance method to set the player's score or total for a
    particular cell. 
      @param game must be in the range from 0 to Match.MAXGAMES-1.
      @param cell must be in the range from CellCodes.ONES to 
        CellCodes.GRANDTOTAL.
      @param score the value to be assigned to the cell.
  */
 
   /**
    * This method sets the score in the particular cell of that game number.
    */
   public void setScoreCell (int game, int cell, int score) 
   {
      scoreSheet[cell] [game]= score;
   }
   
}




