package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	Snake worm;
	Food grub;
	int score;
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		worm = new Snake();
		grub = new Food();
		score = 0;
		
	}
	
	public void play() {
		while (worm.isInbounds() && !worm.selfCollide()) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			worm.changeDirection(dir);
			worm.move();
			boolean eat = worm.eatFood(grub);
			if(eat) {
				grub = new Food();
				score += 1;
			}
			updateDrawing();
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
		worm.draw();
		grub.draw();
		StdDraw.text(.5, .9, "Score: " + score);
		StdDraw.pause(50);
		StdDraw.show();
		//FIXME
		
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
