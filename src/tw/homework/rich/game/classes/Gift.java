/**
 * 
 */
package tw.homework.rich.game.classes;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.exception.GiftNotFoundException;
import tw.homework.rich.game.out.Message;


/**
 * @author noam yacht2005@gmail.com Created at：2012-1-12
 */
public enum Gift {

	MONEY(Message.GIFT_MONEY_NAME, 1, 2000), POINTS(Message.GIFT_POINTS_NAME,
			2, 200), FUSHEN(Message.GIFT_FUSHEN_NAME, 3, Game.FUSHEN_TIMES);

	private String name;
	private int no;
	private int number;

	Gift(String name, int no, int number) {
		this.name = name;
		this.no = no;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public int getNo() {
		return no;
	}

	public int getNumber() {
		return number;
	}

	public static Gift getGiftByNo(int no) throws GiftNotFoundException {
		switch (no) {
		case 1:
			return Gift.MONEY;
		case 2:
			return Gift.POINTS;
		case 3:
			return Gift.FUSHEN;
		}
		throw new GiftNotFoundException();
	}
}
