package com.company;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        ShowBoard m = new ShowBoard();

        m.CreateLayout();
        while (m.startingFrame.user.equals("")) {
            System.out.print(m.startingFrame.user);
            sleep(500);
        }
        System.out.println("break "+ m.startingFrame.user);
        String myUserName = m.startingFrame.user;
        m.setVisible(true);

        final int PORT = 8888;
        Socket socket = new Socket("localhost", PORT);

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        Snake snake = m.board.snake;

        out.writeUTF(myUserName);
        out.flush();
        snake.name = in.readUTF();
        m.sidepanel.userName1 = in.readUTF();
        m.sidepanel.userName2 = in.readUTF();
        m.sidepanel.userName3 = in.readUTF();
        m.sidepanel.userName4 = in.readUTF();
        m.sidepanel.waitFlag = false;
        switch (snake.name){
            case "snake1":
                snake.direction = 'r';
                snake.type = 1;
                break;
            case "snake2":
                snake.direction = 'd';
                snake.type = 2;
                break;
            case "snake3":
                snake.direction = 'l';
                snake.type = 3;
                break;
            case "snake4":
                snake.direction = 'u';
                snake.type = 4;
                break;
        }

        byte[] tile;

        while (socket.isConnected()) {
            try {
                switch (snake.direction) {
                    case 'l':
                        out.writeUTF("{\"action\":\"move\",\"direction\":\"left\",\"snakeName\":\"" + snake.name + "\"}");
                        out.flush();
                        break;
                    case 'r':
                        out.writeUTF("{\"action\":\"move\",\"direction\":\"right\",\"snakeName\":\"" + snake.name + "\"}");
                        out.flush();
                        break;
                    case 'u':
                        out.writeUTF("{\"action\":\"move\",\"direction\":\"up\",\"snakeName\":\"" + snake.name + "\"}");
                        out.flush();
                        break;
                    case 'd':
                        out.writeUTF("{\"action\":\"move\",\"direction\":\"down\",\"snakeName\":\"" + snake.name + "\"}");
                        out.flush();
                        break;
                }
                tile = in.readNBytes(625);
                m.sidepanel.scoreForSnake1 = in.readInt();
                m.sidepanel.scoreForSnake2 = in.readInt();
                m.sidepanel.scoreForSnake3 = in.readInt();
                m.sidepanel.scoreForSnake4 = in.readInt();
                m.board.apple.score = in.readInt();
                m.board.convertTo2D(tile);
                m.board.repaint();
                m.sidepanel.repaint();
                sleep(500);
            }
            catch (SocketException | EOFException s){
                break;
            }
        }
        socket.close();
        snake.win = m.board.checkWin();
        snake.lose = !snake.win;
        m.board.repaint();
        System.out.println("done");
    }
}