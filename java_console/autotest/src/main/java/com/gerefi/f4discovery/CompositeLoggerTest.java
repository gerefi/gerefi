package com.gerefi.f4discovery;

import com.gerefi.GerefiTestBase;
import com.gerefi.binaryprotocol.BinaryProtocolLogger;
import com.gerefi.enums.engine_type_e;
import com.gerefi.io.LinkManager;
import org.junit.Test;

public class CompositeLoggerTest extends GerefiTestBase {
    @Test
    public void testVW() {
        ecu.setEngineType(engine_type_e.VW_ABA);
        ecu.changeRpm(1200);
        LinkManager linkManager = ecu.getLinkManager();

        BinaryProtocolLogger binaryProtocolLogger = new BinaryProtocolLogger(linkManager);
        linkManager.submit(new Runnable() {
            @Override
            public void run() {
                binaryProtocolLogger.getComposite(linkManager.getBinaryProtocol());
            }
        });
    }

}
