
/** When a player selects a particular score category (such as "Four of a
    Kind") an object of this class is instantiated and the evaluate method
    is invoked  to evaluate the DiceBoard with respect to
    the category that the user selected. The Player's score
    information is then updated by private methods in this class according 
    to the score.  
    All totals for this Player's game are re-calculated.<P>
    
    NOTE: this is by far the most difficult class for you to complete.
    Tackle it ONLY after you have got all other classes working completely.*/


/**
  * The Evaluator class is the main class that controls the Categories in
  * the yahtzee game. It sums up the scores and asserts each category the 
  * scores calculatedaccording to the rules of the game.
  * 
  * @author Kitty Jasika Miranda
  * @version 11/10/2007
  */

public class Evaluator

{
    /** initialising an array of bins*/
    private int[] bins;

    /** initialising an array for dice*/
    private Die[] diceArray;

    /** initialising the total score*/
    private int totalScore;

    /** initialisng a player*/
    private Player player;

    /** initialising the number of dice*/
    private int numDie;

    /** initialising a board of dice*/
    private int board;

    /** initialising scores for each category*/
    private int scores;

    /** initialising the cellcode number*/
    private int cellCode;

    /** initialisng the number of a game*/
    private int gameNum;NewClass



    /** Default Constructor for the Evaluator class.*/
    public Evaluator()
    {
    }


    /** Instance method which calculates the  value of the DiceBoard for the 
        category the Player selected.
        This value is added to the Player's score record for this
        game, and all totals for this player within this game are 
        recalculated.<P>
        @param board  the DiceBoard object containing the current state
        of the dice.
        @param cellCode a CellCodes constant value representing the 
        the category for score allocation that the user selected.
        This parameter is in the range CellCodes.ONES to
        CellCodes.CHANCE.
        @param player the current player.
        @param gameNum the current game number.*/

    /**
      * When one of the categories is selected in the yahztee scoresheet it 
      * invokes this method which in turn invokes the appropriate method 
      * related tothat category.
      * 
      */
    public void evaluate(DiceBoard board, int cellCode, Player player, 
                         int gameNum)
    {
        numDie = 5;
        this.player = player;

        scores = 0;
        totalScore = 0;

        diceArray = new Die[numDie];                  // instantiating an array of 5 dice
        for (int i = 0; i <= numDie - 1; i++)
        {
            diceArray[i] = board.getDie(i);           // storing the die in an array
        }

        bins = new int[7];                            // instantiating the number of bins
        binSort();                                    // invoking the method to sort similar dice  
        yahBonus(player);                             // invokes the method to assign yahtzee bonus
                                                              // subsequently after the first yahtzee
 
        if (cellCode == CellCodes.ONES)
        {
            hasNum(1, cellCode, player);
        }
        if (cellCode == CellCodes.TWOS)
        {
            hasNum(2, cellCode, player);
        }
        if (cellCode == CellCodes.THREES)
        {
            hasNum(3, cellCode, player);
        }
        if (cellCode == CellCodes.FOURS)
        {
            hasNum(4, cellCode, player);
        }
        if (cellCode == CellCodes.FIVES)
        {
            hasNum(5, cellCode, player);
        }
        if (cellCode == CellCodes.SIXES)
        {
            hasNum(6, cellCode, player);
        }
        if (cellCode == CellCodes.TOAK)
        {
            hasThreeofAKind(player);
        }
        if (cellCode == CellCodes.FOAK)
        {
            hasFourofAKind(player);
        }
        if (cellCode == CellCodes.FULLH)
        {
            hasFullHouse(player);
        }
        if (cellCode == CellCodes.LOWS)
        {
            hasShortStraight(player);
        }
        if (cellCode == CellCodes.HIGHS)
        {
            hasLongStraight(player);
        }
        if (cellCode == CellCodes.YAH)
        {
            hasYahtzee(player);
        }
        if (cellCode == CellCodes.CHANCE)
        {
            hasChance(player);
        }
    }


