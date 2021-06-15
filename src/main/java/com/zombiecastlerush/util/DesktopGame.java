package com.zombiecastlerush.util;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zombiecastlerush.building.Room;
import com.zombiecastlerush.entity.Player;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * the functions in this class are called before, during and after GUI window
 */
public class DesktopGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;
    private Sprite sprite;
    private ShapeRenderer shape;
    private boolean isCleaned = false;

    @Override
    public void create () {
        batch = new SpriteBatch();
        texture = new Texture("castle.jpg");
        font = new BitmapFont();
        sprite = new Sprite(texture, 0, 0, 1920, 1200);
        font.setColor(Color.BLACK);
        shape = new ShapeRenderer();

    }

    @Override
    public void render () {
        ScreenUtils.clear(1, 0, 0, 1);
        Player player = Game.getInstance().getPlayer();

        batch.begin();
        sprite.draw(batch);
        String welcome = String.format("Welcome %s to Zombie Castle Rush Demo", player.getName());
        font.setColor(Color.BLACK);
        font.getData().setScale(1.2f);
        font.draw(batch, welcome, 10,750);
        batch.end();

        int x = player.getCoordinates().getDx();
        int y = player.getCoordinates().getDy();
        Iterator roomIterator = Game.getInstance().getCastle().getCastleRooms().entrySet().iterator();
        shape.setColor(Color.BROWN);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
        font.setColor(Color.BLUE);
        while(roomIterator.hasNext()){
            Map.Entry roomEntry = (Map.Entry<String, Room>) roomIterator.next();
            String roomName = (String) roomEntry.getKey();
            Room room = (Room) roomEntry.getValue();
            shape.rect(room.getCoordinates().getDx(), room.getCoordinates().getDy(), 100,100);
            font.draw(batch, roomName, room.getCoordinates().getDx(),room.getCoordinates().getDy());
        }
        batch.end();
        shape.setColor(Color.RED);
        shape.circle(x+30, y+30, 10);
        shape.end();

    }

    @Override
    public void dispose () {
        batch.dispose();
        texture.dispose();
        font.dispose();
        shape.dispose();
        System.out.println("The game's GUI is closed.");
        this.isCleaned = true;
    }

    public boolean isCleaned(){
        return this.isCleaned;
    }
}
