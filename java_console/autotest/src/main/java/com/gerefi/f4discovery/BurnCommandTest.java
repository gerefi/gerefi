package com.gerefi.f4discovery;

import com.gerefi.GerefiTestBase;
import com.gerefi.io.LinkManager;
import com.gerefi.io.commands.BurnCommand;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertTrue;

public class BurnCommandTest extends GerefiTestBase {
    @Test
    public void executeBurnCommand() throws InterruptedException {
        LinkManager linkManager = ecu.getLinkManager();
        AtomicReference<Boolean> result = new AtomicReference<>();

        CountDownLatch latch = new CountDownLatch(1);
        linkManager.submit(new Runnable() {
            @Override
            public void run() {
                result.set(BurnCommand.execute(ecu.getLinkManager().getBinaryProtocol()));
                latch.countDown();

            }
        });


        latch.await(30, TimeUnit.SECONDS);
        assertTrue("burn command", result.get());
    }
}
