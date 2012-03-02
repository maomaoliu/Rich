/**
 * 
 */
package tw.homework.rich.game;

import tw.homework.rich.game.position.GiftPosition;
import tw.homework.rich.game.position.HospitalPosition;
import tw.homework.rich.game.position.LandPosition;
import tw.homework.rich.game.position.MagicPosition;
import tw.homework.rich.game.position.MineralPosition;
import tw.homework.rich.game.position.Position;
import tw.homework.rich.game.position.PrisonPosition;
import tw.homework.rich.game.position.PropPosition;
import tw.homework.rich.game.position.StartPosition;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-13
 */
public class MapCreater {
	
	private static Position[] positions;
	private static int number;
	private static int hospital_num;
	private static int prison_num;

	/**
	 * @return
	 */
	public static Map createMap() {
		number = 0;
		positions = new Position[70];

		addPosition(new StartPosition());
		for (int i = 0; i < 13; i++) {
			addPosition(new LandPosition(200, 3));
		}
		hospital_num = number;
		addPosition(new HospitalPosition());
		for (int i = 0; i < 13; i++) {
			addPosition(new LandPosition(200, 3));
		}
		addPosition(new PropPosition());
		for (int i = 0; i < 6; i++) {
			addPosition(new LandPosition(500, 3));
		}
		addPosition(new GiftPosition());
		for (int i = 0; i < 13; i++) {
			addPosition(new LandPosition(300, 3));
		}
		prison_num = number;
		addPosition(new PrisonPosition());
		for (int i = 0; i < 13; i++) {
			addPosition(new LandPosition(300, 3));
		}
		addPosition(new MagicPosition());
		for (int i = 0; i < 6; i++) {
			addPosition(new MineralPosition(Game.MINERAL_POINTS[i]));
		}
		Map map = new Map();
		map.setPositions(positions);
		return map;
	}

	private static void addPosition(Position position) {
		position.setNumber(number);
		positions[number] = position;
		number++;
	}

	public static Position getNextNPosition(int start, int n) {
		return positions[(start + n) % positions.length];
	}

	public static int getHospital_num() {
		return hospital_num;
	}
	
	public static int getPrison_num() {
		return prison_num;
	}

}
