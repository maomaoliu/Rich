/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.MineralPosition;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-18
 */
public class ReachMineralPositionAction implements ReachPositionAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tw.homework.rich.actions.ReachPositionAction#reachPositionAction(tw.homework
	 * .rich.player.Player, tw.homework.rich.Map)
	 */
	@Override
	public void reachPositionAction(Player player, Map map) {
		MineralPosition position = (MineralPosition) map.getPositions()[player
				.getPosition()];
		int points = position.getPoints();
		player.addPoints(points);
		MessagePrint.output(Message.REACH_MINERAL_TIPS + points);
	}

}
