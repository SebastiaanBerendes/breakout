package breakout;

// TODO: implement, document
public class BreakoutState {
	
	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;

	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
	}
	
	public BallState[] getBalls() {
		return this.balls;
	}

	public BlockState[] getBlocks() {
		return this.blocks;
	}

	public PaddleState getPaddle() {
		return this.paddle;
	}

	public Point getBottomRight() {
		return this.bottomRight;
	}

	public void tick(int paddleDir) {
		
	}

	public void movePaddleRight() {
		
	}

	public void movePaddleLeft() {
		
	}
	
	public boolean isWon() {
		if (this.balls.length > 0 && this.blocks.length == 0) {
			return true;}
		else {
			return false;}
	}

	public boolean isDead() {
		if (this.balls.length < 1) {
			return true; }
		else {
			return false; }
	}
}
