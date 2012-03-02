/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.MapCreater;
import tw.homework.rich.game.exception.CashNotEnoughException;
import tw.homework.rich.game.exception.ConstructOutOfTimesException;
import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.exception.LandOwnerNotFoundException;
import tw.homework.rich.game.in.YesOrNo;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.LandPosition;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-18
 */
public class ReachLandPositionAction implements ReachPositionAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tw.homework.rich.actions.ReachPositionAction#reachPositionAction(tw.homework
	 * .rich.player.Player, tw.homework.rich.Map)
	 */
	@Override
	public void reachPositionAction(Player player, Map map) {
		LandPosition position = (LandPosition) map.getPositions()[player
				.getPosition()];
		if (!position.hasOwner()) {
			while (true) {
				MessagePrint.askForBuy(Message.ASK_FOR_BUY_LAND,
						+position.getInitPrice());
				try {
					YesOrNo yOrN = (YesOrNo) Game.getInput();
					if (yOrN.equals(YesOrNo.YES)) {
						player.buyLand(position);
					} 
					break;
				} catch (CashNotEnoughException e) {
					MessagePrint.output(e.getMessage());
				} catch (ClassCastException e) {
					MessagePrint.output(Message.ILLEGAL_INPUT_TIPS);
				} catch (GameLogicErrorException e) {
					MessagePrint.printError(e.getMessage());
				}
			}
		} else if (position.getOwner().equals(player)) {
			if (position.canConstruct()) {
				while (true) {
					MessagePrint.askForBuy(Message.ASK_FOR_UPDATE_LAND,
							+position.getInitPrice());
					try {
						YesOrNo yOrN = (YesOrNo) Game.getInput();
						if (yOrN.equals(YesOrNo.YES)) {
							player.updateLand(position);
						}
						break;
					} catch (ConstructOutOfTimesException e) {
						MessagePrint.printError(e.getMessage());
					} catch (CashNotEnoughException e) {
						MessagePrint.output(e.getMessage());
					} catch (LandOwnerNotFoundException e) {
						MessagePrint.printError(e.getMessage());
					}
				}
			}

		} else {
			if (isOwnerInHospitalOrPrison(position)) {
				MessagePrint.output(Message.EXEMPTTAX+Message.OWNER_IS_MISSING);
				return;
			}
			if(isFushenWithYou(player)){
				MessagePrint.output(Message.EXEMPTTAX+Message.HAS_FUSHEN);
				return;
			}
			try {
				player.payTaxes(position);
			} catch (LandOwnerNotFoundException e) {
				MessagePrint.printError(e.getMessage());
			}
		}
	}

	/**
	 * @param player
	 * @return
	 */
	private boolean isFushenWithYou(Player player) {
		if(player.hasFushen())
			return true;
		return false;
	}

	/**
	 * @param position
	 * @return
	 */
	private boolean isOwnerInHospitalOrPrison(LandPosition position) {
		int ownerPositionNum = position.getOwner().getPosition();
		if (ownerPositionNum == MapCreater.getPrison_num())
			return true;
		if(ownerPositionNum == MapCreater.getHospital_num()
				&& position.getOwner().ifStopThisTime())
			return true;
		return false;
	}

}
