package breakout;

/**
 * Represents a block. 
 *
 * @immutable
 */
public class BlockState {
	
	private Point blockTL;
	private Point blockBR;
	private Rectangle location;
	
	/**
	 * Return a new block with given topleft and bottomright points.
	 * 
	 * @post | getTL() == TL
	 * @post | getBR() == BR
	 */
	public BlockState(Point TL, Point BR) {
		this.blockTL = TL;
		this.blockBR = BR;
		Rectangle loc = new Rectangle(TL, BR);
		this.location = loc;
	}
	
	/** Return this block's topleft point */
	public Point getTL() {
		return this.blockTL;
	}
	
	/** Return this block's bottomright point */
	public Point getBR() {
		return this.blockBR;
	}
	
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return location.collision(Ballcenter, Balldiameter);
	}
}