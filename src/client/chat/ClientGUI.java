package client.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hansoljeong on 2015. 10. 1..
 */
public class ClientGUI extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private client c = new client();

    public ClientGUI(){

        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(800, 100, 400, 600);
        setTitle("Client");

        c.setGui(this);
        c.connect();
    }

    public static void main(String[] args){
        new ClientGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //prints out the text on the screen as you type.
        String message = jtf.getText() + "\n";
        jta.append(message);
        System.out.print(message);
        c.sendMessage(message);
        jtf.setText("");
    }

    public void appendMsg(String message) {
        jta.append(message);
    }
}
