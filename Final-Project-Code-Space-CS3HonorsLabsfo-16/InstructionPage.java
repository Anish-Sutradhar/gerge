import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class InstructionPage implements ActionListener
{
  JFrame frame1;
  JFrame frame2;
  
  /**
  * Constructor for the program to go to the ascreen from a   
  * given screen
  * @param frame1 the frame to switch from
  * @param frame2 the frame to switch to
  */
  public InstructionPage(JFrame frame1, JFrame frame2)
  {
    this.frame1 = frame1;
    this.frame2 = frame2;
  }

  
  public void actionPerformed(ActionEvent e)
  {
    frame2.setVisible(true);
    frame1.setVisible(false);
  }
  
}