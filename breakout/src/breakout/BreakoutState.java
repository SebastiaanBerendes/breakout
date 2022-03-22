package breakout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: implement, document
public class BreakoutState {
	
	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;
	private WallState[] walls;

	
	/**
	 * Return a new BreakoutState with given balls, blocks, most bottomRight point and paddle
	 * 
	 * @post | getBalls() == balls
	 * @post | getBlocks() == blocks
	 * @post | getPaddle() == paddle
	 * @post | getBottomRight() == bottomRight
	 */
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
		Point wallTTL = new Point (Point.ORIGIN.getX(), Point.ORIGIN.getY() - bottomRight.getY());
		Point wallTBR = new Point (bottomRight.getX(), Point.ORIGIN.getY());
		Point wallLTL = new Point (Point.ORIGIN.getX() - bottomRight.getX(), Point.ORIGIN.getY());
		Point wallLBR = new Point (Point.ORIGIN.getX(), bottomRight.getY());
		Point wallRTL = new Point (bottomRight.getX(), Point.ORIGIN.getY());
		Point wallRBR = new Point (2 * bottomRight.getX(), bottomRight.getY());
		WallState wallT = new WallState(wallTTL, wallTBR);
		WallState wallL = new WallState(wallLTL, wallLBR);
		WallState wallR = new WallState(wallRTL, wallRBR);
		WallState[] walls = new WallState[]{wallT, wallL, wallR};
		this.walls = walls;
	}
	
	/** Returns a list of ballstates */
	public BallState[] getBalls() {
		return this.balls;
	}

	/** Returns a list of blockstates */
	public BlockState[] getBlocks() {
		return this.blocks;
	}

	/** Returns the paddle */
	public PaddleState getPaddle() {
		return this.paddle;
	}

	/** Returns the most bottomright point */
	public Point getBottomRight() {
		return this.bottomRight;
	}

	public void tick(int paddleDir) {
		int length_balls = balls.length;
 		int length_blocks = blocks.length;
 		int length_walls = walls.length;
		int[] index_balls = new int[length_balls];
 		int[] index_blocks = new int[length_blocks];
		int counter_balls = 0;
 		int counter_blocks = 0;
 		
		for (int i = 0; i < length_balls; i++) {
			
//			moves all balls according to their velocity			
			balls[i] = new BallState(balls[i].getCenter().plus(balls[i].getVelocity()), balls[i].getVelocity(), balls[i].getDiameter());
			
//			remembers index of balls that reach 30000+ y-coordinate
			if (balls[i].getCenter().getY()+balls[i].getDiameter()/2 >= bottomRight.getY()) {
				index_balls[i] = 1;
				counter_balls += 1;
			}
			else
				index_balls[i] = 0;
			
//			block collision detection
			if (length_blocks != 0) {
				for (int j = 0; j < length_blocks; j++) {
					Vector m_block = blocks[j].collision(balls[i].getCenter(), balls[i].getDiameter());
					if (m_block.getSquareLength() == 1) {
						balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_block), balls[i].getDiameter());
						index_blocks[j] = 1;
						counter_blocks += 1;
					}
//					index moet nog bijgehouden worden zodat de block verwijderd kan worden
					else
						index_blocks[j] = 0;
				}
			}
//			paddle collision detection
			Vector m_paddle = paddle.collision(balls[i].getCenter(), balls[i].getDiameter());
			if (m_paddle.getSquareLength() == 1) {
				// right
				if (m_paddle.getX() == 1) {
					Vector shift = new Vector(paddle.getSpeed(),0);
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().plus(shift), balls[i].getDiameter());
				}
				// left
				else if (m_paddle.getX() == -1) {
					Vector shift = new Vector(-paddle.getSpeed(),0);
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().plus(shift), balls[i].getDiameter());
				}
				// top with speed up
				else {
					Vector speed = balls[i].getVelocity().mirrorOver(m_paddle);
					Vector shift = new Vector(paddle.getSpeed()*6/5,0);
					if (paddleDir == 1) {
						speed = speed.plus(shift);
					}
					else if (paddleDir == -1) {
						speed = speed.minus(shift);
					}
					balls[i] = new BallState(balls[i].getCenter(), speed, balls[i].getDiameter());
				}
			}
//			wall collision detection
			for (int k = 0; k < length_walls; k++) {
				Vector m_wall = walls[k].collision(balls[i].getCenter(), balls[i].getDiameter());
				if (m_wall.getSquareLength() == 1) {
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_wall), balls[i].getDiameter());
				}
			}
			
		}
//		new list with blocks
		int pointer = 0;
		BlockState[] new_blocks = new BlockState[length_blocks-counter_blocks];
		for (int i = 0; i < length_blocks; i++) {
			if (index_blocks[i] == 0) {
				new_blocks[pointer] = blocks[i];
				pointer++;
			}
		}
		this.blocks = new_blocks;
		
//		new list with balls
		int pointer2 = 0;
		BallState[] new_balls = new BallState[length_balls-counter_balls];
		for (int i = 0; i < length_balls; i++) {
			if (index_balls[i] == 0) {
				new_balls[pointer2] = balls[i];
				pointer2++;
			}
		}
		this.balls = new_balls;
	}

	public void movePaddleRight() {
		Vector shift = new Vector(paddle.getSpeed(),0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		if (newBR.getX()<=50000)	
			paddle = new PaddleState(newCenter, newTL, newBR);
	}

	public void movePaddleLeft() {
		Vector shift = new Vector(-paddle.getSpeed(),0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		if (newTL.getX() >= 0)
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
