package com.company;

import java.awt.*;
import javax.swing.*;

public class ShowBoard extends JFrame{

    Board board;
    SidePanel sidepanel;
    StartingFrame startingFrame;
    ImageIcon img;

    public void CreateLayout (){
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        img = new ImageIcon("snake-icon (2).jpg");
        setIconImage(img.getImage());
        setTitle("Snake vs Snake");
        board = new Board();
        sidepanel = new SidePanel();
        startingFrame = new StartingFrame();

        add(board, BorderLayout.CENTER);
        add(sidepanel , BorderLayout.WEST);
        pack();
        setLocationRelativeTo(null);
        setVisible(false);

        startingFrame.CreateLayout();
    }
}

