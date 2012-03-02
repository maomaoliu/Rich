/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.MapCreater;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.Position;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class RobotCommand extends Command {

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player player, Map map) {
		int index = player.getPosition();
		for(int i = 0; i < Command.MAX_PROP_CODE; i++){
			index++;
			Position position = MapCreater.getNextNPosition(index, 1);
			if(position.hasProp()){
				position.removeProp();
			}
		}
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.ROBOT_HELP;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.ROBOT_COMMOND;
	}

}
