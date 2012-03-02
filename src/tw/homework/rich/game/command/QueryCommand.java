/**
 * 
 */
package tw.homework.rich.game.command;

import tw.homework.rich.game.Map;
import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-12
 */
public class QueryCommand extends Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.homework.rich.command.Command#executeCommand()
	 */
	@Override
	public void executeCommand(Player player, Map map) throws GameLogicErrorException {
		MessagePrint.output(Message.QUERY_INFO1);
		MessagePrint.output(Message.QUERY_INFO2 + player.getCash()
				+ Message.MONEY_UNIT);
		MessagePrint.output(Message.QUERY_INFO3 + player.getPoints()
				+ Message.POINT_UNIT);
		int lands[] = player.getAssets().getAssestsNum();
		MessagePrint.output(Message.QUERY_INFO4 + Message.EMPTY_LAND_NAME
				+ lands[0] + Message.BUILDING_UNIT + Message.SEPERATOR
				+ Message.RANK_1_LAND_NAME + lands[1] + Message.BUILDING_UNIT
				+ Message.SEPERATOR + Message.RANK_2_LAND_NAME + lands[2]
				+ Message.BUILDING_UNIT + Message.SEPERATOR
				+ Message.RANK_3_LAND_NAME + lands[3] + Message.BUILDING_UNIT);
		int props[] = player.getProps();
		MessagePrint.output(Message.QUERY_INFO1 + Message.PROP_BLOCK_NAME
				+ props[0] + Message.PROP_UNIT + Message.SEPERATOR
				+ Message.PROP_BOMB_NAME + props[0] + Message.PROP_UNIT
				+ Message.SEPERATOR + Message.PROP_ROBOT_NAME + props[0]
				+ Message.PROP_UNIT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.homework.rich.command.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return Message.QUERY_HELP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.homework.rich.command.Command#getCommondString()
	 */
	@Override
	public String getCommondString() {
		return Message.QUERY_COMMOND;
	}

}
