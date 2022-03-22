package breakout;

public class PaddleState {
	
	private Point center;
	private Point paddleBR;
	private Point paddleTL;
	private Rectangle location;
	
	public PaddleState(Point center, Point TL, Point BR) {
		this.center = center;
		this.paddleBR = BR;
		this.paddleTL = TL;
		Rectangle loc = new Rectangle(TL, BR);
		this.location = loc;
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
}