/**
 * 
 */
package tw.homework.rich.game.out;

import tw.homework.rich.game.player.Player;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-17
 */
public class MessagePrint {

	public static void output(String message) {
		RichFrame.addMessage(message+System.getProperty("line.separator"));
	}

	public static void askForBuy(String message, int money) {
		output(message + money + Message.MONEY_UNIT
				+ Message.ASK_TIPS);
	}

	/**
	 * @param message
	 */
	public static void printError(String message) {
		output(Message.ERROR + ": " + message);
	}

	/**
	 * @param player
	 */
	public static void changePlayer(Player player) {
		output(Message.CURRENT_PLAYER + player.getRole().getName());
		RichFrame.changeLable(player.getRole().getName()+">");
	}
}
