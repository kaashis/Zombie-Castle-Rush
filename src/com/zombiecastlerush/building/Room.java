package com.zombiecastlerush.building;

import com.zombiecastlerush.util.Directions;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String,Room> connectedRooms = new HashMap<String,Room>();
    private String [] items;

    //cosntructors
    public Room(String name, String description){
        setName(name);
        setDescription(description);
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return "Room #" + this.name;
    }
}
