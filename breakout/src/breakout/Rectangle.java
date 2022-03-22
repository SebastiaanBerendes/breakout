package breakout;

public class Rectangle {
	private Point RectangleTL;
	private Point RectangleBR;

	public Rectangle(Point TL, Point BR) {
		this.RectangleTL = TL;
		this.RectangleBR = BR;

	}
	public Vector collision(Point Ballcenter, int Balldiameter) {
		if (this.RectangleTL.getX() <= Ballcenter.getX() && Ballcenter.getX() <= this.RectangleBR.getX()) { 
//			This is for top of Object collision
			if (this.RectangleTL.getY() <= (Ballcenter.getY() + Balldiameter/2) && (Ballcenter.getY() + Balldiameter/2) <= this.RectangleBR.getY()) {
				Vector vectorT = new Vector(0,-1);
				return vectorT;
			}
//			This is for bottom of Object collision
			else if (this.RectangleBR.getY() >= (Ballcenter.getY() - Balldiameter/2) && (Ballcenter.getY() - Balldiameter/2) >= this.RectangleTL.getY()) {
				Vector vectorB = new Vector(0,1);
				return vectorB;
			}
		}
		else if (this.RectangleTL.getY() <= Ballcenter.getY() && Ballcenter.getY() <= this.RectangleBR.getY()) {
//			This is for right of Object collision
			if (this.RectangleTL.getX() <= (Ballcenter.getX() - Balldiameter/2) && (Ballcenter.getX() - Balldiameter/2) <= this.RectangleBR.getX()) {
				Vector vectorR = new Vector(1,0);
				return vectorR;
			}
//			This is for left of Object collision
			else if (this.RectangleTL.getX() <= (Ballcenter.getX() + Balldiameter/2) && (Ballcenter.getX() + Balldiameter/2) <= this.RectangleBR.getX()) {
				Vector vectorL = new Vector(-1,0);
				return vectorL;
			}
		}
		Vector vector_null = new Vector(0,0);
		return vector_null;
	}
}