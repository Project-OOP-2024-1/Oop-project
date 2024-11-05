package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		//Let the window properly close when user clicks the close ("x") button
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); //can't resize this window
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
	 	window.pack();//causes this window to be sized to fir the preferred size and layouts of its subcomponents
		
		window.setLocationRelativeTo(null); //not specify the location of the window --> it will be displayed at the center of the screen
		window.setVisible(true); //can see this window
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
	}

}
