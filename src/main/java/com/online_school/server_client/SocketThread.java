package com.online_school.server_client;

import com.online_school.utils.log.Log;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketThread implements Runnable {
    private SocketChannel socketChannel;
    String nameLog = "Log OnlineSchool";
    String ip;

    public SocketThread(SocketChannel socketChannel, String ip) {
        this.socketChannel = socketChannel;
        this.ip = ip;
    }

    private void sChanel() throws IOException {
        //Response to client
        ByteBuffer writeBuffer = ByteBuffer.wrap(("Connect OK!!! " + '\n').getBytes());
        socketChannel.write(writeBuffer);

        int countBufferRead;
        ByteBuffer bufferRead = ByteBuffer.allocate(4096);

        while ((countBufferRead = socketChannel.read(bufferRead)) > 0) {
            bufferRead.flip();
            byte[] b = new byte[countBufferRead];
            bufferRead.get(b);
            // Printing request
            System.out.println("Server: " + new String(b));
            bufferRead.clear();
        }
        socketChannel.close();
        Log.debug(nameLog, "server socket close");
    }

    @Override
    public void run() {
        Log.debug(nameLog, "Client IP " + ip + " connected");
        try {
            sChanel();
        } catch (IOException e) {
            Log.warning(nameLog, "Socket " + ip + " closed", e.getStackTrace());
        }
    }
}
