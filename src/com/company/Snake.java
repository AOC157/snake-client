package com.company;

public class Snake {
    public char direction;
    public int snakeScore;
    public String name;
    public int type;
    public boolean win;
    public boolean lose;

    public Snake() {
        snakeScore = 0;
        win = false;
        lose = false;
    }
}