package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import utility.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum1;
    public int[][] mapTileNum2;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[20];
        mapTileNum1 = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNum2=  new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap2("/maps/worldmap.txt");
        loadMap1("/maps/worldmap2.txt");

    }

    public void getTileImage() {

        setup(0, "grass1", false);//
        setup(1, "wall3", true);//
        setup(2, "water", true);//
        setup(3, "bg4", false);//
        setup(4, "tree1", true);//
        setup(5, "bg5", false);//
        setup(6, "bridge", false);//
        setup(7, "hangrao", true);//
        setup(8, "snow", false);//
        setup(9, "tree2", true);//
        setup(10, "tree3", true);//
        setup(11, "grass2", false);//
        setup(12, "wood30", true);//
        setup(13, "redhouse", true);//
        setup(14, "ph", true);//
        setup(15, "fences", true);//
        setup(16, "xuongrong", true);//
    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap1(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum1[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap2(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum2[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum1 = mapTileNum1[worldCol][worldRow];
            int tileNum2 = mapTileNum2[worldCol][worldRow];


            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.x + gp.player.screenX;
            int screenY = worldY - gp.player.y + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.x - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.x + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.y - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.y + gp.player.screenY) {

                g2.drawImage(tile[tileNum2].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                g2.drawImage(tile[tileNum1].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }


            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }

    }
}