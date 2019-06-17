import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/* Course: CS 4242
 * Student Name: Jason Wein
 * Student ID: 000563745
 * Assignment #: 1
 * Due Date: June 14, 2019
 * Signature: Jason Wein
 * Score:
 */
public class VacuumCleaner {

	public static void main(String[] args) { 
		// Creates a vacuum agent.
		cleaner cleaner1 = new cleaner();
	    Board gb = new Board();
	    JFrame frame = new JFrame("Vacuum cleaner agent");
	    // Sets random locations for the dirt in the grid.
	    for(int i = 0; i <  cleaner1.dirt.length; i++) {
	        cleaner1.dirt[i] = (int) (Math.random() * 100);
	        //System.out.print(cleaner1.dirt[i] + "  ");
	    }
	    // Creates a JPanel.
	    JPanel jpanel = new JPanel() {
	    // Creates the GUI for the program.
	    public void paint(Graphics graphics) {
	    	super.paint(graphics);
	    	// Draws the x-axis
	    	for(int i=0; i < 11; i++) {
	    		graphics.drawLine(30 + 30 * i, 30, 30 + 30 * i, 330);
	    	}
	    	// Draws the y-axis
	    	for(int i = 0; i < 11; i++) {
	    		graphics.drawLine(30,30  + 30 *i, 330, 30 + 30 * i);
	    	}
	    	// Sets the colour of dirty squares to gray.
	    	graphics.setColor(Color.GRAY);
	    	for(int i = 0; i < 12; i++) {
	    		if(cleaner1.dirt[i] != -1) {
	    			// Finds the location(x-axis, y-axis) for the dirty squares.
	    			int newX = cleaner1.dirt[i] / 10;
	    			int newY = cleaner1.dirt[i] % 10;
	    			// Fills these dirty squares in the grid.
	    			graphics.fillRect((newX* 30)+30, 30+(newY * 30), 30, 30);
	    		}
	    	}
	    	// Creates the vacuum agent and sets its colour to blue.
	    	graphics.setColor(Color.BLUE);
	    	graphics.fillOval(30 + cleaner1.xLocation * 30, 30 + cleaner1.yLocation * 30 , 30, 30);
	    }
	    };
	    // Adds the jpanel to the frame.
	    frame.add(jpanel);
	    frame.setContentPane(jpanel);
	    // Sets the size of the program to 600 x 600
	    frame.setSize(600,600);
	    frame.setLayout(null);
	    // Creates a start button for the program and sets it as a rectangle shape.
	    JButton startButton = new JButton("Start");
	    startButton.setBounds(new Rectangle(400,500,100,50));
	    // Adds the button to the frame.
	    frame.add(startButton);
	    // When the button is pressed, the agent starts to move and the board is refreshed.
	    // The program runs 1000 times and the agent is called each time and the board is refreshed 
	    // after each move.
	    startButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		for(int i = 0; i<1000;i++) {
	    			cleaner1.run();
	    			jpanel.paintImmediately(30, 30, 330, 330);
	    			try {
	    				// Time between moves
	    				Thread.sleep(50);
	    			} catch(InterruptedException ec) {
	    				ec.printStackTrace();
	    			}
	    		}
	    	}
	    });
	    frame.setVisible(true);
	}
}
// Cleaner class. This class acts as the vacuum agent with a set location in the x axis
// and a set location in the y axis.
class cleaner{
	public int xLocation;	// Location in the x axis.
	public int yLocation;	// Location in the y axis.
				
	//public int dirt[] = {1,3,4,9, 24, 51, 18, 12, 36, 14, 35, 69};
	// Creates an array for the dirt values. The amount of dirt in the program
	// can support up to 100 tiles of dirt (a full grid of dirt).
	int dirt[] = new int[99];
	// Score variable
	int score = 0;
	// Cleaner constructor. Sets the location.
	// The commented location is to set it at the point (1,1) for testing
	// and the uncommented location sets it to anywhere between (0,0) and (10,10).
	public cleaner() {
		//this.xLocation = 1;
		this.xLocation = (int) (Math.random() * 10);
		//this.yLocation = 1;
		this.yLocation = (int) (Math.random() * 10);
	}
	// Run function for the vacuum agent.
	public void run() {
		int numberOfDirt = 0;
		// Sets the amount of dirt in the grid to 12 (can be changed to
		// anything between 0 and 100).
		for( int i=0; i< 12; i++) {
			if(dirt[i] != -1) numberOfDirt++;
			// Finds the x and y coordinates for the dirt.
			int newX = dirt[i] / 10;
			int newY = dirt[i] % 10;
			if(newX ==  xLocation && newY == yLocation) {
				dirt[i] = -1;
			}
		}
		// Sets the score to be the number of white (clean) squares that 
		// incremements each time the agent moves.
		score += 100 - numberOfDirt;
		System.out.println("The score of the agent is: " + score);
		
		int number = (int) (Math.random() * 100) % 4;
		// Each of these if statements test to see if the agent is at one of the edges of the grid. If it is, it is to turn around.
		// This ensures that the agent does not travel outside of the grid.
		// Checks to see if the vacuum agent is at the x=10 boundary.
		if(number == 0) {
			xLocation = xLocation-1;
			if(atEdge(xLocation, yLocation)) {
				xLocation++;
			}
		}
		// Checks to see if the vacuum agent is at the x=0 boundary.
		if(number == 1) {
			xLocation = xLocation+1;
			if(atEdge(xLocation, yLocation)) {
				xLocation--;	
			}
		}
		// Checks to see if the vacuum agent is at the y=10 boundary.
		if(number == 2) {
			yLocation = yLocation-1;
			if(atEdge(xLocation, yLocation)) {
				yLocation++;		
			}
		}
		// Checks to see if the vacuum agent is at the y=0 boundary.
		if(number == 3) {
			yLocation = yLocation+1;
			if(atEdge(xLocation, yLocation)) {
				yLocation--;
			}
		}	
	}
	// Boolean method Used to see if the agent is at the edges of the grid.
	private boolean atEdge(int x, int y) {
		if(x < 0 || x >9 || y < 0 || y > 9) {
			return true;
		}
		return false;
	}
}
