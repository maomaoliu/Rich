/**
 * 
 */
package tw.homework.rich.game.classes;

import tw.homework.rich.game.exception.PropNotFoundException;
import tw.homework.rich.game.out.Message;


/**
 * @author noam yacht2005@gmail.com Created at：2012-1-12
 */
public enum Prop {

	BLOCK(Message.PROP_BLOCK_NAME, Message.PROP_BLOCK_MARK, 1, 50), BOMB(
			Message.PROP_BOMB_NAME, Message.PROP_BOMB_MARK, 2, 30), ROBOT(
			Message.PROP_ROBOT_NAME, null, 3, 50);

	private String name;
	private String mark;
	private int no;
	private int points;

	private Prop(String name, String mark, int no, int points) {
		this.name = name;
		this.mark = mark;
		this.no = no;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public String getMark() {
		return mark;
	}

	public int getNo() {
		return no;
	}

	public int getPoints() {
		return points;
	}
	
	public static Prop getPropByNo(int no) throws PropNotFoundException {
		switch(no){
		case 1: return Prop.BLOCK;
		case 2: return Prop.BOMB;
		case 3: return Prop.ROBOT;
		}
		throw new PropNotFoundException();
	}
}
