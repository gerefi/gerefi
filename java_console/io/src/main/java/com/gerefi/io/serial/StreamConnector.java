package com.gerefi.io.serial;

import com.devexperts.logging.Logging;
import com.gerefi.Callable;
import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.io.ConnectionStateListener;
import com.gerefi.io.IoStream;
import com.gerefi.io.LinkConnector;
import com.gerefi.io.LinkManager;

import java.util.Objects;

import static com.devexperts.logging.Logging.getLogging;

/**
 * @author Andrey Belomutskiy
 *         3/3/14
 */
public class StreamConnector implements LinkConnector {
    private static final Logging log = getLogging(StreamConnector.class);

    private final PortHolder portHolder;
    private final LinkManager linkManager;

    public StreamConnector(LinkManager linkManager, Callable<IoStream> ioStreamCallable) {
        this.linkManager = linkManager;

        portHolder = new PortHolder(linkManager, ioStreamCallable);
    }

    @Override
    public void connectAndReadConfiguration(BinaryProtocol.Arguments arguments, ConnectionStateListener listener) {
        Objects.requireNonNull(arguments);
        log.info("StreamConnector: connecting");
        portHolder.listener = listener;
        log.info("scheduleOpening");
        linkManager.execute(() -> {
            log.info("scheduleOpening>openPort");
            portHolder.connectAndReadConfiguration(arguments);
        });
    }

    @Override
    public BinaryProtocol getBinaryProtocol() {
        return portHolder.getBp();
    }

    @Override
    public void stop() {
        portHolder.close();
    }

    @Override
    public String unpack(String packet) {
        return packet;
    }

    @Override
    public void send(String text, boolean fireEvent) throws InterruptedException {
        portHolder.packAndSend(text, fireEvent);
    }
}
