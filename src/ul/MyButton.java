package ul;

import java.awt.*;

public class MyButton {
    public int x,y,width,height;
    private String text;
    private Rectangle bounds;
    private boolean mouseover, mousepresed;
    public int id;

    public MyButton(String text,int x, int y, int width, int height) {
        this.text=text;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

        initBounds();
    }

    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }



    private void initBounds(){
        this.bounds =new Rectangle(x,y,width,height);
    }

    public void draw (Graphics g){

        drawBody(g);

        drawBorder(g);

        drawText(g);

    }

    private void drawBorder(Graphics g) {
    if(mousepresed){
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
        g.drawRect(x+1,y+1,width-2,height-2);
        g.drawRect(x+2,y+2,width-4,height-4);

    }
    else{
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
    }
    }

    private void drawBody(Graphics g) {
        if (mouseover)
            g.setColor(Color.gray);
        else
            g.setColor(Color.white);
        g.fillRect(x,y,width,height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text,x-w/2+width/2,y+h/2+height/2);
    }

    public void resetBools(){
        this.mouseover=false;
        this.mousepresed=false;
    }

    public void setText(String text){
        this.text=text;
    }

    public int getId() {
        return id;
    }

    public void setMousePresed(boolean mousepresed){
        this.mousepresed=mousepresed;
    }

    public void setMouseOver(boolean mouseover){
        this.mouseover=mouseover;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public boolean isMouseOver() {
        return mouseover;
    }
    public boolean isMousePressed() {
        return mousepresed;
    }
}
