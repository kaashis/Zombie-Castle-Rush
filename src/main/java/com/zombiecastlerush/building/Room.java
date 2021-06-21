package com.zombiecastlerush.building;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zombiecastlerush.entity.Entity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zombiecastlerush.util.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder({"name", "description", "connectedRoomNames", "challenge", "inventory"})
public class Room extends Entity {
    private boolean isExit;
    @SuppressWarnings("FieldMayBeFinal")
    @JsonIgnore
    private List<Room> connectedRooms = new ArrayList<>();
    private List<String> connectedRoomNames = new ArrayList<>();
    private Challenge challenge;

    //constructors
    public Room () {}

    public Room(String name, String description) {
        super.setName(name);
        super.setDescription(description);
    }

    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public List<String> getConnectedRoomNames() {
        return connectedRoomNames;
    }

    /**
     * @return
     */
   /* @JsonGetter("connectedRooms")
    public List<String> displayConnectedRooms() {
        List<String> list = new ArrayList<>();
        for (Room r : this.connectedRooms) {
            list.add(r.getName());
        }
        return list;
    }*/

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean value) {
        isExit = value;
    }

    @Override
    public String toString() {
        return Parser.YELLOW + getName() + Parser.ANSI_RESET;//+ "Connected Rooms: " + connectedRooms.toString(;
    }

    //Methods
    //add room to the connected rooms List for this room
    public void addConnectedRooms(Room... rooms) {
        this.connectedRooms.addAll(Arrays.asList(rooms));
    }

    public void addConnectedRoomNames() {
        for (Room room : connectedRooms) {
            getConnectedRoomNames().add(room.getName());
        }
    }
}
