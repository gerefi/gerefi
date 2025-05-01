package com.gerefi.io.commands;

import com.gerefi.config.generated.VariableRegistryValues;
import com.gerefi.io.IoStream;
import com.gerefi.io.tcp.BinaryProtocolServer;

import java.io.IOException;

public class GetOutputsCommandBrokenHelper {
    public static byte[] createRequest() {
        // problem: unused networking layer is not handling meta info properly
        // each session should know what signature it talks to and use dynamic IniModel!
        // we have a pile of dreams/unused code which was moved from PROD into TEST folders
        // under https://github.com/gerefi/gerefi/issues/6845
        return GetOutputsCommand.createRequest(0, VariableRegistryValues.TS_TOTAL_OUTPUT_SIZE);
    }

    public static void sendOutput(IoStream stream) throws IOException {
        byte[] response = new byte[1 + VariableRegistryValues.TS_TOTAL_OUTPUT_SIZE];
        response[0] = (byte) BinaryProtocolServer.TS_OK.charAt(0);
        stream.sendPacket(response);
    }
}
