package inputs;

import main.Game;
import main.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Game game;
    public MyMouseListener(Game game) {
        this.game=game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON1){
            switch (GameStates.gameState){
                case PLAYING:
                    game.getPlaying().mouseClicked(e.getX(), e.getY());
                    break;
                case MENU:
                    game.getMenu().mouseClicked(e.getX(),e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mouseClicked(e.getX(), e.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mouseClicked(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.gameState){
            case PLAYING:
                game.getPlaying().mousePresed(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mousePresed(e.getX(),e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousePresed(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mousePresed(e.getX(), e.getY());
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gameState){
            case PLAYING:
                game.getPlaying().mouseRelesed(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mouseRelesed(e.getX(),e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseRelesed(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mouseRelesed(e.getX(), e.getY());
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameState){
            case PLAYING:
                game.getPlaying().mouseMoved(e.getX(), e.getY());
                break;
            case MENU:
                game.getMenu().mouseMoved(e.getX(),e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseMoved(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mouseMoved(e.getX(), e.getY());
                break;
            default:
                break;
        }

    }
}
