/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.classes.Gift;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-18
 */
public class ReachGiftPositionAction implements ReachPositionAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tw.homework.rich.actions.ReachPositionAction#reachPositionAction(tw.homework
	 * .rich.player.Player, tw.homework.rich.Map)
	 */
	@Override
	public void reachPositionAction(Player player, Map map) {
		outputWelcomeTips();
		try {
			String input = (String) Game.getInput();
			Gift gift = Gift.getGiftByNo(Integer.parseInt(input));
			player.addGift(gift);
			MessagePrint.output(Message.QUIT_GIFT_TIPS2 + gift.getName()
					+ gift.getNumber() + Message.QUIT_GIFT_TIPS);
		} catch (Exception e) {
			MessagePrint.output(Message.QUIT_GIFT_TIPS1
					+ Message.QUIT_GIFT_TIPS);
		}
	}

	/**
	 * 
	 */
	private void outputWelcomeTips() {
		MessagePrint.output(Message.REACH_GIFT_TIPS);
		MessagePrint.output(Message.REACH_GIFT_TIPS1);
		MessagePrint.output(Gift.MONEY.getName() + "\t" + Gift.MONEY.getNo());
		MessagePrint.output(Gift.POINTS.getName() + "\t" + Gift.POINTS.getNo());
		MessagePrint.output(Gift.FUSHEN.getName() + "\t" + Gift.FUSHEN.getNo());
	}

}
