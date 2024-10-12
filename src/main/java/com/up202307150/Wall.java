package com.up202307150;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private final Position position;

    public Wall(int x, int y) {
        this.position=new Position(x,y);
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#25222b")); // Red color for walls
        graphics.putString(position.getX(), position.getY(), "#"); // Using '#' to represent a wall
    }
}
