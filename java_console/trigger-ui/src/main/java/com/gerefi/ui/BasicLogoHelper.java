package com.gerefi.ui;

import javax.swing.*;

import com.gerefi.core.ui.AutoupdateUtil;

import java.awt.*;

public class BasicLogoHelper {
    public static final String LOGO_PATH = "/com/gerefi/";
    static final String GENERIC_LOGO = LOGO_PATH + "logo.png";

    // as opposed to LogoHelper.getBundleSpecificIcon
    public static void setGenericFrameIcon(Frame frame) {
        ImageIcon genericIcon = AutoupdateUtil.loadIcon(GENERIC_LOGO);
        setFrameIcon(frame, genericIcon);
    }

    public static void setFrameIcon(Frame frame, ImageIcon icon) {
        if (icon != null)
            frame.setIconImage(icon.getImage());
    }
}
