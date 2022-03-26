package breakout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a state of the game breakout
 * 
 *@invar | getBalls() != null 
 *@invar | getBlocks() != null
 *@invar | getBottomRight() != null
 *@invar | getPaddle() != null
 */
public class BreakoutState {
	
	/**
	 *@invar | Arrays.stream(balls).allMatch(e -> e.getTL().getX() >= Point.ORIGIN.getX() && e.getTL().getY() >= Point.ORIGIN.getY() && e.getBR().getX() <= bottomRight.getX() && e.getBR().getY() <= bottomRight.getY())	
	 *@invar | Arrays.stream(blocks).allMatch(e -> e.getTL().getX() >= Point.ORIGIN.getX() && e.getTL().getY() >= Point.ORIGIN.getY() && e.getBR().getX() <= bottomRight.getX() && e.getBR().getY() <= bottomRight.getY())
	 *@invar | (bottomRight.getX() > Point.ORIGIN.getX() && bottomRight.getY() > Point.ORIGIN.getY())
	 *@invar | (paddle.getTL().getX() >= Point.ORIGIN.getX() && paddle.getTL().getY() >= Point.ORIGIN.getY() && paddle.getBR().getX() <= bottomRight.getX() && paddle.getBR().getY() <= bottomRight.getY())
	 *@representationObject | balls
	 *@representationObject | blocks
	 *@representationObject | bottomRight
	 *@representationObject | paddle
	 */
	BallState[] balls;
	BlockState[] blocks;
	Point bottomRight;
	PaddleState paddle;

	
	/**
	 * Return a new BreakoutState with given balls, blocks, most bottomRight point and paddle
	 * 
	 * @throws IllegalArgumentException | balls == null || blocks == null || paddle == null || bottomRight == null
	 * @throws IllegalArgumentException | Arrays.stream(balls).allMatch(e -> e.getTL().getX() >= Point.ORIGIN.getX() || e.getTL().getY() >= Point.ORIGIN.getY() || e.getBR().getX() <= bottomRight.getX() || e.getBR().getY() <= bottomRight.getY())
	 * @throws IllegalArgumentException | Arrays.stream(blocks).allMatch(e -> e.getTL().getX() >= Point.ORIGIN.getX() || e.getTL().getY() >= Point.ORIGIN.getY() || e.getBR().getX() <= bottomRight.getX() || e.getBR().getY() <= bottomRight.getY())
	 * @throws IllegalArgumentException | (bottomRight.getX() > Point.ORIGIN.getX() || bottomRight.getY() > Point.ORIGIN.getY())
	 * @throws IllegalArgumentException | (paddle.getTL().getX() >= Point.ORIGIN.getX() || paddle.getTL().getY() >= Point.ORIGIN.getY() || paddle.getBR().getX() <= bottomRight.getX() || paddle.getBR().getY() <= bottomRight.getY())
	 * 
	 * @post | Arrays.equals(getBalls(), balls)
	 * @post | Arrays.equals(getBlocks(), blocks)
	 * @post | getPaddle() == paddle
	 * @post | getBottomRight() == bottomRight
	 */
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		if (balls == null || blocks == null || paddle == null || bottomRight == null)
			throw new IllegalArgumentException();
		if (!(Arrays.stream(balls).allMatch(e -> e.getTL().getX() >= Point.ORIGIN.getX() && e.getTL().getY() >= Point.ORIGIN.getY() && e.getBR().getX() <= bottomRight.getX() && e.getBR().getY() <= bottomRight.getY())
				&& Arrays.stream(blocks).allMatch(e -> e.getTL().getX() >= Point.ORIGIN.getX() && e.getTL().getY() >= Point.ORIGIN.getY() && e.getBR().getX() <= bottomRight.getX() && e.getBR().getY() <= bottomRight.getY())
				&& (bottomRight.getX() > Point.ORIGIN.getX() && bottomRight.getY() > Point.ORIGIN.getY())
				&& (paddle.getTL().getX() >= Point.ORIGIN.getX() && paddle.getTL().getY() >= Point.ORIGIN.getY() && paddle.getBR().getX() <= bottomRight.getX() && paddle.getBR().getY() <= bottomRight.getY())))
			throw new IllegalArgumentException();
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
	}
	
	/** Returns a list of ballstates */
	public BallState[] getBalls() {
		return this.balls.clone();
	}

	/** Returns a list of blockstates */
	public BlockState[] getBlocks() {
		return this.blocks.clone();
	}

	/** Returns the paddle */
	public PaddleState getPaddle() {
		return this.paddle;
	}

	/** Returns the most bottomright point */
	public Point getBottomRight() {
		return this.bottomRight;
	}
	/** Returns the walls */
	public WallState[] getWalls() {
		WallState wallT = new WallState(new Point (Point.ORIGIN.getX(), Point.ORIGIN.getY() - bottomRight.getY()), new Point (bottomRight.getX(), Point.ORIGIN.getY()));
		WallState wallL = new WallState(new Point (Point.ORIGIN.getX() - bottomRight.getX(), Point.ORIGIN.getY()), new Point (Point.ORIGIN.getX(), bottomRight.getY()));
		WallState wallR = new WallState(new Point (bottomRight.getX(), Point.ORIGIN.getY()), new Point (2 * bottomRight.getX(), bottomRight.getY()));
		WallState[] walls = new WallState[]{wallT, wallL, wallR};
		return walls.clone();
	}

	/** 
	 * Moves the balls according to their velocity, checks if there are any collisions between the balls and the blocks, walls and paddle
	 * and reflects the ball accordingly. If any blocks are hit by balls, they are removed and balls that hit the bottom of the screen
	 * also get removed. Balls that hit the paddle will inherit a fifth of the speed of the paddle.
	 */
	public void tick(int paddleDir) {
		WallState[] walls = getWalls();
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
			else {
				index_balls[i] = 0;
			}
//			block collision detection
			for (int j = 0; j < length_blocks; j++) {
				Vector m_block = blocks[j].collision(balls[i].getCenter(), balls[i].getDiameter());
				if (m_block.getSquareLength() == 1) {
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_block), balls[i].getDiameter());
					index_blocks[j] = 1;
					counter_blocks += 1;
				}
				else
					index_blocks[j] = 0;
			}
