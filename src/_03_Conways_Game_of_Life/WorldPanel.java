package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	Random rand = new Random();
	int rand1 = rand.nextInt(1);

	private Timer timer;

	//1. Create a 2D array of Cells. Do not initialize it.

	Cell[][] main;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		//2. Calculate the cell size.
		cellSize = w / cpr;
		//3. Initialize the cell array to the appropriate size.
		main = new Cell[cellsPerRow][cellsPerRow];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for (int i = 0; i < main.length; i++) {
			for (int j = 0; j < main[i].length; j++) {
				main[i][j] = new Cell(i * cellSize, j * cellSize, cellSize);
			}
		}
	}

	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		for (int i = 0; i < main.length; i++) {
			for (int j = 0; j < main[i].length; j++) {
				rand1 = rand.nextInt(1);
				if (rand1 == 0) {
					main[i][j].isAlive = false;
				} else {
					main[i][j].isAlive = true;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		//5. Iterated through the cells and set them all to dead.
		for (int i = 0; i < main.length; i++) {
			for (int j = 0; j < main[i].length; j++) {
				main[i][j].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for (int i = 0; i < main.length; i++) {
			for (int j = 0; j < main[i].length; j++) {
				main[i][j].draw(g);
				g.setColor(Color.BLACK);
				g.drawRect(main[i][j].getX(), main[i][j].getY(), cellSize, cellSize);
			}
		}
		// draws grid

	}

	//advances world one step
	public void step() {
		//7. iterate through cells and get their neighbors
		for (int i = 0; i < main.length; i++) {
			for (int j = 0; j < main[i].length; j++) {

				main[i][j].liveOrDie(getLivingNeighbors(i, j));

			}
		}
		//8. check if each cell should live or die

		repaint();
	}

	//9. Complete the method.
	//   It returns an array list of the  8 or less neighbors of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		int neighbor = 0;
		//	ArrayList<Cell> neighbor = new ArrayList<Cell>();
		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 1; i < y + 2; i++) {
				if (i >= 0 && i < main.length && j >= 0 && j < main[i].length) {
					if (main[i][j].isAlive == true) {
						//				neighbor.add(main[i][j]);
						neighbor++;
					}
				}
			}
		}
		return neighbor;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		for (int i = 0; i < main.length; i++) {
			for (int j = 0; j < main[i].length; j++) {
				if (i * cellSize <= e.getX() && e.getX() <= cellSize + (i * cellSize)) {
					if (j * cellSize <= e.getY() && e.getY() <= cellSize + (j * cellSize)) {
						main[i][j].isAlive = true;

					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
