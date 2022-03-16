package breakout;

public class BallState {
	
	private Point position;
	private Vector velocity;
	
	public BallState(Point position, Vector velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
	public Point getCenter() {
		return this.position;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	
	
}
