import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**@author Zhenglin Huang 
  * zxh108@case.edu
  * The Gomoku class is the class that create the game
  * 
  */
public class Gomoku extends JFrame implements ActionListener{
  
  /** This field grid is the JButton pieces of the board
   * 
   */
  public JButton[][] grid;
  /** This field is the base where the grid pieces will be glued on
   * 
   */
  public JPanel board;
  /** This field indicated the current player of the game, beginning as black.
   * */
  public Color currentPlayer = Color.BLACK;
  /** This field indicated the color of the background, the chest board with no piece on
   * */
  public Color defaultColor = Color.ORANGE;
  /** This field indicates whether the game is going on
   * 
   */
  public boolean proceed = true;
  
  /** The default constructor, call Gomuku(19,19) to create a default board of 19x19
   */ 
  public Gomoku(){
    new Gomoku(19,19);
  }
  
  /** The main constructor of the class, create a chess board
    * @param row the row count of the board
    * @param column the column count of the board
    */
  
  public Gomoku(int row, int column){
    this.setVisible(true);
    board = new JPanel(new GridLayout(row,column));
    grid = new JButton[row][column];
    for (int i = 0; i < row; i++){
      for (int j = 0; j< column; j++){
        grid[i][j] = new JButton();
        grid[i][j].setBackground(defaultColor);
        board.add(grid[i][j]);
        grid[i][j].addActionListener(this);
        grid[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseEntered(java.awt.event.MouseEvent e) {
            JButton source = (JButton)e.getSource();
            if ((source.getBackground() == defaultColor)&&(proceed)){
              source.setText("\bO");
              source.setForeground(getPlayer());
            }else{
              source.setText("X");
              source.setForeground(Color.RED);
            }
          }
          public void mouseExited(java.awt.event.MouseEvent e) {
            JButton source = (JButton)e.getSource();
            source.setText(null);
            source.setBackground(source.getBackground());
          }
        });
      }
    }
    this.getContentPane().add(board, "Center");
  }
  
  /** This is the main method
    * */
  public static void main(String args[]){
    try{
      new Gomoku(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
    }
    catch(ArrayIndexOutOfBoundsException e){
      new Gomoku();
    }
    catch(NumberFormatException e){
      new Gomoku();
    }
  }
  
  /** This method checks starting(and included) the check start point, to the direction designated, and return count of consecutive pieces of the same color with the start point color
    * @param grid the grid is the array of JButtons that the method will check on
    * @param row the row count of the button where the check will start from
    * @param column the column count of the button where the check will start from
    * @param direction the direction where the check will look into
    * @return return count of consecutive pieces of the same color with the start point color
    * */
  public int numberInLine(JButton[][] grid, int row, int column, String direction){
    //Declare the direction where the 
    int searchRow;
    int searchColumn;
    
    int consecutiveCount = 1;
    //decide which direction the search will go on onto arrays
    //translate physical direction to vectors
    while (true) {
      if (direction.equals("North")){
        searchRow = -1;
        searchColumn = 0;
        break;
      }
      if (direction.equals("Northeast")){
        searchRow = -1;
        searchColumn = 1;
        break;
      }
      if (direction.equals("East")){
        searchRow = 0;
        searchColumn = 1;
        break;
      }
      if (direction.equals("Southeast")){
        searchRow = 1;
        searchColumn = 1;
        break;
      }
      if (direction.equals("South")){
        searchRow = 1;
        searchColumn = 0;
        break;
      }
      if (direction.equals("Southwest")){
        searchRow = 1;
        searchColumn = -1;
        break;
      }
      if (direction.equals("West")){
        searchRow = 0;
        searchColumn = -1;
        break;
      }
      if (direction.equals("Northwest")){
        searchRow = -1;
        searchColumn = -1;
        break;
      }
    }
    //end of translation
    
    //Determine the color of the "just played" piece
    Color playedPiece = grid[row][column].getBackground();
    try{
      //According to the "vector", next button on the very direction is searched
      row = row + searchRow;
      column = column + searchColumn;
      //precondition: the grid is not null
      while ((grid[row][column].getBackground()).equals(playedPiece)){
        consecutiveCount++;
        row = row + searchRow;
        column = column + searchColumn;
      }
    }
    //post condition: the grid[row][column] has different color then the currently played piece, or it doesn't exist
    catch(ArrayIndexOutOfBoundsException e){
      //if the button does not exist, it will be treated like it's not the same color
    }
    return consecutiveCount;
    
  }
  /** This method checks starting(and included) the check start point, to the direction designated, and return whether the end of consecutive pieces of the same color with the start point color could be placed another piece of such color
    * @param grid the grid is the array of JButtons that the method will check on
    * @param row the row count of the button where the check will start from
    * @param column the column count of the button where the check will start from
    * @param direction the direction where the check will look into
    * @return whether the end of consecutive pieces of the same color with the start point color could be placed another piece of such color
    * */
  public boolean isOpen(JButton[][] grid, int row, int column, String direction){
    int searchRow;
    int searchColumn;
    //decide which direction the search will go on onto arrays
    //translate physical direction to vectors
    while (true) {
      if (direction.equals("North")){
        searchRow = -1;
        searchColumn = 0;
        break;
      }
      if (direction.equals("Northeast")){
        searchRow = -1;
        searchColumn = 1;
        break;
      }
      if (direction.equals("East")){
        searchRow = 0;
        searchColumn = 1;
        break;
      }
      if (direction.equals("Southeast")){
        searchRow = 1;
        searchColumn = 1;
        break;
      }
      if (direction.equals("South")){
        searchRow = 1;
        searchColumn = 0;
        break;
      }
      if (direction.equals("Southwest")){
        searchRow = 1;
        searchColumn = -1;
        break;
      }
      if (direction.equals("West")){
        searchRow = 0;
        searchColumn = -1;
        break;
      }
      if (direction.equals("Northwest")){
        searchRow = -1;
        searchColumn = -1;
        break;
      }
    }
    //end of translation
    try{
      Color playedPiece = grid[row][column].getBackground();
      row = row + searchRow;
      column = column + searchColumn;
      //precondition: the grid is not null
      while ((grid[row][column].getBackground()).equals(playedPiece)){
        row = row + searchRow;
        column = column + searchColumn;
      }
      //post condition: the current grid[row][column] is not the same color as the currently played piece, or doesn't exist.
      
    }
    catch(ArrayIndexOutOfBoundsException e){
      return false;
    }
    if ((grid[row][column].getBackground()).equals(defaultColor))
      return true;
    
    return false;
  }
  
  
  /** This method indicates what will happen after the button click
    * 
    */
  public void actionPerformed(ActionEvent e){
    JButton source = (JButton)e.getSource();
    int row = 0;
    int column =0;
    int j = 0;
    int i = 0;
    boolean valid = true;
    //source row-column search
    
    for (i = 0; (i < grid.length); i++){
      for ( j = 0; (j< grid[i].length); j++){
        if (grid[i][j] == source){
          row = i;
          column = j;
          break;
        }          
      }
    }
    
    //let a player move
    if (((source.getBackground()).equals(defaultColor)) && (this.proceed)){
      source.setBackground(this.getPlayer());
      //determine if it;s victory
      int countInLine;
      int fourCount = 0;
      int threeCount = 0;
      //Check North and South
      countInLine = numberInLine(this.grid,row,column, "North") + numberInLine(this.grid,row,column, "South") -1;
      if (countInLine == 5) {
        valid = false;
        //stop the game
        this.proceed = false;
      }
      if (countInLine == 4) fourCount++;
      if ((countInLine == 3) && (isOpen(this.grid,row,column, "North") && isOpen(this.grid,row,column, "South")))threeCount++;
      //Check West and East
      countInLine = numberInLine(this.grid,row,column, "West") + numberInLine(this.grid,row,column, "East") -1;
      if (countInLine == 5) {
        valid = false;
        //stop the game
        this.proceed = false;
      }
      if (countInLine == 4) fourCount++;
      if ((countInLine == 3) && (isOpen(this.grid,row,column, "West") && isOpen(this.grid,row,column, "East")))
        threeCount++;
      //Check Northwest and Southeast
      countInLine = numberInLine(this.grid,row,column, "Northwest") + numberInLine(this.grid,row,column, "Southeast") -1;
      if (countInLine == 5) {
        valid = false;
        //stop the game
        this.proceed = false;
      }
      if (countInLine == 4) fourCount++;
      if ((countInLine == 3) && (isOpen(this.grid,row,column, "Northwest") && isOpen(this.grid,row,column, "Southeast")))
        threeCount++;
      //Check Northeast and Southwest
      countInLine = numberInLine(this.grid,row,column, "Northeast") + numberInLine(this.grid,row,column, "Southwest") -1;
      if (countInLine == 5) {
        this.proceed = false;
      }if (countInLine == 5) {
        valid = false;
        //stop the game
        this.proceed = false;
      }
      if (countInLine == 4) fourCount++;
      if ((countInLine == 3) && (isOpen(this.grid,row,column, "Northeast") && isOpen(this.grid,row,column, "Southwest")))
        threeCount++;
      //Decide whether the piece is kept
      if (!proceed){
        if ((getPlayer()).equals(Color.WHITE)) 
          JOptionPane.showMessageDialog(null, "The White has won", "Game over", JOptionPane.INFORMATION_MESSAGE);
        else
          JOptionPane.showMessageDialog(null, "The Black has won","Game over", JOptionPane.INFORMATION_MESSAGE);
      } else{
        //if the game still goes on, check if this piece can satay
        if (fourCount > 1){
          //Withdraw piece
          source.setBackground(defaultColor);
          valid = false;
          //Warn user
          JOptionPane.showMessageDialog(null, "4-4 Exception", "4-4 Exception", JOptionPane.ERROR_MESSAGE);
        }
        if (threeCount > 1){
          //Withdraw piece
          source.setBackground(defaultColor);
          valid = false;
          //Warn user
          JOptionPane.showMessageDialog(null, "3-3 Exception", "3-3 Exception", JOptionPane.ERROR_MESSAGE);
        }
      }
      
    }else{
      if (proceed){
        JOptionPane.showMessageDialog(null, "Cannot move", "The location has another piece already", JOptionPane.ERROR_MESSAGE);
        valid = false;
      }else{
        if ((getPlayer()).equals(Color.WHITE)) 
          JOptionPane.showMessageDialog(null, "The game will restart", "Game over", JOptionPane.INFORMATION_MESSAGE);
        else
          JOptionPane.showMessageDialog(null, "The game will restart","Game over", JOptionPane.INFORMATION_MESSAGE);
        valid = false;
        //reset game
        for (int a = 0; a < grid.length; a++){
          for (int b = 0; b< grid[a].length; b++){
            grid[a][b].setBackground(defaultColor);
            this.proceed = true;
          }
        }
      }
    }
    //if the move is confirmed valid, which is free of any rule violation, the move will be pass to the next player
    if (valid) nextPlayer();
  }
  
  
  /** The move is given to the next player
    * 
    */
  public void nextPlayer(){
    if ((getPlayer()).equals(Color.WHITE)) 
      currentPlayer = (Color.BLACK); 
    else
      currentPlayer = (Color.WHITE);
  }
  /** The method returns the color of the current player
    * 
    */
  public Color getPlayer(){
    return currentPlayer;
  }
}