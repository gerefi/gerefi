package com.gerefi.io.tcp;

import com.gerefi.core.FileUtil;

import java.io.Closeable;
import java.net.ServerSocket;

public class ServerSocketReference implements Closeable {
    private final ServerSocket serverSocket;
    private boolean isClosed;

    public ServerSocketReference(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void close() {
        isClosed = true;
        FileUtil.close(serverSocket);
    }

    public boolean isClosed() {
        return isClosed;
    }
}
