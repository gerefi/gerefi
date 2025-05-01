import com.gerefi.io.IoStream;
import com.gerefi.io.serial.BufferedSerialIoStream;
import com.gerefi.io.tcp.TcpConnector;
import com.gerefi.io.tcp.TcpIoStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UiLinkManagerHelper {
    @NotNull
    public static IoStream open(String port) throws IOException {
        if (TcpConnector.isTcpPort(port))
            return TcpIoStream.open(port);
        return BufferedSerialIoStream.openPort(port);
    }
}
