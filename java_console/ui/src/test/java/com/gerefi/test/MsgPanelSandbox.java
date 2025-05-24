package com.gerefi.test;

import com.gerefi.core.ui.FrameHelper;
import com.gerefi.ui.MessagesPane;
import com.gerefi.core.preferences.storage.PersistentConfiguration;

public class MsgPanelSandbox extends FrameHelper {
    public static void main(String[] args) {
        new FrameHelper().showFrame(new MessagesPane(null, PersistentConfiguration.getConfig().getRoot()).getContent());
    }
}