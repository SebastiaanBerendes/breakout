package breakout;

public class PaddleState {
	
	private Point center;
	
	public PaddleState(Point center) {
		this.center = center;
	}
	
	public Point getPosition() {
		return this.center;
	}
}
