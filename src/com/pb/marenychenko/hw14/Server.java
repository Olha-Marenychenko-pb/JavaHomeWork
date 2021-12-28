package com.pb.marenychenko.hw14;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static class Handler extends Thread {
        private final Socket socket;
        private String userName;
        private BufferedReader in = null;
        private PrintWriter out = null;
        public static Map<String, PrintWriter> concurrentHashMap = null;

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }
        public void sendAll(String serverMessage){
            concurrentHashMap.forEach((k, v) -> v.println(serverMessage));
        }

        public Handler(Socket socket, Map<String, PrintWriter>concurrentHashMap) {
            this.socket = socket;
            this.concurrentHashMap = concurrentHashMap;
        }

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            int messageCounter = 0;
            String serverMessage;
            String clientMessage;
            boolean endChat = false;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                concurrentHashMap.put(Thread.currentThread().getName(),out);

                while (!endChat &&(clientMessage = in.readLine()) != null) {

                    if ( messageCounter == 0)
                    {
                        setUserName(clientMessage.trim());
                        serverMessage = formatter.format(new Date()) + ": " + userName + " вошел в чат";
                        //out.println(serverMessage);
                        sendAll(serverMessage);
                    }
                    else if ("exit".equalsIgnoreCase(clientMessage)) {
                        serverMessage = formatter.format(new Date()) + ": " + userName + " покинул чат";
                        //out.println(serverMessage);
                        sendAll(serverMessage);
                        endChat = true;
                        concurrentHashMap.remove(Thread.currentThread().getName());
                    }
                    else {
                    serverMessage = formatter.format(new Date()) + " # " + userName+ ": " + clientMessage;
                    //out.println(serverMessage);
                    sendAll(serverMessage);
                    }
                    messageCounter++;
                    System.out.println("Сообщение от клиента " + Thread.currentThread().getName() + ": " + clientMessage);
                    System.out.println("Ответ сервера : " + serverMessage);

                }
                out.close();
                in.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    concurrentHashMap.remove(Thread.currentThread().getName());
                    socket.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 1234;
        Thread th;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен на порту : " + port);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Map<String, PrintWriter> concurrentHashMap = new ConcurrentHashMap<String, PrintWriter>();
        while (true) {
            Socket clientSocket = serverSocket.accept();
            th = new Handler(clientSocket, concurrentHashMap);
            threadPool.submit(th);
        }
    }
}
