package breakout;

public class WallState {
	
	private Point topleft;
	private Point bottomright;
	private Rectangle location;
	
	public WallState(Point TL, Point BR) {
		this.bottomright = BR;
		this.topleft = TL;
		Rectangle loc = new Rectangle(TL, BR);
		this.location = loc;
	}
		
	public Point getTL() {
		return this.topleft;
	}
	
	public Point getBR() {
		return this.bottomright;
	}
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return location.collision(Ballcenter, Balldiameter);
	}
}