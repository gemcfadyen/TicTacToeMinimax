# TicTacToe 

[![Build Status](https://travis-ci.org/gemcfadyen/TicTacToe.svg?branch=master)](https://travis-ci.org/gemcfadyen/TicTacToe)
[![Coverage Status](https://coveralls.io/repos/gemcfadyen/TicTacToe/badge.svg?branch=master)]  
(https://coveralls.io/r/gemcfadyen/TicTacToe?branch=master)

Tic Tac Toe is a game where players take turn in marking the cells in a 3 x 3 grid. The player who succeeds in placing three respective marks in a horizontal, vertical or diagonal row wins the game.

# The Brief
Create a game that allows a human to play TicTacToe against an unbeatable computer player.  The computer player must never lose and should win when possible.

# To Run
- clone the repository:
```
git clone git@github.com:gemcfadyen/TicTacToe.git
```

- Using the gradle wrapper invoke the program:
```
gradlew run
```
_Alternatively you can invoke the main method of the Game class in your IDE._

# To Play

- The human player always has the symbol 'O'
 
- When prompted enter 'A' if you would like the Automated Computer Player to take the first move, or enter 'H' if you would like the Human Player to take the first move. 

- Once you have made a valid choice, the empty grid will be displayed as shown below:

![alt tag](/images/1_PlayerAGoesFirst.PNG)

- When prompted to make a move, enter the number of the cell you wish you place your symbol. For example, to place your symbol in the centre cell, enter '4' at the prompt as shown below:

![alt tag](/images/2_TakingYourGo.PNG)

- If you enter an invalid option you will be re-prompted until a valid input is given.

![alt tag](/images/3_Reprompt.PNG)

- When a game is over, you have the option to play again (Y/N). 

![alt tag](/images/4_ReplayOption.PNG)

- If you opt to play again, you will be re-prompted as to which player you want to open the game.

- Good Luck!
