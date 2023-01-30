package server_client;

import utils.log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyClient {
    private String responseClose = "Connect OK!!! ";
    String nameLog = "Log OnlineSchool client";

    public void startMyClient() throws IOException {
        SocketChannel clientSocketChannel = SocketChannel.open();
        clientSocketChannel.connect(new InetSocketAddress("127.0.0.1", MyServer.PORT));


        //Request to server
        ByteBuffer buffer = ByteBuffer.wrap(("Hello from client").getBytes());
        clientSocketChannel.write(buffer);

        // Reading response
        int countBufferRead;
        ByteBuffer bufferRead = ByteBuffer.allocate(4096);

        while ((countBufferRead = clientSocketChannel.read(bufferRead)) > 0) {
            bufferRead.flip();
            byte[] b = new byte[countBufferRead];
            bufferRead.get(b);
            // Printing response
            System.out.println("Client: "+ new String(b));
            bufferRead.clear();
            if (new String(b).contains(responseClose)) {
                break;
            }
        }
        Log.debug(nameLog, "Client finish ");
        clientSocketChannel.close();
    }

}
