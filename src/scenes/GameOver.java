package scenes;

import main.Game;
import ul.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods{
    private MyButton bReplay, bMenu;


    public GameOver(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 1000 / 2 - w / 2;
        int y = 250;
        int yOffset = 100;

        bMenu = new MyButton("Menu",x,y,w,h);
        bReplay = new MyButton("Replay",x, y + yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Arial",Font.BOLD,50));
        g.setColor(Color.red);
        g.drawString("Game over",365,150);

        g.setFont(new Font("Arial",Font.BOLD,20));
        bMenu.draw(g);
        bReplay.draw(g);

    }
    private void replayGame() {
        resetAll();
        SetGameState(PLAYING);
    }
    private void resetAll() {
        game.getPlaying().resetEverything();
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)){
            SetGameState(MENU);
            resetAll();
        }

        else if(bReplay.getBounds().contains(x,y))
            replayGame();

    }


    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x,y))
            bMenu.setMouseOver(true);
        else if(bReplay.getBounds().contains(x,y))
            bReplay.setMouseOver(true);

    }

    @Override
    public void mousePresed(int x, int y) {
        if(bMenu.getBounds().contains(x,y))
            bMenu.setMousePresed(true);
        else if(bReplay.getBounds().contains(x,y))
            bReplay.setMousePresed(true);

    }

    @Override
    public void mouseRelesed(int x, int y) {
        bMenu.resetBools();
        bReplay.resetBools();

    }
}
