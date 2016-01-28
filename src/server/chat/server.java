package server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by hansoljeong on 2015. 10. 1..
 * When you run this app, first start running server class, then client class. Make sure that these two classes are
 * connected. Then run both server and client GUI classes. As you look at the server GUI, you will see the meessage
 * from the client saying I'm a Client. Then you can start chatting with client.
 */
public class server {

    // would like send and receive the message
    //

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerGUI gui;
    private String message;

    //GUI랑 서버 연동시키기
    public final void setGUI(ServerGUI gui){
        this.gui = gui;
    }
    //서버 세팅 및 연결
    public void setting(){
        try{
            //서버 연결시도 및 에러확인
            serverSocket = new ServerSocket(3000);//서버가 3000포트를 이용하는거다. 3000번 포트를 이용해서 통신
            System.out.println("Connecting....");
            socket = serverSocket.accept();//여기서 accept()는 프로그램을 멈추고 외부소켓접촉 요청을 기다린다.
            System.out.println("socket connected " + socket.getInetAddress());


            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            message = in.readUTF();
            System.out.println("Message from the client: " + message);
            gui.appendMsg(message);

            while(in != null){
                message = in.readUTF();
                gui.appendMsg(message);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        server s = new server();
        s.setting();
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
