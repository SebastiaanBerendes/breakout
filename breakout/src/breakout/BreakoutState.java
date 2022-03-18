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
		int length = balls.length;
		for (int i = 0; i < length; i++) {
			balls[i] = new BallState(balls[i].getCenter().plus(balls[i].getVelocity()), balls[i].getVelocity(), balls[i].getDiameter());
			
//			if (0 > balls[i].getTL().getX()) {
//				balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(balls[i].getVelocity()), balls[i].getDiameter());
//			}
//			if (50000 < balls[i].getBR().getX()) {
//				
//			}
//			if (0 > balls[i].getTL().getY()) {
//																CHECK OPGAVE IETS MET RECTANGLE
//			}
//			if (30000 < balls[i].getBR().getY()) {
//				
//			}
		}
	}

	public void movePaddleRight() {
		Vector shift = new Vector(10,0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		paddle = new PaddleState(newCenter, newTL, newBR);
	}

	public void movePaddleLeft() {
		Vector shift = new Vector(-10,0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		paddle = new PaddleState(newCenter, newTL, newBR);
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
