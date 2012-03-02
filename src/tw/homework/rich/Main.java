/**
 * 
 */
package tw.homework.rich;

import java.awt.EventQueue;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.out.RichFrame;


/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-1-11
 */
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RichFrame frame = new RichFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while(!RichFrame.isReady()){
			Thread.sleep(1000);
		}
		Game game = new Game();
		game.begin();
	}

}
