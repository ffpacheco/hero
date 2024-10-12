package com.up202307150;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveLeft(){
        x--;
    }
    public void moveRight(){
        x++;
    }
    public void moveDown(){
        y++;
    }
    public void moveUp(){
        y--;
    }

    public void draw(Screen screen){
        screen.setCharacter(x, y, TextCharacter.fromCharacter ('X')[0]);
    }
}
