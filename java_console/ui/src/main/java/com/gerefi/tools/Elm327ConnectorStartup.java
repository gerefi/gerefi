package com.gerefi.tools;


/*

import com.devexperts.logging.Logging;
import com.gerefi.autodetect.PortDetector;
import com.gerefi.autodetect.SerialAutoChecker;
import com.gerefi.io.can.elm.Elm327Connector;
import com.gerefi.io.serial.SerialIoStream;
import com.gerefi.io.tcp.BinaryProtocolProxy;
import com.gerefi.io.tcp.BinaryProtocolServer;
import com.gerefi.io.tcp.TcpConnector;
import com.gerefi.ui.StatusConsumer;

import java.io.IOException;

public class Elm327ConnectorStartup {
    private final static Logging log = Logging.getLogging(Elm327ConnectorStartup.class);

    public static void start() throws IOException {
        SerialAutoChecker.AutoDetectResult detectResult = PortDetector.autoDetectSerial(null, PortDetector.DetectorMode.DETECT_ELM327);
        String autoDetectedPort = detectResult.getSerialPort();
        //String autoDetectedPort = "COM73";
        if (autoDetectedPort == null) {
            System.err.println(ConsoleTools.RUS_EFI_NOT_DETECTED);
            return;
        }

        Elm327Connector elm327Connector = new Elm327Connector(SerialIoStream.openPort(autoDetectedPort));
        elm327Connector.start(autoDetectedPort);

        BinaryProtocolProxy.createProxy(elm327Connector.getTsStream(), TcpConnector.DEFAULT_PORT, new BinaryProtocolProxy.ClientApplicationActivityListener() {
            @Override
            public void onActivity(BinaryProtocolServer.Packet clientRequest) {
                System.out.println("onActivity");
                Elm327Connector.whyDoWeNeedToSleepBetweenCommands();
            }
        }, StatusConsumer.ANONYMOUS);

        log.info("Running Elm327 connector for " + autoDetectedPort);
    }
}
*/
