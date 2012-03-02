/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.exception.GameException;
import tw.homework.rich.game.player.Player;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-11
 */
public abstract class Command {
	
	protected int code;
	public static final int MAX_PROP_CODE = 10;
	public static final int MIN_PROP_CODE = -10;
	
	public Command(){};
	
	public abstract void executeCommand(Player player, Map map) throws GameException;
	
	public abstract String getHelp();
	
	public abstract String getCommondString();
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
