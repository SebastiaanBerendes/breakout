package breakout;

public class PaddleState {
	
	private Point center;
	private Point paddleTL;
	private Point paddleBR;
	
	
	public PaddleState(Point center, Point TL, Point BR) {
		this.center = center;
		this.paddleBR = BR;
		this.paddleTL = TL;
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
}
