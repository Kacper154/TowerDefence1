package scenes;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods{

    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    public Menu (Game game){
        super(game);
        importImg();
        loadSprites();
    }

    @Override
    public void render(Graphics g) {
        for(int y=0; y<24; y++){
            for(int x=0; x<32; x++){
                if (y%3==0){
                    g.drawImage(sprites.get(9),x*32,y*32,null);
                }
                else{
                    g.drawImage(sprites.get(18),x*32,y*32,null);
                }

            }

        }

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
}