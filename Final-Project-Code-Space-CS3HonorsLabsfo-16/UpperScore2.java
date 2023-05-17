import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UpperScore2 extends Scoreboard
{
  JLabel playerLabel = new JLabel("Player 2");

  /**
    * Constructs the upper scoreboard on the given frame and die that will provide the socres for it
    * @param f the frame the scoreboard will be added on
    * @param die the die that will put the scores onto the scoreboard
    */
  public UpperScore2(JFrame f, Die d)
  {
    super(f, d);
    
    board.setLayout(new GridLayout(10, 3));

    board.add(new TextField("UPPER SECTION"));
    board.add(new TextField("Score")); 
    board.add(new TextField("Final Score"));


    board.add(new TextField("Aces"));   
    board.add(new TextField(""));
    board.add(new TextField("")); 
   

    board.add(new TextField("Twos"));   
    board.add(new TextField(""));
    board.add(new TextField("")); 
    

    board.add(new TextField("Threes"));
    board.add(new TextField(""));
    board.add(new TextField("")); 
    

    board.add(new TextField("Fours"));
    board.add(new TextField("")); 
    board.add(new TextField("")); 
   

    board.add(new TextField("Fives")); 
    board.add(new TextField("")); 
    board.add(new TextField("")); 
    

    board.add(new TextField("Sixes"));
    board.add(new TextField(""));
    board.add(new TextField("")); 
   

    board.add(new TextField("TOTAL SCORE"));
    board.add(new TextField(""));
    board.add(new TextField("")); 

    
    board.add(new TextField("BONUS"));       
    board.add(new TextField(""));  
    board.add(new TextField("")); 

    
    board.add(new TextField("TOTAL Of Upper Section"));     
    board.add(new TextField(""));
    board.add(new TextField("")); 

    scoreSaver.put("Aces", 0);
    scoreSaver.put("Twos", 0);
    scoreSaver.put("Threes", 0);
    scoreSaver.put("Fours", 0);
    scoreSaver.put("Fives", 0);
    scoreSaver.put("Sixes", 0);
    scoreSaver.put("TOTAL SCORE", 0);
    scoreSaver.put("BONUS", 0);
    scoreSaver.put("TOTAL Of Upper Section", 0);

    //makes each textfield uneditable
    for (Component c : board.getComponents()) {
      if (c instanceof TextField) { 
        ((TextField)c).setEditable(false);
      }
    }

    makeClickable();
    
    keypanel.add(board, BorderLayout.CENTER);
    keypanel.add(playerLabel, BorderLayout.NORTH);
    
    keypanel.setBounds(510, 150, 490, 300);



    f.add(keypanel); 
  }

  
  /**
   * Calculates the sum of all dice with a value of 1.
   *
   * @return the sum of all dice with a value of 1
   */
  public int getAces() {
      int sum = 0;
      for (int i = 0; i < die.diceNumber.length; i++) {
          if (die.diceNumber[i] == 1)
              sum++;
      }
      return sum;
  }

  /**
   * Calculates the sum of all dice with a value of 2.
   *
   * @return the sum of all dice with a value of 2
   */
  public int getTwos() {
      int sum = 0;
      for (int i = 0; i < die.diceNumber.length; i++) {
          if (die.diceNumber[i] == 2)
              sum += 2;
      }
      return sum;
  }

  /**
   * Calculates the sum of all dice with a value of 3.
   *
   * @return the sum of all dice with a value of 3
   */
  public int getThrees() {
      int sum = 0;
      for (int i = 0; i < die.diceNumber.length; i++) {
          if (die.diceNumber[i] == 3)
              sum += 3;
      }
      return sum;
  }

  /**
   * Calculates the sum of all dice with a value of 4.
   *
   * @return the sum of all dice with a value of 4
   */
  public int getFours() {
      int sum = 0;
      for (int i = 0; i < die.diceNumber.length; i++) {
          if (die.diceNumber[i] == 4)
              sum += 4;
      }
      return sum;
  }

  /**
   * Calculates the sum of all dice with a value of 5.
   *
   * @return the sum of all dice with a value of 5
   */
  public int getFives() {
      int sum = 0;
      for (int i = 0; i < die.diceNumber.length; i++) {
          if (die.diceNumber[i] == 5)
              sum += 5;
      }
      return sum;
  }

  /**
   * Calculates the sum of all dice with a value of 6.
   *
   * @return the sum of all dice with a value of 6
   */
  public int getSixes() {
      int sum = 0;
      for (int i = 0; i < die.diceNumber.length; i++) {
          if (die.diceNumber[i] == 6)
              sum += 6;
      }
      return sum;
  }

  /**
    * updates the scoreboard
    */
  public void updateScore()
  {
    scoreSaver.put("Aces", getAces());
    scoreSaver.put("Twos", getTwos());
    scoreSaver.put("Threes", getThrees());
    scoreSaver.put("Fours", getFours());
    scoreSaver.put("Fives", getFives());
    scoreSaver.put("Sixes", getSixes());
    
    //inserts the nwely updated scores into the scoreboard
    Component[] boardArray = board.getComponents();
    for(int i = 4; i < boardArray.length; i += 3)
    {
      ((TextField) boardArray[i]).setText(String.valueOf(scoreSaver.get(((TextField) boardArray[i - 1]).getText())));
    }
    ((TextField)boardArray[29]).setText(String.valueOf(getUpperSectionScore()));
  }

   /**
    * returns the final upper section score
    * @return the final upper section score
    */
  public int getUpperSectionScore()
  {
    Component[] boardArray = board.getComponents();
    int sum = 0;
    for (int i = 5; i <= 20; i += 3)
    {
      if (!((TextField)boardArray[i]).getText().equals(""))
        sum += Integer.parseInt(((TextField)boardArray[i]).getText());
    }

    return sum;
  }

  /**
    * makes each textfield clickable
    */
  public void makeClickable()
  {
    Component[] boardArray = board.getComponents();

    for (int i = 4; i < 20; i += 3) {
      ((TextField) boardArray[i]).setText(String.valueOf(scoreSaver.get(((TextField) boardArray[i - 1]).getText())));

      final int index = i; // Create a final variable to capture the current index
      boardArray[i].addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            ((TextField) boardArray[index]).setEnabled(false); // Disable the text field
            String s = ((TextField)boardArray[index]).getText();
          ((TextField)boardArray[index+1]).setText(s);
        }
      });
    }
  }
}