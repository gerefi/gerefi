package com.gerefi.panama;

import com.opensr5.ini.IniMemberNotFound;
import com.opensr5.ini.field.IniField;
import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.config.generated.TsOutputs;
import com.gerefi.core.ISensorHolder;
import com.gerefi.core.SensorCentral;
import com.gerefi.io.LinkManager;

import java.nio.ByteBuffer;

public class PanamaHelper {
    public static final String MCUSERIAL = TsOutputs.MCUSERIAL.getName();

    public static IniField getIniField(final BinaryProtocol bp) throws IniMemberNotFound {
        return bp.getIniFile().getOutputChannel(MCUSERIAL);
    }

    public static IniField getIniField(LinkManager linkManager) throws IniMemberNotFound {
        return getIniField(linkManager.getBinaryProtocol());
    }

    public static int getMcuSerial(IniField mcuSerialField) {
        ByteBuffer bb = ISensorHolder.getByteBuffer(SensorCentral.getInstance().getResponse(), "error", mcuSerialField.getOffset());
        return bb.getInt();
    }
}
