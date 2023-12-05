package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.*;


public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;


    private final double FPSset=120.0;
    private final double UPSset=60.0;

    private MyMouseListener MyMouseListener;
    private KeyboardListener keyBoardListener;



    private Thread gameThread;

    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;



    public Game(){

        setSize(1024,768);
        setResizable(false);
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


    private void initInputs(){
        MyMouseListener = new MyMouseListener();
        keyBoardListener = new KeyboardListener();

        addMouseListener(MyMouseListener);
        addMouseMotionListener(MyMouseListener);
        addKeyListener(keyBoardListener);

        requestFocus();

    }



    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();

    }





    public static void main(String[] args) {

        Game game = new Game();
        game.initInputs();
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
                lastUpdate=now;
                updates++;
            }
            if (System.currentTimeMillis()-lastTimeCheck>=1000){
                System.out.println("FPS: "+frames+" | UPS: "+updates);
                frames=0;
                updates=0;
                lastTimeCheck = System.currentTimeMillis();

            }

        }


    }
    public Render getRender(){
        return render;
    }
}