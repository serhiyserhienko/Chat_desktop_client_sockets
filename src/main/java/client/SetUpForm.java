package client;

import javax.swing.*;
import java.net.Socket;

public class SetUpForm extends JFrame {
    private JTextField nick;
    private JTextField host;
    private JTextField port;
    private JButton Connect;

    private JTextArea textArea1;
    private JPanel jpanel1;

    public SetUpForm() {

        this.setVisible(true);
        this.add(jpanel1);
        this.setSize(300, 300);
        this.setTitle("AppusTalk -anonymous chat-");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Connect.addActionListener(actionEvent -> {
            String str = port.getText();
            Integer i = Integer.parseInt(str);
            Socket s = Main.testConnection(host.getText(), i);
            if (s != null) {
                Main.runClient(nick.getText().toString(), s);
                this.setVisible(false);
            }
        });
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }
}
