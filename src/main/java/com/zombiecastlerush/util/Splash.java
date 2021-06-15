package com.zombiecastlerush.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Splash {
    public static void print() throws FileNotFoundException, InterruptedException {
        File startFile = new File("../Zombie-Castle-Rush/Splash.txt");
        Scanner scan = new Scanner(startFile);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
            Thread.sleep(90);
        }
    }
}