package assignment9;


import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		x = Math.random();
		y = Math.random();
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(0, 0, 0);
		StdDraw.filledCircle(x, y, FOOD_SIZE);
		
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
