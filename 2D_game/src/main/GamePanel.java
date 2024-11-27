package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.OBJ_heart;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // Screen setting
    public final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    //Set Value of tile
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
    //Tile Map
    TileManager tileM = new TileManager(this);
    //KeyHanderler
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    //Player
    public Player player = new Player(this, keyH);
    public EventHandler eHandler = new EventHandler(this);
    //Object
    public Entity monster[]= new Entity[20];
    public Entity object[]= new  Entity[20];
    OBJ_heart player_heart = new OBJ_heart(this);
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entity_list= new ArrayList<>();
    //GameState
    public int gameState;
    public  final int titleState = 0;
    public final int playState = 1;
    public final int pauseState =2;
    public final int dialogueState=3;
    public final int characterState = 4;
    //part of Game setting
    Asset_Setter Setter = new Asset_Setter(this);
    public Collision_checker colis =new Collision_checker(this);
    //Npc
    public Entity[] npc = new Entity[10];
    // UI
    public UI ui;

    {
        ui = new UI(this);
    }



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
    public void Game_setup(){
        Setter.setNPC();
        Setter.setMonster();
        Setter.setObject();
        gameState= titleState;
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
        if (gameState==playState) {
            player.update();
            player_heart.update(player);
            //NPC
            for (Entity e : npc){
                if (e!=null){
                    e.update();
                }
            }
            //Monster
            int i =0;
            for (Entity e : monster){
                if (e!=null && e.life!=0){
                    e.update();
                }
                if (e!=null && e.life==0) {
                    monster[i]=null;
                }
                i++;
            }
            //Skill
            for(i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    if(projectileList.get(i).alive){
                        projectileList.get(i).update();
                    }
                    if(!projectileList.get(i).alive){
                        projectileList.remove(i);
                    }
                }
            }
        }
    }
    //Output for every updating
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Change from Graphics class to Graphics2D
        Graphics2D g2 = (Graphics2D)g;
        if (gameState==titleState){
            //for output screen
            ui.draw(g2);
        }
        else {
            // Map
            tileM.draw(g2);
            //player heart
            player_heart.draw(g2);
            //player
            entity_list.add(player);
            //NPC
            for (Entity e : npc){
                if (e!=null){
                    entity_list.add(e);
                }
            }
            //Object
            for (Entity e : object){
                if (e!=null){
                    entity_list.add(e);
                }
            }
            //Monster
            for (Entity e : monster){
                if (e!=null && e.life!=0) {
                    entity_list.add(e);
                }

            }
            //Skill
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entity_list.add(projectileList.get(i));
                }
            }
            //Sorting
            Collections.sort(entity_list, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.y,o2.y);
                    return result;
                }


            });
            //Draw Entity
            for (int i=0; i<entity_list.size();i++){
                entity_list.get(i).draw(g2);
            }
            //Entity
            entity_list.clear();

            //Pause state
            ui.draw(g2);
        }




        // Save some memory
        g2.dispose();
    }
}