package com.up202307150;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    public Coin(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3e2d51"));
        graphics.fillRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()), new TerminalSize(1, 1), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#e69434"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "C");
    }
}
