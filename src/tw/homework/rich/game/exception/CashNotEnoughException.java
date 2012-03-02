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
public class CashNotEnoughException extends GameException{

	@Override
	public String getMessage() {
		return Message.CASH_NOT_ENOUGH_TIPS;
	}

}
