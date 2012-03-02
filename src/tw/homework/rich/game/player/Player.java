package tw.homework.rich.game.player;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.MapCreater;
import tw.homework.rich.game.actions.ReachGiftPositionAction;
import tw.homework.rich.game.actions.ReachLandPositionAction;
import tw.homework.rich.game.actions.ReachMagicPositionAction;
import tw.homework.rich.game.actions.ReachMineralPositionAction;
import tw.homework.rich.game.actions.ReachPositionAction;
import tw.homework.rich.game.actions.ReachPrisonPositionAction;
import tw.homework.rich.game.actions.ReachPropPositionAction;
import tw.homework.rich.game.classes.Assets;
import tw.homework.rich.game.classes.Gift;
import tw.homework.rich.game.classes.Prop;
import tw.homework.rich.game.command.Command;
import tw.homework.rich.game.exception.CashNotEnoughException;
import tw.homework.rich.game.exception.ConstructOutOfTimesException;
import tw.homework.rich.game.exception.GameLogicErrorException;
import tw.homework.rich.game.exception.LandOwnerNotFoundException;
import tw.homework.rich.game.exception.LandOwnerNotYouException;
import tw.homework.rich.game.exception.PointsNotEnoughException;
import tw.homework.rich.game.exception.PropNotFoundException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.position.GiftPosition;
import tw.homework.rich.game.position.LandPosition;
import tw.homework.rich.game.position.MagicPosition;
import tw.homework.rich.game.position.MineralPosition;
import tw.homework.rich.game.position.Position;
import tw.homework.rich.game.position.PrisonPosition;
import tw.homework.rich.game.position.PropPosition;

public class Player {

	private Role role;
	private boolean isInGame;
	private int position;
	private int cash;
	private Assets assets;
	private int[] props;
	private int points;
	private Command lastCommand;
	private int stopTimes;
	private int fushenTimes;

	public Player() {
		this.position = 0;
		this.assets = new Assets();
		this.props = new int[3];
		this.points = 0;
		this.isInGame = true;
		this.stopTimes = 0;
		this.fushenTimes = 0;
	}

	public void buyLand(LandPosition land) throws CashNotEnoughException, GameLogicErrorException{
		if (this.cash >= land.getInitPrice()) {
			land.setOwner(this);
			this.cash -= land.getInitPrice();
			try {
				assets.add(land);
			} catch (LandOwnerNotFoundException e) {
				throw new GameLogicErrorException();
			}
			MessagePrint.output(land.getNumber() + Message.BUY_LAND_TIPS
					+ land.getInitPrice() + Message.MONEY_UNIT);
		} else
			throw new CashNotEnoughException();
	}

	public void updateLand(LandPosition land)
			throws ConstructOutOfTimesException, CashNotEnoughException,
			LandOwnerNotFoundException {
		int cost = land.construct();
		this.cash -= cost;
		try {
			MessagePrint.output(land.getNumber() + Message.UPDATE_LAND_TIPS1
					+ land.getLandRankName() + Message.UPDATE_LAND_TIPS2
					+ land.getInitPrice() + Message.MONEY_UNIT);
		} catch (GameLogicErrorException e) {
			MessagePrint.output(land.getNumber() + Message.UPDATE_LAND_TIPS1
					+ Message.ERROR_LAND + Message.UPDATE_LAND_TIPS2
					+ land.getInitPrice() + Message.MONEY_UNIT);
			MessagePrint.printError(e.getMessage());
			MessagePrint.output(Message.SMALL_ERROR_TIPS);
		}
	}

	public void sellLand(LandPosition land) throws LandOwnerNotYouException {
		if (land.getOwner().equals(this)) {
			int returnCash = land.destroy();
			this.cash += returnCash;
			assets.lost(land);
			MessagePrint.output(land.getNumber() + Message.SELL_LAND_TIPS
					+ returnCash + Message.MONEY_UNIT);
		} else
			throw new LandOwnerNotYouException();
	}

