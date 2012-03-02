/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.InitCashSetting;
import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.in.YesOrNo;
import tw.homework.rich.game.out.Message;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-11
 */
public class InitCashSettingTest {

	static InitCashSetting ics;

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
		InAndOutTestUtils.setUp();
		ics = new InitCashSetting();
		new Game();
	}
	/**
	 * Test method for
	 * {@link tw.homework.rich.game.InitCashSetting#setInitCash()}.
	 * @throws Exception 
	 */
	@Test
	public void testSetInitCashWithY() throws Exception {
		
		InAndOutTestUtils.inputln("y");
		ics.setInitCash();
		assertEquals(Message.IF_NOT_CHANGE_INIT_CASH,
				InAndOutTestUtils.getOutputTrim());
		testHandleInitCachWithInputWithYes();
	}

	@Test
	public void testSetInitCashWithN() throws Exception {
		
		InAndOutTestUtils.inputln("n");
		InAndOutTestUtils.inputln("30000");
		ics.setInitCash();
		assertEquals(InAndOutTestUtils.join(Message.IF_NOT_CHANGE_INIT_CASH,
				Message.ASK_TO_SET_INIT_CASH),
				InAndOutTestUtils.getOutputTrim());
		testHandleInitCachWithInputWithNo();
	}

	@Test(expected = IllegalInputException.class)
	public void testSetInitCashWithOther() throws IOException,
			IllegalInputException {
		
		InAndOutTestUtils.inputln("rt");
		ics.setInitCash();
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.InitCashSetting#handleInitCachWithInput(tw.homework.rich.game.in.YesOrNo)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public void testHandleInitCachWithInputWithYes() throws IOException {
		
		ics.handleInitCachWithInput(YesOrNo.YES);
		assertEquals(Game.DEFAULT_INIT_CASH, Game.INIT_CASH);
	}

	@Test
	public void testHandleInitCachWithInputWithNo() throws Exception {
		
		InAndOutTestUtils.inputln("30000");
		ics.handleInitCachWithInput(YesOrNo.NO);
		assertEquals(Message.ASK_TO_SET_INIT_CASH,
				InAndOutTestUtils.getOutputTrim());
		assertEquals(30000, Game.INIT_CASH);
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.InitCashSetting#askToSetInitCash()}.
	 * @throws Exception 
	 */
	@Test
	public void testAskToSetInitCashLegal() throws Exception {
		
		InAndOutTestUtils.inputln("30000");
		int result = ics.askToSetInitCash();
		assertEquals(Message.ASK_TO_SET_INIT_CASH,
				InAndOutTestUtils.getOutputTrim());
		assertEquals(30000, result);
	}

	@Test(expected = IllegalInputException.class)
	public void testAskToSetInitCashIllegalNotInScope()
			throws IllegalInputException, Exception {
		
		InAndOutTestUtils.inputln("900");
		ics.askToSetInitCash();
		assertEquals(Message.ASK_TO_SET_INIT_CASH,
				InAndOutTestUtils.getOutputTrim());
	}

	@Test(expected = IllegalInputException.class)
	public void testAskToSetInitCashIllegalWroneInput()
			throws IllegalInputException, Exception {
		
		InAndOutTestUtils.inputln("9oo");
		ics.askToSetInitCash();
		assertEquals(Message.ASK_TO_SET_INIT_CASH,
				InAndOutTestUtils.getOutputTrim());
	}

	/**
	 * Test method for
	 * {@link tw.homework.rich.game.InitCashSetting#isCashInScope(int)}.
	 */
	@Test
	public void testIsCashInScope() {
		assertTrue(ics.isCashInScope(1000));
		assertTrue(ics.isCashInScope(50000));
		assertTrue(ics.isCashInScope(30000));
		assertFalse(ics.isCashInScope(999));
		assertFalse(ics.isCashInScope(50001));
	}

}
