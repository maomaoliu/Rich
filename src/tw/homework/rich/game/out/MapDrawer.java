/**
 * 
 */
package tw.homework.rich.game.out;

import java.util.List;

import javax.swing.text.BadLocationException;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.exception.ConstructOutOfTimesException;
import tw.homework.rich.game.player.Player;
import tw.homework.rich.game.position.LandPosition;
import tw.homework.rich.game.position.Position;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-13
 */
public class MapDrawer {
	

	public static void drawMap(){
		List<Player> players = Game.getPlayers();
		clearMap();
		changeLine();
		Position[] positions = Game.getMap().getPositions();
		int front = 0, back = 69;
		for (int i = 0; i < 29; i++) {
			output(positions[front]);
			front++;
		}
		changeLine();
		for (int line = 1; line < 7; line++) {
			output(positions[back]);
			for (int i = 0; i < 27; i++)
				output(null);
			output(positions[front]);
			front++;
			back--;
			changeLine();
		}
		for (int i = 0; i < 29; i++) {
			output(positions[back]);
			back--;
		}
		changeLine();
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			output(null);
			output(null);
			print(p.getRole().getName(), getColor(p));
		}
		changeLine();
	}

	/**
	 * 
	 */
	private static void clearMap() {
		try {
			RichFrame.clearMap();
		} catch (BadLocationException e) {
			MessagePrint.printError(e.getMessage());
		}
	}

	/**
	 * 
	 */
	private static void changeLine() {
		print(System.getProperty("line.separator"), getColor(null));
	}

	/**
	 * @param position
	 * @return
	 * @throws ConstructOutOfTimesException
	 */
	private static void output(Position position){
		String mark = getMark(position);
		RichColor color = null;
		if(position==null){
			mark = " ";
			color = getColor(null);
		}else if (position instanceof LandPosition) {
			color = getColor(((LandPosition) position).getOwner());
		} else {
			color = getColor(null);
		}
		print(mark, color);
	}

	/**
	 * @param position
	 * @return
	 */
	private static String getMark(Position position) {
		if (position == null)
			return " ";
		if(position.hasProp()){
			return position.getProp().getMark();
		}
		if (!position.hasRole())
			return position.getMark();
		return position.getLatestRole().getAbb();
	}

	/**
	 * @param mark
	 */
	private static void print(String mark, RichColor color) {
		try {
			RichFrame.outMapUnit(" "+mark, color.getColor());
		} catch (BadLocationException e) {
			MessagePrint.printError(e.getMessage());
		}
	}

	/**
	 * @param owner
	 * @return 
	 */
	private static RichColor getColor(Player owner) {
		if (owner == null) {
			return RichColor.DEFAULT_COLOR;
		} else {
			return owner.getRole().getColor();
		}
	}

}
