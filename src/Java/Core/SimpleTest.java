package Java.Core;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleTest extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public static void main(String[] argv) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new SimpleTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Ted Mosby!");
				frame.setVisible(true);
			}
		});
	}


	public SimpleTest() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("red");
		
		buttonPanel = new JPanel();

		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);

		this.getContentPane().add(buttonPanel);

		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);

		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}
	
	private class ColorAction implements ActionListener {
		private Color backgroundColor;

		public ColorAction (Color c) {
			backgroundColor = c;
		}
		
		public void actionPerformed(ActionEvent event) {
			buttonPanel.setBackground(backgroundColor);			
		}
	}
}

