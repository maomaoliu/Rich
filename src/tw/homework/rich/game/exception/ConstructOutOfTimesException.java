/**
 * 
 */
package tw.homework.rich.game.exception;

import tw.homework.rich.game.out.Message;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-13
 */
public class ConstructOutOfTimesException extends GameException {

	@Override
	public String getMessage() {
		return Message.CONSTRUCT_OUT_OF_TIMES_TIPS;
	}
	

}
