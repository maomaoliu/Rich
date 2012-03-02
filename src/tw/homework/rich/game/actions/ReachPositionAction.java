/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-18
 */
public interface ReachPositionAction {
	

	/**
	 * @param player
	 * @param map
	 * @return 
	 */
	public void reachPositionAction(Player player, Map map);

}
