package com.zombiecastlerush.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zombiecastlerush.building.Inventory;

@JsonPropertyOrder({"name", "coordinates", "inventory"})
public class Entity {
    private String name;
    private Inventory inventory = new Inventory();
    private String description;
    private Coordinates coordinates = new Coordinates(-1,-1);

    public void setCoordinates(int x, int y){
        this.coordinates.setCoordinates(x,y);
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public Inventory getInventory() {
        return inventory;
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

    public String getName() {
        return name;
    }
}