/**
 * 
 */
package tw.homework.rich.game.out;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.in.InputHijack;

/**
 * @author noam yacht2005@gmail.com Created at：2012-2-15
 */
public class RichFrame extends JFrame {

	private JPanel contentPane;
	private JTextField inputField;
	private static JTextArea tipsArea;
	private static JTextPane mapPane;
	private static boolean isReady = false;
	private static JLabel inputlabel;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public RichFrame() throws IOException {
		this.addWindowListener(new   WindowAdapter() 
		{ 
		public   void   windowClosing(WindowEvent   e) 
		{ 
		       if(e.getID()==WindowEvent.WINDOW_CLOSING){
		   		MessagePrint.output(Message.SHOW_HOW_TO_QUIT);
		       }
		} 
		}); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		InputHijack.start();
		setTitle("大富翁 v1.0");
		setBounds(100, 100, 542, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mapPane = new JTextPane();
		mapPane.setFont(new Font("Consolas", Font.PLAIN, 14));
		mapPane.setEditable(false);
		mapPane.setBounds(6, 6, 515, 183);
		contentPane.add(mapPane);

		JPanel panel = new JPanel();
		panel.setBounds(6, 191, 515, 142);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(new Rectangle(0, 165, 0, 150));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		tipsArea = new JTextArea();
		tipsArea.setFont(new Font("宋体", Font.PLAIN, 12));
		tipsArea.setTabSize(2);
		tipsArea.setEditable(false);
		tipsArea.setRows(8);
		
		JScrollPane scrollPane = new JScrollPane(tipsArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		inputlabel = new JLabel("命令输入：");
		inputlabel.setFont(new Font("宋体", Font.PLAIN, 12));
		panel_2.add(inputlabel, BorderLayout.WEST);

		inputField = new JTextField();
		inputField.setFont(new Font("Consolas", Font.PLAIN, 12));
		inputField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						InputHijack.inputln(inputField.getText());
						inputField.setText(null);
					} catch (IOException ex) {
						MessagePrint.printError(ex.getMessage());
					}
				}
			}
		});
		panel_2.add(inputField, BorderLayout.CENTER);
		inputField.setColumns(10);
		inputField.grabFocus();

		isReady = true;;
	}

	public static void addMessage(String msg) {
		tipsArea.append(msg);
		tipsArea.selectAll();
		tipsArea.setCaretPosition(tipsArea.getDocument().getLength());
	}

	public static void outMapUnit(String mark, Color color)
			throws BadLocationException {
		Document document = mapPane.getDocument();
		MutableAttributeSet attributeSet = new SimpleAttributeSet();
		StyleConstants.setForeground(attributeSet, color);
		StyleConstants.setFontFamily(attributeSet, "Consolas");
		document.insertString(document.getLength(), mark, attributeSet);
	}

	/**
	 * @throws BadLocationException
	 * 
	 */
	public static void clearMap() throws BadLocationException {
		Document document = mapPane.getDocument();
		document.remove(0, document.getLength());
	}
	
	public static boolean isReady(){
		return isReady;
	}

	/**
	 * @param string
	 */
	public static void changeLable(String string) {
		inputlabel.setText(string);
	}
}
