package nQueens;

/* Course: CS 4242
 * Student Name: Jason Wein
 * Student ID: 000563745
 * Assignment #: 2
 * Due Date: June 28, 2019
 * Signature: Jason Wein
 * Score:
 */
// Astar class that runs the Astar algorithm on the nQueens problem.
public class aStar2 {
	int size = 0;
	int openList[][] = new int[10000][100];
	int closeList[] = new int[10000];
	int h[] = new int[10000];
	int cnt = 0;
	int result[] = new int[100];
	// Astar constructor that takes the size of the board as an parameter.
	public aStar2(int size) {
		this.size = size;
	}

	private void run() {
		int currentState[] = new int[100];
		// Adds x=0 to the open list
		for (int i = 1; i <= size; i++) {
			int temp[] = new int[100];
			temp[1] = i;
			openList[cnt] = temp;
			h[cnt++] = he(temp);
		}
		
		int temp[] = new int[100];
		int flag = 0;	// Checks to see if the answer has been found.
		// Runs while the answer is still not found.
		while (flag == 0) {
			int maxH = 0;
			int maxI = 0;
			for (int i = 0; i < cnt; i++) {
				// Finds the maximum h value in the open list.
				// closeList checks to see if index i is in the closedList.
				if (h[i] > maxH && closeList[i] == 0) {
					maxH = h[i];
					maxI = i;
				}
			}
			// Copies the max state from the open list to the current state.
			for (int i = 1; i <= size; i++) {
				currentState[i] = openList[maxI][i];
			}
			
			closeList[maxI] = 1;	// Changes the index to 1 to note that it has been used.
			int nextState[] = new int[100];
			// Copies currentState to nextState
			for (int i = 1; i <= size; i++) {
				nextState[i] = currentState[i];
			}
			
			int currentLayer = 0;	// Represents the number of queens set on the board.
			// Moves through the y-values of the grid starting at 0 to add queens to the board.
			for (int i = 1; i <= size; i++) {
				if (currentState[i] == 0)	// If the current layer has no queen then break from the for loop.
					break;
				currentLayer++;
			}
			// Checks each x-axis position.
			for (int i = 1; i <= size; i++) {
				nextState[currentLayer + 1] = i;	// Moves from the y to y+1.
				// If there is not a conflict then add this to the open list.
				if (isConflict(nextState, currentLayer + 1) == false) {
					// Checks to see if the solution already has been found.
					if (currentLayer == size - 1) {
						flag = 1;
						result = nextState;
						break;
					}
					// Puts this state into the open list
					for (int j = 1; j <= size; j++) {
						openList[cnt][j] = nextState[j];
					}
					// Calculate the heuristic value of this state.
					h[cnt++] = he(nextState);
					// Prints the process.
					for (int j = 0; j < cnt; j++) {
						for (int e = 1; e <= size; e++) {
							System.out.print(openList[j][e] + " ");
						}
						System.out.print("||");
					}
					System.out.println();
					for (int j = 0; j < cnt; j++) {
						System.out.print(closeList[j] + "         ");
					}
					System.out.println();
				}
			}
		}
	}

	// Checks if you can put a queen at this position.
	private boolean isConflict(int state[], int l) {

		int x = l;
		int y = state[l];
		// Checks the upper left direction for a conflict.
		while (x > 0 && y > 0) {
			x--;
			y--;
			// Returns that there is a conflict if true.
			if (x > 0 && y > 0 && state[x] == y)
				return true;
		}
		x = l;
		y = state[l];
		// Checks the upper right direction for a conflict.
		while (x > 0 && y <= size) {
			x--;
			y++;
			// Returns that there is a conflict if true.
			if (x > 0 && y <= size && state[x] == y)
				return true;
		}
		x = l;
		y = state[l];
		// Checks above for a conflict.
		while (x > 0) {
			x--;
			// Returns that there is a conflict if true.
			if (x > 0 && state[x] == y)
				return true;
		}
		// Return false if no conflict.
		return false;
	}
	
	// Heuristic for the algorithm.
	private int he(int state[]) {
		int res = 0;	// Result
		int map[][] = new int[100][100];	// 2x2 matrix
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// If the first line has a queen.
				if (state[i + 1] != 0) {
					// Set the row to 1 to show that the row is not available anymore.
					map[i][j] = 1;
					// Sets the column to 1 to show that the column is not available anymore.
					map[j][state[i + 1] - 1] = 1;
					// Checks queen's diagonal.
					if (j + 1 == state[i + 1]) {
						int x = i;
						int y = j;
						// Checks the upper left diagonal
						while (x >= 0 && y >= 0) {
							map[x][y] = 1;
							x--;
							y--;
						}
						x = i;
						y = j;
						// Checks lower left diagonal
						while (x >= 0 && y < size) {
							map[x][y] = 1;
							x--;
							y++;
						}
						x = i;
						y = j;
						// Checks the upper right diagonal
						while (x < size && y >= 0) {
							map[x][y] = 1;
							x++;
							y--;
						}
						x = i;
						y = j;
						// Checks the lower right diagonal
						while (x < size && y < size) {
							map[x][y] = 1;
							x++;
							y++;
						}
					}
				}
			}
		}
		// Checks to find available positions on the grid.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 0)
					res++;
			}
		}
		// Adds g to h to find f. {f=g+h}
		for (int i = 1; i<= size; i++) {
			if(state[i] == 0) break;
			res++;
		}
		// Returns the result.
		return res;
	}
	// Runs the algorithm and returns the result.
	public int[] getResult() {
		run();
		return result;
	}
}