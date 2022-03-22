package breakout;

public class BlockState {
	
	private Point blockTL;
	private Point blockBR;
	private Rectangle location;
	
	
	public BlockState(Point TL, Point BR) {
		this.blockTL = TL;
		this.blockBR = BR;
	}
	
	public Point getTL() {
		return this.blockTL;
	}
	
	public Point getBR() {
		return this.blockBR;
	}
	public Vector collision(Point Ballcenter, int Balldiameter) {
		return location.collision(Ballcenter, Balldiameter);
	}
}