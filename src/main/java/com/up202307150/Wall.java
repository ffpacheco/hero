package com.up202307150;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Wall extends Element{

    public Wall(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#25222b")); // Red color for walls
        graphics.putString(getPosition().getX(), getPosition().getY(), "#"); // Using '#' to represent a wall
    }
}
