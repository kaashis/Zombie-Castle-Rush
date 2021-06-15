package com.zombiecastlerush.building;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class CastleTest {
    private Castle castle = new Castle();
    private String inputPath = System.getProperty("user.dir").concat("\\assets\\rooms.json");
    private String outputPath = System.getProperty("user.dir").concat("\\target\\rooms.json");
    private File outputFile = new File(outputPath);
    private File inputFile = new File(inputPath);


    @Test
    public void testReadItemsFromDir() throws IOException {
        System.out.println(castle.getCastleRooms().size());
        castle.getCastleRooms().clear();
        System.out.println(castle.getCastleRooms().size());

        castle.readItemsFromDir(inputFile);
        System.out.println(castle.getCastleRooms().size());
        castle.writeItemsToDir(outputFile);
    }

    @Test
    public void testWriteItemsToDir() throws IOException {
        File file = new File(outputPath);
        castle.writeItemsToDir(file);
    }
}