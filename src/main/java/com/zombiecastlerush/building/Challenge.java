package com.zombiecastlerush.building;

/**
 * challenge class provides the challenge description and validation
 * TODO: more methods and attributes
 */
public class Challenge {
    private String description;
    private boolean cleared = true;

    public Challenge(String description){
        //TODO: build our Challenge
    }

    public String getDescription(){
        return this.description;
    }

    /**
     * TODO: what does validation() provide?
     * @param value - boolean for whether or not challenge is cleared
     */
    public void setCleared(boolean value) {
        cleared = value;
    }

    public boolean isCleared() {
        return cleared;
    }
}
