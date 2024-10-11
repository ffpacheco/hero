package com.up202307150;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private final Screen screen;

    public Game() throws java.io.IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);

        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();
    }

    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(10, 10, new TextCharacter('X'));
        this.screen.refresh();
    }

    public void run() throws IOException {
        this.draw();
    }
}