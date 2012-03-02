/**
 * 
 */
package tw.homework.rich.game;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import tw.homework.rich.game.command.Command;
import tw.homework.rich.game.command.CommandCreater;
import tw.homework.rich.game.command.QuitCommand;
import tw.homework.rich.game.command.RollCommand;
import tw.homework.rich.game.exception.GameException;
import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.in.InputHijack;
import tw.homework.rich.game.in.YesOrNo;
import tw.homework.rich.game.out.MapDrawer;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;
import tw.homework.rich.game.player.Player;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-11
 */
public class Game {

	public static final int DEFAULT_INIT_CASH = 10000;
	public static final int MIN_INIT_CASH = 1000;
	public static final int MAX_INIT_CASH = 50000;
	public static final int MIN_PLAYERS_NUM = 2;
	public static final int MAX_PLAYERS_NUM = 4;
	public static final long SLEEP_SECONDS = 2;
	public static final int HOSPITAL_STOP_TIMES = 3;
	public static final int PRISON_STOP_TIMES = 2;
	public static final int FUSHEN_TIMES = 5;
	public static final int MAX_PROP_NUMBER = 10;
	public static final int[] MINERAL_POINTS = { 20, 80, 100, 40, 80, 60 };

	public static int INIT_CASH;

	private static Map map;
	private static Scanner in;
	private static Command[] commands;
	private static List<Player> players;
	private Player currentPlayer;
	private static boolean isGameOver;

	public Game() {
		in = new Scanner(System.in);
		init();
	}

	private void init() {
		isGameOver = false;
		map = MapCreater.createMap();
		commands = CommandCreater.createAvailableCommands();
	}

	public void begin() {
		setInitInfo();
		showMenu();
		showMap();
		playGame();
	}

	/**
	 * 
	 */
	public void setInitInfo() {
		setInitCash();
		setPlayers();
		switchPlayer();
	}

	private void setInitCash() {
		InitCashSetting ics = new InitCashSetting();
		while (true) {
			try {
				ics.setInitCash();
				break;
			} catch (IllegalInputException e) {
				MessagePrint.output(e.getMessage());
			}
		}
	}

	private void playGame() {
		Command command = null;
		Player player = getPlayer();
		MessagePrint.changePlayer(player);
		do {
			try {
				try {
					command = (Command) getInput();
				} catch (Exception e) {
					throw new IllegalInputException();
				}
				command.executeCommand(player, map);
				showMap();
				if (command instanceof RollCommand) {
					if(player.hasFushen()){
						player.costFushenOneTime();
					}
					player = switchPlayer();
					MessagePrint.changePlayer(player);
					while (player.ifStopThisTime()) {
						player.stopThisTime();
						player = switchPlayer();
						MessagePrint.changePlayer(player);
					}
				}
			} catch (GameException e) {
				MessagePrint.output(e.getMessage());
				continue;
			}
		} while (!isGameOver);
	}

	public static Object getInput() {
		String input = in.nextLine().toLowerCase();
		Object object = handleInput(input);
		if (object instanceof QuitCommand) {
			((QuitCommand) object).executeCommand(null, null);
		}
		return object;
	}

	private static Object handleInput(String input) {
		Command command = getCommand(input);
		if (command != null) {
			return command;
		} else {
			YesOrNo yesOrNo = judgeForYesOrNo(input);
			if (yesOrNo != YesOrNo.ERROR) {
				return yesOrNo;
			} else
				return input;
		}
	}

	public static YesOrNo judgeForYesOrNo(String yesOrNo) {
		if (yesOrNo.equals(Message.YES)) {
			return YesOrNo.YES;
		} else if (yesOrNo.equals(Message.NO)) {
			return YesOrNo.NO;
		} else
			return YesOrNo.ERROR;
	}

	public static void showMap() {
		MapDrawer.drawMap();
	}

	private void setPlayers() {
		InitPlayersSetting ips = new InitPlayersSetting();
		while (true) {
			try {
				players = ips.initPlayers();
				break;
			} catch (GameException e) {
				MessagePrint.output(e.getMessage());
			}
		}
	}

	private Player getPlayer() {
		return this.currentPlayer;
	}

	private Player switchPlayer() {
		if (currentPlayer == null)
			return currentPlayer = players.get(0);
		else {
			int index = players.indexOf(currentPlayer);
			while (true) {
				index = (index + 1) % players.size();
				if (players.get(index).isInGame())
					return currentPlayer = players.get(index);
			}
		}
	}

	public static void removePlayer(Player player) {
		player.bankrupt();
		if (judgeIfGameOver())
			over();
	}

	private static Command getCommand(String string) {
		return CommandCreater.createCommand(string);
	}

	public static void showMenu() {
		for (Command command : commands) {
			MessagePrint.output(command.getHelp());
		}

	}

	/* 以下判断游戏结束 */

	public static void over() {
		isGameOver = true;
		Player winner = getWinner();
		MessagePrint.output(Message.GAME_OVER);
		MessagePrint.output(Message.WINNER_TIPS + winner.getRole().getName());
	}

	private static boolean judgeIfGameOver() {
		if (getSurvivalsNumber() == 1)
			return true;
		return false;
	}

	private static int getSurvivalsNumber() {
		int survivals = 0;
		for (Player player : players) {
			if (player.isInGame())
				survivals++;
		}
		return survivals;
	}

	private static Player getWinner() {
		for (Player player : players) {
			if (player.isInGame())
				return player;
		}
		return null;
	}
	
	public static void quit() throws IOException{
		InputHijack.stop();
		System.exit(0);
	}
	
	public static List<Player> getPlayers(){
		return players;
	}

	public static Map getMap() {
		return map;
	}

	public static void setMap(Map map) {
		Game.map = map;
	}
}
