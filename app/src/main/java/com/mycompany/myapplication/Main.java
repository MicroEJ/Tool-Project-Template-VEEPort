package com.mycompany.myapplication;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;

public class Main {

    public static final String HELLO_WORLD = "Hello World";

    public static void main(String[] args) {
        MicroUI.start();

        Display display = Display.getDisplay();
        GraphicsContext g = display.getGraphicsContext();
        int displayWidth = display.getWidth();
        int displayHeight = display.getHeight();

        g.setColor(Colors.BLACK);
        Painter.fillRectangle(g, 0, 0, displayWidth, displayHeight);

        g.setColor(Colors.WHITE);
        Font font = Font.getDefaultFont();
        int x = (displayWidth - font.stringWidth(HELLO_WORLD)) / 2;
        int y = (displayHeight - font.getHeight()) / 2;
        Painter.drawString(g, HELLO_WORLD, font, x, y);

        display.flush();
    }
}