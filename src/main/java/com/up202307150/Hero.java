package com.up202307150;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class Hero {
    private Position position;

    public Hero(Position position) {
        this.position=position;
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY() );
    }
    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY() );
    }
    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public void setPosition(Position position){
        this.position=position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#5fc787"));
                graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "X");
    }


}