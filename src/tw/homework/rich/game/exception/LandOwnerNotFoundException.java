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
public class LandOwnerNotFoundException extends GameException {

	@Override
	public String getMessage() {
		return Message.LAND_OWNER_NOT_FOUND_TIPS;
	}
	

}
