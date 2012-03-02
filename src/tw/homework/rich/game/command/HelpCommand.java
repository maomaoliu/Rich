/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class HelpCommand extends Command {

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player source, Map map) {
		Game.showMenu();
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.HELP_HELP;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.HELP_COMMOND;
	}

}
