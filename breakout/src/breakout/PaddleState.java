package breakout;

/**
 * Represents a paddle. 
 *
 * @immutable
 */
public class PaddleState {
	
	private Point center;
	private Point paddleBR;
	private Point paddleTL;
	private Rectangle location;
	private int Speed;
	
	public PaddleState(Point center, Point TL, Point BR) {
		this.center = center;
		this.paddleBR = BR;
		this.paddleTL = TL;
		Rectangle loc = new Rectangle(TL, BR);
		this.location = loc;
		this.Speed = 10;
	}
	
	public Point getCenter() {
		return this.center;
	}
	
	public Point getTL() {
		return this.paddleTL;
	}
	
	public Point getBR() {
		return this.paddleBR;
	}
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return location.collision(Ballcenter, Balldiameter);
	}
	public int getSpeed() {
		return this.Speed;
	}
}