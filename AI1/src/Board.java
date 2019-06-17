
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class Board {

private final JPanel board = new JPanel(new BorderLayout(3, 3));
private JButton[][] c1squares = new JButton[10][10];
private JPanel c1Board;
private final JLabel messagec1 = new JLabel("Vacuum cleaner agent");
JToolBar tool = new JToolBar();
Insets Margin = new Insets(0,0,0,0);
int squares = 10;
int space = 100;	
ImageIcon icon = new ImageIcon(new BufferedImage(space, space, BufferedImage.TYPE_INT_ARGB));

Board() {
    initializeGui();
}

public final void initializeGui() {

    board.setBorder(new EmptyBorder(5, 5, 5, 5));
    tool.setFloatable(false);
    board.add(tool, BorderLayout.PAGE_START);
    tool.add(messagec1);
    c1Board = new JPanel(new GridLayout(0, 10));
    c1Board.setBorder(new LineBorder(Color.BLACK));
    board.add(c1Board);

    for (int i = 1; i < c1squares.length; i++) {
        for (int j = 0; j < c1squares[i].length; j++) {
            JButton b = new JButton();
            b.setMargin(Margin);              
            b.setIcon(icon);
            //if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
            //    b.setBackground(Color.WHITE);
            //} else {
            //    b.setBackground(Color.GRAY);
            //}
            /*
            if((j == 1 && i == 2))
            	b.setBackground(Color.BLUE);
            else if ((j == 3 && i == 2) || j == 2 && i == 1)
            	b.setBackground(Color.WHITE);
            else
            	b.setBackground((Color.GRAY));
            	*/
            c1squares[j][i] = b;
        }
    } 
    for (int i = 1; i < squares; i++) {
        for (int j = 0; j < squares; j++) {     
                    c1Board.add(c1squares[j][i]);      
        }
    } 
}

public final JComponent getGui() {
    return board;
}

}
