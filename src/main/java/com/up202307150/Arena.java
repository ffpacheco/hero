package com.up202307150;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;




import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;
    private final Hero hero;
    private final List<Wall> walls;
    private final List<Coin> coins;
    private final List<Monster> monsters;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.hero = new Hero(new Position(14,7));
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void restart(){
        this.hero.setPosition(new Position(14, 7));
        this.coins.clear();
        this.monsters.clear();
        this.coins.addAll(createCoins());
        this.monsters.addAll(createMonsters());
    }

    public void processKey(KeyStroke key) {
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

            default: break;
            }
        moveMonsters();

    }


    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins();

        }
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        int coinsCount = 5;

        while (coins.size() < coinsCount) {

            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            Position newPosition = new Position(x, y);

            if (canSpawnAt(newPosition, coins)) {
                coins.add(new Coin(newPosition));
            }
        }
        return coins;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        int monstersCount = 5;
        while (monsters.size() < monstersCount){
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            Position newPosition = new Position(x, y);


            if (canSpawnAt(newPosition, coins) &&  canSpawnAt(newPosition, monsters)) {
                monsters.add(new Monster(newPosition));
            }
        }
        return monsters;
    }

    private boolean canSpawnAt(Position position, List<? extends Element> elements) {

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        for (Element element : elements) {
            if (element.getPosition().equals(position)) {
                return false;
            }
        }
        return !hero.getPosition().equals(position);
    }

    private void retrieveCoins(){
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (hero.getPosition().equals(coin.getPosition())) {
                coins.remove(i);
                break;
            }
        }
    }

    private void moveMonsters(){
        for (Monster monster : monsters){
            Position newPosition= monster.move();
            if (canSpawnAt(newPosition,monsters)){
                monster.setPosition(newPosition);
            }
        }
    }

    public boolean verifyMonsterCollisions(){
        for (Monster monster : monsters) {
            if (hero.getPosition().equals(monster.getPosition())) {
                System.out.println(
                        """
                                   000000    0000000   0000000    000000\s
                                 00      00  00    00  00    00  00
                                 00      00  00    00  00    00  00
                                 00      00  000000    000000     00000\s
                                 00      00  00        00             00
                                 00      00  00        00             00
                                   000000    00        00        000000
                                """);
                    return true;
            }
        }return false;
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3e2d51"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);

        for (Wall wall : walls){
            wall.draw(graphics);
        }
        for (Coin coin : coins){
            coin.draw(graphics);
        }

        for (Monster monster : monsters){
            monster.draw(graphics);
        }

    }

}
