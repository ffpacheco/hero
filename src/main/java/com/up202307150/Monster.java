package com.up202307150;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    private final Random random = new Random();
    public Monster(Position position) {
        super(position);
    }

    public Position move(){
        int direction = random.nextInt(4);
        return switch (direction) {
            case 0 -> new Position(getPosition().getX() + 1, getPosition().getY());
            case 1 -> new Position(getPosition().getX() - 1, getPosition().getY());
            case 2 -> new Position(getPosition().getX(), getPosition().getY() + 1);
            case 3 -> new Position(getPosition().getX(), getPosition().getY() - 1);
            default -> getPosition();
        };

    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3e2d51"));
        graphics.fillRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()), new TerminalSize(1, 1), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#e15048"));

        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }


}
