import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Die extends JComponent
{

  JFrame frame;

  LinkedList<ImageIcon> die;

  ImageIcon dice1;
  ImageIcon dice2;
  ImageIcon dice3;
  ImageIcon dice4;
  ImageIcon dice5;
  ImageIcon dice6;

  JPanel dicePanel;

  int[] diceNumber = new int[5];
  public Die(JFrame frame)
  {

    this.frame = frame;
    
    dice1 = new ImageIcon("Images/Dice1.PNG");
    dice2 = new ImageIcon("Images/Dice2.PNG");
    dice3 = new ImageIcon("Images/Dice3.PNG");
    dice4 = new ImageIcon("Images/Dice4.PNG");
    dice5 = new ImageIcon("Images/Dice5.PNG");
    dice6 = new ImageIcon("Images/Dice6.PNG");

    die = new LinkedList<ImageIcon>();

    die.add(dice1);
    die.add(dice2);
    die.add(dice3);
    die.add(dice4);
    die.add(dice5);
    // die.add(dice6);

    dicePanel = new JPanel();
    dicePanel.add(new JLabel(dice1));
    dicePanel.add(new JLabel(dice2));
    dicePanel.add(new JLabel(dice3));
    dicePanel.add(new JLabel(dice4));
    dicePanel.add(new JLabel(dice5));
    // dicePanel.add(new JLabel(dice6));
    dicePanel.setBounds(300, 0, 400, 90);
    frame.add(dicePanel);


  }

  
  /**
  * rolls the dice pieces so that they are random numbers
  * 
  */
  public void roll()
  {
    for (int i = 0; i < die.size(); i++)
    {
        int rand =(int)(Math.random() * 6) + 1;
        diceNumber[i] = rand;
        switch(rand)
        {
          case(1):
             die.set(i, dice1);
             break;
          case(2):
             die.set(i, dice2);
             break;
          case(3):
             die.set(i, dice3);
             break;
          case(4):
             die.set(i, dice4);
             break;
          case(5):
             die.set(i, dice5);
             break;
          case(6):
             die.set(i, dice6);
             break;
        }
    }
    
    for(int i = 0; i < die.size(); i++)
    {
      Component[] diceArray = dicePanel.getComponents();
      if(diceArray[i] instanceof JLabel)
          ((JLabel)diceArray[i]).setIcon(die.get(i));
    }
    
  }


      
    
  
}