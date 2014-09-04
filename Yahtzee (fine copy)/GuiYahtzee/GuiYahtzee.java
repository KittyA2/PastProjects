import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** This class creates the graphical user interface (GUI) for the Yahtzee game. 
 <P>
 DO NOT MAKE ANY CHANGES TO THIS CLASS.
 <P>
 The details in this class are beyond Cosc121. You do not need to understand how this class works to complete the assignment.
 
 @author Andy Cockburn 
 @version v1.0
*/

public class GuiYahtzee  {
  
    private final String butLabels[] = {"Ones", "Twos", "Threes",
  	"Fours", "Fives", "Sixes", "Three of a Kind", "Four of a Kind", 
  	"Full House", "Short Straight", "Long Straight", "Yahtzee", "Chance"};

    private JPanel scores = new JPanel();
    private JButton cats[] = new JButton[CellCodes.CHANCE+1];
    private JPanel diceSet = new JPanel();
    private GuiDice dice[] = new GuiDice[DiceBoard.NUMDIE];
    private JCheckBox chbs[] = new JCheckBox[DiceBoard.NUMDIE];
    protected JTextField tfs[][] = 
      new JTextField[CellCodes.GRANDTOTAL+1][Match.MAXGAMES];
    protected JButton rollButton = new JButton("Roll the Dice!");
    protected JButton nextPlayerJButton = new JButton("Next Player");
    protected DiceBoard db = new DiceBoard();
    protected JLabel nextPlayerJLabel = new JLabel();
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;
  
     /*
     The following methods activate and deactivate
     GUI components to ensure that there is continual syntactic
     correctness in the interface.
    
     show_scoreSheet displays the current status of the player's game.
    
     enable_JButtons enables only those category selection JButtons that
     have not yet been used by the player in this game.
    
     disable_AllCells disables all categories selection JButtons.  It is
     called when the user has made their selection. 

     reset_board resets the dice-board for the next roll series.
     Dice locking check-boxes are disabled and set to unchecked.
     */

     
    protected void show_scoreSheet (Player p, int gameNum) {
      int score = 0;
      for (int g = 0; g <= gameNum; g++) {
        for (int but = CellCodes.ONES; but <= CellCodes.GRANDTOTAL; but++) {
           score = p.getScoreCell(g, but);
           if (score < 0)
             tfs[but][g].setText("");
           else
             tfs[but][g].setText(String.valueOf(score));
        }
      }
    }

    protected void enable_JButtons (Player p, int gameNum) {
      for (int but = CellCodes.ONES; but <= CellCodes.CHANCE; but++) {
          if (p.getScoreCell(gameNum, but) < 0) 
            cats[but].setEnabled(true);
      }
    }
  
    protected void disable_AllCells () {
      for (int but = CellCodes.ONES; but <= CellCodes.CHANCE; but++) {
        cats[but].setEnabled(false);
      }
    }

    protected  void reset_board() {
       db.resetBoard();
       configure_die(db);
       for (int die = 0; die < DiceBoard.NUMDIE; die++) {
         chbs[die].setSelected(false);
         chbs[die].setEnabled(false);
       }
    }

    /* The following two methods activate the scoring buttons. */
    protected void clickScoreCell(int code, Match m) {
      Player p = m.getCurrentPlayer();
      int  gameNum = m.getGameNum();
      Evaluator eval = new Evaluator ();
      eval.evaluate(db, code, p, gameNum);
      show_scoreSheet(p, gameNum);
      disable_AllCells();
      rollButton.setEnabled(false);
      nextPlayerJButton.setEnabled(true);
    }
  
    // Listener for category selection.
    // Must create an evaluator for the board state.
    private void attach_listener (final JButton b, final int code, final Match m) {
      b.addActionListener(new ActionListener () {
        public void actionPerformed(ActionEvent e) { 
           // The row's field (for the right game) is configured with the
           // score. 
           clickScoreCell(code, m);
        }
      });
    }
    
