/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-18
 */
public class ReachPrisonPositionAction implements ReachPositionAction {

	/* (non-Javadoc)
	 * @see tw.homework.rich.actions.ReachPositionAction#reachPositionAction(tw.homework.rich.player.Player, tw.homework.rich.Map)
	 */
	@Override
	public void reachPositionAction(Player player, Map map) {
		player.setStopTimes(Game.PRISON_STOP_TIMES);
		MessagePrint.output(Message.REACH_PRISON_TIPS);
	}

}
