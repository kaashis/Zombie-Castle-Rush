package com.zombiecastlerush.role;

import com.zombiecastlerush.building.Room;

import java.util.List;

/**
 * TODO: what does Player class provide?
 */
public class Player extends Role{

    public Player(String name){
        super(name);
    }

    public Player(String name, Room room) {
        super(name, room);
    }

    /**
     * TODO: move to a room by room name/description
     * @param roomName - name of room to travel to
     */
    public boolean moveTo(String roomName){
        Room targetRoom = this.canMoveToRoom(roomName);
        if(targetRoom != null){
            String previous = super.getCurrentPosition().getName();
            super.setCurrentPosition(targetRoom);
            System.out.printf("Player %s moved from room %s to room %s\n", super.getName(), previous, super.getCurrentPosition().getName());
            return true;
        } else{
            System.out.printf("Player %s's current room %s doesn't connect to destination room or current room challenge is not cleared.", super.getName(), super.getCurrentPosition().getName());
            return false;
        }
    }

    /**
     * decide next valid movement with target room name
     * @param roomName - name of room to travel to
     * @return Room reference if input room name is valid for my next movement
     */
    public Room canMoveToRoom(String roomName) {
        List<Room> validRooms = this.whichRoomNext();
        boolean cleared = this.getCurrentPosition().getChallenge().isCleared();
        if (validRooms != null && cleared) {
            for (Room r : validRooms) {
                if (r.getName().equalsIgnoreCase(roomName))
                    return r; // TODO: is it possible to have rooms with same name? (Xander asking)
            }
        }
        return null;
    }

    /**
     * helper method that provide me a list which room can i go to
     * @return List of Rooms - List of available rooms to travel to.
     */
    private List<Room> whichRoomNext() {
        return (super.getCurrentPosition() == null) ? null : super.getCurrentPosition().getConnectedRooms();
    }
}
