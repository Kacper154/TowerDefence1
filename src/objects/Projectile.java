package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private float speed;
    private int Id, projectileType,dmg;
    private boolean active=true;

    public Projectile(float x, float y, float speed, int dmg, int id, int projectileType) {
        pos=new Point2D.Float(x,y);
        this.speed=speed;
        this.dmg=dmg;
        this.Id=id;
        this.projectileType = projectileType;
    }

    public void move(){
        pos.x+=speed;

    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return Id;
    }


    public int getProjectileType() {
        return projectileType;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDmg(){
        return dmg;
    }
}
