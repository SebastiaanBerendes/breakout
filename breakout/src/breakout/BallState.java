package breakout;

/**
 * Represents a ball. 
 *
 * @immutable
 */
public class BallState {
	
	private Point center;
	private Vector velocity;
	private int diameter;
	
	/**
	 * Return a new ball with given center, velocity and diameter.
	 * 
	 * @post | getCenter() == position
	 * @post | getVelocity() == velocity
	 * @post | getDiameter() == diameter
	 */
	public BallState(Point position, Vector velocity, int diameter) {
		this.center = position;
		this.velocity = velocity;
		this.diameter = diameter;
	}
	
	/** Return this ball's center point */
	public Point getCenter() {
		return this.center;
	}
	
	/** Return this ball's velocity */
	public Vector getVelocity() {
		return this.velocity;
	}
	
	/** Return this ball's diameter */
	public int getDiameter() {
		return this.diameter;
	}
	
	/** Return this ball's adjacent square topleft point */
	public Point getTL() {
		int radius = diameter/2;
		int xcord = center.getX()-radius;
		int ycord = center.getY()-radius;
		Point TL = new Point(xcord, ycord);
		return TL;
	}
	
	/** Return this ball's adjacent square bottomright point */
	public Point getBR() {
		int radius = diameter/2;
		int xcord = center.getX()+radius;
		int ycord = center.getY()+radius;
		Point BR = new Point(xcord, ycord);
		return BR;
	}
}
