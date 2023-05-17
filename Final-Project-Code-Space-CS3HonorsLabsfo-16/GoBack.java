import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GoBack implements ActionListener
{
  JFrame frame1;
  JFrame frame2;
  
  /**
  * Constructor for the program to go back to the main screen from a   * given screen
  * @param frame1 the frame to switch to
  * @param frame2 the frame to switch from
  */
  public GoBack(JFrame frame1, JFrame frame2)
  {
    this.frame1 = frame1;
    this.frame2 = frame2;
  }

  public void actionPerformed(ActionEvent e)
  {
    frame1.setVisible(true);
    frame2.setVisible(false);
  }
}
  