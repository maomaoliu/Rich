/**
 * 
 */
package tw.homework.rich.game.exception;

import tw.homework.rich.game.out.Message;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-17
 */
public class IllegalCommandException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275817980043485145L;

	@Override
	public String getMessage() {
		return Message.ILLEGAL_INPUT_TIPS;
	}

}
