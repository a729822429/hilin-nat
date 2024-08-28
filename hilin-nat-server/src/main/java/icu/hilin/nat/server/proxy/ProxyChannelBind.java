package icu.hilin.nat.server.proxy;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ProxyChannelBind {

    private final Object lock = new Object();

    @Setter
    private NetSocket serverSocket;
    @Setter
    private NetSocket clientSocket;

    private final List<Buffer> receiveBuffers = new ArrayList<>();

    public void serverReceive(Buffer buf) {
        synchronized (lock) {
            if (buf != null) {
                receiveBuffers.add(buf);
            }
            if (clientSocket != null) {
                for (Buffer receiveBuffer : receiveBuffers) {
                    clientSocket.write(receiveBuffer);
                }
                receiveBuffers.clear();
            }
        }
    }

    public void clientReceive(Buffer buf) {
        synchronized (lock) {
            serverSocket.write(buf);
        }
    }

    public void close() {
        serverSocket.close();
        if (clientSocket != null) {
            clientSocket.close();
        }
    }

}
