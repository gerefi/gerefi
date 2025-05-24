package com.gerefi.binaryprotocol.test;

import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.binaryprotocol.BinaryProtocolLocalCache;
import com.gerefi.io.LinkManager;
import com.gerefi.io.tcp.TcpIoStream;

import java.io.IOException;
import java.net.Socket;

import static com.gerefi.io.tcp.TcpConnector.DEFAULT_PORT;
import static com.gerefi.io.tcp.TcpConnector.LOCALHOST;

/**
 * @see TcpServerSandbox
 */
public class TcpClientSandbox {
    public static void main(String[] args) throws IOException {
        BinaryProtocolLocalCache.DISABLE_LOCAL_CONFIGURATION_CACHE = true;

        Socket s = new Socket(LOCALHOST, DEFAULT_PORT);
        TcpIoStream tsStream = new TcpIoStream("sandbox", s);

        LinkManager linkManager = new LinkManager();
//        SandboxCommon.verifyCrcNoPending(tsStream, linkManager);

        for (int i = 0; i < 3; i++) {
            // warm-up cycles just for fun
            String signature = BinaryProtocol.getSignature(tsStream);
        }


        {
            int count = 10000;
            long startMs = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                // warm-up cycles just for fun
                String signature = BinaryProtocol.getSignature(tsStream);
            }
            long time = System.currentTimeMillis() - startMs;
            double timePerCommand = 1.0 * time / count;
            System.out.println("Executed " + count + " getSignature in " + time + "ms\n" + "Per-signature: " + timePerCommand + "ms");
        }

        {
            //BinaryProtocol bp = new BinaryProtocol(linkManager, tsStream);
            int count = 10000;
            long startMs = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                //  boolean response = bp.requestOutputChannels();
                SandboxCommon.runGetProtocolCommand("dd", tsStream);
            }
            long time = System.currentTimeMillis() - startMs;
            double timePerCommand = 1.0 * time / count;
            System.out.println("Executed " + count + " GetProtocol in " + time + "ms\n" + "Per-GetProtocol: " + timePerCommand + "ms");
            //          log.info("requestOutputChannels " + response);
            //     });


        }
        System.exit(0);
    }

}
