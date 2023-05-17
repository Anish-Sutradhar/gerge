import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public abstract class Scoreboard 
{ 
  JPanel board = new JPanel(); //the panel consisting of textfields
  JPanel keypanel = new JPanel(); //panel with the board JPanel and the label on top
  Map<String, Integer> scoreSaver = new TreeMap<String, Integer>(); //map intended to save every newly updated scores
  

  Die die;

  /**
  * Constructs a scoreboard on the given frame and die that will provide the socres for it
  * @param f the frame the scoreboard will be added on
  * @param die the die that will put the scores onto the scoreboard
  */
  public Scoreboard(JFrame f, Die die)
  {
    keypanel.setLayout(new BorderLayout());
    this.die = die;
  }

  /**
  * updates the scoreboard
  */
  public abstract void updateScore();

  /**
  * makes each textfield chickable
  */
  public abstract void makeClickable();

  /**
  * returns a list of the textfields in the scoreboard
  * @return the lost of textfields in the scoreboard
  */
  public Component[] getComponents()
  {
    return board.getComponents();
  }
}