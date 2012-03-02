/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.classes.Prop;
import tw.homework.rich.game.exception.PropNotFoundException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class SellToolCommand extends Command {

	public SellToolCommand() {
		super();
	}

	/**
	 * @param code
	 */
	public SellToolCommand(int code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player player, Map map) throws PropNotFoundException {
		Prop prop = Prop.getPropByNo(code);
		player.sellProp(prop);
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.SELLTOOL_HELP;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.SELLTOOL_COMMOND;
	}

}
