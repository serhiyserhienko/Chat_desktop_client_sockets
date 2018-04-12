package client;

import java.io.*;
import java.net.Socket;

public class Main {

    private static Socket clientSocket;
    private static BufferedReader socketReader;
    private static BufferedWriter socketWriter;

    public static String NICKNAME = "Unnamed";
    public static String HOST_ADDRESS = "localhost";
    public static int HOST_PORT = 8383;

    private static client.ChatView view;
    private static client.SetUpForm setUpForm;

    public static void main(String[] args) {

        client.SetUpForm view = new client.SetUpForm();

    }

    public static void runClient(String nick, Socket s) {
        try {
            NICKNAME = nick;
            clientSocket = s;
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.ChatView view = new client.ChatView(socketWriter);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String str = socketReader.readLine();
                    view.getChat().append(str + "\n");
                    if (!view.getChat().isFocusOwner()) {
                        view.getChat().setCaretPosition(view.getChat().getDocument().getLength());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }

    public static Socket testConnection(String host, Integer port) {

        Socket s = null;
        try {
            s = new Socket(host, port.intValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
