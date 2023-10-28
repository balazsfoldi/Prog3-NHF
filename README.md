# **Tic-Tac-Toe Specification**

The task is to create a Tic-Tac-Toe game with a graphical interface. This is my final project for my Programming 3 course.

- ## Main Menu
  When the game is started, we encounter a main menu. In the main menu, there will be three options available: 
  - "Start Game",
  - "Scoreboard"and
  - "Exit" buttons.
    
  The latter exits the game, while "Scoreboard" loads the scores of all previous players from a file. If a player wins, they receive 2 points, in the case of a tie, both players receive 1 point each, and in case   of a defeat, the losing side receives 0 points. Points can only be earned in "Player vs. Player" mode.

- ## Game Modes
  After selecting the "Start Game" option, another menu appears where you can choose who to play against:
  - "Player vs. Player" or
  - "Player vs. AI".
    
  With the first option, you can play against another player on the same screen. If you choose this option, two text boxes will appear where players can enter their names, which will be displayed in the  Scoreboard.
  If you choose "Player vs. AI", another menu appears where you can select the AI difficulty:
  - "Difficulty 1" and
  - "Difficulty 2".
    
  Before the game actually begins, there is always an interface where you can set the board size. By default, it is a 3x3 board, but you have the option to play on a 5x5 or 7x7 board as well.

- ## Game Rules
  The rules of the game are simple. Players take turns placing their O or X-shaped markers. To win on a 3x3 board, you need to place 3 identical markers in a row or diagonally, while on the other two board sizes, you need 4 in a row. Players cannot place their markers in a square where the other player's marker is already present. If they attempt to do so, the program will simply not allow it, and the user won't see any error messages. The game ends when someone manages to arrange their markers in the required order or when there are no empty cells left, resulting in a tie.

- ## Technical Information
  The program will use SwingGUI for graphical rendering. The Scoreboard will always display the current standings sorted by points, and I will achieve this sorting using classes from java.util. The program will save the game state when exiting and store the scoreboard in a file. It will attempt to load the game state every time it starts, and the scoreboard will be loaded only if the user wants to view it. I will implement testing using JUnit.