    /* The following methods create the graphical user interface */
    private JTextField[] make_five_fields (int row) {
      gbConstraints = new GridBagConstraints();
      gbConstraints.gridy = row;
      gbConstraints.fill = GridBagConstraints.NONE;
      final JTextField tfsrow[] = new JTextField[Match.MAXGAMES];
      for (int field = 0; field < Match.MAXGAMES; field++) {
        tfsrow[field] = new JTextField(3);
        // tfsrow[field].setEnabled(false);
        tfsrow[field].setEditable(false);
        gbConstraints.gridx = field+1;
        gbLayout.setConstraints(tfsrow[field], gbConstraints);
        scores.add(tfsrow[field]);
      }
      return tfsrow;
    }
  
    private void oneRow (final String JLabel, final int butCode, final Match m) { 
      gbConstraints = new GridBagConstraints();
      cats[butCode] = new JButton(JLabel);
      cats[butCode].setEnabled(false);
      cats[butCode].setForeground(Color.black);
      cats[butCode].setBackground(Color.lightGray);
      gbConstraints.gridx = 0;
      gbConstraints.fill = GridBagConstraints.HORIZONTAL;
      gbConstraints.gridy = butCode;
      gbLayout.setConstraints(cats[butCode], gbConstraints);
      scores.add(cats[butCode]);
  
      tfs[butCode] = make_five_fields (butCode);
      attach_listener(cats[butCode], butCode, m);
    }
  
    private void make_totals_rows(String s, int row) {
      gbConstraints = new GridBagConstraints();
      JLabel JLabel = new JLabel(s);
      gbConstraints.gridx = 0;
      gbConstraints.fill = GridBagConstraints.HORIZONTAL;
      gbConstraints.gridy = row;
      gbLayout.setConstraints(JLabel, gbConstraints);
      scores.add(JLabel);
      tfs[row] = make_five_fields(row);
    }
  
    private void make_dice_and_JCheckBoxes(final DiceBoard db, boolean cheat)  {  
      diceSet.setBackground(Color.white);
      GridBagLayout gb = new GridBagLayout();
      GridBagConstraints gc;
      diceSet.setLayout(gb);
      for (int die = 0; die < DiceBoard.NUMDIE; die++) {
         dice[die] = new GuiDice(db.getDie(die), cheat);
         gc = new GridBagConstraints();
         gc.gridy = 0; gc.gridx = die;
         gb.setConstraints(dice[die], gc);
         diceSet.add(dice[die]);
      }
      // Add the roll JButton
      gc = new GridBagConstraints();
      gc.gridy = 0; gc.gridx = DiceBoard.NUMDIE;
      gc.gridwidth = GridBagConstraints.REMAINDER;
      gc.fill = GridBagConstraints.HORIZONTAL; 
      gc.weightx = 1.0;
      gb.setConstraints(rollButton, gc);
      diceSet.add(rollButton);
  
      for (int die = 0; die < DiceBoard.NUMDIE; die++) {
         final int which = die;
         chbs[which] = new JCheckBox();
         chbs[which].setEnabled(false);
         chbs[which].addItemListener(new ItemListener () {
           // This method is called when the user clicks the JCheckBox
           public void itemStateChanged(ItemEvent e) {
             if (e.getStateChange() == ItemEvent.DESELECTED) 
               db.getDie(which).setLock(false);
             else 
               db.getDie(which).setLock(true);
           }
         });
         gc = new GridBagConstraints();
         gc.gridy = 1; gc.gridx = die;
         gb.setConstraints(chbs[die], gc);
         diceSet.add(chbs[which]);
      }

      gc = new GridBagConstraints();
      gc.gridy = 1; gc.gridx = DiceBoard.NUMDIE;
      gc.gridwidth = GridBagConstraints.REMAINDER;
      gb.setConstraints(nextPlayerJButton, gc);
      diceSet.add(nextPlayerJButton);

    }
  
    /* The following methods are concerned with controlling the game state */
    public void configure_die (DiceBoard db) {
      for (int die = 0; die < DiceBoard.NUMDIE; die++) {
        dice[die].showval(db.getDie(die).getValue());
      }
    }
  
