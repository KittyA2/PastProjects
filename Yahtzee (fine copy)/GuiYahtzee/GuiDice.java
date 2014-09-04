import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** DO NOT MAKE ANY CHANGES TO THIS CLASS */

public class GuiDice extends JLabel {
  private ImageIcon im;
  private Die die;
  private static String imPath = "";
  private static ImageIcon im0 = new ImageIcon(imPath + "none.gif");
  private static ImageIcon im1 = new ImageIcon(imPath + "one.gif");
  private static ImageIcon im2 = new ImageIcon(imPath + "two.gif");
  private static ImageIcon im3 = new ImageIcon(imPath + "three.gif");
  private static ImageIcon im4 = new ImageIcon(imPath + "four.gif");
  private static ImageIcon im5 = new ImageIcon(imPath + "five.gif");
  private static ImageIcon im6 = new ImageIcon(imPath + "six.gif");
  
  public GuiDice () { 
  }

  public GuiDice (Die d, boolean cheat) {
    die = d;
    im = im0;
    if (cheat) {
      this.addMouseListener(new MouseAdapter() {
        public void mousePressed (MouseEvent e) {
           int v = die.getValue();
           if (++v > 6)
             v = 1;
           die.setDie(v);
           showval(v);
        }
      });
    }
    setIcon(im);
  }

  public void showval (int val) {
    switch (val) {
      case 0 :
        im = im0;
        break;
      case 1 :
        im = im1;
        break;
      case 2 :
        im = im2;
        break;
      case 3 :
        im = im3;
        break;
      case 4 :
        im = im4;
        break;
      case 5 :
        im = im5;
        break;
      case 6 :
        im = im6;
        break;
      default :
        im = im0;
     }
   setIcon(im);
  }
}




