/**
 * 
 */
package tw.homework.rich.game.actions;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.classes.Prop;
import tw.homework.rich.game.exception.GameException;
import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-18
 */
public class ReachPropPositionAction implements ReachPositionAction {

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
		while (true) {
			try {
				int points = player.getPoints();
				if (propNumGreaterThanMax(0, player.getPropsNum())) {
					MessagePrint.output(Message.PROP_REACH_MAX_TIPS1);
					break;
				} else if (!isPlayerCanBuyProps(points)) {
					MessagePrint.output(Message.POINTS_NOT_ENOUGH1 + points
							+ Message.POINTS_NOT_ENOUGH2);
					break;
				} else {
					String input;
					input = judgeInput(Game.getInput());
					if (input.equals(Message.PROP_EXIT_COMMAND)) {
						MessagePrint.output(Message.QUIT_PROP_TIPS);
						break;
					} else if (propNumGreaterThanMax(input.length(),
							player.getPropsNum())) {
						MessagePrint.output(Message.PROP_REACH_MAX_TIPS2);
					} else {
						int costPoints = 0;
						Prop[] props = new Prop[input.length()];
						for (int i = 0; i < input.length(); i++) {
							int propNum = Integer.parseInt(input.substring(i,
									i + 1));
							Prop prop = Prop.getPropByNo(propNum);
							costPoints += prop.getPoints();
							props[i] = Prop.getPropByNo(propNum);
						}
						if (costPoints > points) {
							MessagePrint.output(Message.POINTS_NOT_ENOUGH1
									+ points + Message.POINTS_NOT_ENOUGH3
									+ input);
						} else {
							player.buyProp(props);
							MessagePrint.output(Message.BUY_PROP_TIPS
									+ costPoints);
						}
					}
				}
			} catch (GameException e) {
				MessagePrint.output(e.getMessage());
			}
		}

	}

	/**
	 * @param length
	 * @param propsNum
	 * @return
	 */
	private boolean propNumGreaterThanMax(int length, int propsNum) {
		return (length + propsNum) > Game.MAX_PROP_NUMBER;
	}

	/**
	 * @param input
	 * @return
	 * @throws IllegalInputException
	 */
	private String judgeInput(Object input) throws IllegalInputException {
		try {
			String str = (String) input;
			if (str.equals(Message.QUIT_COMMOND))
				return str;
			for (int i = 0; i < str.length(); i++) {
				int propNum = Integer.parseInt(str.substring(i, i + 1));
				Prop.getPropByNo(propNum);
			}
			return str;
		} catch (Exception e) {
			throw new IllegalInputException();
		}
	}

	private boolean isPlayerCanBuyProps(int points) {
		if (points < minPointsOfProps())
			return false;
		return true;
	}

	private int minPointsOfProps() {
		int min = Prop.BLOCK.getPoints();
		min = min > Prop.BOMB.getPoints() ? Prop.BOMB.getPoints() : min;
		min = min > Prop.ROBOT.getPoints() ? Prop.ROBOT.getPoints() : min;
		return min;
	}

	private void outputWelcomeTips() {		
		MessagePrint.output(Message.REACH_PROP_TIPS);
	MessagePrint.output(Message.REACH_PROP_TIPS1);
		MessagePrint.output(Prop.BLOCK.getName() + "\t" + Prop.BLOCK.getNo()
				+ "\t" + Prop.BLOCK.getPoints());
		MessagePrint.output(Prop.BOMB.getName() + "\t" + Prop.BOMB.getNo()
				+ "\t" + Prop.BOMB.getPoints());
		MessagePrint.output(Prop.ROBOT.getName() + "\t" + Prop.ROBOT.getNo()
				+ "\t" + Prop.ROBOT.getPoints());
	}

}
