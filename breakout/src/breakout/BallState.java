package breakout;

public class BallState {
	
	private Point center;
	private Vector velocity;
	private int diameter;
	
	
	public BallState(Point position, Vector velocity, int diameter) {
		this.center = position;
		this.velocity = velocity;
		this.diameter = diameter;
	}
	
	public Point getCenter() {
		return this.center;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	
	public int getDiameter() {
		return this.diameter;
	}
	
	public Point getTL() {
		int radius = diameter/2;
		int xcord = center.getX()-radius;
		int ycord = center.getY()-radius;
		Point TL = new Point(xcord, ycord);
		return TL;
	}
	
	public Point getBR() {
		int radius = diameter/2;
		int xcord = center.getX()+radius;
		int ycord = center.getY()+radius;
		Point BR = new Point(xcord, ycord);
		return BR;
	}
}
