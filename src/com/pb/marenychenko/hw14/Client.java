package com.pb.marenychenko.hw14;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static class ClientReader extends Thread{
        BufferedReader inServer;
        public ClientReader(BufferedReader inServer)
        {
            this.inServer=inServer;
        }
        public void run() {
            String dataFromServer;
                while (true) {
                        try {
                            while ((dataFromServer = inServer.readLine()) != null) {
                                System.out.println(dataFromServer);
                            }
                            Thread.currentThread().sleep(400);
                            Thread.currentThread().yield();

                        } catch (Exception ex) {}
                }//while
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Клиент стартовал");
        String serverIp = "127.0.0.1";
        int serverPort = 1234;
        int messageCounter = 0;

        System.out.println("Соединяемся с чатом " + serverIp + ":" + serverPort + " [покинуть чат - exit]");
        Socket server = new Socket(serverIp, serverPort);
        BufferedReader inServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter outServer = new PrintWriter(server.getOutputStream(), true);
        BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));
        ClientReader clientReader = new ClientReader(inServer);
        clientReader.setDaemon(true);
        clientReader.start();
        System.out.print("Введите имя пользователя: ");

        String dataFromUser, dataFromServer;

        while ((dataFromUser = inConsole.readLine()) != null) {
            outServer.println(dataFromUser);
            //dataFromServer = inServer.readLine();
            //System.out.println(dataFromServer);

            if ((messageCounter>0) && ("exit".equalsIgnoreCase(dataFromUser))) {
                break;
            }
            messageCounter++;
        }
        outServer.close();
        inServer.close();
        server.close();
    }
}
