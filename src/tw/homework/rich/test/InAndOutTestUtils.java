/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.assertEquals;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import javax.swing.JTextArea;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.out.RichFrame;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-10
 */
public class InAndOutTestUtils {

	private static PipedOutputStream redirectedInput_in;
	private static PipedInputStream redirectedInput_out;

	private static PipedOutputStream redirectedOutput_out;
	private static PipedInputStream redirectedOutput_in;

	private static RichFrame richFrame; 
	private static Field field;
	
	public static InputStream standardInputStream;
	public static PrintStream standardOutputStream;
	
	@BeforeClass
	public static void before() throws Exception {
		setUp();
	}

	@AfterClass
	public static void after() throws Exception {
		tearDown();
	}

	@Test
	public void test() throws Exception{

		clearPipes();
		
		Scanner in = new Scanner(System.in);
		inputln("xx");
		assertEquals("xx", in.nextLine());
		inputln("yy");
		assertEquals("yy", in.nextLine());

		System.out.println("aa");
		assertEquals("aa", getOutputFromSystemOut());

		((JTextArea)field.get(richFrame)).setText("123");
		assertEquals("123", getOutputFromFrame());

		clearPipes();
		assertEquals("", getOutputFromFrame());
		
		assertEquals("a\r\nb", join("a","b"));
	}

	/**
	 * <p>
	 * Sets up the test environment.
	 * </p>
	 * 
	 * @throws IOException
	 *             to JUnit
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws InterruptedException 
	 */
	public static void setUp() throws Exception {

		standardOutputStream = System.out;
		redirectedOutput_in = new PipedInputStream();
		redirectedOutput_out = new PipedOutputStream(redirectedOutput_in);
		System.setOut(new PrintStream(redirectedOutput_out));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					richFrame = new RichFrame();
					richFrame.setVisible(false);
					field = RichFrame.class.getDeclaredField("tipsArea");
					field.setAccessible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		while(!RichFrame.isReady() || field==null || !field.isAccessible()){
			Thread.sleep(1000);
		}

		standardInputStream = System.in;
		redirectedInput_in = new PipedOutputStream();
		redirectedInput_out = new PipedInputStream(redirectedInput_in);
		System.setIn(redirectedInput_out);
	}

	public static void input(String string) throws IOException {
		InAndOutTestUtils.redirectedInput_in.write(string.getBytes());
		InAndOutTestUtils.redirectedInput_in.flush();
	}

	public static void inputln(String string) throws IOException {
		input(string + System.getProperty("line.separator"));
	}

	public static String getOutputFromSystemOut() throws IOException {
		byte[] b = new byte[1000];
		int len = 0;
		len = redirectedOutput_in.read(b);
		return new String(b, 0, len).trim();
	}
	
	public static String getOutputFromFrame() throws Exception{
		String s = ((JTextArea) field.get(richFrame)).getText();
		((JTextArea) field.get(richFrame)).setText("");
		return s;
	}

	public static String getOutputTrim() throws Exception {
		return getOutputFromFrame().trim();
	}

	public static String join(String s1, String s2) {
		return new StringBuilder(s1)
				.append(System.getProperty("line.separator")).append(s2)
				.toString();
	}
	
	public static void clearPipes() throws Exception{
		redirectedInput_in.flush();
		redirectedOutput_out.write(1);
		getOutputFromSystemOut();
		((JTextArea)field.get(richFrame)).setText("");
	}

	/**
	 * <p>
	 * Tears down the test environment.
	 * </p>
	 * 
	 * @throws IOException
	 *             to JUnit
	 */
	public static void tearDown() throws IOException {
		redirectedOutput_out.close();
		System.out.close();
		System.setIn(standardInputStream);
		System.setOut(standardOutputStream);
		field.setAccessible(false);
		richFrame = null;
		redirectedInput_in.close();
		System.in.close();
	}
}
