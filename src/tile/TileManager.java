package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import sprite.SpriteSheet;
import utility.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum1;
    public int[][] mapTileNum2;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[600];
        mapTileNum1 = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNum2=  new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap2("/maps/worldmap.txt");
        loadMap1("/maps/worldmap2.txt");

    }

    public void getTileImage() {
        SpriteSheet sheet = new SpriteSheet("/tiles/map_tileset.png", gp.originalTileSize, gp.originalTileSize);
        for (int i =0; i< 18;i++){
            for (int j=0; j<29;j++){
                tile[i*29+j]=new Tile();
                tile[i*29+j].image = sheet.getSprite(j, i);
                tile[i*29+j].collision = false;
            }
        }
        //Set up for tile not collision
        int[] list = {203,204,232,233,261,262,290,291,319,320,348,349,343,372,373,417,418,
                419,420,446,447,448,449,475,476,477,478,504,505,506,507,458,459,460,461,209,
                210,211,212,238,241,265,266,267,270,271,272,294 ,301,323,330,352,353,354,357,
                358,359,383,386,412,413,414,415,155};
        for(int t : list){
            tile[t].collision=true;
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