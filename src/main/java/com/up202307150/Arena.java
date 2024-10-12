package com.up202307150;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private final Hero hero;
    private final List<Wall> walls;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.hero = new Hero(new Position(10,10));
        this.walls = createWalls();
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
    public boolean canHeroMove(Position position) {

        if (position.getX() < 0 || position.getX() >= width  || position.getY() < 0 || position.getY() >= height) {
            return false;
        }

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c,0)));

            walls.add(new Wall (new Position( c, height - 1)));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall (new Position(0, r)));
            walls.add(new Wall(new Position( width - 1, r)));
        }
        return walls;
    }

    public void draw(TextGraphics graphics) throws IOException{
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3e2d51"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new
                        TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls){
            wall.draw(graphics);
        }
    }

}
