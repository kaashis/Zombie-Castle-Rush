package com.zombiecastlerush.building;

import com.zombiecastlerush.role.Player;
import junit.framework.TestCase;
import org.junit.Test;

public class ChallengeTest extends TestCase {

    private Player player = new Player("Player name # 1");
    Room room0 = new Room("room-0", "ENTRY room");
    Room room1 = new Room("room-1", "ENTRY room");

    @Override
    public void setUp() throws Exception {
        super.setUp();
        room0.addConnectedRooms(room1);
        player.setCurrentPosition(room0);
    }

    @Test
    public void testPlayerCanMoveToNextRoomIfChallengeIsCleared() {
        assertNotNull(player.canMoveToRoom(room1.getName()));
    }

    @Test
    public void testPlayerCanNotMoveToNextRoomIfChallengeIsNotCleared() {
        room0.getChallenge().setCleared(false);
        assertNull(player.canMoveToRoom(room1.getName()));
    }
}