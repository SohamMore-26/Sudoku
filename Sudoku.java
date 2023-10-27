
import java.util.*; // ArrayList, Collection and Random classes are used from this package
import javax.swing.JFrame;

public class Sudoku 
{

    // Variables are declared static so that it will remain commen for the every object of class

    static JFrame frame; // Object of JFrame
    static Panal p; // Object is created of Panel class
    private  static int[][] grid;
    private static int[][] temp;
    private static Random ran = new Random(); // Random class generate a random number
    private static int level = 2; // Integer for level ( Default sey to the low level )

    public static void main(String[] args) 
    {
        
        grid = new int[9][9]; // 2d array for grid
        temp = new int[9][9]; // 2d array for temporary grid
        frame = new JFrame();
        frame.setResizable(false); // so that window cannot be resizable when running
        frame.setLocation(320, 40);
        frame.setSize(650, 650);
        frame.setTitle("Sudoko");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new Panal(); 
        frame.setContentPane(p); // used to replace the frame with panel
        frame.setVisible(true);
    }

    public static ArrayList<Integer> getRandomNum() 
    {
        ArrayList<Integer> numbers = new ArrayList<Integer>(); // A dynamic integer array is created

        for (Integer i = 1; i < 10; i++) // Loop to add 1 to 9 numbers in the array 'number'
        {
            numbers.add(i);
        }
        Collections.shuffle(numbers); // this method will shuffel the numbers in the array
        return numbers;
    }

    public static void setlevel(int lev) // Level of the Game ( Easy(2) -- Medium(3) -- Hard(4) )
    {
        level = lev;
    }

    public static void newGame() 
    {
        int k = 0;

        ArrayList<Integer> randomnumber = getRandomNum(); // with the help of array list we can create a dynamic array and it will store the shuffeled numbers from 1 to 9
        
        for (int i = 0; i < 9; i++) // loop for rows
        {
            for (int j = 0; j < 9; j++) // loop for column
            {
                grid[i][j] = 0;
                if (((j + 2) % 2) == 0 && ((i + 2) % 2) == 0) // if i and j both are even it will place random number or otherwise it will place 0
                {
                    grid[i][j] = randomnumber.get(k);
                    k++;
                    if (k == 9) // Condition to reset K after reaching 9
                    {
                        k = 0;
                    }
                }
            }
        }

        search(grid);

        int rann = ran.nextInt(level); 
        int c = 0;

        for (int i = 0; i < 9; i++) // Loop for column of temporary grid
        {
            for (int j = 0; j < 9; j++) // Loop for rows of temporary grid
            {
                temp[i][j] = 0; 
                if (c < rann) 
                {
                    c++;
                    continue;
                } 
                else 
                {
                    rann = ran.nextInt(level);
                    c = 0;
                    temp[i][j] = grid[i][j];
                }
            }
        }
        p.setarray(grid, temp);
        p.setTextLable();
    }

    public static int[][] getFreeCellList(int[][] grid) 
    {

        int numberOfFreeCells = 0;
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                if (grid[i][j] == 0) 
                {
                    numberOfFreeCells++;
                }
            }
        }

        int[][] freeCellList = new int[numberOfFreeCells][2];
        int count = 0;
        // This loop will create a matrix with the content list of position of the free cell
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                if (grid[i][j] == 0) 
                {
                    freeCellList[count][0] = i;
                    freeCellList[count][1] = j;
                    count++;
                }
            }
        }

        return freeCellList;
    }

    public static boolean search(int[][] grid) // It is backtracking algorithm
    {
        int[][] freeCellList = getFreeCellList(grid);
        int k = 0;
        boolean found = false;

        while (!found) 
        {
            //get free element one by one
            int i = freeCellList[k][0];
            int j = freeCellList[k][1];
            // if element equal 0 give 1 to first test
            if (grid[i][j] == 0) 
            {
                grid[i][j] = 1;
            }
            // now check 1 if is avaible
            if (isAvaible(i, j, grid)) 
            {
                //if free is equal k ==> board solved
                if (k + 1 == freeCellList.length) 
                {
                    found = true;
                } 
                else 
                {
                    k++;
                }
            }
            //increase element  by 1 
            else if (grid[i][j] < 9) 
            {
                grid[i][j] = grid[i][j] + 1;
            } 
            //now if element value eqaule 9 backtrack to later element
            else {
                while (grid[i][j] == 9) {
                    grid[i][j] = 0;
                    if (k == 0) {
                        return false;
                    }
                    k--; //backtrack to later element
                    i = freeCellList[k][0];
                    j = freeCellList[k][1];
                }
                grid[i][j] = grid[i][j] + 1;
            }
        }

        return true;
    }

    public static boolean isAvaible(int i, int j, int[][] grid) 
    {

        // Check   row
        for (int column = 0; column < 9; column++) 
        {
            if (column != j && grid[i][column] == grid[i][j]) 
            {
                return false;
            }
        }

        // Check  column
        for (int row = 0; row < 9; row++) 
        {
            if (row != i && grid[row][j] == grid[i][j]) 
            {
                return false;
            }
        }

        // Check box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) 
        {
            //      i=5 ,j=2   || row =3  col=0   ||i=3  j=0
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) 
            {
                if (row != i && col != j && grid[row][col] == grid[i][j]) 
                {
                    return false;
                }
            }
        }

        return true; //else return true
    }
}
