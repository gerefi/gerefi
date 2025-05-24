package com.gerefi.io.commands;

import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.config.generated.Integration;

import static com.gerefi.binaryprotocol.IoHelper.checkResponseCode;
import static com.gerefi.config.generated.Integration.TS_RESPONSE_BURN_OK;

public class BurnCommand {
    public static boolean execute(BinaryProtocol bp) {
        byte[] response = bp.executeCommand(Integration.TS_BURN_COMMAND, "burn");
        boolean isExpectedBurnResponseCode = checkResponseCode(response, (byte) TS_RESPONSE_BURN_OK);
        boolean isExpectedBurnResponseLength = response.length == 1;
        return isExpectedBurnResponseCode && isExpectedBurnResponseLength;
    }
}
