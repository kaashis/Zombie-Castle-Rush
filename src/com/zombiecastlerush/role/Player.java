package com.zombiecastlerush.role;

import com.zombiecastlerush.building.Room;
import com.zombiecastlerush.util.Directions;

/**
 * TODO: what does Player class provide?
 */
class Player extends Role{

    public Player(int id) {
        super(id);
    }

    public Player(int id, Room room) {
        super(id, room);
    }

    /**
     * TODO: move to a room by direction
     * @param direction
     */
    public void moveTo(Directions direction){

    }

    /**
     * TODO: move to a room by room name/description
     * @param roomName
     */
    public void moveTo(String roomName){

    }
}
