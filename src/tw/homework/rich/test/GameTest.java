/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.exception.IllegalRoleCodeException;
import tw.homework.rich.game.in.YesOrNo;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.player.Role;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-19
 */
public class GameTest {

	static Game game;

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
		game = new Game();
		init();
	}

	public void init() throws Exception {
		InAndOutTestUtils.inputln("y");
		InAndOutTestUtils.inputln("123");
	}
	
	/**
	 * Test method for {@link tw.homework.rich.game.Game#getInput()}.
	 */
	@Test
	public void testGetInput() {
		assertEquals(YesOrNo.YES, Game.getInput());
		assertEquals("123", Game.getInput());
	}

	/**
	 * Test method for {@link tw.homework.rich.game.Game#judgeForYesOrNo(java.lang.String)}.
	 */
	@Test
	public void testJudgeForYesOrNo() {
		assertEquals(YesOrNo.YES, Game.judgeForYesOrNo("y"));
		assertEquals(YesOrNo.NO, Game.judgeForYesOrNo("n"));
		assertEquals(YesOrNo.ERROR, Game.judgeForYesOrNo("ie"));
	}
	
	/**
	 * Test method for {@link tw.homework.rich.game.Game#setInitInfo()}.
	 * @throws IllegalRoleCodeException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetInitInfo() throws IllegalRoleCodeException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		game.setInitInfo();
		assertEquals(10000, Game.INIT_CASH);
		List<Player> players = new ArrayList<Player>(3);
		for (int i = 1; i < 4; i++) {
			Player p = new Player();
			p.setRole(Role.getRoleByCode(i));
			p.setCash(Game.INIT_CASH);
			players.add(p);
		}
		assertEquals(players, Game.getPlayers());
		Field field = Game.class.getDeclaredField("currentPlayer");
		field.setAccessible(true);
		assertEquals(players.get(0), (Player)field.get(game));
		field.setAccessible(false);
	}

	/**
	 * Test method for {@link tw.homework.rich.game.Game#removePlayer(tw.homework.rich.game.player.Player)}.
	 * @throws IllegalRoleCodeException 
	 */
	@Test
	public void testRemovePlayer() throws IllegalRoleCodeException {
		game.setInitInfo();
		Game.removePlayer(Game.getPlayers().get(0));
		assertFalse(Game.getPlayers().get(0).isInGame());
	}

	@Test
	public void testJudgeIfGameOver() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method method = Game.class.getDeclaredMethod("judgeIfGameOver");
		method.setAccessible(true);
		game.setInitInfo();
		assertFalse((Boolean)method.invoke(game));
		Game.removePlayer(Game.getPlayers().get(0));
		assertFalse((Boolean)method.invoke(game));
		Game.removePlayer(Game.getPlayers().get(1));
		assertTrue((Boolean)method.invoke(game));
		method.setAccessible(false);
	}
	/**
	 * Test method for {@link tw.homework.rich.game.Game#over()}.
	 * @throws Exception 
	 */
	@Test
	public void testOver() throws Exception {
		testJudgeIfGameOver();
		InAndOutTestUtils.clearPipes();
		Game.over();
		String output = InAndOutTestUtils.join(Message.GAME_OVER, Message.WINNER_TIPS);
		output = output.concat(Game.getPlayers().get(2).getRole().getName());
		assertEquals(output, InAndOutTestUtils.getOutputTrim());
	}

}