    protected void doARoll(DiceBoard db, Match m, final JButton rollBut) {
      db.rollBoard();
  
      configure_die(db);
      for (int die = 0; die < DiceBoard.NUMDIE; die++) {
         chbs[die].setEnabled(true);
      }
      switch (db.getNextRollNum()) {
        case 1:
  	  // Roll number has cycled round to 1.
 	  rollBut.setEnabled(false);// Will be reset on score allocation
 	  break;
        case 2:
          rollBut.setText("Second Roll");
          break;
        case 3:
          rollBut.setText("Last Roll");
 	  break;
      }
    }
    
    private void final_scores (Match m) {
        int numPlayers = 0;
        int playerTotal = 0;
        int gameNum = 0, maxGameNum = 0;
        Player p;
        
        // Create window
        final JFrame f = new JFrame();
        JPanel titles = new JPanel();
        titles.setLayout(new BorderLayout());
        JLabel maintitle = new JLabel("Yahtzee World!");
        maintitle.setFont(new Font("SansSerif", Font.ITALIC, 24));
        JLabel instr = new JLabel("Final Scores (total of game scores).");
        instr.setFont(new Font("SansSerif", Font.PLAIN, 16));
        titles.add(maintitle, "North");
        titles.add(instr, "South");

        JPanel names = new JPanel();
        JLabel l[] = new JLabel[Match.MAXPLAYERS];
        final JTextField t[] = new JTextField[Match.MAXPLAYERS];

        numPlayers = m.getNumPlayers();
        names.setLayout(new GridLayout(numPlayers, 2));
        for (int playerNum = 0; playerNum < numPlayers; playerNum++) {
          p = m.getPlayer(playerNum);
          playerTotal = 0;
          l[playerNum] = new JLabel(p.getName());
          t[playerNum] = new JTextField(20);
          names.add(l[playerNum]);
          names.add(t[playerNum]);
          maxGameNum = (m.getGameNum() >= Match.MAXGAMES) ? 
		    (Match.MAXGAMES-1) : m.getGameNum(); 
          for (gameNum = 0; gameNum <= maxGameNum; gameNum++) {
            playerTotal += 
	        p.getScoreCell(gameNum, CellCodes.GRANDTOTAL);
          }
          t[playerNum].setText(String.valueOf(playerTotal));
        }

      JButton quit = new JButton("Quit");
      quit.addActionListener(new ActionListener () {
         // This method is called when the user clicks the JButton
         public void actionPerformed(ActionEvent e) {
           System.exit(0);
         }
      });
      f.add(titles,  "North");
      f.add(names, "Center");
      f.add(quit, "South");
      f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      f.pack();
      f.setVisible(true);
      f.setResizable(false);
    }

