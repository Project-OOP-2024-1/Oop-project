package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum1;
    public int[][] mapTileNum2;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum1 = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNum2 = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();

        loadMap2("/maps/worldmap.txt");
        loadMap1("/maps/worldmap2.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
            tile[0].collision=false;
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall3.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bg4.png"));
            tile[3].collision=false;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bg5.png"));
            tile[5].collision=false;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bridge.png"));
            tile[6].collision=false;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/hangrao.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/snow.png"));
            tile[8].collision=false;
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree2.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree3.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
            tile[11].collision=false;
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wood30.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/redhouse.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ph.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fences.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/xuongrong.png"));

        } catch (IOException e) {
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

//        g2.drawImage(tile[4].image, 0,0 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[4].image, 0,16 , gp.tileSize, gp.tileSize, null);


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
