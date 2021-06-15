package com.zombiecastlerush.util;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * this class serves as a desktop GUI interface
 * in iteration 3, it only display the game map and current player's position
 */
class DesktopLauncher implements Runnable{
    private Thread desktopGameThread;
    private static DesktopLauncher desktopLauncher;
    private DesktopGame desktopGame;

    private DesktopLauncher(){
        this.desktopGame = new DesktopGame();
    }

    public static DesktopLauncher getInstance(){
        if(DesktopLauncher.desktopLauncher == null){
            DesktopLauncher.desktopLauncher = new DesktopLauncher();
        }
        return DesktopLauncher.desktopLauncher;
    }

    @Override
    public void run() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Zombie Castle Rush");
        config.setWindowedMode(1200, 800);
        new Lwjgl3Application(this.desktopGame, config);
    }

    /**
     * start displaying game map in a new thread
     */
    public void start(){
        System.out.println("Displaying game map on your desktop");
        if(this.desktopGameThread == null){
            this.desktopGameThread = new Thread(this, "DesktopLauncher");
            this.desktopGameThread.start();
        }
    }

    /**
     * prompt the user
     */
    public void stop(){
        if(!this.desktopGame.isCleaned() && !this.isRunning())
            System.out.println("Please exit the game window.");
    }

    public boolean isRunning(){
        return this.desktopGameThread == null;
    }
}