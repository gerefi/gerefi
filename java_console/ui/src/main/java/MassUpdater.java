import com.gerefi.SerialPortScanner;
import com.gerefi.maintenance.jobs.AsyncJobExecutor;
import com.gerefi.maintenance.jobs.DfuManualJob;
import com.gerefi.maintenance.jobs.OpenBltManualJob;
import com.gerefi.ui.StatusWindow;
import com.gerefi.ui.widgets.ToolButtons;
import com.gerefi.core.gerEFIVersion;
import com.gerefi.io.UpdateOperationCallbacks;

import javax.swing.*;

import static com.gerefi.SerialPortScanner.SerialPortType.OpenBlt;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class MassUpdater {
    private final StatusWindow mainStatus = new StatusWindow();
    private final Set<String> knownBlts = new HashSet<>();

    public MassUpdater() {
        mainStatus.showFrame("Mass Updater " + gerEFIVersion.CONSOLE_VERSION);

        final AtomicBoolean previousDfuState = new AtomicBoolean();
        AtomicBoolean isUsingDfu = new AtomicBoolean(); // it seems like DFU detection is not 100% reliable? a work-around to avoid double-DFU

        SerialPortScanner.INSTANCE.addListener(currentHardware -> {

            if (!isUsingDfu.get() && currentHardware.isDfuFound() != previousDfuState.get()) {
                mainStatus.getContent().logLine(currentHardware.isDfuFound() ? "I see a DFU device!" : "No DFU...");
                if (currentHardware.isDfuFound()) {
                    isUsingDfu.set(true);
                    UpdateOperationCallbacks releaseSemaphore = new UpdateOperationCallbacks() {
                        @Override
                        public void log(String message, boolean breakLineOnTextArea, boolean sendToLogger) {

                        }

                        @Override
                        public void done() {
                            isUsingDfu.set(false);
                        }

                        @Override
                        public void error() {
                            isUsingDfu.set(false);
                        }

                        @Override
                        public void clear() {}
                    };
                    SwingUtilities.invokeLater(() -> AsyncJobExecutor.INSTANCE.executeJobWithStatusWindow(
                        new DfuManualJob(),
                        releaseSemaphore,
                        () -> {}
                    ));
                }
                previousDfuState.set(currentHardware.isDfuFound());
            }

            List<SerialPortScanner.PortResult> currentBltList = currentHardware.getKnownPorts().stream().filter(portResult -> portResult.type == OpenBlt).collect(Collectors.toList());
            Set<String> currentSet = currentBltList.stream().map(portResult -> portResult.port).collect(Collectors.toSet());
            for (Iterator<String> it = knownBlts.iterator(); it.hasNext(); ) {
                String port = it.next();
                if (!currentSet.contains(port)) {
                    mainStatus.getContent().logLine(port + ": No longer present");
                    it.remove();
                }
            }
            for (SerialPortScanner.PortResult openBltPort : currentBltList) {
                if (!knownBlts.contains(openBltPort.port)) {
                    knownBlts.add(openBltPort.port);
                    mainStatus.getContent().logLine("New OpenBlt " + openBltPort);

                    SwingUtilities.invokeLater(() -> AsyncJobExecutor.INSTANCE.executeJobWithStatusWindow(new OpenBltManualJob(openBltPort, mainStatus.getContent())));
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        ToolButtons.showDeviceManager();
        SwingUtilities.invokeAndWait(MassUpdater::new);
    }
}
