package com.up202307150;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

public class Game  {
    public final Screen screen;
    public boolean running = true;
    public final Arena arena;
    private boolean gameOver = false;


    public Game() throws java.io.IOException {

        TerminalSize terminalSize = new TerminalSize(30, 15);

        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        arena = new Arena(30,15);
    }
    public void endGame() throws IOException {
        gameOver=true;
        running=false;
        screen.close();
    }

    public void restart() {
        gameOver = false;
        arena.restart();

    }
    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character) {
            char c = key.getCharacter();
            if (c== ' ' && gameOver){
                restart();
            }
            if (c == 'q' || c == 'Q') {
                endGame();
            }
            if (key.getKeyType() == KeyType.EOF){
                endGame();
            }
        } else {
            arena.processKey(key);
            if (arena.verifyMonsterCollisions()) {
                handleGameOver();
            }
        }
    }

    public void handleGameOver() throws IOException {
        gameOver=true;
        draw();
    }

    public void drawOverScreen(TextGraphics graphics){
        graphics.putString(10,6, "GAME OVER");
        graphics.putString(3,7,"Press 'Space' to restart");
        graphics.putString(7,8," or 'Q' to quit");

    }

    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        if (gameOver){
            drawOverScreen(graphics);
        }
        else{arena.draw(graphics);}

        screen.refresh();
    }


    public void run() throws IOException {
        running = true;
        while(running) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);

        }

    }
}