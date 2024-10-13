package com.up202307150;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Hero extends Element{
    private Position previousPosition;
    public Hero(Position position) {
        super(position);
        this.previousPosition = position;
    }


    public Position moveLeft(){
        return new Position(getPosition().getX() - 1, getPosition().getY() );
    }
    public Position moveRight(){
        return new Position(getPosition().getX() + 1, getPosition().getY() );
    }
    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }
    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    @Override
    public void setPosition(Position position) {
        this.previousPosition = getPosition();
        super.setPosition(position);
    }
    public Position getPreviousPosition() {
        return previousPosition;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#5fc787"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }


}