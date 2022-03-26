package breakout;

/**
 * Represents a block. 
 * @invar | getTL() != null
 * @invar | getBR() != null
 * @invar | getVisibility() == false || getVisibility() == true
 * @immutable
 */
public class BlockState {
	
	private Point blockTL;
	private Point blockBR;
	private boolean visible;
	
	/**
	 * Return a new block with given topleft and bottomright points.
	 * @pre | TL != null
	 * @pre | BR != null
	 * @pre | visibility == true || visibility == false
	 * @pre | TL.getX() < BR.getX() && TL.getY() < BR.getY()
	 * @post | getTL().equals(TL)
	 * @post | getBR().equals(BR)
	 * @post | getVisibility() == visibility
	 */
	public BlockState(Point TL, Point BR, boolean visibility) {
		this.blockTL = TL;
		this.blockBR = BR;
		this.visible = visibility;
	}
	
	/** Return this block's topleft point */
	public Point getTL() {
		return this.blockTL;
	}
	
	/** Return this block's bottomright point */
	public Point getBR() {
		return this.blockBR;
	}
	
	/** Returns the Rectangle abstraction of this block */
	public Rectangle getRectangle() {
		Rectangle loc = new Rectangle(this.blockTL, this.blockBR);
		return loc;
	}
	
	public boolean getVisibility() {
		return this.visible;
	}
	
	/** Checks if this block has a collision with a ball with given center and diameter and returns the reflection vector
	 *  If there is no collision the function returns the zero vector.
	 * @pre | Ballcenter != null
	 * @pre | Balldiameter > 0
	 * 
	 * @post | result.equals(Vector.UP) || result.equals(Vector.DOWN) || result.equals(Vector.RIGHT) || result.equals(Vector.LEFT) || result.equals(new Vector(0,0))
	 */
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return this.getRectangle().collision(Ballcenter, Balldiameter);
	}
}