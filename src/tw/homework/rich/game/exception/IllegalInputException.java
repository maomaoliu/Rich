/**
 * 
 */
package tw.homework.rich.game.exception;

import tw.homework.rich.game.out.Message;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-18
 */
public class IllegalInputException extends GameException {
	@Override
	public String getMessage() {
		return Message.ILLEGAL_INPUT_TIPS;
	}
	
}