//			paddle collision detection
			Vector m_paddle = paddle.collision(balls[i].getCenter(), balls[i].getDiameter());
			if (m_paddle.getSquareLength() == 1) {
				// right
				if (m_paddle.getX() == 1) {
					Vector shift = new Vector(paddle.getSpeed(),0);
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_paddle).plus(shift), balls[i].getDiameter());
				}
				// left
				else if (m_paddle.getX() == -1) {
					Vector shift = new Vector(paddle.getSpeed(),0);
					balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_paddle).minus(shift), balls[i].getDiameter());
				}
				// top with speed up
				else {
					Vector speed = balls[i].getVelocity().mirrorOver(m_paddle);
					Vector shift = new Vector(paddle.getSpeed()/5,0);
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
			if (index_blocks[i] == 0 && pointer < length_blocks-counter_blocks) {
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
	
	/**
	 * When the right arrowkey is pressed, the paddle will move to the right.
	 */
	public void movePaddleRight() {
		Vector shift = new Vector(paddle.getSpeed(),0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		if (newBR.getX()<=bottomRight.getX())	
			paddle = new PaddleState(newCenter, newTL, newBR);
	}
	
	/**
	 * When the left arrowkey is pressed, the paddle will move to the left.
	 */
	public void movePaddleLeft() {
		Vector shift = new Vector(-paddle.getSpeed(),0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		if (newTL.getX() >= Point.ORIGIN.getX())
			paddle = new PaddleState(newCenter, newTL, newBR);
	}
	
	/**
	 * Returns true if the game is won, returns false if it isn't
	 * @inspects | getBalls(), getBlocks()
	 */
	public boolean isWon() {
		if (this.getBalls().length > 0 && this.getBlocks().length == 0) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Returns true if it is game over, returns false if it isn't
	 * @inspects | getBalls()
	 */
	public boolean isDead() {
		if (this.getBalls().length < 1) 
			return true; 
		else 
			return false; 
	}
}
