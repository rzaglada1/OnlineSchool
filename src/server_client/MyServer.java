package server_client;

import utils.log.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class MyServer implements Runnable{

    String nameLog = "Log OnlineSchool server";

    public static final int PORT = 1111;


    public void startMyServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(PORT));

        System.out.println("Server started. Port = " + PORT);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            String ip = socketChannel.getRemoteAddress().toString().substring(1,
                    socketChannel.getRemoteAddress().toString().indexOf(':'));


            if (!StorageBlackList.getInstance().checkBlackList(ip)) {
                new Thread(new SocketThread(socketChannel, ip), ip).start();
            } else {
                Log.debug(nameLog, "IP " + ip + " in blackList!!!");
                socketChannel.close();
            }
        }
    }

    @Override
    public void run() {
        try {
            startMyServer();
        } catch (IOException e) {
            Log.warning(nameLog, "Server stopped", e.getStackTrace());
        }
    }
}
