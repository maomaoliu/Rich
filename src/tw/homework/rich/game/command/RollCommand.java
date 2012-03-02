/**
 * 
 */
package tw.homework.rich.game.command;

import java.util.Random;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-12
 */
public class RollCommand extends Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.homework.rich.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player player, Map map) {
		int steps = new Random().nextInt(6) + 1;
		MessagePrint.output(Message.ROLL_RESULT+steps+Message.ROLL_UNIT);
		player.walkForward(steps, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.homework.rich.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.ROLL_HELP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.ROLL_COMMOND;
	}

}
