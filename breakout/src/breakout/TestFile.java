package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFile {
	BallState ball1;
	BallState ball2;
	BlockState block1;
	BlockState block2;
	PaddleState paddle1;
	WallState wall;
	BreakoutState breakout1;
	BreakoutState breakout2;
	
// Variabelen initialiseren
	@BeforeEach
	void setUp() throws Exception {
		ball1 = new BallState(new Point(3,6), new Vector(2,1), 7);
		ball2 = new BallState(new Point(34,89), new Vector(-5,10), 20);
		block1 = new BlockState(new Point(1,1), new Point(11,11));
		block2 = new BlockState(new Point(5,65), new Point(23,84));
		paddle1 = new PaddleState(new Point(20,20), new Point(15,10), new Point(25,30));
		wall = new WallState(new Point(0,-50), new Point(100,0));
		breakout1 = new BreakoutState(new BallState[]{ball1}, new BlockState[]{block1}, new Point(300,500), paddle1);
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
	}
}
