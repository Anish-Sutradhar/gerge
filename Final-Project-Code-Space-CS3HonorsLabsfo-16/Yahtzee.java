import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Yahtzee
{
  public Yahtzee(JFrame frame)
  {  
    frame.setSize(1000, 760);
    frame.setTitle("Yahtzee");
    
    
    //Screen for instructions
    JFrame frame2 = new JFrame();
    frame2.setSize(1000, 760);

    //PLayers
    Player player1 = new Player(true);
    Player player2 = new Player(false);

    //Buttons
    JButton rollButton = new JButton("Roll"); //Button to roll the die, also affects the game's progress
    rollButton.setBounds(463, 90, 75, 50);
    frame.add(rollButton);
    
    JButton howToPlayButton = new JButton("How To Play"); //button to go to different screen for instructions
    howToPlayButton.setBounds(800, 10, 125, 50);
    howToPlayButton.addActionListener(new InstructionPage(frame, frame2));
    frame.add(howToPlayButton);
    
    JButton backButton = new JButton("Back"); // Button to go back to the main screen from the instructions screen
    backButton.setBounds(463, 50, 75, 50);
    backButton.addActionListener(new GoBack(frame, frame2));
    frame2.add(backButton);
    
    
    //Adds the instructions to the page
    TextArea t = new TextArea();
    
    t.setText("Welcome to Yahtzee! \n\nTo play, player 1 will first roll the dice by clicking the roll button. \nThen based on the scores that appear in the score column of the score sheet for player 1, select a score from that roll by clicking on the textbox with the score. \nThe score will now appear in the final score column in the corresponding row. \nNow it's player 2's turn. \nPlayer 2 performs the same steps that player 1 just did by first pressing the roll button to roll the dice for their turn. \nBoth players continue doing this until all the rows with a score type have filled up. \nOnce that has happened, click on the roll button one more time to see who won. \nMake sure to always select a different row after each roll. \n\nBelow is some info on scoring and what each row means.\n\nUpper section combinations \nOnes: Get as many ones as possible. \nTwos: Get as many twos as possible. \nThrees: Get as many threes as possible. \nFours: Get as many fours as possible. \nFives: Get as many fives as possible. \nSixes: Get as many sixes as possible. \n\nFor the six combinations above the score for each of them is the sum of dice of the right kind. \nE.g. if you get 1,3,3,3,5 and you choose Threes you will get 3*3 = 9 points. \nThe sum of all the above combinations is calculated and if it is 63 or more, the player will get a bonus of 35 points.  \n\n\nLower section combinations \nThree of a kind: Get three dice with the same number. Points are the sum all dice (not just the three of a kind). \nFour of a kind: Get four dice with the same number. Points are the sum all dice (not just the four of a kind). \nFull house: Get three of a kind and a pair, e.g. 1,1,3,3,3 or 3,3,3,6,6. Scores 25 points. \nSmall straight: Get four sequential dice, 1,2,3,4 or 2,3,4,5 or 3,4,5,6. Scores 30 points. \nLarge straight: Get five sequential dice, 1,2,3,4,5 or 2,3,4,5,6. Scores 40 points. \nChance: You can put anything into chance. The score is simply the sum of the dice. \nYAHTZEE: Five of a kind. Scores 50 points.");
    t.setEditable(false);
    t.setBounds(150, 150, 700, 500);
    frame2.add(t);

    //Whatever component is added last tends to cover up the entire screen, so a blank textfield has been added to prevent that
    TextField blank = new TextField("");
    frame2.add(blank);
    
    Die die = new Die(frame);

    Scoreboard upperScore = new UpperScore(frame, die);
    Scoreboard lowerScore = new LowerScore(frame, die);
    Scoreboard upperScore2 = new UpperScore2(frame, die);
    Scoreboard lowerScore2 = new LowerScore2(frame, die);

    frame.add(die.dicePanel);
    
    
    rollButton.addActionListener(new RollDie(new Scoreboard[]{upperScore, lowerScore, upperScore2, lowerScore2}, player1, player2));
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame2.setVisible(false);
  }
}