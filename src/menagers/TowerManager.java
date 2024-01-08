package menagers;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Towers.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerimage;
    private ArrayList<Tower>towers=new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing){
        this.playing=playing;
        
        loadTowerImgs();
    }



    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerimage = new BufferedImage[3];
        towerimage[0]=atlas.getSubimage(8*32,3*32,32,32);
        towerimage[1]=atlas.getSubimage(9*32,3*32,32,32);
        towerimage[2]=atlas.getSubimage(8*32,4*32,32,32);

    }

    public void addTower(Tower selectedTower, int XPos, int YPos){
        towers.add(new Tower(XPos,YPos,towerAmount++, selectedTower.getTowerType()));

    }
    public void removeTower(Tower displayedTower) {
        for(int i=0;i<towers.size();i++){
            if(towers.get(i).getId()==displayedTower.getId()){
                towers.remove(i);
            }
        }
    }

    public void update(){
        for(Tower t: towers){
            t.update();
            atak(t);
        }

    }

    private void atak(Tower t) {
            for(Enemy e: playing.getEnemyManager().getEnemies()){
                if(e.isAlive()){
                    if(isEnemyinRange(t,e)){
                        if(t.isCooldownOver()){
                            playing.shootEnemy(t,e);
                            t.resetCooldown();
                        }

                }
                    
                }
            }
        }

    private boolean isEnemyinRange(Tower t, Enemy e) {
        if(t.getY()==e.getY())
            return true;
        else
            return false;
    }

    public void draw(Graphics g){
        for(Tower t: towers){
            g.drawImage(towerimage[t.getTowerType()],t.getX(),t.getY(),null);
        }

    }


    public Tower getTowerAt(int x, int y) {
        for(Tower t:towers)
            if(t.getX()==x)
                if(t.getY()==y)
                    return t;
        return null;

    }

    public BufferedImage[] getTowerimage(){
        return towerimage;
    }

    public void reset(){
        towers.clear();
        towerAmount=0;
    }


}
