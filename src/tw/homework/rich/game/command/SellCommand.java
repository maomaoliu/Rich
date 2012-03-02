/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.exception.LandOwnerNotYouException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.LandPosition;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class SellCommand extends Command {

	public SellCommand() {
		super();
	}

	/**
	 * @param code
	 */
	public SellCommand(int code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player player, Map map) throws LandOwnerNotYouException {
		LandPosition land;
		try {
			land = (LandPosition) map.getPositions()[code];
		} catch (ClassCastException e) {
			throw new LandOwnerNotYouException();
		}
		if(player.getAssets().hasLand(land)){
			player.sellLand(land);
		} else throw new LandOwnerNotYouException();
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.SELL_HELP;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.SELL_COMMOND;
	}

}
