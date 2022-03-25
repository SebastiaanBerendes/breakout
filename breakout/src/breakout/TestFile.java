package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFile {
	BallState ball1;
	BallState ball2;
	BallState ball3;
	BlockState block1;
	BlockState block2;
	BlockState block3;
	BlockState block4;
	PaddleState paddle1;
	PaddleState paddle2;
	WallState wall;
	WallState wall2;
	BreakoutState breakout1;
	BreakoutState breakout2;
	
// Variabelen initialiseren
	@BeforeEach
	void setUp() throws Exception {
		ball1 = new BallState(new Point(3,6), new Vector(2,1), 7);
		ball2 = new BallState(new Point(34,89), new Vector(-5,10), 20);
		ball3 = new BallState(new Point(150,220), new Vector(0,-20),10);
		block1 = new BlockState(new Point(1,1), new Point(11,11));
		block2 = new BlockState(new Point(5,65), new Point(23,84));
		block3 = new BlockState(new Point(100,155),new Point(200,200));
		block4 = new BlockState(new Point(200,155),new Point(300,200));
		paddle1 = new PaddleState(new Point(20,20), new Point(15,10), new Point(25,30));
		paddle2 = new PaddleState(new Point(150,250), new Point(100,240), new Point(200,260));
		wall = new WallState(new Point(0,-50), new Point(100,0));
		wall2 = new WallState (new Point(Point.ORIGIN.getX(), Point.ORIGIN.getY() - 3000), new Point (5000, Point.ORIGIN.getY()));
		breakout1 = new BreakoutState(new BallState[]{ball1}, new BlockState[]{block1,block2}, new Point(5000,3000), paddle1);
		breakout2 = new BreakoutState(new BallState[]{ball3}, new BlockState[]{block3,block4}, new Point(500,500), paddle2);
	}
	@Test
	void testBallState() {
		assertEquals(new Point(3,6),ball1.getCenter());
		assertEquals(new Point(34,89),ball2.getCenter());
		assertEquals(new Vector(2,1),ball1.getVelocity());
		assertEquals(new Vector(-5,10),ball2.getVelocity());
		assertEquals(7,ball1.getDiameter());
		assertEquals(20,ball2.getDiameter());
		assertEquals(new Point(6,9),ball1.getBR());
		assertEquals(new Point(44,99),ball2.getBR());
		assertEquals(new Point(0,3),ball1.getTL());
		assertEquals(new Point(24,79),ball2.getTL());
	}
	@Test
	void testBlockState() {
		assertEquals(new Point(1,1),block1.getTL());
		assertEquals(new Point(23,84),block2.getBR());
		assertEquals(block1.getRectangle().equals(new Rectangle(new Point(1,1),new Point(11,11))),true);
		assertEquals(new Vector(1,0), block1.collision(new Point(12,5), 2));
		assertEquals(new Vector(0,0), block1.collision(new Point(12,5), 1));
		assertEquals(new Vector(-1,0),block2.collision(new Point(4,70), 3));
		assertEquals(new Vector(0,0),block2.collision(new Point(4,70), 1));
	}
	@Test
	void testPaddleState() {
		assertEquals(new Point(20,20),paddle1.getCenter());
		assertEquals(new Point(15,10),paddle1.getTL());
		assertEquals(new Point(25,30),paddle1.getBR());
		assertEquals(10,paddle1.getSpeed());
		assertEquals(paddle1.getRectangle().equals(new Rectangle(new Point(15,10),new Point(11,11))),false);
		assertEquals(paddle1.getRectangle().equals(new Rectangle(new Point(14,10),new Point(25,30))),false);
		assertEquals(new Vector(0,-1), paddle1.collision(new Point(17,9),3));
		assertEquals(new Vector(0,0), paddle1.collision(new Point(17,9),1));
	}
	@Test
	void testWallState() {
		assertEquals(new Point(0,-50),wall.getTL());
		assertEquals(new Point(100,0),wall.getBR());
		assertEquals(wall.getRectangle().equals(1),false);
		assertEquals(wall.getRectangle().equals(null),false);
		assertEquals(new Vector(0,0),wall.collision(new Point(13,5), 2));
		assertEquals(new Vector(0,1),wall.collision(new Point(45,6), 14));
	}
	@Test
	void testBreakoutState() {
		assertEquals(Arrays.equals(breakout1.getBalls(), new BallState[]{ball1}),true);
		assertEquals(Arrays.equals(breakout1.getBlocks(), new BlockState[]{block1}),false);
		assertEquals(new Point(5000,3000),breakout1.getBottomRight());
		assertEquals(paddle1,breakout1.getPaddle());
		assertEquals(breakout1.getWalls()[0].getTL(),wall2.getTL());
		assertEquals(breakout1.getWalls()[0].getBR(),wall2.getBR());
		assertEquals((new BreakoutState(new BallState[]{}, new BlockState[]{block1,block2}, new Point(5000,3000), paddle1)).isDead(),true);
		assertEquals((new BreakoutState(new BallState[]{ball1}, new BlockState[]{block1,block2}, new Point(5000,3000), paddle1)).isDead(),false);
		assertEquals((new BreakoutState(new BallState[]{ball1}, new BlockState[]{}, new Point(5000,3000), paddle1)).isWon(),true);
		assertEquals((new BreakoutState(new BallState[]{}, new BlockState[]{block1,block2}, new Point(5000,3000), paddle1)).isWon(),false);
		breakout2.tick(0);
		assertEquals(breakout2.getBalls()[0].getVelocity().getY(),20);
		assertEquals(breakout2.getBlocks().length,1);
		breakout2.tick(0);
		BreakoutState breakout2a = new BreakoutState(breakout2.getBalls(), breakout2.getBlocks(), breakout2.getBottomRight(), breakout2.getPaddle()); 
		BreakoutState breakout2b = new BreakoutState(breakout2.getBalls(), breakout2.getBlocks(), breakout2.getBottomRight(), breakout2.getPaddle());
		BallState ball3a = new BallState(new Point(90,250), new Vector(5,0),10);
		BreakoutState breakout2c = new BreakoutState(new BallState[]{ball3a},breakout2.getBlocks(),breakout2.getBottomRight(),paddle2);
		BallState ball3b = new BallState(new Point(210,250), new Vector(-5,0),10);
		BreakoutState breakout2d = new BreakoutState(new BallState[]{ball3b},breakout2.getBlocks(),breakout2.getBottomRight(),breakout2.getPaddle());
		breakout2.tick(0); breakout2a.tick(-1); breakout2b.tick(1); breakout2c.tick(0); breakout2d.tick(0);
		assertEquals(breakout2.getBalls()[0].getVelocity().equals(new Vector(0,-20)),true);
		assertEquals(breakout2a.getBalls()[0].getVelocity().equals(new Vector(-2,-20)),true);
		assertEquals(breakout2b.getBalls()[0].getVelocity().equals(new Vector(2,-20)),true);
		assertEquals(breakout2c.getBalls()[0].getVelocity().equals(new Vector(-15,0)),true);
		assertEquals(breakout2d.getBalls()[0].getVelocity().equals(new Vector(15,0)),true);
		BallState ball3c = new BallState(new Point(10,250), new Vector(-5,0),10);
		BreakoutState breakout2e = new BreakoutState(new BallState[]{ball3c},breakout2.getBlocks(),breakout2.getBottomRight(),paddle2);
		breakout2e.tick(0);
		assertEquals(breakout2e.getBalls()[0].getVelocity().getX(),5);
		BallState ball3d = new BallState(new Point(10,250), new Vector(0,10),10);
		BreakoutState breakout2f = new BreakoutState(new BallState[]{ball3d},breakout2.getBlocks(),new Point(300,265),paddle2);
		breakout2f.tick(0);
		assertEquals(breakout2f.getBalls().length,0);
		breakout2f.movePaddleLeft();
		assertEquals(breakout2f.getPaddle().getCenter().equals(new Point(140,250)),true);
		breakout2f.movePaddleRight();
		assertEquals(breakout2f.getPaddle().getCenter().equals(new Point(150,250)),true);
	}
}
