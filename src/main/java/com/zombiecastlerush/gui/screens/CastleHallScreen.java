package com.zombiecastlerush.gui.screens;

import asciiPanel.AsciiPanel;
import com.zombiecastlerush.building.Puzzle;
import com.zombiecastlerush.building.Room;
import com.zombiecastlerush.gui.*;
import com.zombiecastlerush.util.Game;
import com.zombiecastlerush.util.Parser;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CastleHallScreen implements Screen{

    private World world;
    private final Creature player;
    private final int screenWidth;
    private final int screenHeight;
    private KeyEvent key;

    public CastleHallScreen(Creature player) {
        this.player = player;
        screenWidth = 90;
        screenHeight = 51;
        createWorld();
        player.setWorld(world);
        if (player.x < 1){
            player.x = 88;
        } else if (player.x >= 40 && player.x <=45 && player.y == 50 ){
            player.y = 1;
        } else if (player.x >= 71 && player.x <=76 && player.y == 0){
            player.y = 49;
        } else if (player.x > 88){
            player.x = 1;
        }

        CreatureFactory creatureFactory = new CreatureFactory(world);
        for (int i = 0; i < 3; i++){
            creatureFactory.newZombies();
        }

    }

    private void createWorld() {
        String path = "Resources/Castle/CastleHall.txt";
        world = new WorldBuilder(90, 51)
                .design(path)
                .build();
    }


    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();

        //playground
        displayTiles(terminal, left, top);
        //status
        displayStatus(terminal, screenWidth + 1, 0);
        //inventory
        displayInventory(terminal, screenWidth + 1, (screenHeight - screenHeight % 3) / 3);
        //display map
        displayMap(terminal, screenWidth + 1, (screenHeight - screenHeight % 3) * 2 / 3);
        //prompt
        displayDescription(terminal, 0, screenHeight);
        //user input
        displayUserInput(terminal, 0, terminal.getHeightInCharacters() - 3);

        terminal.write(player.glyph(), player.x - left, player.y - top, player.color());


    }


    public Screen respondToUserInput(KeyEvent key) {
        this.key = key;
        if(player.hp() < 1){return new LoseScreen();}
        if (player.x == 89 && (player.y == 17 || player.y == 18 || player.y ==19)){
            return new EastWingScreen(player);
        }else if (player.x == 0 && (player.y == 17 || player.y == 18 || player.y ==19)){
            return new WestWingScreen(player);
        }
        else if (player.x <=45 && player.x >= 40 && player.y == 0){
            return new ShopScreen(player);
        }
        else if(player.x <=76 && player.x >= 71 && player.y == 50) {
            return new DrawBridgeScreen(player);
        } else{
            switch (key.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    player.moveBy(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.moveBy(1, 0);
                    break;
                case KeyEvent.VK_UP:
                    player.moveBy(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    player.moveBy(0, 1);
                    break;

            }
        }


        return this;
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;

                Creature creature = world.creature(wx, wy);
                if (creature != null) {
                    terminal.write(creature.glyph(), creature.x - left, creature.y - top, creature.color());
                }else{
                    terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
                }
            }
        }
    }

    private void displayStatus(AsciiPanel terminal, int right, int top) {
        //draw yellow boundary lines
        int length = terminal.getWidthInCharacters() - screenWidth - 2;
        terminal.write(drawLine(length), right, top, Color.ORANGE);
        terminal.write("Status", right, top + 1, Color.green);

        // display player hp
        String stats = player.hp() < 1 ? "":String.format("You: %6d/%3d hp", player.hp(), player.maxHp());
        terminal.write(stats, right, top + 3, Color.magenta);

        //if player has an opponent, aka in fight, then display its hp.
        String enemyStats = player.opponent() == null || player.opponent().hp() < 1 ? "":
                String.format("Zombie: %3d/%3d hp", player.opponent().hp(), player.opponent().maxHp());
        terminal.write(enemyStats, right, top + 4, Color.green);


    }


    private void displayInventory(AsciiPanel terminal, int right, int middle) {
        int length = terminal.getWidthInCharacters() - screenWidth - 2;
        terminal.write(drawLine(length), right, middle, Color.ORANGE);
        terminal.write("Inventory", right, middle + 1, Color.green);
        terminal.write("placeholder", right, middle + 2, Color.magenta);
    }

    private void displayMap(AsciiPanel terminal, int right, int bottom) {
        int length = terminal.getWidthInCharacters() - screenWidth - 2;
        terminal.write(drawLine(length), right, bottom, Color.orange);
        int height = terminal.getHeightInCharacters();

        for (int i = 0; i < height; i++) {
            terminal.write("|", right - 1, i, Color.orange);
        }
        terminal.write("Map", right, bottom + 1, Color.green);
        terminal.write("placeholder", right, bottom + 2, Color.magenta);
    }

    private void displayUserInput(AsciiPanel terminal, int left, int i) {
        terminal.write(drawLine(screenWidth), left, i, Color.orange);
        terminal.write("Enter command -> ", left, i + 1, Color.red);

        Command.type(key, terminal, 18, i + 1);
    }

    private void displayDescription(AsciiPanel terminal, int left, int bottom) {

        Room castle = Game.castle.getCastleRooms().get("Castle-Hall");
        terminal.write("Castle Hall", left, bottom + 1, Color.RED);
        String description = castle.getDescription();
        terminal.write(description, left, bottom + 3, Color.white);
        String msg1 ="The box pulses with power. You know not how, but it has a riddle for you,";

        String msg2= "and it will not let you leave until you have solved it.Perhaps you should attempt puzzle.";
        terminal.write(msg1, left, bottom + 4, Color.magenta);
        terminal.write(msg2, left, bottom + 5, Color.magenta);
    }

    private String drawLine(int length) {

        String line = "";
        for (int i = 0; i < length; i++) {
            line += "-";
        }
        return line;
    }
}