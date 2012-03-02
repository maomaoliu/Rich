/**
 * 
 */
package tw.homework.rich.game.position;

import tw.homework.rich.game.exception.CashNotEnoughException;
import tw.homework.rich.game.exception.ConstructOutOfTimesException;
import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.exception.LandOwnerNotFoundException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.player.Player;

/**
 * 土地
 * @author noam yacht2005@gmail.com Created at：2012-1-13
 */
public class LandPosition extends Position {

	private int initPrice;
	private Player owner;
	/**
	 * no owner: -1.</br>
	 * has owner, not constructed: 0.</br>
	 * constructed one time: 1.</br>
	 * constructed two times: 2.</br>
	 * constructed three times: 3.</br>
	 */
	private int curConstructTimes;
	private int maxConstructTimes;

	public LandPosition(int initPrice, int maxConstructTimes) {
		this.initPrice = initPrice;
		this.maxConstructTimes = maxConstructTimes;
		this.curConstructTimes = -1;
		this.owner = null;
	}

	
	
	@Override
	public String getMark() {
		String mark = "";
		switch(this.curConstructTimes){
		case -1:
			mark = Message.NO_OWNER_LAND;
			break;
		case 0:
			mark = Message.EMPTY_LAND;
			break;
		case 1:
			mark = Message.RANK_1_LAND;
			break;
		case 2:
			mark = Message.RANK_2_LAND;
			break;
		case 3:
			mark = Message.RANK_3_LAND;
			break;
		default:
			mark = Message.ERROR_LAND;
		}
		return mark;
	}

	public boolean canConstruct(){
		return curConstructTimes<maxConstructTimes;
	}

	/**
	 * 建设土地
	 * @return 建设所需花费的现金数
	 * @throws ConstructOutOfTimesException
	 * @throws CashNotEnoughException
	 * @throws LandOwnerNotFoundException
	 */
	public int construct() throws ConstructOutOfTimesException,
			CashNotEnoughException, LandOwnerNotFoundException {
		if (!hasOwner())
			throw new LandOwnerNotFoundException();
		if (!canConstruct())
			throw new ConstructOutOfTimesException();
		int cost = this.getCostNextConstruction();
		if (owner.getCash() < cost)
			throw new CashNotEnoughException();
		curConstructTimes++;
		return cost;
	}
	
	/**
	 * 重置土地，所有者清空，价值清空
	 * @return 返还的现金数，为土地价值的2倍
	 */
	public int destroy() {
		int returnCash = calculateValue() * 2;
		this.curConstructTimes = -1;
		this.owner = null;
		return returnCash;
	}
	
	/**
	 * 征税
	 * @return 土地价值的一半
	 * @throws LandOwnerNotFoundException
	 */
	public int getTaxes() throws LandOwnerNotFoundException {
		if (!hasOwner())
			throw new LandOwnerNotFoundException();
		return calculateValue() / 2;
	}
	
	public boolean hasOwner(){
		return owner!=null;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		this.curConstructTimes++;
	}

	/**
	 * 计算土地价值，即本块土地经多次建设所花费的现金数
	 * @return 土地价值现金数
	 */
	private int calculateValue() {
		return initPrice + (initPrice * curConstructTimes);
	}

	/**
	 * 计算下次建设所需花费的现金数
	 * @return 下次建设所需花费的现金数
	 * @throws ConstructOutOfTimesException
	 */
	private int getCostNextConstruction() throws ConstructOutOfTimesException {
		if (!canConstruct())
			throw new ConstructOutOfTimesException();
		return initPrice;
	}

	public int getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(int initPrice) {
		this.initPrice = initPrice;
	}

	public Player getOwner() {
		return owner;
	}

	public int getCurConstructTimes() {
		return curConstructTimes;
	}

	public int getMaxConstructTimes() {
		return maxConstructTimes;
	}

	public String getLandRankName() throws GameLogicErrorException{
		String name = "";
		switch(this.curConstructTimes){
		case -1:
			name = Message.EMPTY_LAND_NAME;
			break;
		case 0:
			name = Message.EMPTY_LAND_NAME;
			break;
		case 1:
			name = Message.RANK_1_LAND_NAME;
			break;
		case 2:
			name = Message.RANK_2_LAND_NAME;
			break;
		case 3:
			name = Message.RANK_3_LAND_NAME;
			break;
		default:
			throw new GameLogicErrorException();
		}
		return name;
	}
}
