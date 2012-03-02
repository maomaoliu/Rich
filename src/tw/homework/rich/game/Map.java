/**
 * 
 */
package tw.homework.rich.game;

import tw.homework.rich.game.position.Position;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class Map {
	
	
	private Position[] positions;

	public Position[] getPositions() {
		return positions;
	}

	public void setPositions(Position[] positions) {
		this.positions = positions;
	}
	
	public Position getPosition(int positionNumber){
		return positions[positionNumber];
	}
	
	
	
}
