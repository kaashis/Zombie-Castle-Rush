package com.zombiecastlerush.building;

import java.util.ArrayList;
import java.util.List;


public class Room {
    private String name;
    private String description;
    private List<Room> connectedRooms = new ArrayList<>();
    private String [] items;
    private Challenge challenge = new Challenge("This is a test challenge");

    //cosntructors
    public Room(String name, String description){
        setName(name);
        setDescription(description);
    }

    //Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public String[] getItems() {
        return items;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    @Override
    public String toString(){
        return getName();//+ "Connected Rooms: " + connectedRooms.toString(;
    }

    //Methods
    //add room to the connected rooms List for this room
    public void addConnectedRooms(Room room) {
        this.connectedRooms.add(room);
    }

    //need to work on this
    public void setItems(String[] items) {
        this.items = items;
    }
}