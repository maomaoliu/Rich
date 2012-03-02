/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.out.Message;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-12
 */
public class CommandCreater {

	/**
	 * @return
	 * 
	 */
	public static Command[] createAvailableCommands() {
		Command[] commands = new Command[9];
		commands[0] = new QuitCommand();
		commands[1] = new RobotCommand();
		commands[2] = new RollCommand();
		commands[3] = new BlockCommand();
		commands[4] = new BombCommand();
		commands[5] = new QueryCommand();
		commands[6] = new HelpCommand();
		commands[7] = new SellCommand();
		commands[8] = new SellToolCommand();
		return commands;

	}

	/**
	 * @param nextLine
	 * @return
	 */
	public static Command createCommand(String nextLine) {
		String[] command = nextLine.split(" ");
		if (command.length > 2 || command.length < 1)
			return null;
		String commandString = command[0].toLowerCase();
		int code = 0;
		try {
			if (command.length == 2)
				code = Integer.parseInt(command[1]);
		} catch (NumberFormatException e) {
			return null;
		}
		if (commandString.equals(Message.QUIT_COMMOND) && command.length == 1) {
			return new QuitCommand();
		} else if (commandString.equals(Message.ROBOT_COMMOND)
				&& command.length == 1) {
			return new RobotCommand();
		} else if (commandString.equals(Message.ROLL_COMMOND)
				&& command.length == 1) {
			return new RollCommand();
		} else if (commandString.equals(Message.BLOCK_COMMOND)
				&& command.length == 2) {
			return new BlockCommand(code);
		} else if (commandString.equals(Message.BOMB_COMMOND)
				&& command.length == 2) {
			return new BombCommand(code);
		} else if (commandString.equals(Message.QUERY_COMMOND)
				&& command.length == 1) {
			return new QueryCommand();
		} else if (commandString.equals(Message.HELP_COMMOND)
				&& command.length == 1) {
			return new HelpCommand();
		} else if (commandString.equals(Message.SELL_COMMOND)
				&& command.length == 2) {
			return new SellCommand(code);
		} else if (commandString.equals(Message.SELLTOOL_COMMOND)
				&& command.length == 2) {
			return new SellToolCommand(code);
		}
		return null;
	}

}
