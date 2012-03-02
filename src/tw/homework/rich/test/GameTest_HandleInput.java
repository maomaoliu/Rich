/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.command.BlockCommand;
import tw.homework.rich.game.command.BombCommand;
import tw.homework.rich.game.command.HelpCommand;
import tw.homework.rich.game.command.QueryCommand;
import tw.homework.rich.game.command.QuitCommand;
import tw.homework.rich.game.command.RobotCommand;
import tw.homework.rich.game.command.RollCommand;
import tw.homework.rich.game.command.SellCommand;
import tw.homework.rich.game.command.SellToolCommand;
import tw.homework.rich.game.in.YesOrNo;


/**
 * @author noam yacht2005@gmail.com Created at：2012-2-10
 */
public class GameTest_HandleInput {

	Method method;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		method = Game.class.getDeclaredMethod("handleInput", String.class);
		method.setAccessible(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		method.setAccessible(false);
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testHandleInputWithY() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		assertEquals(YesOrNo.YES, method.invoke(null, "y"));
		assertEquals("yes", method.invoke(null, "yes"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testHandleInputWithN() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		assertEquals(YesOrNo.NO, method.invoke(null, "n"));
		assertEquals("no", method.invoke(null, "no"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testHandleInputWithRoll() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "roll") instanceof RollCommand);
		assertEquals("roll x", (String) method.invoke(null, "roll x"));
		assertEquals("roll 3", (String) method.invoke(null, "roll 3"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testHandleInputWithBlockN() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "block 4") instanceof BlockCommand);
		assertEquals("block x", (String) method.invoke(null, "block x"));
		assertEquals("block", (String) method.invoke(null, "block"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testHandleInputWithBombN() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "bomb 4") instanceof BombCommand);
		assertEquals("bomb x", (String) method.invoke(null, "bomb x"));
		assertEquals("bomb", (String) method.invoke(null, "bomb"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 */
	@Test
	public void testHandleInputWithRobot() throws IllegalArgumentException,
		IllegalAccessException, InvocationTargetException {
	assertTrue(method.invoke(null, "robot") instanceof RobotCommand);
	assertEquals("robot x", (String) method.invoke(null, "robot x"));
	assertEquals("robot 3", (String) method.invoke(null, "robot 3"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 */
	@Test
	public void testHandleInputWithSellX() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "sell 4") instanceof SellCommand);
		assertEquals("sell x", (String) method.invoke(null, "sell x"));
		assertEquals("sell", (String) method.invoke(null, "sell"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 */
	@Test
	public void testHandleInputWithSellToolX() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "selltool 4") instanceof SellToolCommand);
		assertEquals("selltool x", (String) method.invoke(null, "selltool x"));
		assertEquals("selltool", (String) method.invoke(null, "selltool"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testHandleInputWithQuery() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "query") instanceof QueryCommand);
		assertEquals("query x", (String) method.invoke(null, "query x"));
		assertEquals("query 3", (String) method.invoke(null, "query 3"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testHandleInputWithHelp() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "help") instanceof HelpCommand);
		assertEquals("help x", (String) method.invoke(null, "help x"));
		assertEquals("help 3", (String) method.invoke(null, "help 3"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testHandleInputWithQuit() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertTrue(method.invoke(null, "quit") instanceof QuitCommand);
		assertEquals("quit x", (String) method.invoke(null, "quit x"));
		assertEquals("quit 3", (String) method.invoke(null, "quit 3"));
	}

	/**
	 * Test method for {@link tw.homework.rich.Game#handleInput()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testHandleInputWithOthers() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		assertEquals("452", (String) method.invoke(null, "452"));
	}

}
