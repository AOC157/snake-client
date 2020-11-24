package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingFrame extends JFrame  {
    public JFrame frame ;
    private JPanel panel ;
    private JLabel userLabel;
    private JTextField userText;
    public String user;
    private JLabel fail;
    private JButton enterButton;
    private ImageIcon img;
    StartingFrame(){

        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,220);
        img = new ImageIcon("snake-icon (2).jpg");
        frame.setIconImage(img.getImage());
        frame.setTitle("Snake vs Snake");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(new Color(65, 222, 127));
        user = "";
        frame.add(panel);
        panel.setLayout(null);
        frame.setResizable(false);
    }

    public void CreateLayout(){

        userLabel = new JLabel("Nickname");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        userLabel.setBounds(75,10,100,95);
        panel.add(userLabel);


        fail = new JLabel("");
        fail.setBounds(145,120,205,95);
        panel.add(fail);


        //for getting the name
        userText = new JTextField(20);
        userText.setBounds(75,75,195,28);
        userText.setDocument(new JTextFieldLimit(20));
        panel.add(userText);


        enterButton = new JButton();
        enterButton.setBounds(120,115,100,30);
        enterButton.setFont(new Font("Arial", Font.PLAIN, 18));
        enterButton.setText("Enter");
        enterButton.setBackground(Color.YELLOW);
        ActionListener listener = new ClickListener();
        enterButton.addActionListener(listener);
        panel.add(enterButton);



        frame.setVisible(true);


    }



    class ClickListener implements ActionListener
    {

        public void actionPerformed(ActionEvent event)
        {
            boolean flag = true ;
            user = userText.getText();

            if(user.equals("")){
                fail.setText("try again !");
            }
            else{
                flag = false;
                System.out.println(user);
            }
            if (!flag){
                frame.dispose();
            }
        }
    }
}

