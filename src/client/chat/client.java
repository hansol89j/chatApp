package client.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by hansoljeong on 2015. 10. 1..
 */
public class client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGUI gui;
    private String message;

    public final void setGui(ClientGUI gui) {
        this.gui = gui;
    }

    public void connect(){
        try {
            socket = new Socket("localhost", 3000);
            System.out.println("Connected to server");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("I'm a Client.");
            System.out.println("Transmitting message has completed");


            while(in != null){
                message = in.readUTF();
                gui.appendMsg(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        client c = new client();
        c.connect();
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
