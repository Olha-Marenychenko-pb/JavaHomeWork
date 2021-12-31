package com.pb.marenychenko.hw15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientV2 implements Runnable{
    private JTextArea textArea1;
    private JButton sendButton;
    private JPanel mainPanel;
    private JTextField textField1;
    private JFrame myWindow;
    private PrintWriter outServer;
    private int messageCounter =0;
    @Override
    public void run() {
        myWindow = new JFrame("Чат на Swing");
        myWindow.setContentPane(this.mainPanel);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.pack();
        textField1.requestFocusInWindow();
        myWindow.setVisible(true);
    }

    private void doClick()   {
        String message = textField1.getText();
        outServer.println(message);
        textField1.setText("");
        if (messageCounter!=0) {
            if ("exit".equalsIgnoreCase(message)) {
                exit();
            }
        }
        else {messageCounter++;}
    };
    public void exit(){
        myWindow.setVisible(false); //you can't see me!
        myWindow.dispose(); //Destroy
        System.exit(0);
    }
    private void addMessage(String msg){
        textArea1.append(msg + "\n");
    };

    private void addRequest(String msg){

        textField1.requestFocusInWindow();
        textField1.setText(msg);
        textField1.selectAll();
    };
    public ClientV2(PrintWriter outServer) {
        this.outServer = outServer;
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClick();
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    doClick();
                }
                else {
                    super.keyPressed(e);
                }
            }
        });
    }


    public static void main(String[] args) throws Exception {
        class ClientReader extends Thread{
            BufferedReader inServer;
            ClientV2 clientV2;
            public ClientReader(ClientV2 clientV2, BufferedReader inServer)
            {
                this.inServer=inServer;
                this.clientV2=clientV2;
            }
            public void run() {
                String dataFromServer;
                while (true) {
                    try {
                        while ((dataFromServer = inServer.readLine()) != null) {
                            clientV2.addMessage(dataFromServer);
                        }
                        Thread.currentThread().sleep(400);
                        Thread.currentThread().yield();

                    } catch (Exception ex) {}
                }//while
            }
        }
        ClientReader clientReader;
        ClientV2 clientV2 = null;

        String serverIp = "127.0.0.1";
        int serverPort = 1234;
        try(
                Socket server = new Socket(serverIp, serverPort);
                BufferedReader inServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter outServer = new PrintWriter(server.getOutputStream(), true);
                )
        {
            clientV2 = new ClientV2(outServer);
            SwingUtilities.invokeLater(clientV2);
            clientV2.addMessage("Соединяемся с чатом " + serverIp + ":" + serverPort + " [покинуть чат - exit]");
            clientReader = new ClientReader(clientV2, inServer);
            clientReader.setDaemon(true);
            clientReader.start();
            clientV2.addRequest("Введите имя пользователя");
            Thread.currentThread().join();
        }
        catch (Exception ex) {
            if ((clientV2 != null)&&(clientV2.myWindow.isVisible())){
            clientV2.addMessage("ex");
            clientV2.addMessage("Завершаем работу..");
            Thread.currentThread().sleep(2000);
            clientV2.exit();}
            else {
                System.out.println(ex);
            }
        }

    }
}

