/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-18
 */
public class ReachMagicPositionAction implements ReachPositionAction {

	@Override
	public void reachPositionAction(Player player, Map map) {
		MessagePrint.output(Message.CONSTRUCTING_TIPS);
	}

}
