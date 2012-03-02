/**
 * 
 */
package tw.homework.rich.game.position;

import tw.homework.rich.game.out.Message;

/**
 * 矿地
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-13
 */
public class MineralPosition extends Position {
	
	int points;
	
	public MineralPosition(int points) {
		super();
		this.points = points;
	}

	/* (non-Javadoc)
	 * @see tw.homework.rich.position.Position#getMark()
	 */
	@Override
	public String getMark() {
		return Message.MINERAL_POSITION;
	}

	public int getPoints() {
		return points;
	}

}
