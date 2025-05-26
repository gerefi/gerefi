package com.gerefi;

import com.opensr5.ini.IniFileModel;
import com.opensr5.ini.IniFileModelImpl;
import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.binaryprotocol.IniFileProvider;
import com.gerefi.functional_tests.EcuTestHelper;
import com.gerefi.waves.EngineChart;
import org.junit.*;

public class GerefiTestBase {
    protected EcuTestHelper ecu;

    protected boolean needsHardwareTriggerInput() {
        // Most tests do not, but some may need it
        return false;
    }

    @Before
    public void startUp() {
        BinaryProtocol.iniFileProvider = signature -> IniFileModelImpl.readIniFile(LocalIniFileProvider.INI_FILE_FOR_SIMULATOR_ROOT_PATH);
        try {
            ecu = EcuTestHelper.createInstance(needsHardwareTriggerInput());
        } catch (Throwable e) {
            throw new IllegalStateException("During start-up", e);
        }
    }

    @After
    public void checkStackUsage() {
        if (ecu != null)
            ecu.sendCommand("threadsinfo");
    }

    protected EngineChart nextChart() {
        return TestingUtils.nextChart(ecu.commandQueue);
    }
}
