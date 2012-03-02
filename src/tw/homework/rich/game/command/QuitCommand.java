/**
 * 
 */
package tw.homework.rich.game.command;

import java.io.IOException;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class QuitCommand extends Command {

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player source, Map map) {
		MessagePrint.output(Message.QUIT_GAME_TIPS);
		try {
			Thread.sleep(Game.SLEEP_SECONDS*1000);
			Game.quit();
		} catch (InterruptedException e) {
			MessagePrint.printError(e.getMessage());
		} catch (IOException e) {
			MessagePrint.printError(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.QUIT_HELP;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.QUIT_COMMOND;
	}

}
