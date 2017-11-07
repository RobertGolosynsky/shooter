package com.shooter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int port = 4444;
        String host = null;
        if (args.length>1) {
            host = args[1];
        }

        if (host==null){
            try {
                ServerSocket ss = new ServerSocket(port);
                Socket socket = ss.accept();
                socket.getOutputStream().write("Hello".getBytes());
                socket.getOutputStream().flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {


            try {
                final Socket socket = new Socket(host,port);
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Scanner scanner = new Scanner(socket.getInputStream());
                            System.out.println(scanner.nextLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                t.join();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }
}
