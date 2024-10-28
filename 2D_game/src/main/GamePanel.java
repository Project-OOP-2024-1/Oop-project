package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Entity;
import entity.Monster;
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

    KeyHandler_multi_object keyH = new KeyHandler_multi_object();
    Thread gameThread;
    Player player = new Player(this, keyH);
    OBJ_heart player_heart = new OBJ_heart(this);
    Monster monster = new Monster(this,keyH,true);
    public ArrayList<Entity> projectileList = new ArrayList<>();




    public Collision_checker colis = new Collision_checker(this);

    // Initiate position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        // Adding key sets for player and monster
        keyH.addNewKeySet1(player.obj_name, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_F);
        keyH.addNewKeySet1(monster.obj_name, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,KeyEvent.VK_SLASH);
        monster.setTarget(player);
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
//            System.out.println("Monster Key: "+monster.keyMonster.key_up+" "+monster.keyMonster.key_down+" "+monster.keyMonster.key_left+" "+monster.keyMonster.key_right);

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
        monster.update();

        System.out.println("gp: len projectiile list" + projectileList.size());
        for (int i=0;i<projectileList.size();i++){
            if (projectileList.get(i)!=null){
                if(projectileList.get(i).alive){
                    projectileList.get(i).update();
                }
                else{
                    projectileList.remove(i);
                }
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Change from Graphics class to Graphics2D
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        player_heart.draw(g2);
        monster.draw(g2);
        for (int i=0;i<projectileList.size();i++){
            if(projectileList.get(i)!=null){
                projectileList.get(i).draw(g2);
            }
        }
            // Save some memory
        g2.dispose();
    }
}
