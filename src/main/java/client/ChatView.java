package client;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.IOException;


public class ChatView extends JFrame {
    private JPanel panel1;
    private JTextArea message;
    private JTextArea chat;

    public ChatView(BufferedWriter socketWriter) {

        this.setVisible(true);
        this.add(panel1);
        this.setSize(300, 300);
        this.setTitle("AppusTalk -anonymous chat-");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                String messageText = message.getText().replace("\n", "");
                if (keyEvent.getKeyCode() == 10) {
                    if (!messageText.isEmpty()) {
                        try {
                            String m = Main.NICKNAME + ": " + message.getText();
                            socketWriter.write(m);
                            socketWriter.flush();
                            message.setText(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        message.setText("");
                    }
                }
            }
        });
    }

    public JTextArea getMessage() {
        return message;
    }

    public void setMessage(JTextArea message) {
        this.message = message;
    }

    public JTextArea getChat() {
        return chat;
    }

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }
}
