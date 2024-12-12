package main;

import javax.swing.JFrame;
import api.GroqAPIClient;

public class Main {
    public static void main(String[] args) {
//        String prompt = "Hello, how can I assist you today?";
//        String response = GroqAPIClient.sendMessage(prompt);
//
//        System.out.println("Groq API Response: " + response);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}