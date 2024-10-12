package com.up202307150;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Wall extends Element{

    public Wall(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#1F1A1E"));
        graphics.fillRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()), new TerminalSize(1, 1), ' ');
    }

}
