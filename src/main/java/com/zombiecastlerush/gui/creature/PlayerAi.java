package com.zombiecastlerush.gui.creature;

import com.zombiecastlerush.gui.layout.Tile;

public class PlayerAi extends CreatureAi {

    public PlayerAi(Creature creature) {
        super(creature);
    }

    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround() || tile.isDoor()) {
            creature.x = x;
            creature.y = y;
        }
    }
}