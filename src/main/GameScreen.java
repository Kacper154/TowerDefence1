package main;

import main.Game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private Game game;





    public GameScreen(Game game){
        this.game=game;






    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);



        //g.drawImage(sprites.get(18),0,0,null);

        /*BufferedImage i =img.getSubimage(32*9,32,32,32);
        g.drawImage(i,0,0,null);

        for(int y=0; y<24; y++){
            for(int x=0; x<32; x++){
                g.setColor(getRandomColor());
                g.fillRect(x*32,y*32,32,32);
            }

        }*/
    }



}
