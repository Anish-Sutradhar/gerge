import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LowerScore extends Scoreboard
{
  JLabel label = new JLabel("Lower Section");
  
  Component[] boardArray;

  /**
    * Constructs a lower scoreboard on the given frame and die that will provide the socres for it
    * @param f the frame the scoreboard will be added on
    * @param die the die that will put the scores onto the scoreboard
    */
  public LowerScore(JFrame f, Die d)
  {
    super(f, d);
    
    board.setLayout(new GridLayout(9, 3));

    board.add(new TextField("3 of a kind"));
    board.add(new TextField("")); 
    board.add(new TextField("")); 

    
    board.add(new TextField("4 of a kind"));
    board.add(new TextField(""));     
    board.add(new TextField(""));
 

    board.add(new TextField("Full House"));
    board.add(new TextField(""));
    board.add(new TextField(""));        


    board.add(new TextField("Sm. Straight"));
    board.add(new TextField(""));  
    board.add(new TextField(""));  


    board.add(new TextField("Lg. Straight"));
    board.add(new TextField(""));  
    board.add(new TextField(""));        


    board.add(new TextField("YAHTZEE")); 
    board.add(new TextField(""));  
    board.add(new TextField(""));        
 

    board.add(new TextField("Chance"));
    board.add(new TextField(""));  
    board.add(new TextField(""));        


    board.add(new TextField("Lower Section Total"));
    board.add(new TextField(""));  
    board.add(new TextField(""));        
 
     
    board.add(new TextField("Grand Total"));
    board.add(new TextField(""));  
    board.add(new TextField(""));

    scoreSaver.put("3 of a kind", 0);
    scoreSaver.put("4 of a kind", 0);
    scoreSaver.put("Full House", 0);
    scoreSaver.put("Sm. Straight", 0);
    scoreSaver.put("Lg. Straight", 0);
    scoreSaver.put("YAHTZEE", 0);
    scoreSaver.put("Chance", 0);
    scoreSaver.put("Lower Section Total", 0);
    scoreSaver.put("Grand Total", 0);
  
    
    for (Component c : board.getComponents()) {
      if (c instanceof TextField) { 
        ((TextField)c).setEditable(false);
      }
    }

    makeClickable();
   
    keypanel.add(board, BorderLayout.CENTER);
    keypanel.add(label, BorderLayout.NORTH);
    
    keypanel.setBounds(0, 450, 490, 260);
    
    f.add(keypanel); 
  }

  /**
   * Calculates the sum of all dice when a three of a kind is present.
   *
   * @return the sum of all dice when a three of a kind is present, or 0 if not present
   */
  public int get3OfAKind() {
    int sum = 0;
    HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
  
    for (int number : die.diceNumber) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
      if (countMap.get(number) == 3) {
        for (int i = 0; i < die.diceNumber.length; i++) {
          sum += die.diceNumber[i];
        }
      }
    }
    return sum;
  }
  // public int get3OfAKind() {
  //   int sum = 0;

  //   Map<Integer, Integer> freq = new TreeMap<Integer, Integer>();
  //   for (int i = 0; i < die.diceA)
  // }

    
  /**
   * Calculates the sum of all dice if there is a 4-of-a-kind combination.
   *
   * @return The sum of all dice if there is a 4-of-a-kind combination, or 0 if not present.
   */
  public int get4OfAKind() 
  {
    int sum = 0;
    HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
  
    for (int number : die.diceNumber) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
      if (countMap.get(number) == 4) {
        for (int i = 0; i < die.diceNumber.length; i++) {
          sum += die.diceNumber[i];
        }
      }
    }
    return sum;
  }
  
  /**
   * Checks if there is a Yahtzee combination (all dice showing the same number).
   *
   * @return 50 if there is a Yahtzee combination, or 0 if not present.
   */
  public int getYahtzee() 
  {
    HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
  
    for (int number : die.diceNumber) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
      if (countMap.get(number) == 5) {
        return 50;
      }
    }
    return 0;
  }
  
  /**
   * Checks if there is a Full House combination (three of a kind and a pair).
   *
   * @return 25 if there is a Full House combination, or 0 if not present.
   */
  public int getFullHouse() 
  {
    HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
  
    for (int number : die.diceNumber) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
    }
  
    Iterator<Integer> countIterator = countMap.keySet().iterator();
  
    int freq1 = countMap.get(countIterator.next());
    int freq2 = countMap.get(countIterator.next());
  
    if ((freq1 == 3 && freq2 == 2) || (freq1 == 2 && freq2 == 3)) {
      return 25;
    }
  
    return 0;
  }
  
  /**
   * Checks if there is a Large Straight combination (consecutive numbers in ascending order).
   *
   * @return 40 if there is a Large Straight combination, or 0 if not present.
   */
  public int getLargeStraight() 
  {
    Arrays.sort(die.diceNumber);
  
    for (int i = 0; i < die.diceNumber.length - 1; i++) {
      if (die.diceNumber[i] != die.diceNumber[i + 1] - 1) {
        return 0;
      }
    }
  
    return 40;
  }
  
  /**
   * Checks if there is a Small Straight combination (four consecutive numbers).
   *
   * @return 30 if there is a Small Straight combination, or 0 if not present.
   */
  
  public int getSmallStraight()
  {
    Arrays.sort(die.diceNumber);

    boolean isSmallStraightAtBeginning = (die.diceNumber[0] == die.diceNumber[1] - 1) &&
          (die.diceNumber[1] == die.diceNumber[2] - 1) &&
          (die.diceNumber[2] == die.diceNumber[3] - 1);

    boolean isSmallStraightAtEnd = (die.diceNumber[1] == die.diceNumber[2] - 1) &&
          (die.diceNumber[2] == die.diceNumber[3] - 1) &&
          (die.diceNumber[3] == die.diceNumber[4] - 1);

    if(isSmallStraightAtBeginning || isSmallStraightAtEnd)
    {
      return 30;
    }
    return 0;
  }

  /**
   * Calculates the sum of all dice.
   *
   * @return The sum of all dice.
   */
  public int getChance() {
    int sum = 0;
    for (int i = 0; i < die.diceNumber.length; i++) {
      sum += die.diceNumber[i];
    }
    return sum;
  }

  /**
    * updates the scoreboard
    */
  public void updateScore()
  {
    scoreSaver.put("3 of a kind", get3OfAKind());
    scoreSaver.put("4 of a kind", get4OfAKind());
    scoreSaver.put("Full House", getFullHouse());
    scoreSaver.put("Sm. Straight", getSmallStraight());
    scoreSaver.put("Lg. Straight", getLargeStraight());
    scoreSaver.put("YAHTZEE", getYahtzee());
    scoreSaver.put("Chance", getChance());
    scoreSaver.put("Lower Section Total", 0);
    scoreSaver.put("Grand Total", 0);

    //inserts the nwely updated scores into the scoreboard
    Component[] boardArray2 = board.getComponents();
    for(int i = 1; i < boardArray2.length; i += 3)
    {
      ((TextField) boardArray2[i]).setText(String.valueOf(scoreSaver.get(((TextField) boardArray2[i - 1]).getText())));
    }
    ((TextField)boardArray2[23]).setText(String.valueOf(getLowerSectionScore()));
  }

  /**
    * returns the final lower section score
    * @return the final lower section score
    */
  public int getLowerSectionScore()
  {
    Component[] boardArray = board.getComponents();
    int sum = 0;
    for (int i = 2; i <= 20; i += 3)
    {
      if (!((TextField)boardArray[i]).getText().equals(""))
        sum += Integer.parseInt(((TextField)boardArray[i]).getText());
    }

    return sum;
  }

  /**
    * makes each textfield chickable
    */
  public void makeClickable()
  {
    boardArray = board.getComponents();

    for(int i = 1; i < 20; i += 3)
    {
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