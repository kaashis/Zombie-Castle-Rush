package com.zombiecastlerush.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zombiecastlerush.building.Room;
import com.zombiecastlerush.role.Role;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelperTest {

    @Test
    public void encodeJsonRole() throws JsonProcessingException {
        Room room = new Room("2", "I am room 2");
        Role role = new Role(1, room);
        String json = Helper.encodeJsonRole(role);
    }

    @Test
    public void decodeJsonRole() {
    }

    @Test
    public void decodeJsonRoom() {
    }

    @Test
    public void encodeJsonRoom() {
    }
}