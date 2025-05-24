package com.gerefi.functional_tests;

import com.gerefi.config.generated.Integration;
import com.gerefi.core.MessagesCentral;

import static com.gerefi.TestingUtils.assertNull;

public enum TestHelper {
    INSTANCE;

    private String criticalError;

    TestHelper() {
        MessagesCentral.getInstance().addListener((clazz, message) -> {
            if (message.startsWith(Integration.CRITICAL_PREFIX))
                criticalError = message;
        });
    }

    public void assertNotFatal() {
        assertNull("Fatal not expected: " + criticalError, criticalError);
    }
}
