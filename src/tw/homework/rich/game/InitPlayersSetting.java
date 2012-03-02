/**
 * 
 */
package tw.homework.rich.game;

import java.util.ArrayList;
import java.util.List;

import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.exception.IllegalRoleCodeException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.player.Role;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-11
 */
public class InitPlayersSetting {

	public List<Player> initPlayers() throws IllegalInputException {
		MessagePrint.output(Message.TO_SET_PLAYERS);
		String playersNos = (String) Game.getInput();
		if (isPlayersNumInScope(playersNos.length())) {
			List<Player> players = new ArrayList<Player>(playersNos.length());
			try {
				for (int i = 0; i < playersNos.length(); i++) {
					Role role;
					role = Role.getRoleByCode(Integer.parseInt(playersNos
							.substring(i, i + 1)));
					Player player = new Player();
					player.setRole(role);
					player.setCash(Game.INIT_CASH);
					if (players.contains(player))
						throw new IllegalInputException();
					else
						players.add(player);
				}
				return players;
			} catch (NumberFormatException e) {
				throw new IllegalInputException();
			} catch (IllegalRoleCodeException e) {
				throw new IllegalInputException();
			}
		} else
			throw new IllegalInputException();
	}

	/**
	 * @param length
	 * @return
	 */
	private boolean isPlayersNumInScope(int playerNum) {
		if (playerNum >= Game.MIN_PLAYERS_NUM
				&& playerNum <= Game.MAX_PLAYERS_NUM)
			return true;
		return false;
	}

}
