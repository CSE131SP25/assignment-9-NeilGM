package assignment9;

import java.util.LinkedList;


public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		deltaX = 0;
		deltaY = 0;
		segments = new LinkedList<>();
		BodySegment seg1 = new BodySegment(.5, .5, SEGMENT_SIZE);
		segments.add(seg1);
		
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		for(int i = segments.size() - 1; i > 0; i--) {
			segments.get(i).setX(segments.get(i-1).getX());
			segments.get(i).setY(segments.get(i-1).getY());
		}
		segments.get(0).setX(segments.get(0).getX() + deltaX);
		segments.get(0).setY(segments.get(0).getY() + deltaY);
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for(int i = 0; i<segments.size(); i++) {
			segments.get(i).draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		boolean foodEat = false;
		if(isTouching(f, segments.get(0))){
			foodEat = true;
			BodySegment sNext = new BodySegment(segments.get(0).getX() + deltaX, segments.get(0).getY() + deltaY, SEGMENT_SIZE);
			segments.add(0, sNext);	
		}
		return foodEat;
	}
	public double distanceCenterToPoint(double xOther, double yOther, BodySegment s) {
	
		double distance = Math.sqrt(Math.pow(s.getX() - xOther, 2) + Math.pow(s.getY() - yOther, 2));
		return distance;
	}

	/**
	 * @param other the other Entity
	 * @return the distance between this Entity's center and the specified other
	 *         Entity's center.
	 */
	public double distanceCenterToCenter(Food other, BodySegment s) {
		return distanceCenterToPoint(other.getX(), other.getY(), s);
	}

	/**
	 * @param xOther      the x-coordinate of another Entity's center.
	 * @param yOther      the y-coordinate of another Entity's center.
	 * @param radiusOther the radius of another Entity.
	 * @return the distance between this Entity's edge and the specified other
	 *         Entity's edge.
	 */
	public double distanceEdgeToEdge(double xOther, double yOther, double radiusOther, BodySegment s) {
		double edgeDistance = distanceCenterToPoint(xOther, yOther, s) - radiusOther - SEGMENT_SIZE;
		return edgeDistance;
		
	}

	/**
	 * @param other the other Entity.
	 * @return the distance between this Entity's edge and the specified other
	 *         Entity's edge.
	 */
	public double distanceEdgeToEdge(Food other, BodySegment s) {
		return distanceEdgeToEdge(other.getX(), other.getY(), .02, s);
	}

	/**
	 * @param xOther      the x-coordinate of another Entity's center.
	 * @param yOther      the y-coordinate of another Entity's center.
	 * @param radiusOther the radius of another Entity.
	 * @return true if the bounding circle of this Entity overlaps with the bounding
	 *         circle of the specified other Entity, false otherwise.
	 */
	public boolean isTouching(double xOther, double yOther, double radiusOther, BodySegment s) {
		if(distanceEdgeToEdge(xOther, yOther, radiusOther, s) > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isTouching(Food other, BodySegment s) {
		return isTouching(other.getX(), other.getY(), .02, s);
	}
	
	public boolean isTouching(BodySegment other, BodySegment s) {
		double wormRadius = .005;
		return isTouching(other.getX(), other.getY(), wormRadius, s);
	}
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		if( segments.get(0).getX() < 0 || segments.get(0).getX() > 1 || segments.get(0).getY() < 0 || segments.get(0).getY() > 1) {
			return false;
		}
		return true;
	}
	
	public boolean selfCollide() {

		for(int i = 1; i < segments.size(); i++) {
			if(isTouching(segments.get(i), segments.get(0))) {
				return true;
			}
		}
		return false;
	}
}
