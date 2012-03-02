/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.InitPlayersSetting;
import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.exception.IllegalRoleCodeException;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.player.Role;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-12
 */
public class InitPlayersSettingTest {

	static InitPlayersSetting ips;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void afterClass() throws Exception {
		InAndOutTestUtils.tearDown();
	}
	
	@Before
	public void setUp() throws Exception{
		InAndOutTestUtils.clearPipes();
	}


	public static void init() throws Exception{
		ips = new InitPlayersSetting();
		InAndOutTestUtils.setUp();
		new Game();
	}
	/**
	 * Test method for
	 * {@link tw.homework.rich.game.InitPlayersSetting#initPlayers()}.
	 * 
	 * @throws IllegalRoleCodeException
	 * @throws IllegalInputException
	 * @throws IOException
	 */
	@Test
	public void testInitPlayersWithLegel() throws IllegalRoleCodeException,
			IllegalInputException, IOException {
		
		InAndOutTestUtils.inputln("123");
		List<Player> players = new ArrayList<Player>(3);
		for (int i = 1; i < 4; i++) {
			Player p = new Player();
			p.setRole(Role.getRoleByCode(i));
			p.setCash(Game.INIT_CASH);
			players.add(p);
		}
		assertEquals(players, ips.initPlayers());
	}

	@Test(expected = IllegalInputException.class)
	public void testInitPlayersWith15() throws IOException,
			IllegalInputException {
		
		InAndOutTestUtils.inputln("15");
		ips.initPlayers();
	}

	@Test(expected = IllegalInputException.class)
	public void testInitPlayersWith1() throws IOException,
			IllegalInputException {
		
		InAndOutTestUtils.inputln("1");
		ips.initPlayers();
	}

	@Test(expected = IllegalInputException.class)
	public void testInitPlayersWith1231() throws IOException,
			IllegalInputException {
		
		InAndOutTestUtils.inputln("1231");
		ips.initPlayers();
	}

	@Test(expected = IllegalInputException.class)
	public void testInitPlayersWithxx() throws IOException,
			IllegalInputException {
		
		InAndOutTestUtils.inputln("xx1");
		ips.initPlayers();
	}
}