	public void payTaxes(LandPosition land) throws LandOwnerNotFoundException {
		int tax = land.getTaxes();
		MessagePrint.output(Message.PAY_TAXES_TIPS1
				+ land.getOwner().getRole().getName() + Message.PAY_TAXES_TIPS2
				+ tax + Message.MONEY_UNIT);
		if (this.cash < tax) {
			Game.removePlayer(this);
		} else {
			this.cash -= tax;
			land.getOwner().cash += tax;
			MessagePrint.output(this.getRole().getName()
					+ Message.PAY_TAXES_TIPS3 + tax + Message.MONEY_UNIT
					+ Message.SEPERATOR + land.getOwner().getRole().getName()
					+ Message.PAY_TAXES_TIPS4 + tax + Message.MONEY_UNIT);
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int[] getProps() {
		return props;
	}

	public void setProps(int[] props) {
		this.props = props;
	}

	public Command getLastCommand() {
		return lastCommand;
	}

	public void setLastCommand(Command lastCommand) {
		this.lastCommand = lastCommand;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public Assets getAssets() {
		return assets;
	}

	public int getStopTimes() {
		return stopTimes;
	}

	public void setStopTimes(int stopTimes) {
		this.stopTimes = stopTimes;
	}

	/**
	 * @param steps
	 */
	public void walkForward(int steps, Map map) {

		for (int i = 0; i < steps; i++) {
			try {
				Position tmpPosition = MapCreater.getNextNPosition(
						this.position, 1);
				changePosition(tmpPosition.getNumber(), map);
				if (tmpPosition.hasProp()) {
					Prop prop = tmpPosition.getProp();
					if (prop.equals(Prop.BLOCK)) {
						reachBlockAction(map);
						break;
					} else if (prop.equals(Prop.BOMB)) {
						reachBombAction(map);
						break;
					} else
						throw new GameLogicErrorException();
				}
			} catch (GameLogicErrorException e) {
				MessagePrint.printError(e.getMessage());
			}
		}

		Position endPosition = map.getPositions()[this.position];
		Game.showMap();
		ReachPositionAction action;
		if (endPosition instanceof LandPosition) {
			action = new ReachLandPositionAction();
			action.reachPositionAction(this, map);
		} else if (endPosition instanceof PropPosition) {
			action = new ReachPropPositionAction();
			action.reachPositionAction(this, map);
		} else if (endPosition instanceof GiftPosition) {
			action = new ReachGiftPositionAction();
			action.reachPositionAction(this, map);
		} else if (endPosition instanceof PrisonPosition) {
			action = new ReachPrisonPositionAction();
			action.reachPositionAction(this, map);
		} else if (endPosition instanceof MagicPosition) {
			action = new ReachMagicPositionAction();
			action.reachPositionAction(this, map);
		} else if (endPosition instanceof MineralPosition) {
			action = new ReachMineralPositionAction();
			action.reachPositionAction(this, map);
		}

	}

	/**
	 * @param map
	 */
	private void reachBombAction(Map map) {
		sendToHospital(map);
		map.getPositions()[this.position].removeProp();
		MessagePrint.output(Message.REACH_BOMB_TIPS);
	}

	/**
	 * @param map
	 */
	private void reachBlockAction(Map map) {
		map.getPositions()[this.position].removeProp();
		MessagePrint.output(Message.REACH_BLOCK_TIPS);
	}

	public void sendToHospital(Map map) {
		changePosition(MapCreater.getHospital_num(), map);
		stopTimes = Game.HOSPITAL_STOP_TIMES;
	}

	public boolean ifStopThisTime() {
		return stopTimes > 0;
	}

	public void stopThisTime() {
		if (ifStopThisTime()) {
			stopTimes--;
			MessagePrint.output(Message.STOP_TIPS);
		}
	}

	private void changePosition(int position, Map map) {
		map.getPositions()[this.position].removeRole(this.getRole());
		this.position = position;
		map.getPositions()[position].addRole(this.getRole());
	}

	public boolean isInGame() {
		return isInGame;
	}

	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}

	public void bankrupt() {
		this.isInGame = false;
		this.position = 0;
		this.cash = 0;
		this.assets.truncate();
		this.props = new int[3];
		this.points = 0;
		this.fushenTimes = 0;
		this.stopTimes = 0;
		MessagePrint.output(this.role + Message.PLAYER_BANKRUPT_TIPS);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Player) {
			Player player = (Player) obj;
			if (this.role == player.role)
				return true;
			return false;
		}
		return false;
	}

	public void buyProp(Prop[] props) throws PointsNotEnoughException {
		for (Prop prop : props) {
			int propIndex = prop.getNo() - 1;
			this.costPoints(prop.getPoints());
			this.props[propIndex]++;
		}
	}

	public void sellProp(Prop prop) throws PropNotFoundException {
		int propIndex = prop.getNo() - 1;
		int propNumber = this.props[propIndex];
		if (propNumber > 0) {
			this.addPoints(prop.getPoints());
			this.props[propIndex]--;
			MessagePrint.output(Message.SELL_PROP_TIPS1 + prop.getName()
					+ Message.SELL_PROP_TIPS2 + prop.getPoints()
					+ Message.POINT_UNIT);
		} else
			throw new PropNotFoundException();
	}

	public int getPropsNum() {
		int number = 0;
		for (int i : props) {
			number += i;
		}
		return number;
	}

	public int getPoints() {
		return this.points;
	}

	public void addPoints(int x) {
		this.points += x;
	}

	public void costPoints(int x) throws PointsNotEnoughException {
		if (this.points < x)
			throw new PointsNotEnoughException();
		else
			points -= x;
	}

	public boolean hasFushen() {
		return fushenTimes > 0;
	}

	private void addFushen(int times) {
		this.fushenTimes = times;
	}

	public void costFushenOneTime() {
		if (hasFushen())
			fushenTimes--;
	}

	public void addGift(Gift gift) {
		if (gift == Gift.MONEY) {
			this.cash += gift.getNumber();
		} else if (gift == Gift.POINTS) {
			this.addPoints(gift.getNumber());
		} else {
			addFushen(gift.getNumber());
		}
	}
}
