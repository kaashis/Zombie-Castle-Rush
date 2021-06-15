package com.zombiecastlerush.building;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zombiecastlerush.entity.Coordinates;
import com.zombiecastlerush.entity.Entity;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Castle {
    //create a map of rooms in castle
    private Map<String, Room> castleRooms = new HashMap<>();

    //Ctor
    public Castle() {
        // create rooms
        Room eastWing = new Room("East-Wing", "Another box is here. Sounds like the moans of a man in agony grow louder as you venture deeper into this room.");
        Room westWing = new Room("West-Wing", "Eerily quiet, only a box awaits you in this chamber.");
        Room castleHall = new Room("Castle-Hall", "It is cold, dark, and empty, save for a dimly lit, white box. ");
        Room drawBridge = new Room("Draw-Bridge", "The bridge is up, and there is no way to the other side. Nothing about the giant, open castle doors looks inviting, but alas, it is the only way forward. A box lays on the ground right before the doorway.");
        Room combatHall = new Room("Combat-Hall", "Festooned with the arms and armor of warriors past, this room is better lit than the others. In the middle of the room, a single coffin has been left slightly open, its lid closed just enough to obscure the contents from view");
        Shop shop = new Shop("Shop", "A strangely silent shopkeeper seems to preside over a collection of wares, oblivious or indifferent to your presence.");

        //add coordinates
        eastWing.setCoordinates(600,200);
        westWing.setCoordinates(200,200);
        castleHall.setCoordinates(200,600);
        drawBridge.setCoordinates(600,600);
        shop.setCoordinates(400,400);
        combatHall.setCoordinates(700,400);

        //add connected rooms to room
        eastWing.addConnectedRooms(castleHall, combatHall);
        castleHall.addConnectedRooms(drawBridge, eastWing, westWing, shop);
        drawBridge.addConnectedRooms(westWing, castleHall);
        westWing.addConnectedRooms(castleHall, drawBridge);
        combatHall.addConnectedRooms(eastWing);
        shop.addConnectedRooms(castleHall);

        //add Challenge to room
        eastWing.setChallenge(new Puzzle("East-Wing-Puzzle", "What is (2+2) X (2-2)?", "0"));
        eastWing.getChallenge().getInventory().addItems(new Item("Knife", "This is a knife", 25.0),new Item("Sword", "The Sword of Light", 100));
        westWing.setChallenge(new Puzzle("West-Wing-Puzzle", "What is (2+2) X (2-2)?", "0"));
        westWing.getChallenge().getInventory().addItems(new Item("Spoon", "This is a spoon", 25.0));
        castleHall.setChallenge(new Puzzle("Castle-Hall-Puzzle", "What is (2+2) X (2-2)?", "0"));
        castleHall.getChallenge().getInventory().addItems(new Item("Fork", "This is a fork", 5.0));
        drawBridge.setChallenge(new Puzzle("Draw-Bridge-Puzzle", "What is (2+2) X (2-2)?", "0"));
        drawBridge.getChallenge().getInventory().addItems(new Item("Vase", "This is a vase", 5.0));
        combatHall.setChallenge(new Combat("Life or Death Battle"));
        combatHall.setExit(true);


        //add items to Rooms inventory
        shop.getInventory().addItems(
                new Item("Sword", "This is the sword of Destiny, made up of the Valyrian Steel", 100.0),
                new Item("Helmet", "This is the ultimate shield which will be carried by Captain America in the distant future", 50.0),
                new Item("Potion", "Drinking this potion will restore your health", 100.0)
        );

        //Add rooms to castleRooms
        castleRooms.put(eastWing.getName(), eastWing);
        castleRooms.put(westWing.getName(), westWing);
        castleRooms.put(castleHall.getName(), castleHall);
        castleRooms.put(drawBridge.getName(), drawBridge);
        castleRooms.put(combatHall.getName(), combatHall);
        castleRooms.put(shop.getName(), shop);
    }

    //getter
    public Map<String, Room> getCastleRooms() {
        return castleRooms;
    }

    @Override
    public String toString() {
        return castleRooms.keySet().toString();
    }

    /**
     * read rooms from a .json file and convert it into a list of room objects
     * this function can assign above list of rooms to Inventory reference
     * it can also return above list of rooms
     * TODO: this can be combined into a generic class that converts any type Entity to json
     * @param file      where we save the .json file
     * @return a list of Rooms
     * @throws IOException
     */
    public void readItemsFromDir(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Invalid output file path");
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file);
        if(root.isArray()){
            for(int i = 0; i < root.size(); i++){
                Room room = this.convertJsonToRoom(root.path(i),mapper);
                this.castleRooms.put(room.getName(), room);
            }
        }
    }

    /**
     * helper method that manually convert jsonnode to Room object
     * @param jsonRoom
     * @param mapper
     * @return
     */
    private Room convertJsonToRoom(JsonNode jsonRoom, ObjectMapper mapper){
        //System.out.println(jsonRoom);
        String name = jsonRoom.path("name").asText();
        String description = jsonRoom.path("description").asText();
        Room newRoom = new Room(name, description);
        JsonNode connectedRooms = jsonRoom.path("connectedRooms");
        if(connectedRooms.isArray()){
            for(JsonNode room : connectedRooms){
                System.out.println(room.asText());
            }
        }
        JsonNode challengeJson = jsonRoom.path("challenge");
        this.convertJsonToChallenge(newRoom, challengeJson, mapper);
        JsonNode inventoryJson = jsonRoom.path("inventory").path("items");
        this.convertJsonToInventory(newRoom, inventoryJson, mapper); // add inventory
        JsonNode coordinates = jsonRoom.path("coordinates");
        Coordinates cor = mapper.convertValue(coordinates, Coordinates.class);
        newRoom.setCoordinates(cor.getDx(), cor.getDy());
        return newRoom;
    }

    /**
     * TODO: convert automatically with annotation or customized deserializer
     * @param jsonChall
     * @param mapper
     * @return
     */
    private void convertJsonToChallenge(Room room, JsonNode jsonChall, ObjectMapper mapper){
        Challenge challenge = new Puzzle(jsonChall.path("description").asText(),jsonChall.path("question").asText(),jsonChall.path("solution").asText());
        challenge.setCleared(jsonChall.path("cleared").asBoolean());
        System.out.println(jsonChall.path("inventory"));
        this.convertJsonToInventory(challenge,jsonChall.path("inventory").path("items"),mapper);
        room.setChallenge(challenge);
    }

    private void convertJsonToInventory(Entity entity, JsonNode jsonInventory, ObjectMapper mapper){
        if(jsonInventory.isArray()){
            for(JsonNode item : jsonInventory) {
                Item i = mapper.convertValue(item, Item.class);
                System.out.println(i);
                entity.getInventory().addItems(i);
            }
        }
    }

    /**
     * write a list of Rooms to a .json file
     *
     * @param file  target output file
     * @throws IOException
     */
    public void writeItemsToDir(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Invalid output file path");
        }
        ObjectMapper mapper = new ObjectMapper();
        if (!file.exists()) {
            file.createNewFile(); // create one if not exist
        }
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(file, this.getCastleRooms().values());
    }
}