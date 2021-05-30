package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;

		//4. select a random cell to start
		Random r = new Random();
		int rWidth = r.nextInt(width);
		int rHeight = r.nextInt(height);

		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.allsquare[rWidth][rHeight]);

		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		int unvisitedN = 0;
		for (int i = -1; i < 1; i++) {
			for (int j = -1; j < 1; j++) {
				if (maze.allsquare[i][j].hasBeenVisited() == false) {
					unvisitedN++;
				}
			}
		}
		//C. if has unvisited neighbors,
		Random N = new Random();
		int random = N.nextInt(unvisitedN);
		//C1. select one at random.
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (i != 2 && j != 2) {
				
				}
			}
		}
		//C2. push it to the stack
		
		//C3. remove the wall between the two cells

		//C4. make the new cell the current cell and mark it as visited

		//D. if all neighbors are visited

		//D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

	}

	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}