package com.up202307150;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private final int width;
    private final int height;
    private final Hero hero;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.hero = new Hero(new Position(10,10));
    }
    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowDown:
                moveHero(hero.moveDown());
                break;

            case ArrowUp:
                moveHero(hero.moveUp());
                break;

            case ArrowRight:
                moveHero(hero.moveRight());
                break;

            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;

            default:
                break;
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    public boolean canHeroMove(Position position){
        return position.getX()>=0 && position.getX() < width && position.getY()>=0 && position.getY() < height;
    }
    public void draw(Screen screen) throws IOException{
        hero.draw(screen);
    }
}
