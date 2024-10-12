package com.up202307150;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

public class Game  {
    private final Screen screen;
    private int x=10;
    private int y=10;
    private boolean running = true;
    private Hero hero;

    public Game() throws java.io.IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);

        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        hero = new Hero(10,10);
    }
    private void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case EOF:
                running =false;
                break;

            case ArrowDown:
                hero.moveDown();
                break;

            case ArrowUp:
                hero.moveUp();
                break;

            case ArrowRight:
                hero.moveRight();
                break;

            case ArrowLeft:
                hero.moveLeft();
                break;

            case Character:
                readChar(key.getCharacter());
                break;
        }
    }

    private void readChar(char c) throws IOException {
        if (c == 'q' || c == 'Q'){
            screen.close();
            running=false;
        }
    }

    private void draw() throws IOException {

        screen.clear();
        hero.draw(screen);
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