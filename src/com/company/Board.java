package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Board extends JPanel {
    Snake snake;

    JButton direction;

    Apple apple;

    public static final int COL_COUNT = 25;

    public static final int ROW_COUNT = 25;

    public static final int TILE_SIZE = 25;

    private static final int EYE_SMALL_INSET = TILE_SIZE / 6;
    private static final int EYE_LENGTH = TILE_SIZE / 5;
    private static final int EYE_LARGE_INSET = TILE_SIZE / 3;

    private static final Font FONT = new Font("Tahoma", Font.BOLD, 60);
    private static final Font FontForApple = new Font("Tahoma", Font.BOLD, 21);

    public static final int BLOCK = -10;
    public static final int SNAKE1 = 1;
    public static final int SNAKE2 = 2;
    public static final int SNAKE3 = 3;
    public static final int SNAKE4 = 4;
    public static final int SNAKE1HEAD = -1;
    public static final int SNAKE2HEAD = -2;
    public static final int SNAKE3HEAD = -3;
    public static final int SNAKE4HEAD = -4;
    public static final int EMPTY = 0;
    public static final int APPLE = 10;


    public byte[][] tiles;


    public Board(){
        snake = new Snake();


        tiles = new byte[ROW_COUNT][COL_COUNT];
        apple = new Apple();
        setPreferredSize(new Dimension(COL_COUNT * TILE_SIZE, ROW_COUNT * TILE_SIZE));
        setBackground(Color.lightGray);
        clearBoard();
        createButton();
        direction.setOpaque(false);
        direction.setContentAreaFilled(false);
        direction.setBorderPainted(false);
        add(direction);
    }

    public void clearBoard() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for(int j = 0 ; j < COL_COUNT ; j++){
                tiles[i][j] = EMPTY;
            }
        }
    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < COL_COUNT; x++) {
            for (int y = 0; y < ROW_COUNT; y++) {
                int type = getTile(x, y);
                if (type != EMPTY) {
                    drawTile(x * TILE_SIZE, y * TILE_SIZE, type, g);
                }
            }
        }

        //for lines between
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        for (int x = 0; x < COL_COUNT; x++) {
            for (int y = 0; y < ROW_COUNT; y++) {
                g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, getHeight());
                g.drawLine(0, y * TILE_SIZE, getWidth(), y * TILE_SIZE);
            }
        }

        if(snake.lose){
            gameOver(g);
        }
        if(snake.win){
            win(g);
        }
    }
    public  void drawTile(int x, int y, int type, Graphics g) {

        int radius = TILE_SIZE/2;
        if(type == BLOCK){
            g.setColor(Color.BLACK);
            g.fillRect(x,y,TILE_SIZE,TILE_SIZE);
        }
        else if(type == APPLE ){  // apple
            g.setColor(Color.RED);
            g.setFont(FontForApple);
            g.fillOval(x + 2, y + 2 , TILE_SIZE - 4, TILE_SIZE - 4);
            g.setColor(new Color(102,51,0));
            g.drawString(String.valueOf(apple.score),x+radius-5,y+radius+8);
        }
        else if(type == SNAKE1HEAD){
            g.setColor(new Color(0, 209, 65));
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            if(snake.name.equals("snake1")){
                drawEye(x,y,g);
            }
        }
        else if(type == SNAKE1){  // snake1
            g.setColor(Color.GREEN);
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
        else if(type == SNAKE2HEAD){
            g.setColor(new Color(0, 172, 220));
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            if(snake.name.equals("snake2")){
                drawEye(x,y,g);
            }
        }
        else if(type == SNAKE2){  // snake2
            g.setColor(Color.CYAN);
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
        else if(type == SNAKE3HEAD){
            g.setColor(new Color(220, 0, 78));
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            if(snake.name.equals("snake3")){
                drawEye(x,y,g);
            }
        }
        else if(type == SNAKE3){  // snake3
            g.setColor(Color.MAGENTA);
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
        else if(type == SNAKE4HEAD){
            g.setColor(new Color(220, 146, 4));
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            if(snake.name.equals("snake4")){
                drawEye(x,y,g);
            }
        }
        else if(type == SNAKE4){  // snake4
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }

    public void drawEye(int x, int y ,Graphics g) {
        g.setColor(Color.BLACK);
        switch (snake.direction) {
            case 'u': {
                int baseY = y + EYE_SMALL_INSET;
                g.drawLine(x + EYE_LARGE_INSET, baseY, x + EYE_LARGE_INSET, baseY + EYE_LENGTH);
                g.drawLine(x + TILE_SIZE - EYE_LARGE_INSET, baseY, x + TILE_SIZE - EYE_LARGE_INSET, baseY + EYE_LENGTH);
                break;
            }
            case 'd': {
                int baseY = y + TILE_SIZE - EYE_SMALL_INSET;
                g.drawLine(x + EYE_LARGE_INSET, baseY, x + EYE_LARGE_INSET, baseY - EYE_LENGTH);
                g.drawLine(x + TILE_SIZE - EYE_LARGE_INSET, baseY, x + TILE_SIZE - EYE_LARGE_INSET, baseY - EYE_LENGTH);
                break;
            }
            case 'r': {
                int baseX = x + TILE_SIZE - EYE_SMALL_INSET;
                g.drawLine(baseX, y + EYE_LARGE_INSET, baseX - EYE_LENGTH, y + EYE_LARGE_INSET);
                g.drawLine(baseX, y + TILE_SIZE - EYE_LARGE_INSET, baseX - EYE_LENGTH, y + TILE_SIZE - EYE_LARGE_INSET);
                break;
            }

            case 'l': {
                int baseX = x + EYE_SMALL_INSET;
                g.drawLine(baseX, y + EYE_LARGE_INSET, baseX + EYE_LENGTH, y + EYE_LARGE_INSET);
                g.drawLine(baseX, y + TILE_SIZE - EYE_LARGE_INSET, baseX + EYE_LENGTH, y + TILE_SIZE - EYE_LARGE_INSET);
                break;
            }
        }
    }
    private void createButton() {
        direction = new JButton();
        direction.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    snake.direction = 'd';
                    System.out.println("move down");
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    snake.direction = 'u';
                    System.out.println("move up");
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    snake.direction = 'r';
                    System.out.println("move right");
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    snake.direction = 'l';
                    System.out.println("move left");
                }
            }
        });
    }

    public void convertTo2D(byte[] tile) {
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                tiles[i][j] = tile[i * 25 + j];
            }
        }
    }

    public void gameOver(Graphics g){
        g.setFont(FONT);
        g.setColor(new Color(0,102,0));
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        String Message = "Game over";
        g.drawString(Message, (centerY) - g.getFontMetrics().stringWidth(Message) / 2, centerY - 50);

    }
    public void win(Graphics g){
        g.setFont(FONT);
        g.setColor(new Color(153,0,255));
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        String Message = "You Win";
        g.drawString(Message, (centerY) - g.getFontMetrics().stringWidth(Message) / 2, centerY - 50);
    }

    public boolean checkWin() {
        for (int i = 0; i < ROW_COUNT; i++){
            for (int j = 0; j < COL_COUNT; j++){
                if(tiles[i][j] != EMPTY && tiles[i][j] != APPLE && tiles[i][j] != BLOCK && tiles[i][j] != snake.type && tiles[i][j] != -1 * snake.type){
                    return false;
                }
            }
        }
        return true;
    }
}