/**
 * 
 */
package tw.homework.rich.game.position;

import tw.homework.rich.game.out.Message;

/**
 * 起点
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-13
 */
public class StartPosition extends Position {

	/* (non-Javadoc)
	 * @see tw.homework.rich.position.Position#getMark()
	 */
	@Override
	public String getMark() {
		return Message.START_POSITION;
	}

	
}
