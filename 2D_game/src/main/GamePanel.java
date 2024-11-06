package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

//Runnable : Game can run in a separate thread. This allows the game to
//continuously update its logic and redraw without being interrupted by other activities
//in the application

public class GamePanel extends JPanel implements Runnable {
    // Screen setting
    public final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; // Window size
    public final int screenHeight = maxScreenRow * tileSize;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    int FPS = 60;// Frame per second

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);

    public EventHandler eHandler = new EventHandler(this);



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);// Recognizes pressed keys
        this.setFocusable(true);// This is required for the panel to receive keyboard events
    }

    public void startGameThread() {
        // Instantiate a thread
        gameThread = new Thread(this);
        gameThread.start();
    }
 
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;// time interval between Frames
        double nextDrawTime = System.nanoTime() + drawInterval;// time for draw the next frame

        // Game loop
        while(gameThread != null) {

            long currentTime = System.nanoTime();

            // 1. Update
            update();

            // 2. Draw: Draw the updated information
            repaint();

            // 
            try {
                double remainingTime = nextDrawTime - System.nanoTime();//used to calculate the remaining time (in nanoseconds) before the next frame needs to be drawn in the game loop.
                remainingTime = remainingTime/1000000;//millisecond
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                } 

                Thread.sleep((long) remainingTime);// stop current thread

                nextDrawTime += drawInterval;// update time for the next frame drawing

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }   

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Change from Graphics class to Graphics2D
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        // Save some memory
        g2.dispose();
    }
}
