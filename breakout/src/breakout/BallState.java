package breakout;

public class BallState {
	
	private Point position;
	private Vector velocity;
	private int diameter;
	
	
	public BallState(Point position, Vector velocity, int diameter) {
		this.position = position;
		this.velocity = velocity;
		this.diameter = diameter;
	}
	
	public Point getCenter() {
		return this.position;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	
	public int getDiameter() {
		return this.diameter;
	}
	
}
