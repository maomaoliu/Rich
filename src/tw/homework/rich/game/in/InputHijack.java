/**
 * 
 */
package tw.homework.rich.game.in;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-15
 */
public class InputHijack {

	private static PipedOutputStream redirectedInput_in;
	private static PipedInputStream redirectedInput_out;
	public static InputStream standardInputStream;
	
	public static void start() throws IOException {
		standardInputStream = System.in;
		redirectedInput_in = new PipedOutputStream();
		redirectedInput_out = new PipedInputStream(redirectedInput_in);
		System.setIn(redirectedInput_out);
	}
	
	public static void stop() throws IOException {
		redirectedInput_in.close();
		System.in.close();
		System.setIn(standardInputStream);
	}
	
	public static void input(String string) throws IOException {
		redirectedInput_in.write(string.getBytes());
		redirectedInput_in.flush();
	}
	
	public static void inputln(String string) throws IOException {
		input(string + System.getProperty("line.separator"));
	}
}
