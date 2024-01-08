package scenes;

import main.Game;
import ul.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static main.GameStates.*;
import static main.GameStates.SetGameState;

public class Menu extends GameScene implements SceneMethods{

    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    private MyButton bPlaying, bSettings, bQuit;
    public Menu (Game game){
        super(game);
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 1000 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bPlaying = new MyButton("Play", x, y, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);

    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSprites() {
        for (int y=0; y<10; y++){
            for (int x=0; x<10; x++){
                sprites.add(img.getSubimage(x*32,y*32,32,32));

            }
        }
    }

    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x,y)){
            SetGameState(PLAYING);
        }
        else if (bSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePresed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePresed(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePresed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePresed(true);
        }
    }

    @Override
    public void mouseRelesed(int x, int y) {
        resetButtons();

    }

    private void resetButtons() {
        bPlaying.resetBools();
        bSettings.resetBools();
        bQuit.resetBools();
    }
}
