package breakout;

public class BlockState {
	
	private Point blockTL;
	private Point blockBR;
	
	
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
}
