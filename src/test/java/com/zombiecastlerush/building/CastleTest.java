package com.zombiecastlerush.building;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class CastleTest {
    private Castle castle = new Castle();
    private String inputPath = System.getProperty("user.dir").concat("\\assets\\rooms.json");
    private String outputPath = System.getProperty("user.dir").concat("\\assets\\rooms.json");
    private File outputFile = new File(outputPath);
    private File inputFile = new File(inputPath);


    @Test
    public void testReadItemsFromDir() throws IOException {
        assertEquals(castle.getCastleRooms().size(),6);
        castle.getCastleRooms().clear();
        assertEquals(castle.getCastleRooms().size(),0);
        castle.readItemsFromDir(inputFile);
        assertEquals(castle.getCastleRooms().size(),6);
        castle.writeItemsToDir(outputFile);
    }

    @Test
    public void testWriteItemsToDir() throws IOException {
        File file = new File(outputPath);
        castle.writeItemsToDir(file);
    }
}