package ul;

import helpz.Constants;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.text.DecimalFormat;

import static main.GameStates.*;
import static objects.Tower.*;

public class BottonBar {
    private int x,y,width,height;
    private MyButton bMenu, bPause;
    private MyButton[] towerButtons;
    private Playing playing;
    private Tower selectedTower;
    private Tower displayedTower;
    private DecimalFormat formater;
    private int gold=100;
    private boolean showTowerCost;
    private int TowerCostType;
    private MyButton sellTower;
    private int lives=25;

    public BottonBar(int x, int y, int width, int height, Playing plaing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing=plaing;
        formater=new DecimalFormat("0.0");

        initButtons();
    }

    public void resetEverything() {
        lives=25;
        TowerCostType=0;
        showTowerCost=false;
        gold=100;
        selectedTower=null;
        displayedTower=null;
    }

    public void payForTower(int towerType) {
        this.gold-=Constants.Towers.GetTowerCost(towerType);
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 900, 620, 100, 30);
        bPause = new MyButton("Pause",900,660,100,30);
        towerButtons = new MyButton[3];
        int w= 50;
        int h=50;
        int xStart=100;
        int yStart=650;
        int xOffset=(int)(w*1.1f);

        for(int i=0; i< towerButtons.length; i++){
            towerButtons[i]= new MyButton("", xStart+ xOffset*i,yStart,w,h,i);
        }

        sellTower=new MyButton("Sell",590,690,100,20);
    }

    public void removeOnelive(){
        lives--;
        if(lives<=0){
            SetGameState(GAME_OVER);
        }
    }

    private void drawButtonFeedback(Graphics g, MyButton b) {
        if (b.isMouseOver())
            g.setColor(Color.white);
        else
            g.setColor(Color.BLACK);
        g.drawRect(b.x, b.y, b.width, b.height);
        if (b.isMousePressed()) {
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
        }
    }



    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);
        for(MyButton b:towerButtons){
            g.setColor(Color.gray);
            g.fillRect(b.x,b.y,b.width,b.height);
            g.drawImage(playing.getTowermenager().getTowerimage()[b.getId()], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g,b);
        }


    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);

        drawButtons(g);

        drawDisplayedTower(g);

        drawWaveInfo(g);

        drawGoldAmount(g);

        if(showTowerCost)
            drawTowerCost(g);

        g.setColor(Color.black);
        g.drawString("Lives: "+lives,100,640);
    }

    private void drawTowerCost(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(280,610,120,50);
        g.setColor(Color.black);
        g.drawRect(280,610,120,50);
        g.drawString(""+getTowerCostName(),285,630);
        g.drawString("Cost: "+getTowerCostCost()+" gold",285,655);

        if(isTowerCostMoreThanCurrentGold()){
            g.setColor(Color.red);
            g.drawString("No Money",170,720);
            
        }
    }

    private boolean isTowerCostMoreThanCurrentGold() {
        return getTowerCostCost()>gold;
    }

    private int getTowerCostCost() {
        return Constants.Towers.GetTowerCost(TowerCostType);
    }

    private String getTowerCostName() {
        return Constants.Towers.GetName(TowerCostType);
    }

    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: "+gold,100,720);
    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.BOLD,15));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);

    }

    private void drawWavesLeftInfo(Graphics g) {
        int current=playing.getWaveManager().getWaveIndex();
        int size=playing.getWaveManager().getWaves().size();
        g.drawString("Wave "+(current+1)+" / "+size,300,680);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining=playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies left: "+remaining,300,700);
        
    }

    private void drawWaveTimerInfo(Graphics g){
        if(playing.getWaveManager().isWaveTimerStarted()){
            float timeLeft= playing.getWaveManager().getTimeLeft();
            String formated=formater.format(timeLeft);
            g.drawString("Time left: "+formated,300,720);
        }

    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower!=null){
            g.setColor(Color.lightGray);
            g.fillRect(490,635,220,85);
            g.setColor(Color.black);
            g.drawRect(490,635,220,85);
            g.drawRect(500,640,50,50);
            g.drawImage(playing.getTowermenager().getTowerimage()[displayedTower.getTowerType()],500,640,50,50,null);
            g.setFont(new Font("Arial",Font.BOLD,15 ));
            g.drawString(""+ Constants.Towers.GetName(displayedTower.getTowerType()),560,650);
            g.drawString("ID: "+ displayedTower.getId(),560,670);

            drawDisplayedTowerBorder(g);

            sellTower.draw(g);

            if(sellTower.isMouseOver()){
                g.setColor(Color.red);
                g.drawString("Sell for: "+getSellAmount(displayedTower)+"g",560,685);
            }


        }
    }

    private int getSellAmount(Tower displayedTower) {
        return Constants.Towers.GetTowerCost(displayedTower.getTowerType())/2;

    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.cyan);
        g.drawRect(displayedTower.getX(), displayedTower.getY(),32 ,32);
    }


    public void displayTower(Tower t){
        displayedTower=t;
    }


    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else if(bPause.getBounds().contains(x, y)){
            pauseGame();
        }
        else {

            if(displayedTower!=null) {
                if (sellTower.getBounds().contains(x, y)) {
                    sellTowerClicked();
                    return;
                }
            }

            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x,y)){
                    if(!isGoldEnoughForTower(b.getId()))
                        return;
                    selectedTower= new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    private void sellTowerClicked() {
        playing.removeTower(displayedTower);
        gold+=Constants.Towers.GetTowerCost(displayedTower.getTowerType())/2;
        displayedTower=null;
    }

    private void pauseGame(){
        playing.setGamePaused(!playing.isGamePaused());
        if(playing.isGamePaused())
            bPause.setText("UnPause");
        else
            bPause.setText("Pause");
    }

    private boolean isGoldEnoughForTower(int towerType) {
        return gold>=Constants.Towers.GetTowerCost(towerType);
    }


    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);
        showTowerCost=false;
        sellTower.setMouseOver(false);
        for (MyButton b : towerButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bPause.getBounds().contains(x, y)) {
            bPause.setMouseOver(true);
            
        }
        else {
            if(displayedTower!=null){
                if(sellTower.getBounds().contains(x,y)){
                    sellTower.setMouseOver(true);
                    return;
                }
            }

            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    showTowerCost=true;
                    TowerCostType=b.getId();
                    return;
                }
        }

    }


    public void mousePresed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePresed(true);
        else if (bPause.getBounds().contains(x, y)) {
            bPause.setMousePresed(true);

        }
        else{

            if(displayedTower!=null){
                if(sellTower.getBounds().contains(x,y)){
                    sellTower.setMousePresed(true);
                    return;
                }
            }

            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMousePresed(true);
                    return;
                }

        }


    }


    public void mouseRelesed(int x, int y) {
        bMenu.resetBools();
        bPause.resetBools();
        for (MyButton b : towerButtons)
            b.resetBools();
        sellTower.resetBools();

    }

    public void addGold(int getReward) {
        this.gold+=getReward;

    }

    public int getLives(){
        return lives;
    }
}
