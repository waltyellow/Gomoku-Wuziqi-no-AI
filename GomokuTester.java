import org.junit.*;
import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
/**
 * by walter huang zxh108
 * Test class Gomoku
 */
public class GomokuTester {
  public Color defaultColor = Color.ORANGE;
  
  /**
 * Test method isopen
 */
  @Test
  public void isOpenTester(){
    
    Gomoku test = new Gomoku(8,8);
    JButton[][] testgrid = new JButton[8][8];
    test.setTitle("Gomoku is open test");
    boolean[] expected = {false,true,true,false,false,false,true,false};
    int[][] testGridInfo =
    { {0,2,1,0,0,0,0,0},
      {0,0,1,1,1,1,0,0},
      {0,1,1,1,0,0,0,0},
      {1,0,1,0,2,0,0,0},
      {0,0,1,0,0,0,0,0},
      {0,0,1,0,0,0,0,0},
      {0,0,1,0,0,0,0,0},
      {0,0,1,0,0,0,0,0}
    }
    ;
    for (int i = 0; i < 8; i++){
      for (int j = 0; j< 8; j++){
        //Forward a copy of the test to the actual grid
        if (testGridInfo[i][j] == 1) test.grid[i][j].setBackground(Color.BLACK);
        if (testGridInfo[i][j] == 2) test.grid[i][j].setBackground(Color.WHITE);
        
        testgrid[i][j] = new JButton();
        testgrid[i][j].setBackground(defaultColor);
        if (testGridInfo[i][j] == 1) testgrid[i][j].setBackground(Color.BLACK);
        if (testGridInfo[i][j] == 2) testgrid[i][j].setBackground(Color.WHITE);
        
      }
    }
    
    // Visualization of the test
    test.grid[1][2].setText("Sample");
    test.grid[1][2].setForeground(Color.YELLOW);
    
    
    String[] directions = {"North","Northeast","East","Southeast","South","Southwest","West","Northwest"};
    int i=0;
    while (i < 8){
      assertEquals("The open status in line on "+directions[i]+" did not match",expected[i], test.isOpen(testgrid,1,2,directions[i]));
      i++;
    }
    test.proceed = false;
  }
  @Test
  /**
 * Test method number in line
 */
  public void numberInLineTester(){
    Gomoku test = new Gomoku(8,8);
    JButton[][] testgrid = new JButton[8][8];
    test.setTitle("Gomoku number in line test");
    int[] expected = {2,1,4,2,7,3,1,1};
    int[][] testGridInfo =
    { {0,2,1,0,0,0,0,0},
      {0,0,1,1,1,1,0,0},
      {0,1,1,1,0,0,0,0},
      {1,0,1,0,2,0,0,0},
      {0,0,1,0,0,0,0,0},
      {0,0,1,0,0,0,0,0},
      {0,0,1,0,0,0,0,0},
      {0,0,1,0,0,0,0,0}
    }
    ;
    for (int i = 0; i < 8; i++){
      for (int j = 0; j< 8; j++){
        //Forward a copy of the test to the actual grid
        if (testGridInfo[i][j] == 1) test.grid[i][j].setBackground(Color.BLACK);
        if (testGridInfo[i][j] == 2) test.grid[i][j].setBackground(Color.WHITE);
        
        testgrid[i][j] = new JButton();
        testgrid[i][j].setBackground(defaultColor);
        if (testGridInfo[i][j] == 1) testgrid[i][j].setBackground(Color.BLACK);
        if (testGridInfo[i][j] == 2) testgrid[i][j].setBackground(Color.WHITE);
        
      }
    }
    
    // Visualization of the test
    test.grid[1][2].setText("Sample");
    test.grid[1][2].setForeground(Color.YELLOW);
    
    String[] directions = {"North","Northeast","East","Southeast","South","Southwest","West","Northwest"};
    int i=0;
    //check 8 directions
    while (i < 8){
      
      assertEquals("The number in line on "+directions[i]+" did not match",expected[i], test.numberInLine(testgrid,1,2,directions[i]));
      i++;
      
    }
    //lock the board
    test.proceed = false;
  }
  
}