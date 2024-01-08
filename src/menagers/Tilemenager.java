package menagers;

import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tilemenager {

    public Tile Grass,Road;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public Tilemenager(){
        loadAtlas();
        createTiles();

    }

    private void createTiles() {

        tiles.add(Grass=new Tile(getSprite(8,1)));
        tiles.add(Road=new Tile(getSprite(9,0)));

    }

    private void loadAtlas() {

        atlas= LoadSave.getSpriteAtlas();
        
    }

    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord*32,yCord*32,32,32);
    }

}
