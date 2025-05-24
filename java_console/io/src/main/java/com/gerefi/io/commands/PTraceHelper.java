package com.gerefi.io.commands;

import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.config.generated.Integration;
import com.gerefi.tracing.Entry;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.gerefi.binaryprotocol.IoHelper.checkResponseCode;

public class PTraceHelper {
    @NotNull
    public static List<Entry> requestWaitAndGetPTrace(BinaryProtocol bp) {
        bp.executeCommand(Integration.TS_PERF_TRACE_BEGIN, "begin trace");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return executeGetPTraceCommand(bp);
    }

    @NotNull
    private static List<Entry> executeGetPTraceCommand(BinaryProtocol bp) {
        byte[] packet = bp.executeCommand(Integration.TS_PERF_TRACE_GET_BUFFER, "get trace");
        if (!checkResponseCode(packet, (byte) Integration.TS_RESPONSE_OK) || ((packet.length - 1) % 8) != 0)
            throw new IllegalStateException("Unsupported command or error? packet length=" + (packet != null ? 0 : packet.length));

        return Entry.parseBuffer(packet);
    }
}
