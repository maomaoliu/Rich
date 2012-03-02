/**
 * 
 */
package tw.homework.rich.game;

import tw.homework.rich.game.exception.IllegalInputException;
import tw.homework.rich.game.in.YesOrNo;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.MessagePrint;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-11
 */
public class InitCashSetting {

	/**
	 * @throws IllegalCashException
	 */
	public void setInitCash() throws IllegalInputException {
		MessagePrint.output(Message.IF_NOT_CHANGE_INIT_CASH);
		Object choise = Game.getInput();
		if (choise instanceof YesOrNo) {
			handleInitCachWithInput((YesOrNo) choise);
		} else
			throw new IllegalInputException();
	}

	/**
	 * 
	 * @param input
	 */
	public void handleInitCachWithInput(YesOrNo input) {
		if (input == YesOrNo.YES) {
			Game.INIT_CASH = Game.DEFAULT_INIT_CASH;
		} else {
			while (true) {
				try {
					Game.INIT_CASH = askToSetInitCash();
					break;
				} catch (IllegalInputException e) {
					MessagePrint.output(new IllegalInputException()
					.getMessage());
				}
			}
		}
	}

	/**
	 * @return
	 * @throws IllegalInputException
	 */
	public int askToSetInitCash() throws IllegalInputException {
		MessagePrint.output(Message.ASK_TO_SET_INIT_CASH);
		int initCash;
		try {
			initCash = Integer.parseInt((String) Game.getInput());
		} catch (NumberFormatException e) {
			throw new IllegalInputException();
		} catch (ClassCastException e){
			throw new IllegalInputException();
		}
		if (isCashInScope(initCash)) {
			return initCash;
		} else
			throw new IllegalInputException();
	}

	/**
	 * @param initCash
	 * @return
	 */
	public boolean isCashInScope(int initCash) {
		if (initCash >= Game.MIN_INIT_CASH && initCash <= Game.MAX_INIT_CASH)
			return true;
		return false;
	}
}
