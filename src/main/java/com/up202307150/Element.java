package com.up202307150;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;
    public Element(Position position){

            this.position=position;
    }

    public Position getPosition(){
            return position;
    }

    public void setPosition(Position position){
        this.position=position;
    }
    public abstract void draw(TextGraphics graphics);
}
