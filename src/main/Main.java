package main;

import javax.swing.JFrame;

public class Main {
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D game");
//        window.setUndecorated(true); for full screen

        GamePanel gamePanel;
        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.Game_setup();
        gamePanel.startGameThread();
    }
}