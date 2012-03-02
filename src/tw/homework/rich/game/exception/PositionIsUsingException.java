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
public class PositionIsUsingException extends GameException {

	@Override
	public String getMessage() {
		return Message.POSITION_IS_USING_TIPS;
	}

}
