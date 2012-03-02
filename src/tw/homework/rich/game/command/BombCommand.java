/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.MapCreater;
import tw.homework.rich.game.classes.Prop;
import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.exception.PositionIsUsingException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.Position;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class BombCommand extends Command {

	public BombCommand() {
		super();
	}

	/**
	 * @param code
	 */
	public BombCommand(int code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player player, Map map)
			throws PositionIsUsingException, IllegalInputException {
		if (!isInScope(code)) {
			throw new IllegalInputException();
		}
		int index = player.getPosition();
		Position position = MapCreater.getNextNPosition(index, code);
		position.addProp(Prop.BOMB);
	}

	/**
	 * @param code
	 * @return
	 */
	private boolean isInScope(int code) {
		if (code > Command.MAX_PROP_CODE || code < Command.MIN_PROP_CODE)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.BOMB_HELP;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.BOMB_COMMOND;
	}

}
