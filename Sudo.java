import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sudo extends JFrame {

    public int puzzle[] = {1,2,3,4};

    // void shuffle(int[] array) {
    //     Random rand = new Random();
    //     for (int i = array.length - 1; i > 0; i--) {
    //         int j = rand.nextInt(i + 1);
    //         int temp = array[i];
    //         array[i] = array[j];
    //         array[j] = temp;
    //     }
    // }

    // private void fillDiagonalBoxes() {
    //     int[] values = {1, 2, 3, 4};
    //     shuffle(values);

    //     for (int i = 0; i < 4; i++) {
    //         for (int j = 0; j < 4; j++) {
    //             puzzle[i][j] = values[(i + j) % 4];
    //         }
    //     }
    // }



    Sudo() {
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4));
        add(gridPanel);

        JTextField grid[][] = new JTextField[4][4];

        // fillDiagonalBoxes();

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                /*
                 * subPanels[r*c] = new JPanel(new GridLayout(rows, columns));
                 * subPanels[r*c].setBorder(innerBorder);
                 * subPanels[r*c].setBackground(Color.WHITE);
                 * GuiPanel.add(subPanels[r*c]);
                 */

                grid[r][c] = new JTextField(puzzle[c]);
                grid[r][c].setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(grid[r][c]);

            }

        }
        // JTextField a = new JTextField("1");
        // a.setHorizontalAlignment(JTextField.CENTER);
        // JTextField b = new JTextField("2");
        // b.setHorizontalAlignment(JTextField.CENTER);
        // JTextField c = new JTextField("3");
        // c.setHorizontalAlignment(JTextField.CENTER);
        // JTextField d = new JTextField("4");
        // d.setHorizontalAlignment(JTextField.CENTER);
        // JTextField e = new JTextField("1");
        // e.setHorizontalAlignment(JTextField.CENTER);
        // JTextField f = new JTextField("2");
        // f.setHorizontalAlignment(JTextField.CENTER);
        // JTextField g = new JTextField("3");
        // g.setHorizontalAlignment(JTextField.CENTER);
        // JTextField h = new JTextField("4");
        // h.setHorizontalAlignment(JTextField.CENTER);
        // JTextField i = new JTextField("1");
        // i.setHorizontalAlignment(JTextField.CENTER);
        // JTextField j = new JTextField("2");
        // j.setHorizontalAlignment(JTextField.CENTER);
        // JTextField k = new JTextField("3");
        // k.setHorizontalAlignment(JTextField.CENTER);
        // JTextField l = new JTextField("4");
        // l.setHorizontalAlignment(JTextField.CENTER);
        // JTextField m = new JTextField("1");
        // m.setHorizontalAlignment(JTextField.CENTER);
        // JTextField n = new JTextField("2");
        // n.setHorizontalAlignment(JTextField.CENTER);
        // JTextField o = new JTextField("3");
        // o.setHorizontalAlignment(JTextField.CENTER);
        // JTextField p = new JTextField("4");
        // p.setHorizontalAlignment(JTextField.CENTER);

        // gridPanel.add(a);
        // gridPanel.add(b);
        // gridPanel.add(c);
        // gridPanel.add(d);
        // gridPanel.add(e);
        // gridPanel.add(f);
        // gridPanel.add(g);
        // gridPanel.add(h);
        // gridPanel.add(i);
        // gridPanel.add(j);
        // gridPanel.add(k);
        // gridPanel.add(l);
        // gridPanel.add(m);
        // gridPanel.add(n);
        // gridPanel.add(o);
        // gridPanel.add(p);

        JPanel buttonpanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        buttonpanel.add(solveButton);
        JButton clearButton = new JButton("New");
        buttonpanel.add(clearButton);
        JButton checkButton = new JButton("Check");
        buttonpanel.add(checkButton);

        add(buttonpanel, BorderLayout.SOUTH);

        setVisible(true);

    }

    public static void main(String[] args) {
        Sudo game = new Sudo();
    }
}