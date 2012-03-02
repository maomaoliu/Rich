/**
 * 
 */
package tw.homework.rich.game.out;

import java.awt.Color;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-14
 */
public enum RichColor {
	
	RED("red", Color.RED),
	BLUE("blue", Color.BLUE),
	YELLOW("yellow", Color.YELLOW),
	GREEN("green", Color.GREEN),
	WHITE("white", Color.WHITE),
	BLACK("black", Color.BLACK);
	
	private String colorDesc;
	private Color color;
	
	public static final RichColor DEFAULT_COLOR = RichColor.BLACK;
	
	private RichColor(String colorDesc, Color color){
		this.colorDesc = colorDesc;
		this.color = color;
	}

	public String getColorDesc() {
		return colorDesc;
	}

	public Color getColor() {
		return color;
	}

}
