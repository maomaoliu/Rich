/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.exception.CashNotEnoughException;
import tw.homework.rich.game.exception.ConstructOutOfTimesException;
import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.exception.LandOwnerNotFoundException;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.LandPosition;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-19
 */
public class LandPositionTest {

	LandPosition land;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InAndOutTestUtils.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InAndOutTestUtils.tearDown();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		InAndOutTestUtils.clearPipes();
		new Game();
		land = new LandPosition(200, 3);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.position.LandPosition#canConstruct()}.
	 * 
	 * @throws LandOwnerNotFoundException
	 * @throws CashNotEnoughException
	 * @throws ConstructOutOfTimesException
	 */
	@Test
	public void testCanConstruct() throws ConstructOutOfTimesException,
			CashNotEnoughException, LandOwnerNotFoundException {
		assertTrue(land.canConstruct());
		Player player = new Player();
		player.setCash(10000);
		land.setOwner(player);
		land.construct();
		assertTrue(land.canConstruct());
		land.construct();
		assertTrue(land.canConstruct());
		land.construct();
		assertFalse(land.canConstruct());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.position.LandPosition#construct()}.
	 * 
	 * @throws LandOwnerNotFoundException
	 * @throws CashNotEnoughException
	 * @throws ConstructOutOfTimesException
	 */
	@Test
	public void testConstruct() throws ConstructOutOfTimesException,
			CashNotEnoughException, LandOwnerNotFoundException {
		Player player = new Player();
		player.setCash(10000);
		assertEquals(-1, land.getCurConstructTimes());
		land.setOwner(player);
		assertEquals(0, land.getCurConstructTimes());
		int cost = land.construct();
		assertEquals(200, cost);
		assertEquals(1, land.getCurConstructTimes());
	}
	
	@Test(expected=CashNotEnoughException.class)
	public void testConstructWithLessCash() throws ConstructOutOfTimesException,
			CashNotEnoughException, LandOwnerNotFoundException {
		Player player = new Player();
		land.setOwner(player);
		player.setCash(100);
		land.construct();
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.position.LandPosition#destroy()}.
	 * @throws LandOwnerNotFoundException 
	 * @throws CashNotEnoughException 
	 * @throws ConstructOutOfTimesException 
	 */
	@Test
	public void testDestroy() throws ConstructOutOfTimesException, CashNotEnoughException, LandOwnerNotFoundException {
		Player player = new Player();
		player.setCash(10000);
		land.setOwner(player);
		land.construct();
		land.construct();
		land.construct();
		assertTrue(land.hasOwner());
		assertEquals(3, land.getCurConstructTimes());
		int cash = land.destroy();
		assertEquals(1600, cash);
		assertFalse(land.hasOwner());
		assertEquals(-1, land.getCurConstructTimes());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.position.LandPosition#getTaxes()}.
	 * @throws LandOwnerNotFoundException 
	 * @throws CashNotEnoughException 
	 * @throws ConstructOutOfTimesException 
	 */
	@Test
	public void testGetTaxes() throws ConstructOutOfTimesException, CashNotEnoughException, LandOwnerNotFoundException {
		Player player = new Player();
		player.setCash(10000);
		land.setOwner(player);
		assertEquals(100, land.getTaxes());
		land.construct();
		assertEquals(200, land.getTaxes());
		land.construct();
		assertEquals(300, land.getTaxes());
		land.construct();
		assertEquals(400, land.getTaxes());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.position.LandPosition#getLandRankName()}.
	 * @throws GameLogicErrorException 
	 * @throws LandOwnerNotFoundException 
	 * @throws CashNotEnoughException 
	 * @throws ConstructOutOfTimesException 
	 */
	@Test
	public void testGetLandRankName() throws GameLogicErrorException, ConstructOutOfTimesException, CashNotEnoughException, LandOwnerNotFoundException{
		assertEquals(-1, land.getCurConstructTimes());
		assertEquals("空地", land.getLandRankName());
		Player player = new Player();
		player.setCash(10000);
		land.setOwner(player);
		assertEquals(0, land.getCurConstructTimes());
		assertEquals("空地", land.getLandRankName());
		land.construct();
		assertEquals(1, land.getCurConstructTimes());
		assertEquals("茅屋", land.getLandRankName());
		land.construct();
		assertEquals(2, land.getCurConstructTimes());
		assertEquals("洋房", land.getLandRankName());
		land.construct();
		assertEquals(3, land.getCurConstructTimes());
		assertEquals("摩天楼", land.getLandRankName());
	}
}
