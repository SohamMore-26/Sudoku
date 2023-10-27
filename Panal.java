
// All frontend work for the game is done in this file

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Panal extends JPanel 
{

    Sudoku game;  // This is the object of the class we created 
    private JButton nbtn = new JButton("new game"); // This button defines the button for new game
    private static JTextField[][] boxes; // This boxes are for numbers
    private JPanel[][] paneles;
    private JPanel center, bPanel;
    private JButton nBtn, cBtn, eBtn, hardBtn, midBtn, easyBtn, slove;
    private int[][] temp = new int[9][9]; // The array to store temporary grid we created in Sudoku class
    private int[][] grid = new int[9][9]; // the array to store grid we created in Sudoku class


    public JTextField newtextfield() 
    {
        JTextField j = new JTextField("");
        j.setBorder(BorderFactory.createLineBorder(Color.lightGray)); // To create a thin lightgray border around the boxes in game
        j.setFont(new Font(Font.DIALOG, Font.PLAIN, 25)); // Set the font for the game
        j.setHorizontalAlignment(JTextField.CENTER); // To allign the JTextField we created in center

        /*-------------------mouse lisner----------------*/

        j.addMouseListener(new MouseAdapter() // Here we reregistered the mouse listener for the JTextField
        {

            public void mouseEntered(MouseEvent e) // This listener is used to change the color of Editable JTextField to yellow when cursor entered 
            {
                if (j.isEditable()) 
                {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.decode("#f6ea80")));
                    ((JTextField) e.getSource()).setBackground(Color.decode("#f6ea80"));
                }
            }

            public void mouseExited(MouseEvent e) // This listener is used to make it default white when cursor exited
            {
                if (j.isEditable()) 
                {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    ((JTextField) e.getSource()).setBackground(Color.white);
                }
            }
        });

        /*--------------------KeyListener----------------------------*/

        j.addKeyListener(new KeyAdapter() 
        {

            public void keyReleased(KeyEvent e) 
            {
                if (j.isEditable()) 
                {
                    ((JTextField) e.getSource()).setForeground(Color.decode("#0c4"));
                } 
                else 
                {
                    ((JTextField) e.getSource()).setForeground(Color.black);
                }
            }
        });
        return j;
    }
    
    public Panal()
    {
        
        /*------------------------main panal  -------------------------------------*/

        center = new JPanel();  //main panel
        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
        center.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(center);  //add main panel to frame 

        boxes = new JTextField[9][9]; // Boxes are the JTextfields used in game
        paneles = new JPanel[3][3]; // panels is the array of JPanel and per panel contain 9 boxes
        
        for (int i = 0; i < 3; i++)  // This loop is for add 9 panels and assign it black border
        {
            for (int j = 0; j < 3; j++) 
            {
                paneles[i][j] = new JPanel();
                paneles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                paneles[i][j].setLayout(new GridLayout(3, 3)); // grid layout is used to add 9 textfields in grid pattern
                center.add(paneles[i][j]);
            }
        }

        /*------------------------text fieldes in boxes-------------------------------------*/

        for (int n = 0; n < 9; n++) // Loop through rows of textfields (9 rows)
        {
            for (int i = 0; i < 9; i++) // Loop through columns of textfields (9 columns)
            {
                boxes[n][i] = newtextfield(); // Create a new textfield and store it in the 2D array "boxes"

                // Calculate the row group (fm) and column group (cm) for the current textfield
                int fm = (n + 1) / 3; // Determine the current row group (divide row index by 3)
        
                // Check if the current row index is not divisible by 3 (i.e., not the first row in a group)
                if ((n + 1) % 3 > 0) 
                {
                    fm++; // Increment the row group if the row is not the first in the group
                }
        
                int cm = (i + 1) / 3; // Determine the current column group (divide column index by 3)

                // Check if the current column index is not divisible by 3 (i.e., not the first column in a group)
                if ((i + 1) % 3 > 0) 
                {
                    cm++; // Increment the column group if the column is not the first in the group
                }

                // Add the current textfield (boxes[n][i]) to the corresponding panel (paneles[fm - 1][cm - 1])
                paneles[fm - 1][cm - 1].add(boxes[n][i]);
            }
        }
        /*------------------------panal created to contain all buttons -------------------------------------*/

        bPanel = new JPanel();
        bPanel.setBackground(Color.decode("#AABFFF")); // to assign blue color to the panel
        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true)); // 6 defines the width of the pixels
        bPanel.setLayout(new GridLayout(3, 2, 2, 10));


        /*------------------------new game button -------------------------------------*/
        
        nBtn = new JButton("New Game");
        nbtn.setSize(20, 50);
        nBtn.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
                restgame();
                Sudoku.newGame();

            }
        });

        /*------------------------check game button -------------------------------------*/
        cBtn = new JButton("Check Game");

        cBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                for (int i = 0; i < 9; i++) 
                {
                    for (int j = 0; j < 9; j++) 
                    {
                        if (!boxes[i][j].isEditable()) // Checks if the box is editable or not
                        {
                            continue;
                        } 
                        else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) 
                        {
                            boxes[i][j].setBackground(Color.decode("#C0DCD9")); // It makes the background of JTextbox to green of the value is correct
                        } 
                        else if (boxes[i][j].getText().isEmpty()) 
                        {
                            boxes[i][j].setBackground(Color.WHITE);
                            continue;
                        } 
                        else 
                        {
                            boxes[i][j].setBackground(Color.red);
                        }
                    }
                }
            }
        });

        /*------------------------Hard button -------------------------------------*/
        hardBtn = new JButton("Hard");

        hardBtn.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                restgame();
                Sudoku.setlevel(4);
                Sudoku.newGame();
            }
        });
        /*------------------------medium button -------------------------------------*/
        midBtn = new JButton("Medium");

        midBtn.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                restgame();
                Sudoku.setlevel(3);
                Sudoku.newGame();

            }
        });
        /*------------------------easy button -------------------------------------*/
        easyBtn = new JButton("Easy");

        easyBtn.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                restgame();
                Sudoku.setlevel(2);
                Sudoku.newGame();
            }
        });
        /*------------------------panal for new Hard button -------------------------------------*/
        slove = new JButton("Solve");

        slove.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) 
            {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            boxes[i][j].setText(String.valueOf(grid[i][j]));
                        }
                    }
            }
        });

        /*------------------------add button panal and butons to frame and panel -------------------------------------*/
        bPanel.add(hardBtn);   //add new game button to 
        bPanel.add(midBtn);
        bPanel.add(easyBtn);
        bPanel.add(nBtn);   //add new game button to 
        bPanel.add(cBtn);
        bPanel.add(slove);

        add(bPanel, "South");   //add button panel to frame 

    }

    public void setarray(int[][] grid, int[][] temp) // This method is used to obtain the grid and tempory grid created in Sudoku class
    {
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                this.temp[i][j] = temp[i][j];
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public void setTextLable() 
    {
        for (int i = 0; i < 9; i++) //This loop is used to make box green which already contains number
        {
            for (int j = 0; j < 9; j++) 
            {
                if (this.temp[i][j] != 0) 
                {
                    boxes[i][j].setText(String.valueOf(this.temp[i][j]));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.decode("#C0DCC0"));
                } 
                else 
                {
                    boxes[i][j].setText("");
                }
            }
        }
    }

    public static void restgame() 
    {
        // This method is used to make the boxes as it is

        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                boxes[i][j].setForeground(Color.black);
                boxes[i][j].setEditable(true);
                boxes[i][j].setBackground(Color.WHITE);
            }
        }
    }

}
