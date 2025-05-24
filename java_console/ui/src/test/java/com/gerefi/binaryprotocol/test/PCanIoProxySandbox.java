package com.gerefi.binaryprotocol.test;

import com.gerefi.io.can.PCanIoStream;
import com.gerefi.tools.CANConnectorStartup;

import java.io.IOException;

public class PCanIoProxySandbox {
    public static void main(String[] args) throws IOException {
        PCanIoStream stream = PCanIoStream.createStream();
        CANConnectorStartup.start(stream, (status) -> System.out.println("Status: " + status));
    }
}
