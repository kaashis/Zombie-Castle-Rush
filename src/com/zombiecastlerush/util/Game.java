package com.zombiecastlerush.util;


import com.zombiecastlerush.building.Map;

/**
 * singleton class Game
 * it provides access to a Map and a role Controller
 */
public class Game {
    private Map map;
    private static Game game;

    private Game(){
        this.initiateController();
        this.initiateMap();
    }

    public static Game getInstance(){
        if(Game.game == null){
            Game.game = new Game();
        }
        return Game.game;
    }

    /**
     * TODO: What does start() provide?
     */
    public void start(){
        System.out.println("Game started here...");

    }

    /**
     * TODO: what does stop() provide?
     */
    public void stop() {
        // TODO: stop game and release all connected resources
        System.out.println("Game stopped here.\n Saving status and releasing resources");
    }

    /**
     * TODO: initiate the roll Controller
     */
    private void initiateController(){
        System.out.printf("Initiating Controller...");
    }

    /**
     * TODO: initiate the Map
     */
    private void initiateMap(){
        this.map = Map.getInstance();
        this.map.loadMap();
    }
}
