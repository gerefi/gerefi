package com.gerefi.ui;

import com.gerefi.core.ui.FrameHelper;

import javax.swing.*;

public class LiveDataPaneSandbox {
    public static void main(String[] args) {
        UIContext uiContext = new UIContext();
        InitOnFirstPaintPanel panel = LiveDataPane.createLazy(uiContext);

        new FrameHelper().showFrame(panel.getContent());
    }
}
