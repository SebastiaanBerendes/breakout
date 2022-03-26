package breakout;

/**
 * Each instance of this class stores a topleft point and a bottomright point.
 *@invar | getTL() != null
 *@invar | getBR() != null
 *@invar | getTL().getX() < getBR().getX() && getTL().getY() < getBR().getY()
 *@immutable
 */
public class WallState {
	
	/**
	 *@invar | topleft != null
	 *@invar | bottomright != null
	 *@invar | topleft.getX() < bottomright.getX() && topleft.getY() < bottomright.getY()
	 */
	private Point topleft;
	private Point bottomright;
	
	/**
	 * Returns a wall with given topleft and bottomright point
	 * @pre | TL != null || BR != null
	 * @pre | TL.getX() < BR.getX() && TL.getY() < BR.getY()
	 * @post | getBR().equals(BR)
	 * @post | getTL().equals(TL)
	 */
	public WallState(Point TL, Point BR) {
		this.bottomright = BR;
		this.topleft = TL;
	}
	
	/** Returns this WallStates most topleft point */
	public Point getTL() {
		return this.topleft;
	}
	
	/** Returns this WallStates most bottomright point */
	public Point getBR() {
		return this.bottomright;
	}
	
	/** Checks if this wall has a collision with a ball with given center and diameter and returns the reflection vector.
	 *  If there is no collision the function returns the zero vector.
	 * @pre | Ballcenter != null
	 * @pre | Balldiameter > 0
	 * @inspects | getRectangle()
	 * @post | result.equals(Vector.UP) || result.equals(Vector.DOWN) || result.equals(Vector.RIGHT) || result.equals(Vector.LEFT) || result.equals(new Vector(0,0))
	 */
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return this.getRectangle().collision(Ballcenter, Balldiameter);
	}
	
	/** Returns the Rectangle abstraction of this wall 
	 *  @inspects | getTL(), getBR()
	 */
	public Rectangle getRectangle() {
		Rectangle loc = new Rectangle(this.topleft, this.bottomright);
		return loc;
	}
}