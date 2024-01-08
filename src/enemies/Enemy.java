package enemies;

import helpz.Constants;
import menagers.EnemyManager;

import java.awt.Rectangle;

public abstract class Enemy {

    protected EnemyManager enemyManager;
    protected float x, y;
    protected Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected boolean alive=true;

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager=enemyManager;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        setStartHealth();
    }

    private void setStartHealth(){
        health=Constants.Enemies.GetStartHealth(enemyType);
        maxHealth=health;
    }

    public void hurt(int dmg){
        this.health-=dmg;
        if(health<=0){
            alive=false;
            enemyManager.rewardPlayer(enemyType);
        }
    }

    public void kill(){
        alive=false;
        health=0;
    }

    public void move(float x) {
        this.x -= x;
        updateHitBox();
    }

    private void updateHitBox() {
        bounds.x=(int)x;
        bounds.y=(int)y;
    }

    public float getHealthBar(){
        return health/(float)maxHealth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public boolean isAlive() {
        return alive;
    }
}
