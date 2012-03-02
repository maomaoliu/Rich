/**
 * 
 */
package tw.homework.rich.game.player;

import tw.homework.rich.game.exception.IllegalRoleCodeException;
import tw.homework.rich.game.out.Message;
import tw.homework.rich.game.out.RichColor;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-15
 */
public enum Role {
	
	QIANFUREN(1, Message.QIANFUREN, Message.QIANFUREN_ABB, RichColor.RED),
	ATUBO(2, Message.ATUBO, Message.ATUBO_ABB, RichColor.GREEN),
	SUNXIAOMEI(3, Message.SUNXIAOMEI, Message.SUNXIAOMEI_ABB, RichColor.BLUE),
	JINBEIBEI(2, Message.JINBEIBEI, Message.JINBEIBEI_ABB, RichColor.YELLOW);
	
	private int code;
	private String name;
	private String abb;
	private RichColor color;

	private Role(int code, String name, String abb, RichColor color){
		this.code = code;
		this.name = name;
		this.abb = abb;
		this.color = color;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public RichColor getColor() {
		return color;
	}

	public String getAbb() {
		return abb;
	}
	
	public static Role getRoleByCode(int code) throws IllegalRoleCodeException{
		switch(code){
		case 1: return Role.QIANFUREN;
		case 2: return Role.ATUBO;
		case 3: return Role.SUNXIAOMEI;
		case 4: return Role.JINBEIBEI;
		}
		throw new IllegalRoleCodeException();
	}
}
