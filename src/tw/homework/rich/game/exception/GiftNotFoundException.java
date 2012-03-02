/**
 * 
 */
package tw.homework.rich.game.exception;

import tw.homework.rich.game.out.Message;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-13
 */
public class GiftNotFoundException extends GameException {

	@Override
	public String getMessage() {
		return Message.GIFT_NOT_FOUND_TIPS;
	}
}
