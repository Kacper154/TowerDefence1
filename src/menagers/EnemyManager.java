package menagers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import enemies.*;
import helpz.LoadSave;
import scenes.Playing;

import static helpz.Constants.Enemies.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int HPBareWidth=20;
    private Random random;
    private WaveManager waveManager;
    private ArrayList<Integer> rand = new ArrayList<>();



    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        loadEnemyImgs();
        random = new Random();
        Random();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        enemyImgs[0] = atlas.getSubimage(9*32, 32, 32, 32);
        enemyImgs[1] = atlas.getSubimage(8*32, 2*32, 32, 32);
        enemyImgs[2] = atlas.getSubimage(9 * 32, 2*32, 32, 32);
        enemyImgs[3] = atlas.getSubimage(8 * 32, 6*32, 32, 32);
    }

    public void update() {

        for (Enemy e : enemies){
            if(e.isAlive())
                updateEnemyMove(e);
            }
    }

    public void updateEnemyMove(Enemy e){
        if(isAtEnd(e)){
            e.kill();
            playing.removeOnelive();
        }
        else
            e.move(GetSpeed(e.getEnemyType()));

    }


    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
    }

    public void Random(){
        rand.add(random.nextInt(7));
        rand.add(random.nextInt(7));
        rand.add(random.nextInt(7));
        rand.add(random.nextInt(7));
    }



    public void addEnemy(int enemyType) {
        int y=0;
        if(playing.getWaveManager().getWaveIndex()==0){
           y= rand.get(0);
        }
        if(playing.getWaveManager().getWaveIndex()==1) {
            int a = random.nextInt(2);
            if (a == 0) {
                y = rand.get(0);
            }
            if (a == 1) {
                y = rand.get(1);
            }
        }

        if(playing.getWaveManager().getWaveIndex()==2){
            int b=random.nextInt(3);
            if (b==0){
                y= rand.get(0);
            }
            if (b==1){
                y= rand.get(1);
            }
            if(b==2){
                y=rand.get(2);
            }

        }
        switch (enemyType){
            case ORC:
                enemies.add(new Orc(31 * 32, y*3 * 32, 0,this));
                break;
            case BAT:
                enemies.add(new Bat(31 * 32, y*3 * 32, 0,this));
                break;
            case KNIGHT:
                enemies.add(new Knight(31 * 32, y*3 * 32, 0,this));
                break;
            case WOLF:
                enemies.add(new Wolf(31 * 32, y*3 * 32, 0,this));
                break;
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies){
            if(e.isAlive()){
                drawEnemy(e, g);
                drawHealthBar(e,g);
            }

        }

    }
    private boolean isAtEnd(Enemy e) {
        if (e.getX() == 0 * 32)
            return true;
         return false;
    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX()+16-(getNewBarWidth(e)/2),(int)e.getY()-10,getNewBarWidth(e),3);
    }

    private int getNewBarWidth(Enemy e){
        return (int)(HPBareWidth*e.getHealthBar());

    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }


    public int getAmountOfAliveEnemies() {
        int size=0;
        for(Enemy e: enemies)
            if(e.isAlive())
                size++;
        return size;

    }

    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }

    public void reset(){
        enemies.clear();
        rand.clear();
        Random();
    }
}
