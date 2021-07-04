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
		maze = new Maze(width, height);
		//4. select a random cell to start
		Random r = new Random();
		int rWidth = r.nextInt(width);
		int rHeight = r.nextInt(height);

		//5. call selectNextPath method with the randomly selected cell
		//		System.out.println(rWidth);
		//		System.out.println(rHeight);
		//		System.out.println(maze.allsquare[rWidth][rHeight]);
		selectNextPath(maze.getCell(rWidth,rHeight));

		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		ArrayList<Cell> unvisited = getUnvisitedNeighbors(currentCell);

		//		int unvisitedN = 0;
		//		for (int i = -1; i < 1; i++) {
		//			for (int j = -1; j < 1; j++) {
		//				if (maze.allsquare[i][j].hasBeenVisited() == false) {
		//					unvisitedN++;
		//				}
		//			}
		//		}

		//C. if has unvisited neighbors,

		if (unvisited.size() > 0) {
			Cell c = unvisited.get(randGen.nextInt(unvisited.size()));
			uncheckedCells.push(c);
			removeWalls(c, currentCell);
			currentCell = c;
			c.setBeenVisited(true);
			selectNextPath(c);
			
		} else {
			if (!uncheckedCells.isEmpty()) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
		//C1. select one at random.

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
		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		} else if (c1.getY() == c2.getY()) {
			if (c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
		
		for (int i = c.getX() - 1; i < c.getX() + 2; i++) {
			for (int j = c.getY() - 1; j < c.getY() + 2; j++) {
//				if (i == c.getX() && j == c.getY()) {
//					continue;
//				} 
				if(i == c.getX() || j == c.getY()) {
				 if (i >= 0 && i < width && j >= 0 && j < height) {
					if (maze.getCell(i, j).hasBeenVisited() == false) {
						unvisited.add(maze.getCell(i,j));
					}
				}
				}
			}

		}
		return unvisited;
	}
}