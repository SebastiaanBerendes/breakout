package breakout;

/**
 * Represents a paddle. 
 * @invar | getCenter() != null
 * @invar | getBR() != null
 * @invar | getTL() != null
 * @immutable
 */
public class PaddleState {
	
	private Point center;
	private Point paddleBR;
	private Point paddleTL;
	
	/**
	 * Return a new paddle with given center, topleft and bottomright points.
	 * @pre | center != null
	 * @pre | TL != null
	 * @pre | BR != null
	 * @pre | TL.getX() < BR.getX() && TL.getY() < BR.getY()
	 * @pre | (BR.getX()+TL.getX())/2 == center.getX() && (BR.getY()+TL.getY())/2 == center.getY()
	 * @post | getCenter().equals(center)
	 * @post | getTL().equals(TL)
	 * @post | getBR().equals(BR)
	 */
	public PaddleState(Point center, Point TL, Point BR) {
		this.center = center;
		this.paddleBR = BR;
		this.paddleTL = TL;
	}
	
	/** Returns this paddle's center */
	public Point getCenter() {
		return this.center;
	}
	
	/** Returns this paddle's most topleft point */
	public Point getTL() {
		return this.paddleTL;
	}
	
	/** Returns this paddle's most bottomright point */
	public Point getBR() {
		return this.paddleBR;
	}
	
	/** Checks if this paddle has a collision with a ball with given center and diameter and returns the reflection vector.
	 *  If there is no collision the function returns the zero vector.
	 * @pre | Ballcenter != null
	 * @pre | Balldiameter > 0
	 * 
	 * @post | result.equals(Vector.UP) || result.equals(Vector.DOWN) || result.equals(Vector.RIGHT) || result.equals(Vector.LEFT) || result.equals(new Vector(0,0))
	 */
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return this.getRectangle().collision(Ballcenter, Balldiameter);
	}
	
	/** Returns this paddle's speed */
	public int getSpeed() {
		return 10;
	}
	
	/** Returns the Rectangle abstraction of this paddle */
	public Rectangle getRectangle() {
		Rectangle loc = new Rectangle(this.paddleTL, this.paddleBR);
		return loc;
	}
}