package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import main.Game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private Game game;
    private MyMouseListener MyMouseListener;
    private KeyboardListener keyBoardListener;






    public GameScreen(Game game){
        this.game=game;






    }


    public void initInputs(){
        MyMouseListener = new MyMouseListener(game);
        keyBoardListener = new KeyboardListener(game);

        addMouseListener(MyMouseListener);
        addMouseMotionListener(MyMouseListener);
        addKeyListener(keyBoardListener);

        requestFocus();

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);


    }



}
