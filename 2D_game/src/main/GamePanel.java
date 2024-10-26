package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.OBJ_heart;

public class GamePanel extends JPanel implements Runnable {
    // Screen setting
    public final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize; // Window size
    final int screenHeight = maxScreenRow * tileSize;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    OBJ_heart player_heart = new OBJ_heart(this);
    public Collision_checker colis = new Collision_checker(this);

    // Initiate position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        // Instantiate a thread
        gameThread = new Thread(this);
        gameThread.start();
    }
 
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // Game loop
        while(gameThread != null) {

            long currentTime = System.nanoTime();

            // 1. Update
            update();

            // 2. Draw: Draw the updated information
            repaint();

            // 
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                } 

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }   

    public void update() {

        player.update();
        player_heart.update(player);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Change from Graphics class to Graphics2D
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        player_heart.draw(g2);

        // Save some memory
        g2.dispose();
    }
}
