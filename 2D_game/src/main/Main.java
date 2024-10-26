package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();//create a new empty window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the window
        window.setResizable(false);//set the stable window's dimension
        window.setTitle("2D game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();//match the window's dimension

        window.setLocationRelativeTo(null);// Position of the window is in the center of the screen
        window.setVisible(true);//Display the window on the screen

        gamePanel.startGameThread();
    }
}