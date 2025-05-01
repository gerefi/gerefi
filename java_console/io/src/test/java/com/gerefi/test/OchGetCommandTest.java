package com.gerefi.test;

import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.binaryprotocol.IoHelper;
import com.gerefi.config.generated.Integration;
import com.gerefi.io.commands.GetOutputsCommand;
//import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OchGetCommandTest {
    @Test
    public void testReadBytes() {
        int offset = 400;
        int chunkSize = 300;
        byte[] payload = GetOutputsCommand.createRequest(offset, chunkSize);
        byte[] fullPacket = BinaryProtocol.getFullRequest((byte) Integration.TS_OUTPUT_COMMAND, payload);
//        System.out.println("OchGetCommand command " + Hex.encodeHexString(fullPacket, false));
        assertArrayEquals(new byte[]{0x4f, (byte) 0x90, 0x01, 0x2c, 0x01}, fullPacket);


        byte[] crcPacket = IoHelper.makeCrc32Packet(fullPacket);
//        System.out.println("OchGetCommand command with CRC " + Hex.encodeHexString(crcPacket));
        assertEquals(11, crcPacket.length);
    }
}
