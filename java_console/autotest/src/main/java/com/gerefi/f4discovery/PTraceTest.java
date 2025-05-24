package com.gerefi.f4discovery;

import com.gerefi.GerefiTestBase;
import com.gerefi.io.LinkManager;
import com.gerefi.io.commands.PTraceHelper;
import com.gerefi.tracing.Entry;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertTrue;

public class PTraceTest extends GerefiTestBase {
    @Test
    public void assertPTrace() throws InterruptedException {
        LinkManager linkManager = ecu.getLinkManager();
        AtomicReference<List<Entry>> result = new AtomicReference<>();

        CountDownLatch latch = new CountDownLatch(1);
        linkManager.submit(new Runnable() {
            @Override
            public void run() {
                result.set(PTraceHelper.requestWaitAndGetPTrace(linkManager.getBinaryProtocol()));
                latch.countDown();

            }
        });


        latch.await(30, TimeUnit.SECONDS);
        List<Entry> entries = result.get();
        assertTrue("PTrace entries: " + entries, entries != null && !entries.isEmpty());
    }
}
