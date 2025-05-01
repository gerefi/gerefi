package com.gerefi.binaryprotocol.test;


/*

import com.gerefi.binaryprotocol.IncomingDataBuffer;
import com.gerefi.config.generated.Fields;
import com.gerefi.config.generated.Integration;
import com.gerefi.io.IoStream;
import com.gerefi.io.LinkManager;
import com.gerefi.io.can.elm.Elm327Connector;
import com.gerefi.io.serial.BaudRateHolder;
import com.gerefi.io.serial.SerialIoStream;

import java.io.IOException;

import static com.gerefi.binaryprotocol.IoHelper.checkResponseCode;
import static com.gerefi.io.can.elm.Elm327Connector.ELM327_DEFAULT_BAUDRATE;

public class Elm327Sandbox {
    public static void main(String[] args) throws InterruptedException, IOException {
        BaudRateHolder.INSTANCE.baudRate = ELM327_DEFAULT_BAUDRATE;
        String serialPort = "COM7";
        Elm327Connector connector = new Elm327Connector(SerialIoStream.openPort(serialPort));
        boolean initConnection = connector.start(serialPort);
        if (!initConnection)
            return;

        IoStream tsStream = connector.getTsStream();

        IncomingDataBuffer dataBuffer = tsStream.getDataBuffer();
        System.out.println("Hello new ELM327 connection, pending=" + dataBuffer.getPendingCount());


        Elm327Connector.whyDoWeNeedToSleepBetweenCommands();

        LinkManager linkManager = new LinkManager();
        SandboxCommon.verifyCrcNoPending(tsStream, linkManager);

//        SandboxCommon.runFcommand("First time", tsStream);
        if (1 == 1)
            return;

//        /*
//        SandboxCommon.runFcommand("First time", tsStream);
//        Elm327Connector.whyDoWeNeedToSleepBetweenCommands();
//
//        SandboxCommon.runFcommand("Second time", tsStream);
//        Elm327Connector.whyDoWeNeedToSleepBetweenCommands();
///

        SandboxCommon.verifySignature(tsStream, "", "ELM");
        Elm327Connector.whyDoWeNeedToSleepBetweenCommands();

        SandboxCommon.verifySignature(tsStream, "Let's do it again! ", "ELM");
        Elm327Connector.whyDoWeNeedToSleepBetweenCommands();

        {
            tsStream.sendPacket(new byte[]{Integration.TS_HELLO_COMMAND});
            byte[] response = dataBuffer.getPacket("[hello command]");
            if (!checkResponseCode(response))
                return;
            String signature = new String(response, 1, response.length - 1);
            System.out.println(Integration.TS_HELLO_COMMAND + " returned " + signature);

            if (!signature.startsWith(Fields.PROTOCOL_SIGNATURE_PREFIX))
                throw new IllegalStateException("Unexpected S " + signature);
        }

        Elm327Connector.whyDoWeNeedToSleepBetweenCommands();
        System.out.println("****************************************");
        System.out.println("********  ELM327 LOOKS GREAT  **********");
        System.out.println("****************************************");


        SandboxCommon.verifyCrcNoPending(tsStream, linkManager);
        SandboxCommon.verifyCrcNoPending(tsStream, linkManager);

        SandboxCommon.readImage(tsStream, linkManager);


        System.exit(-1);
    }

}
*/