    /**
     * Sorts the array of dice and stores the number of dice that have the 
     * same value in each bin constructed in the above method.
     * It gets the value of each die in the array. 
     */
    private void binSort()
    {
        for (int i = 0; i < diceArray.length; i++)
        {
            int count = bins[diceArray[i].getValue()]++;
            totalScore += diceArray[i].getValue();
        }
    }


    /**
     * This method comprises all the categories from Ones to Sixes.
     * The counter is the number of dice with the similar value.
     * @param num the number of the die that has the same value.
     * @param cell the number of the cell in which the score should be 
     * inserted. 
     * @param player score is assigned to that specific player 
     */
    private void hasNum(int num, int cell, Player player)
    {
        int scores = 0;
        int counter = bins[num];
        scores = counter * num;
        player.setScoreCell(gameNum, cell, scores);
        uppSecTotal(player);
    }
    

    /**
     * This method checks if three of the dice have the same value if they do 
     * it adds upthe three similar values as well as the other dice values.
     * @param player score is assigned to that specific player
     */
    private boolean hasThreeofAKind(Player player)
    {
        int scores = 0;
        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] == 3)
            {
                scores = totalScore;
            }
        }
        player.setScoreCell(gameNum, CellCodes.TOAK, scores);
        lowSecTotal(player);
        return true;
    }


    /**
     * This method checks if four of the dice have the same value if they do 
     * it adds up the four similar values as well as the other dice values.
     * @param player score is assigned to that specific player
     */
    private void hasFourofAKind(Player player)
    {
        int scores = 0;
        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] == 4)
            {
                scores = totalScore;
            }
        }
        player.setScoreCell(gameNum, CellCodes.FOAK, scores);
        lowSecTotal(player);
    }


    /**
     * This method detects if the dice three and two of the dice have the same
     * value and or if the five dice have the same value. If the conditions 
     * are satisfied it assigns the defined score.
     * @param player score is assigned to that specific player
     */
    private void hasFullHouse(Player player)
    {
        int scores = 0;
        boolean hasPair = false;
        boolean hasThreeofAKind = false;
        boolean hasYahtzee = false;

        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] == 3)
            {
                hasThreeofAKind = true;
            }
        }
        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] == 2)
            {
                hasPair = true;
            }
        }
        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] == 5)
            {
                hasYahtzee = true;
            }
        }
        if ((hasThreeofAKind == true && hasPair == true) || 
            (hasYahtzee == true))
        {
            scores = 25;
        }
        player.setScoreCell(gameNum, CellCodes.FULLH, scores);
        lowSecTotal(player);
    }


    /**
     * This method looks for consecutive sequence of five dice.
     * It works in the three cases: 1 -> if the dice are from 1 to 4
     * 2 -> if the dice are from 2 to 5, and 3 -> if the dice are from 3 to 6.
     *  @param player score is assigned to that specific player
     */
    private void hasShortStraight(Player player)
    {
        int scores = 0;
        int count = 1;
        for (int i = 1; i < bins.length; i++)
        {
            if (bins[i] >= 1)
            {
                count++;
                if (count == 4)
                {
                    break;
                }
            }
            else
            {
                count = 0;
            }
        }
        if (count == 4)
        {
            scores = 30;
        }
        player.setScoreCell(gameNum, CellCodes.LOWS, scores);
        lowSecTotal(player);
    }


    /**
     * This method looks for consecutive sequence of five dice.
     * It works in the two cases: 1 -> if the dice are from 1 to 5
     * and the 2 -> if the dice are from 2 to 6. 
     * @param player score is assigned to that specific player
     */
    private void hasLongStraight(Player player)
    {
        int scores = 0;
        int count = 1;
        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] >= 1)
            {
                count++;
                if (count == 5)
                {
                    break;
                }
            }
            else
            {
                count = 0;
            }
        }
        if (count == 5)
        {
            scores = 40;
        }
        player.setScoreCell(gameNum, CellCodes.HIGHS, scores);
        lowSecTotal(player);
    }


    /**
     * If the all the diceValues are the same this method assigns the 
     * score defined.
     * @param player score is assigned to that specific player
     */
    private boolean hasYahtzee(Player player)
    {
        int scores = 0;
        for (int i = 0; i < bins.length; i++)
        {
            if (bins[i] == 5)
            {
                scores = 50;
            }
        }
        player.setScoreCell(gameNum, CellCodes.YAH, scores);
        lowSecTotal(player);
        return true;
    }


    /**
     * This method adds up all the dice values.
     * @param player score is assigned to that specific player
     */
    private void hasChance(Player player)
    {
        int scores = 0;
        for (int i = 0; i < bins.length; i++)
        {
            {
                scores = totalScore;
            }
        }
        player.setScoreCell(gameNum, CellCodes.CHANCE, scores);
        lowSecTotal(player);
    }


    /**
     * This method checks if the upper section exceeds the score of 63, and if
     * it does it assigns a bonus of 35 to the upper section.
     * @param player score is assigned to that specific player 
     */
    private void uppSecBonus(Player player)
    {
        int scores = 0;
        int hasNum = player.getScoreCell(gameNum, CellCodes.TOTALUS);
        if (hasNum > 63)
        {
            scores += 35;
        }
        player.setScoreCell(gameNum, CellCodes.USBONUS, scores);
    }


    /**
     * This method sums up the scores from the upper section and adds 
     * the upper section bonus to the total.
     * @param player score is assigned to that specific player 
     */
    private void uppSecTotal(Player player)
    {
        int scores = 0;
        for (int i = CellCodes.ONES; i <= CellCodes.SIXES; i++)
        {
            int cellScore = player.getScoreCell(gameNum, i);
            if (cellScore != -1)
            {
                scores += player.getScoreCell(gameNum, i);
            }
        }
        player.setScoreCell(gameNum, CellCodes.TOTALUS, scores);
        if (scores > 63)
        {
            uppSecBonus(player);
        }
        player.setScoreCell(gameNum, CellCodes.TOTALUS, scores);
        grandTotal(player);
    }


    /**
     * This method sums up the scores from the category
     * ThreeofAKind till Chance.
     * @param player score is assigned to that specific player
     */
    private void lowSecTotal(Player player)
    {
        int scores = 0;
        for (int i = CellCodes.TOAK; i <= CellCodes.CHANCE; i++)
        {
            int cellScore = player.getScoreCell(gameNum, i);
            if (cellScore != -1)
            {
                scores += player.getScoreCell(gameNum, i);
            }
        }
        player.setScoreCell(gameNum, CellCodes.TOTALLS, scores);
        grandTotal(player);
    }


    /**
     * This method assigns 100 when the player has an yahtzee for the second 
     * time and keeps on adding the bonus as long as all the dice are of the 
     * same value.
     * @param player score is assigned to that specific player 
     */
    private void yahBonus(Player player)
    {
        int scores = 0;
        int hasNum = player.getScoreCell(gameNum, CellCodes.YAH);

        if (hasNum == 50)
            for (int i = 0; i < bins.length; i++)
            {
                if (bins[i] == numDie)
                {
                    scores = 100 + player.getScoreCell(gameNum, 
                                                       CellCodes.YAHBONUS);
                }
            }
        player.setScoreCell(gameNum, CellCodes.YAHBONUS, scores);
        grandTotal(player);
    }


    /**
     * This method adds up the yahtzee bonus as well as the total upper and 
     * lower section.
     * @param player score is assigned to that specific player
     */
    private void grandTotal(Player player)
    {
        int scores = 0;
        for (int i = CellCodes.TOTALUS; i <= CellCodes.TOTALLS; i++)
        {
            int cellScore = player.getScoreCell(gameNum, i);
            if (cellScore != -1)
            {
                scores += player.getScoreCell(gameNum, i);
            }
        }
        player.setScoreCell(gameNum, CellCodes.GRANDTOTAL, scores);
    }

}

