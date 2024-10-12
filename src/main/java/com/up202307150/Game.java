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
        arena = new Arena(40,20);
    }

    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character) {
            char c = key.getCharacter();
            if (c == 'q' || c == 'Q') {
                running = false;
                screen.close();
            }
        } else {
            arena.processKey(key);
        }
    }
    public void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
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