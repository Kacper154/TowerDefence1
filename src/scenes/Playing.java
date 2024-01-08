package scenes;

import enemies.Enemy;
import helpz.Constants;
import helpz.LevelBuild;
import main.Game;
import menagers.*;
import objects.Tower;
import ul.MyButton;
import ul.BottonBar;


import java.awt.*;
import java.awt.event.KeyEvent;

import static main.GameStates.*;
import static main.GameStates.SetGameState;

public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private Tilemenager tilemenager;
    private BottonBar bottomBar;
    private EnemyManager enemyManager;
    private TowerManager towermenager;
    private ProjectileManager projectileManager;
    private WaveManager waveManager;
    private Tower selectedTower;
    private int mouseX,mouseY;
    private int goldTick;
    private boolean gamePaused;
    public Playing (Game game){
        super(game);
        lvl = LevelBuild.getLevelData();
        tilemenager= new Tilemenager();
        bottomBar= new BottonBar(0,768-5*32,1024,4*32,this);

        enemyManager=new EnemyManager(this);
        towermenager = new TowerManager(this);
        projectileManager = new ProjectileManager(this);
        waveManager = new WaveManager(this);
    }

    public void update(){
        if(!gamePaused){

            if(getWaveManager().getWaveIndex()==2 && getEnemyManager().getAmountOfAliveEnemies()==0)
                SetGameState(GAME_OVER);

            waveManager.update();

            goldTick++;
            if(goldTick%(60*3)==0)
                bottomBar.addGold(1);

            if(isAllEnemysDead()){
                if(isThereMoreWaves()){
                    waveManager.startWaveTimer();
                    if(isWavesTimerOver()){
                        waveManager.increseWaveIndex();
                        enemyManager.getEnemies().clear();
                        waveManager.resetEnemyIndex();
                    }
                }

            }

            if(isTimeForNewEnemy()){
                spawnEnemy();
            }
            enemyManager.update();
            towermenager.update();
            projectileManager.update();

        }

    }

    private boolean isWavesTimerOver() {
        return waveManager.isTimerOver();

    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemysDead() {
        if(waveManager.isThereMoreEnemysInWave()){
            return false;
        }
        for(Enemy e: enemyManager.getEnemies()){
            if(e.isAlive())
                return false;
        }
        return true;
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()){
            if(waveManager.isThereMoreEnemysInWave()){
                return true;
            }
        }
        return false;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower=selectedTower;
    }


    @Override
    public void render(Graphics g) {
        for(int y=0;y< lvl.length;y++){
            for(int x=0;x< lvl[y].length;x++){
                int id=lvl[y][x];
                g.drawImage(tilemenager.getSprite(id),x*32,y*32,null);
            }
        }
        bottomBar.draw(g);
        enemyManager.draw(g);
        towermenager.draw(g);
        projectileManager.draw(g);
        drawSelectedTower(g);
        drawHighlight(g);
        drawWaveInfo(g);

    }

    private void drawWaveInfo(Graphics g) {

    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(mouseX,mouseY,32,32);
    }

    private void drawSelectedTower(Graphics g) {
        if(selectedTower!=null)
            g.drawImage(towermenager.getTowerimage()[selectedTower.getTowerType()], mouseX, mouseY,null);
    }


    @Override
    public void mouseClicked(int x, int y) {
        if(y>=608){
            bottomBar.mouseClicked(x,y);
        }
        else{
            if(selectedTower!=null){
                if(isTileRoad(mouseX,mouseY)&&mouseX<11*32){
                    if(getTowerAt(mouseX,mouseY)==null){
                        towermenager.addTower(selectedTower,mouseX,mouseY);
                        removeGold(selectedTower.getTowerType());
                        selectedTower=null;

                    }

                }

            }
            else{
                Tower t= getTowerAt(mouseX,mouseY);
                bottomBar.displayTower(t);
            }
        }
    }

    private void removeGold(int towerType) {
        bottomBar.payForTower(towerType);
    }

    private void updateWaveManager() {
        getWaveManager().update();
    }


    private Tower getTowerAt(int x, int y) {
        return towermenager.getTowerAt(x,y);
    }
    public void removeTower(Tower displayedTower) {
        towermenager.removeTower(displayedTower);
    }

    private boolean isTileRoad(int x, int y) {
        int id=lvl[y/32][x/32];
        if (id==1)
            return true;
        else
            return false;
    }

    public void shootEnemy(Tower t, Enemy e) {
        projectileManager.newProjectile(t,e);
    }

    public void setGamePaused(boolean gamePaused){
        this.gamePaused=gamePaused;
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            selectedTower=null;
        }
    }
    @Override
    public void mouseMoved(int x, int y) {
        if(y>=608){
            bottomBar.mouseMoved(x,y);
        }
        else{
            mouseX=(x/32)*32;
            mouseY=(y/32)*32;
        }

    }

    @Override
    public void mousePresed(int x, int y) {
        if(y>=608){
            bottomBar.mousePresed(x,y);
        }
    }

    @Override
    public void mouseRelesed(int x, int y) {
        bottomBar.mouseRelesed(x,y);

    }

    public void rewardPlayer(int enemyType){
        bottomBar.addGold(Constants.Enemies.GetReward(enemyType));

    }


    public TowerManager getTowermenager(){
        return towermenager;
    }

    public EnemyManager getEnemyManager(){
        return enemyManager;
    }

    public WaveManager getWaveManager(){
        return waveManager;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void removeOnelive() {
        bottomBar.removeOnelive();
    }

    public void resetEverything() {
        bottomBar.resetEverything();
        enemyManager.reset();
        towermenager.reset();
        projectileManager.reset();
        waveManager.reset();
        mouseX=0;
        mouseY=0;
        selectedTower=null;
        goldTick=0;
        gamePaused=false;
    }
}
