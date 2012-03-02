/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.MapCreater;
import tw.homework.rich.game.classes.Assets;
import tw.homework.rich.game.classes.Gift;
import tw.homework.rich.game.classes.Prop;
import tw.homework.rich.game.exception.CashNotEnoughException;
import tw.homework.rich.game.exception.ConstructOutOfTimesException;
import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.exception.LandOwnerNotFoundException;
import tw.homework.rich.game.exception.LandOwnerNotYouException;
import tw.homework.rich.game.exception.PointsNotEnoughException;
import tw.homework.rich.game.exception.PropNotFoundException;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.player.Role;
import tw.homework.rich.game.position.LandPosition;

public class PlayerTest {

	Player player;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InAndOutTestUtils.setUp();
		new Game();
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
		player = new Player();
		player.setCash(10000);
		player.setRole(Role.ATUBO);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#buyLand(tw.homework.rich.game.position.LandPosition)}
	 * .
	 * 
	 * @throws CashNotEnoughException
	 * @throws GameLogicErrorException
	 * @throws LandOwnerNotFoundException
	 */
	@Test
	public void testBuyLand() throws CashNotEnoughException,
			GameLogicErrorException, LandOwnerNotFoundException {
		player.setCash(201);
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
		assertEquals(player, land.getOwner());
		assertEquals(1, player.getCash());
		Assets assets = player.getAssets();
		assertEquals(1, assets.getAssestsNum()[0]);
		assertTrue(assets.hasLand(land));
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#buyLand(tw.homework.rich.game.position.LandPosition)}
	 * .
	 */
	@Test(expected = CashNotEnoughException.class)
	public void testBuyLandWithLessCash() throws CashNotEnoughException,
			GameLogicErrorException {
		player.setCash(199);
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#updateLand(tw.homework.rich.game.position.LandPosition)}
	 * .
	 * 
	 * @throws LandOwnerNotFoundException
	 * @throws CashNotEnoughException
	 * @throws ConstructOutOfTimesException
	 * @throws GameLogicErrorException
	 */
	@Test
	public void testUpdateLand() throws ConstructOutOfTimesException,
			CashNotEnoughException, LandOwnerNotFoundException,
			GameLogicErrorException {
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
		player.updateLand(land);
		assertEquals(9600, player.getCash());
		Assets assets = player.getAssets();
		assertEquals(0, assets.getAssestsNum()[0]);
		assertEquals(1, assets.getAssestsNum()[1]);
		assertTrue(assets.hasLand(land));
		player.updateLand(land);
		assertEquals(9400, player.getCash());
		assertEquals(0, assets.getAssestsNum()[0]);
		assertEquals(0, assets.getAssestsNum()[1]);
		assertEquals(1, assets.getAssestsNum()[2]);
		assertEquals(0, assets.getAssestsNum()[3]);
		assertTrue(assets.hasLand(land));
		player.updateLand(land);
		assertEquals(9200, player.getCash());
		assertEquals(0, assets.getAssestsNum()[0]);
		assertEquals(0, assets.getAssestsNum()[1]);
		assertEquals(0, assets.getAssestsNum()[2]);
		assertEquals(1, assets.getAssestsNum()[3]);
		assertTrue(assets.hasLand(land));
	}

	@Test(expected = ConstructOutOfTimesException.class)
	public void testUpdateLandWithMoreUpdate()
			throws ConstructOutOfTimesException, CashNotEnoughException,
			LandOwnerNotFoundException, GameLogicErrorException {
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
		player.updateLand(land);
		player.updateLand(land);
		player.updateLand(land);
		player.updateLand(land);
	}

	@Test(expected = CashNotEnoughException.class)
	public void testUpdateLandWithLessCash()
			throws ConstructOutOfTimesException, CashNotEnoughException,
			LandOwnerNotFoundException, GameLogicErrorException {
		player.setCash(399);
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
		player.updateLand(land);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#sellLand(tw.homework.rich.game.position.LandPosition)}
	 * .
	 * 
	 * @throws LandOwnerNotYouException
	 * @throws GameLogicErrorException
	 * @throws CashNotEnoughException
	 * @throws LandOwnerNotFoundException
	 * @throws ConstructOutOfTimesException
	 */
	@Test
	public void testSellLand() throws LandOwnerNotYouException,
			CashNotEnoughException, GameLogicErrorException,
			ConstructOutOfTimesException, LandOwnerNotFoundException {
		player.setCash(800);
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
		player.sellLand(land);
		assertEquals(1000, player.getCash());
		Assets assets = player.getAssets();
		assertEquals(0, assets.getAssestsNum()[0]);
		assertFalse(assets.hasLand(land));
		player.buyLand(land);
		player.updateLand(land);
		player.updateLand(land);
		player.updateLand(land);
		player.sellLand(land);
		assertEquals(1800, player.getCash());
		assertEquals(0, assets.getAssestsNum()[0]);
		assertEquals(0, assets.getAssestsNum()[1]);
		assertEquals(0, assets.getAssestsNum()[2]);
		assertEquals(0, assets.getAssestsNum()[3]);
		assertFalse(assets.hasLand(land));
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#payTaxes(tw.homework.rich.game.position.LandPosition)}
	 * .
	 * 
	 * @throws GameLogicErrorException
	 * @throws CashNotEnoughException
	 * @throws LandOwnerNotFoundException
	 * @throws ConstructOutOfTimesException
	 */
	@Test
	public void testPayTaxes() throws CashNotEnoughException,
			GameLogicErrorException, LandOwnerNotFoundException,
			ConstructOutOfTimesException {
		Player owner = new Player();
		owner.setCash(10000);
		owner.setRole(Role.SUNXIAOMEI);
		LandPosition land = new LandPosition(200, 3);
		owner.buyLand(land);
		player.payTaxes(land);
		assertEquals(9900, player.getCash());
		assertEquals(9900, owner.getCash());
		owner.updateLand(land);
		assertEquals(9700, owner.getCash());
		player.payTaxes(land);
		assertEquals(9700, player.getCash());
		assertEquals(9900, owner.getCash());
	}

	@Test
	public void testPayTaxesWithLessCash() throws Exception {
		GameTest gt = new GameTest();
		gt.setUp();
		gt.testSetInitInfo();
		Player owner = Game.getPlayers().get(0);
		LandPosition land = new LandPosition(200, 3);
		owner.buyLand(land);
		assertEquals(9800, owner.getCash());
		player = Game.getPlayers().get(1);
		assertTrue(player.isInGame());
		player.setCash(90);
		player.payTaxes(land);
		assertEquals(9800, owner.getCash());
		assertFalse(player.isInGame());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#sendToHospital(tw.homework.rich.game.Map)}
	 * .
	 */
	@Test
	public void testSendToHospital() {
		Map map = MapCreater.createMap();
		int hospNum = MapCreater.getHospital_num();
		assertFalse(map.getPosition(hospNum).hasRole());
		player.sendToHospital(map);
		assertEquals(hospNum, player.getPosition());
		assertEquals(3, player.getStopTimes());
		assertTrue(map.getPosition(hospNum).hasRole());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#ifStopThisTime()}.
	 */
	@Test
	public void testIfStopThisTime() {
		player.setStopTimes(3);
		assertTrue(player.ifStopThisTime());
		player.setStopTimes(0);
		assertFalse(player.ifStopThisTime());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#stopThisTime()}.
	 */
	@Test
	public void testStopThisTime() {
		player.setStopTimes(3);
		player.stopThisTime();
		assertEquals(2, player.getStopTimes());
		player.stopThisTime();
		assertEquals(1, player.getStopTimes());
		player.stopThisTime();
		assertEquals(0, player.getStopTimes());
		player.stopThisTime();
		assertEquals(0, player.getStopTimes());
	}

	/**
	 * Test method for {@link tw.homework.rich.game.player.Player#bankrupt()}.
	 * 
	 * @throws GameLogicErrorException
	 * @throws CashNotEnoughException
	 * @throws LandOwnerNotFoundException
	 * @throws ConstructOutOfTimesException
	 * @throws IllegalAccessException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	@Test
	public void testBankrupt() throws CashNotEnoughException,
			GameLogicErrorException, ConstructOutOfTimesException,
			LandOwnerNotFoundException, SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		LandPosition land = new LandPosition(200, 3);
		player.buyLand(land);
		player.updateLand(land);
		testAddPoints();
		player.bankrupt();
		assertFalse(player.isInGame());
		assertFalse(land.hasOwner());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#buyProp(tw.homework.rich.game.classes.Prop[])}
	 * .
	 * 
	 * @throws PointsNotEnoughException
	 */
	@Test
	public void testBuyProp() throws PointsNotEnoughException {
		player.addPoints(400);
		Prop[] props = { Prop.BOMB, Prop.ROBOT, Prop.ROBOT };
		player.buyProp(props);
		assertEquals(270, player.getPoints());
		assertEquals(0, player.getProps()[0]);
		assertEquals(1, player.getProps()[1]);
		assertEquals(2, player.getProps()[2]);
	}

	@Test(expected = PointsNotEnoughException.class)
	public void testBuyPropWithLessPoints() throws PointsNotEnoughException {
		Prop[] props = { Prop.BOMB, Prop.ROBOT, Prop.ROBOT };
		player.buyProp(props);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#sellProp(tw.homework.rich.game.classes.Prop)}
	 * .
	 * 
	 * @throws PointsNotEnoughException
	 * @throws PropNotFoundException
	 */
	@Test
	public void testSellProp() throws PointsNotEnoughException,
			PropNotFoundException {
		testBuyProp();
		player.sellProp(Prop.ROBOT);
		assertEquals(320, player.getPoints());
		assertEquals(0, player.getProps()[0]);
		assertEquals(1, player.getProps()[1]);
		assertEquals(1, player.getProps()[2]);
	}

	@Test(expected = PropNotFoundException.class)
	public void testSellPropWithNoProp() throws PointsNotEnoughException,
			PropNotFoundException {
		testBuyProp();
		player.sellProp(Prop.BLOCK);
	}

	/**
	 * Test method for {@link tw.homework.rich.game.player.Player#getPropsNum()}
	 * .
	 * @throws PointsNotEnoughException 
	 * @throws PropNotFoundException 
	 */
	@Test
	public void testGetPropsNum() throws PointsNotEnoughException, PropNotFoundException {
		assertEquals(0, player.getPropsNum());
		testBuyProp();
		assertEquals(3, player.getPropsNum());
		player.sellProp(Prop.ROBOT);
		assertEquals(2, player.getPropsNum());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#addPoints(int)}.
	 */
	@Test
	public void testAddPoints() {
		player.addPoints(200);
		assertEquals(200, player.getPoints());
		player.addPoints(200);
		assertEquals(400, player.getPoints());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#costPoints(int)}.
	 * @throws PointsNotEnoughException 
	 */
	@Test
	public void testCostPoints() throws PointsNotEnoughException {
		player.addPoints(200);
		player.costPoints(30);
		assertEquals(170, player.getPoints());
	}
	
	@Test(expected=PointsNotEnoughException.class)
	public void testCostPointsWithLessPoints() throws PointsNotEnoughException {
		player.addPoints(30);
		player.costPoints(50);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#costFushenOneTime()}.
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testCostFushenOneTime() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = player.getClass().getDeclaredField("fushenTimes");
		field.setAccessible(true);
		field.set(player, 2);
		player.costFushenOneTime();
		assertEquals(1, field.get(player));
		player.costFushenOneTime();
		assertEquals(0, field.get(player));
		player.costFushenOneTime();
		assertEquals(0, field.get(player));
		field.setAccessible(false);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.player.Player#addGift(tw.homework.rich.game.classes.Gift)}
	 * .
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testAddGift() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		player.addGift(Gift.MONEY);
		assertEquals(12000, player.getCash());
		player.addGift(Gift.POINTS);
		assertEquals(200, player.getPoints());
		player.addGift(Gift.FUSHEN);
		Field field = player.getClass().getDeclaredField("fushenTimes");
		field.setAccessible(true);
		assertEquals(5, field.get(player));
		field.setAccessible(false);
	}

}