  // Constructor for  the GUI
  public GuiYahtzee (final Match m, boolean cheat) {
    JButton quit = new JButton("Final Scores, then Quit");
    JLabel title = new JLabel("Yahtzee World!");
    title.setFont(new Font("SansSerif", Font.ITALIC, 24));
    final JFrame f = new JFrame();
    JPanel cont = new JPanel();
    cont.setLayout(new BorderLayout());

    // add the three JPanels
    gbLayout = new GridBagLayout();
    scores.setLayout(gbLayout);

    // Create the rows of category JButtons and fields
    for (int row = CellCodes.ONES; row <= CellCodes.CHANCE; row++) {
      oneRow(butLabels[row], row, m);
    }

    // Make the totals displays
    make_totals_rows("Upper Section Bonus", CellCodes.USBONUS);
    make_totals_rows("Total Upper Section", CellCodes.TOTALUS);
    make_totals_rows("Total Lower Section", CellCodes.TOTALLS);
    make_totals_rows("Yahtzee Bonus", CellCodes.YAHBONUS);
    make_totals_rows("Grand Total", CellCodes.GRANDTOTAL);

    // Make the dice and JCheckBoxes
    make_dice_and_JCheckBoxes(db, cheat);    

    rollButton.addActionListener(new ActionListener () {
         // This method is called when the user clicks the JButton
         public void actionPerformed(ActionEvent e) {
           enable_JButtons(m.getCurrentPlayer(), m.getGameNum());
           doARoll(db, m, rollButton);
         }
    });

    if (m.getNumPlayers() == 1) 
      nextPlayerJButton.setText("Next Go");
    nextPlayerJButton.setEnabled(false);
    nextPlayerJButton.addActionListener(new ActionListener () {
       // This method is called when the user clicks the JButton
       public void actionPerformed(ActionEvent e) {
         m.nextPlayer();
         nextPlayerJButton.setEnabled(false);
         if (m.getGameNum() == Match.MAXGAMES) {
           nextPlayerJLabel.setText("All games played!");
           nextPlayerJLabel.setBackground(Color.black);
           nextPlayerJLabel.setBackground(Color.white);
           rollButton.setEnabled(false); 
         } else {
           rollButton.setEnabled(true);
           nextPlayerJLabel.setText("Player: " +
		  m.getCurrentPlayer().getName() + " Game: " +
		  (m.getGameNum()+1) + " Round: " + (m.getRoundNum()+1));
           show_scoreSheet(m.getCurrentPlayer(), m.getGameNum());
           rollButton.setText("Roll the Dice!");
           reset_board();
         }
       }
    });

    nextPlayerJLabel.setText("Player: " +
                m.getCurrentPlayer().getName() + " Game: " +
                (m.getGameNum()+1) + " Round: " + (m.getRoundNum()+1));
    quit.addActionListener(new ActionListener () {
       // This method is called when the user clicks the JButton
       public void actionPerformed(ActionEvent e) {
         final_scores(m);
         f.dispose();
       }
    });

    cont.add(quit, "North");
    cont.add(title, "Center");
    cont.add(scores, "South");
    f.add(cont, "North");
    f.add(diceSet, "Center");
    f.add(nextPlayerJLabel, "South");
    f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
    f.setResizable(false);
  }

  public GuiYahtzee () {
    final Match m = new Match();
    final JFrame f = new JFrame();
    JPanel titles = new JPanel();
    JPanel cheatok = new JPanel();

    titles.setLayout(new BorderLayout());
    JLabel maintitle = new JLabel("Yahtzee World!\nPlayer Details...");
    maintitle.setFont(new Font("SansSerif", Font.ITALIC, 24));
    JLabel instr = new JLabel("Type the name of each player (1 to " +
		Match.MAXPLAYERS + " players)");
    instr.setFont(new Font("SansSerif", Font.PLAIN, 16));
    titles.add(maintitle, "North");
    titles.add(instr, "South");

    JPanel names = new JPanel();
    JLabel l[] = new JLabel[Match.MAXPLAYERS];
    final JTextField t[] = new JTextField[Match.MAXPLAYERS];
    names.setLayout(new GridLayout(Match.MAXPLAYERS, 2));
    for (int player = 0; player < Match.MAXPLAYERS; player++) {
      l[player] = new JLabel("Player " + (player+1) + " name");
      t[player] = new JTextField(20);
      names.add(l[player]);
      names.add(t[player]);
    }

    cheatok.setLayout(new BorderLayout());
    final JCheckBox cheat = new JCheckBox("Cheat", false);
    JButton ok = new JButton("OK");
    ok.addActionListener(new ActionListener () {
       // This method is called when the user clicks the JButton
       public void actionPerformed(ActionEvent e) {
         String name = "";
         for (int player = 0; player < Match.MAXPLAYERS; player++)  {
             name = t[player].getText();
             if (name.length() != 0)
               m.addPlayer(name);
         }
         if (m.getNumPlayers() == 0) {
           m.addPlayer("DUMMY");
           System.out.println("No named players.  Inventing one called `DUMMY'");
         }
         GuiYahtzee me = new GuiYahtzee(m, cheat.isSelected());
         f.dispose();
       }
    });
    cheatok.add(cheat, "West");
    cheatok.add(ok, "East");

    f.add(titles,  "North");
    f.add(names, "Center");
    f.add(cheatok, "South");
    f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
    f.setResizable(false);
  }

  public static void main (String[] args) {
    new GuiYahtzee();
  }
}
