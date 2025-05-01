package com.gerefi.ui;

import javax.swing.*;

import com.gerefi.core.ui.FrameHelper;
import com.gerefi.ui.InitOnFirstPaintPanel;
import com.gerefi.ui.LiveDataPane;
import com.gerefi.ui.UIContext;

public class LiveDataPaneSandbox {
    public static void main(String[] args) {
        UIContext uiContext = new UIContext();
        InitOnFirstPaintPanel panel = LiveDataPane.createLazy(uiContext);

        new FrameHelper().showFrame(panel.getContent());
    }
}
