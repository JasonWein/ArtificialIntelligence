
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nQueens.aStar2;

/* Course: CS 4242
 * Student Name: Jason Wein
 * Student ID: 000563745
 * Assignment #: 2
 * Due Date: June 28, 2019
 * Signature: Jason Wein
 * Score:
 */
public class nQueens {
	static int result[] = new int[100];

	public static void main(String[] args) {
		System.out.println("Enter the number of dimensions of the board (Example: 4 for 4x4) (Input should be >= 4)");
		// Takes a value as an input for the dimensions of the board {nxn}.
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		if(n < 4) {
			System.out.println("Please enter a number greater than or equal to 4.");
			System.exit(1);
		}
		// Calls the Astar algorithm.
		aStar2 astar = new aStar2(n);
		// Gets the result for the algorithm.
		result = astar.getResult();
		// Print the result in the x-axis
		for (int i = 1; i <= n; i++) {
			System.out.println(result[i]);
		}
		JFrame frame = new JFrame("nQueens problem");
		
		// Creates a JPanel.
		JPanel jpanel = new JPanel() {
			// Creates the GUI for the program.
			public void paint(Graphics graphics) {
				super.paint(graphics);
				// Draws the x-axis
				for (int i = 0; i < n + 1; i++) {
					graphics.drawLine(30 + 50 * i, 30, 30 + 50 * i, 30 + 50 * n); // Fix for variable n
				}
				// Draws the y-axis
				for (int i = 0; i < n + 1; i++) {
					graphics.drawLine(30, 30 + 50 * i, 30 + 50 * n, 30 + 50 * i); // Fix for variable n
				}
				// Draws the queens onto the GUI board and makes them red.
				graphics.setColor(Color.RED);
				for (int i = 0; i < n; i++) {
					graphics.drawOval(30 + (50 * (result[i+1] - 1)), 30 + 50 * i, 50, 50);
				}
			}
		};
		// Adds the jpanel to the frame.
		frame.add(jpanel);
		frame.setContentPane(jpanel);
		// Sets the size of the program to 600 x 600
		frame.setSize(800, 800);
		frame.setLayout(null);

		frame.setVisible(true);
	}
}
