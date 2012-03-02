/**
 * 
 */
package tw.homework.rich.game.classes;

import java.util.ArrayList;
import java.util.List;

import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.exception.LandOwnerNotFoundException;
import tw.homework.rich.game.position.LandPosition;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-12
 */
public class Assets {
	
	private List<LandPosition> lands;

	public Assets() {
		lands = new ArrayList<LandPosition>();
	}

	public void add(LandPosition land) throws LandOwnerNotFoundException {
		if(land.getCurConstructTimes()!=0)
			throw new LandOwnerNotFoundException();
		lands.add(land);
	}

	public void lost(LandPosition land) {
		lands.remove(land);
	}
	
	public boolean hasLand(LandPosition land){
		return lands.contains(land);
	}

	public void truncate(){
		for(LandPosition land: lands){
			land.destroy();
		}
		lands = null;
	}
	
	public int[] getAssestsNum() throws GameLogicErrorException{
		int[] assestsNum = new int[4];
		for(LandPosition lp:lands){
			try {
				assestsNum[lp.getCurConstructTimes()]++;
			} catch (Exception e) {
				throw new GameLogicErrorException();
			}
		}
		return assestsNum;
	}
}
