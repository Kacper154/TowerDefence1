package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.GameOver;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;


public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;


    private final double FPSset=120.0;
    private final double UPSset=60.0;




    private Thread gameThread;

    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private GameOver gameOver;



    public Game(){

        setSize(1024,768);
        setResizable(false);
        setTitle("Obrona");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initClasses();
        add(gameScreen);
        setVisible(true);

    }

    private void initClasses(){
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings= new Settings(this);
        gameOver=new GameOver(this);
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }


    public Settings getSettings() {
        return settings;
    }






    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();

    }

    private void updateGame(){
        switch(GameStates.gameState){

            case PLAYING:
                playing.update();
                break;
            case MENU:
                break;
            case SETTINGS:
                break;
        }
    }





    public static void main(String[] args) {

        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();

        }

    @Override
    public void run() {

        double timePerFrame= 1000000000.0/FPSset;
        double timePerUpdate= 1000000000.0/UPSset;

        long lastFrame=System.nanoTime();
        long lastTimeCheck=System.currentTimeMillis();
        long lastUpdate=System.nanoTime();

        int frames=0;
        int updates=0;

        long now;

        while(true){

            now=System.nanoTime();

            if(now-lastFrame >=timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }
            if(now-lastUpdate >=timePerUpdate){
                updateGame();
                lastUpdate=now;
                updates++;
            }
            if (System.currentTimeMillis()-lastTimeCheck>=1000){
                //System.out.println("FPS: "+frames+" | UPS: "+updates);
                frames=0;
                updates=0;
                lastTimeCheck = System.currentTimeMillis();

            }

        }


    }
    public Render getRender(){
        return render;
    }

    public GameOver getGameOver() {
        return gameOver;
    }
}