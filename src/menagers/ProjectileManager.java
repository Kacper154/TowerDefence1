package menagers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Towers.*;
import static helpz.Constants.Projectiles.*;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile>projectiles=new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id=0;

    public ProjectileManager(Playing playing ){
        this.playing=playing;
        importImgs();
    }
    private void importImgs(){
        BufferedImage atlas= LoadSave.getSpriteAtlas();
        proj_imgs=new BufferedImage[3];
        proj_imgs[0]=atlas.getSubimage(8*32,5*32,32,32);
        proj_imgs[1]=atlas.getSubimage(9*32,5*32,32,32);
        proj_imgs[2]=atlas.getSubimage(9*32,4*32,32,32);
    }

    public void newProjectile(Tower t, Enemy e){
        int type=getProjType(t);
        int distx=(int)Math.abs(t.getX()-e.getX());
        int disty=(int)Math.abs(t.getY()-e.getY());
        int totaldist= distx+disty;

        float xPer=(float)distx/totaldist;


        float speed=(float)xPer* Constants.Projectiles.GetSpeed(type);


        if (t.getX()>e.getX())
            speed*=-1;

        projectiles.add(new Projectile(t.getX(),t.getY(),speed,t.getDmg(),proj_id++,type));
    }

    private int getProjType(Tower t) {
        switch (t.getTowerType()){
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }
        return 0;
    }

    public void update(){
        for(Projectile p: projectiles){
            if(p.isActive()){
                p.move();
                if(isProjHittingEnemy(p)){
                    p.setActive(false);

                }
            }

        }
    }

    private boolean isProjHittingEnemy(Projectile p){
        for(Enemy e: playing.getEnemyManager().getEnemies()){
            if(e.isAlive())
                if(e.getBounds().contains(p.getPos())){
                    e.hurt(p.getDmg());
                    return true;
            }

        }
        return false;
    }
    public void draw(Graphics g){
        for(Projectile p: projectiles){
            if(p.isActive())
                g.drawImage(proj_imgs[p.getProjectileType()],(int)p.getPos().x,(int)p.getPos().y,null);
        }
    }

    public void reset(){
        projectiles.clear();
        proj_id=0;
    }
}
