package com.zombiecastlerush.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dx", "dy"})
class Coordinates {
    private int dx = -1;
    private int dy = -1;

    @JsonCreator
    public Coordinates(@JsonProperty("dx") int x, @JsonProperty("dy") int y) {
        this.dx = x;
        this.dy = y;
    }

    public void setCoordinates(int x, int y){
        this.dx = x;
        this.dy = y;
    }

    public int getDx(){
        return this.dx;
    }

    public int getDy(){
        return this.dy;
    }
}
