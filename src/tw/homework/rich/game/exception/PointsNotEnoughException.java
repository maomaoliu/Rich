/**
 * 
 */
package tw.homework.rich.game.exception;

import tw.homework.rich.game.out.Message;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-12
 */
public class PointsNotEnoughException extends GameException {

	@Override
	public String getMessage() {
		return Message.POINTS_NOT_ENOUGH;
	}

}
