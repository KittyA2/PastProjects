/** The Match class is the main ``controller'' of the yahtzee match.  
    A Match sequence is as follows: each player gets to play their turn in
    the first round,
    then each player gets to play their turn in the second round, and so on 
    until all MAXROUNDS
    (13) rounds have been played for the first game.  Then each player gets
    to play the first round for the second game, and so on...
    
    The Match class controls the following:<P>
    <UL>
    <LI> which Player plays next, and which is the current Player;
    <LI> the round number (0 through MAXROUNDS-1);
    <LI> the game  number (0 through MAXGAMES-1).
    </UL>*/



/**
  * The Match class keeps a track of the number of players, rounds and games.
  * It updates each player's turn in every round of a game till thirteen 
  * rounds are complete and also updates each game till five games are 
  * completed. 
  * 
  * @author Kitty Jasika Miranda
  * @version 11/10/2007
  */

public class Match

{
    /** The maximum number of players allowed per match. This
        value must be 5 when the class is submitted. */
    public final static int MAXPLAYERS = 5;

    /** The maximum number of games per match. This value must be 5 when
        the class is submitted. */
    public final static int MAXGAMES = 5;

    /** The maximum number of rounds per game. This value must be 13 when
        the class is submitted. */
    public final static int MAXROUNDS = 13;

    /** initialises an array of players*/
    private Player[] players;

    /** initialises the number of players*/
    private int numPlayers;

    /** initialises a player playing at present*/
    private int currentPlayer;

    /** */
    private int numRounds;

    /** initialises the number of games*/
    private int numGames;


    /** Constructor for the match.  Initialises the number of players, the
        game number, the round number, and the current player number all  to
        zero.*/


    /**
      * Constructor1:
      * Instantiates only the private fields declared above. 
      * 
      */
    public Match()
    {
        currentPlayer = 0;
        numPlayers = 0;
        players = new Player[MAXPLAYERS];
        numGames = 0;
        numRounds = 0;
    }

    /** Instance method which adds a named player into the set of players
        and adds one to the total number of players.*/


    /**
      * This method checks first if the number of players is less than the 
      * players allowed in the game and then increments the number of players 
      * by adding a player.
      */
    public void addPlayer(String name)
    {
        if (numPlayers < MAXPLAYERS)
        {
            Player player = new Player(name);
            players[numPlayers++] = player;
        }
    }

    /** Instance method which returns the number of players.
        The number returned is in the range 0 to MAXPLAYERS. 
        Returns zero only when no players have yet been added through
        addPlayer().*/


    /**
      * @return the present number of players
      */
    public int getNumPlayers()
    {
        return numPlayers;
    }

    /** Instance method which returns the Player who's turn it is. */

    /**
      * @returns the present player
      */
    public Player getCurrentPlayer()
    {
        return players[currentPlayer];
    }

    /** Instance method which returns the specified Player. 
        @param playerNum numerical identifier for the player.  Must be
        in the range 0 to n-1, where n is the number of players in the 
        game.*/


    /**
      * @return the position of the player (whether he is first or second etc)
      *  
      */
    public Player getPlayer(int playerNum)
    {
        return players[playerNum];
    }

    /** Instance method which advances to the next Player.  If this is the
        last Player then advance to the next round. If this is the last
        round, then advance to the next game.*/

    /**
      * This method updates the rounds and games of the game.
      */
    public void nextPlayer()
    {
        currentPlayer++;                        // increments to the next palyer
        if (currentPlayer >= numPlayers)
        {
            currentPlayer = 0;                  // back to the first player
            numRounds++;                        // increments the game round
        }

        if (numRounds >= MAXROUNDS)
        {
            numRounds = 0;                      // back to the first round of another game
            numGames++;                         // increments the number of games
        }

    }

    /** Instance method which returns the number of the current game.
        The number returned is 0 or greater (zero for the first game).  
        Note that the graphical user
        interface is responsible for quitting the game when the game 
        number advances to MAXGAMES */

    /**
      * @return the number of the game
      */
    public int getGameNum()
    {
        return numGames;
    }

    /** Instance method which returns the number of the current round.
        The number returned is in the range 0 to MAXROUNDS-1. */

    /**
      * @return the number of the round
      */
    public int getRoundNum()
    {
        return numRounds;
    }

}



