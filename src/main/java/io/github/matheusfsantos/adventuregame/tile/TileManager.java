package io.github.matheusfsantos.adventuregame.tile;

import io.github.matheusfsantos.adventuregame.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][]  mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tile = new Tile[10];
        this.mapTileNum = new int[this.gamePanel.MAX_SCREEN_COL][this.gamePanel.MAX_SCREEN_ROW];

        this.getTileImage();
        this.loadMap();
    }

    public void loadMap() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int columns = 0;
            int rows = 0;

            while(columns < this.gamePanel.MAX_SCREEN_COL && rows < this.gamePanel.MAX_SCREEN_ROW) {
                String line = bufferedReader.readLine();

                while(columns < this.gamePanel.MAX_SCREEN_COL) {
                    String[] numbers = line.split(" ");
                    int num =  Integer.parseInt(numbers[columns]);
                    mapTileNum[columns][rows] = num;
                    columns++;
                }

                columns = 0;
                rows++;
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public void draw(Graphics2D graphics2d) {
        int columns = 0;
        int rows = 0;
        int x = 0;
        int y = 0;

        int tileNum;

        while(columns < this.gamePanel.MAX_SCREEN_COL && rows < this.gamePanel.MAX_SCREEN_ROW) {
            tileNum = mapTileNum[columns][rows];

            graphics2d.drawImage(this.tile[tileNum].image, x, y, this.gamePanel.TILE_SIZE, this.gamePanel.TILE_SIZE, null);
            columns++;
            x += this.gamePanel.TILE_SIZE;

            if(columns == this.gamePanel.MAX_SCREEN_COL) {
                columns = 0;
                x = 0;

                rows++;
                y += this.gamePanel.TILE_SIZE;
            }
        }
    }
}
