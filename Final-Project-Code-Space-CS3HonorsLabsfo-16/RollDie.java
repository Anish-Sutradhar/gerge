import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RollDie implements ActionListener
{
  Scoreboard[] boards;
  Player p1;
  Player p2;

  public RollDie(Scoreboard[] boards, Player p1, Player p2)
  {
    this.boards = boards;
    this.p1 = p1;
    this.p2 = p2;
  }

  ArrayList<Integer> numbers0 = new ArrayList<Integer>();
        ArrayList<Integer> numbers1 = new ArrayList<Integer>();
        ArrayList<Integer> numbers2 = new ArrayList<Integer>();
        ArrayList<Integer> numbers3 = new ArrayList<Integer>();
  /**
  * perfoms the action when the corresponding button is pressed
  */
  public void actionPerformed(ActionEvent e)
  {
    //if (rollCount == 1)
    //TextField playerTurn = new TextField(""); 
    if(!isFinished())
    {
      boards[0].die.roll();
      if (p1.isGoing)
      {
        
        boards[0].updateScore();
        boards[1].updateScore();
        
        
        
        Component[] boardArray0 = boards[0].getComponents();
        for(int i = 4; i < boardArray0.length; i +=3)
        {
          boardArray0[i].setEnabled(true);
        } 
        
        Component[] boardArray1 = boards[1].getComponents();
                for(int i = 1; i < boardArray1.length; i += 3)
        {
          boardArray1[i].setEnabled(true);
        }
        
        Component[] boardArray2 = boards[2].getComponents();
        for(int i = 4; i < boardArray2.length; i +=3)
        {
          if(boardArray2[i].isEnabled())
          {
            // numbers2.add(i);
            boardArray2[i].setEnabled(false);
          }
        } 
        
        Component[] boardArray3 = boards[3].getComponents();
        for(int i = 1; i < boardArray3.length; i += 3)
        {
          if(boardArray3[i].isEnabled())
          {
            // numbers3.add(i);
            boardArray3[i].setEnabled(false);
          }
        }
      }
      else
      {
        boards[2].updateScore();
        boards[3].updateScore();
        
        Component[] boardArray2 = boards[2].getComponents();
        for(int i = 4; i < boardArray2.length; i +=3) {
          boardArray2[i].setEnabled(true);
        } 
        
        Component[] boardArray3 = boards[3].getComponents();
        for(int i = 1; i < boardArray3.length; i += 3) {
          boardArray3[i].setEnabled(true);
        }
        
        Component[] boardArray0 = boards[0].getComponents();
        for(int i = 4; i < boardArray0.length; i +=3)
        {
          if(boardArray0[i].isEnabled())
          {
            // numbers0.add(i);
            boardArray0[i].setEnabled(false);
          }
        } 
        
        Component[] boardArray1 = boards[1].getComponents();
        for(int i = 1; i < boardArray1.length; i += 3)
        {
          if(boardArray1[i].isEnabled())
          {
            // numbers1.add(i);
            boardArray1[i].setEnabled(false);
          }
        }

        
  
      }
        
      p1.transition();
      p2.transition();
    }
    else
    {
      JFrame victoryFrame = new JFrame();
      victoryFrame.setSize(1000, 760);
      Component[] boardArray = boards[0].getComponents();
      Component[] boardArray2 = boards[1].getComponents();
      Component[] boardArray3 = boards[2].getComponents();
      Component[] boardArray4 = boards[3].getComponents();
      
      if(((UpperScore)boards[0]).getUpperSectionScore() >= 63)
      {
        ((TextField)boardArray[29]).setText(
          String.valueOf(((UpperScore)boards[0]).getUpperSectionScore() + 35)
        );
      }
      else
      {
        ((TextField)boardArray[29]).setText(
          String.valueOf(((UpperScore)boards[0]).getUpperSectionScore()
        ));
      }

            if(((UpperScore2)boards[2]).getUpperSectionScore() >= 63)
      {
        ((TextField)boardArray3[29]).setText(
          String.valueOf(((UpperScore2)boards[2]).getUpperSectionScore() + 35)
        );
      }
      else
      {
        ((TextField)boardArray3[29]).setText(
          String.valueOf(((UpperScore2)boards[2]).getUpperSectionScore()
        ));
      }
      ((TextField)boardArray2[26]).setText(String.valueOf(((UpperScore)boards[0]).getUpperSectionScore() + ((LowerScore)boards[1]).getLowerSectionScore()));
            ((TextField)boardArray4[26]).setText(String.valueOf(((UpperScore2)boards[2]).getUpperSectionScore() + ((LowerScore2)boards[3]).getLowerSectionScore()));
        TextField winner = new TextField(determineWinner());

      winner.setBounds(400, 355, 200, 50);
      victoryFrame.add(winner);
      victoryFrame.setVisible(true);
      
      
      
    }
  }

  private boolean isFinished()
  {
    for (Scoreboard b: boards)
    {
      Component[] t = b.getComponents();
      for (int i = 2; i < 20; i += 3)
      {
        if (((TextField)t[i]).getText().equals(""))
          return false;
      }
    }

    return true;
  }

  private String determineWinner()
  {
    if(((UpperScore)boards[0]).getUpperSectionScore() + 
       ((LowerScore)boards[1]).getLowerSectionScore() >  
       ((UpperScore2)boards[2]).getUpperSectionScore() + 
       ((LowerScore2)boards[3]).getLowerSectionScore())
      return "Player 1 wins!";
    else if(((UpperScore)boards[0]).getUpperSectionScore() + 
       ((LowerScore)boards[1]).getLowerSectionScore() <  
       ((UpperScore2)boards[2]).getUpperSectionScore() + 
       ((LowerScore2)boards[3]).getLowerSectionScore())
      return "Player 2 wins!";
    else
      return "Its a tie!";
        
  }

  
}