package com.zombiecastlerush.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zombiecastlerush.building.Room;
import com.zombiecastlerush.role.Role;

/**
 * TODO: this is the utility static class that provides all necessary static methods
 */
class Helper {
    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * encode a Role object into Json formatted string
     * @param role a Role instance
     * @return json formatted string
     * @throws JsonProcessingException
     */
    public static String encodeJsonRole(Role role) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(role);
    }

    /**
     * decode json formatted string to a Role instance
     * @param jsonRole json formatted string - Role
     * @return a Role instance
     * @throws JsonProcessingException
     */
    public static Role decodeJsonRole(String jsonRole) throws JsonProcessingException {
        return mapper.readValue(jsonRole, Role.class);
    }


    /**
     * decode json formatted string to a Room instance
     * @param jsonRoom json formatted string - Room
     * @return a Room instance
     * @throws JsonProcessingException
     */
    public static Room decodeJsonRoom(String jsonRoom) throws JsonProcessingException {
        return mapper.readValue(jsonRoom, Room.class);
    }

    /**
     * encode a Room instance to json formatted string
     * @param room a room instance
     * @return json formatted string
     * @throws JsonProcessingException
     */
    public static String encodeJsonRoom(Room room) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(room);
    }
}
