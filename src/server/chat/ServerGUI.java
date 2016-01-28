package server.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hansoljeong on 2015. 10. 1..
 */
public class ServerGUI extends JFrame implements ActionListener{

    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private server s = new server();

    public ServerGUI(){

        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200, 100, 400, 600);
        setTitle("Server");

        s.setGUI(this);
        s.setting();

    }

    public static void main(String[] args){
        new ServerGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //prints out the text on the screen as you type.
        String message = jtf.getText() + "\n";
        jta.append(message);
        System.out.print(message);

        s.sendMessage(message);

        jtf.setText("");
    }

    public void appendMsg(String message) {
        jta.append(message);
        System.out.print("Receiving message: " + message);
    }
}
