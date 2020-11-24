package com.company;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;


public class SidePanel extends JPanel {

    public static final int COL_COUNT = 25;

    public static final int ROW_COUNT = 25;

    public static final int TILE_SIZE = 25;

    public String userName1;
    public String userName2;
    public String userName3;
    public String userName4;

    public int scoreForSnake1;
    public int scoreForSnake2;
    public int scoreForSnake3;
    public int scoreForSnake4;

    public boolean waitFlag;

    private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 20);
    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 13);


    public SidePanel() {
        setPreferredSize(new Dimension(320, ROW_COUNT * TILE_SIZE));
        setBackground(new Color(130, 130, 100));
        waitFlag = true;
        scoreForSnake4 = 0;
        scoreForSnake3 = 0;
        scoreForSnake2 = 0;
        scoreForSnake1 = 0;
        userName1 = "";
        userName2 = "";
        userName3 = "";
        userName4 = "";
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);


        //  Draw the game name onto the window.

        g.setFont(LARGE_FONT);
        g.drawString("Snake vs Snake", getWidth() / 2 - g.getFontMetrics().stringWidth("Snake vs Snake") / 2, 50);
        int drawY = 150;
        int drawY2 = 132 ;
        g.setFont(SMALL_FONT);
        g.setColor(Color.GREEN);
        g.fillRect(30,drawY2 += 50  , TILE_SIZE, TILE_SIZE);
        //g.fillRect(50,160 , TILE_SIZE, TILE_SIZE);
        g.drawString(userName1 + ": " + scoreForSnake1 , 65 , drawY += 50);
        g.setColor(Color.CYAN);
        g.fillRect(30,drawY2 += 50  , TILE_SIZE, TILE_SIZE);
        g.drawString(userName2 + ": " + scoreForSnake2 , 65 , drawY += 50);
        g.setColor(Color.magenta);
        g.fillRect(30,drawY2 += 50 , TILE_SIZE, TILE_SIZE);
        g.drawString(userName3 + ": " + scoreForSnake3 , 65 , drawY += 50);
        g.setColor(Color.ORANGE);
        g.fillRect(30,drawY2 += 50 , TILE_SIZE, TILE_SIZE);
        g.drawString(userName4 + ": " + scoreForSnake4 , 65 , drawY += 50);
        if(waitFlag){
            g.setFont(LARGE_FONT);
            g.setColor(Color.BLACK);
            g.drawString("Wait for other players",50, drawY += 100);
        }

    }
}