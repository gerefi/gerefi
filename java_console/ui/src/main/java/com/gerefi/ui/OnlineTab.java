package com.gerefi.ui;

import com.gerefi.binaryprotocol.MsqFactory;
import com.gerefi.tools.online.Online;
import com.gerefi.tune.xml.Msq;
import com.gerefi.ui.util.Misc;
import org.putgemin.VerticalFlowLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;

public class OnlineTab {

    private final JPanel content = new JPanel(new VerticalFlowLayout());

    public OnlineTab(UIContext uiContext) {
        AuthTokenPanel authTokenPanel = new AuthTokenPanel();

        content.add(Misc.getGerEFI_online_manual());

        content.add(authTokenPanel.getContent());

        JButton upload = new JButton("Upload");
        upload.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Msq tune = MsqFactory.valueOf(uiContext.getLinkManager().getCurrentStreamState().getControllerConfiguration(), null);
                Online.uploadTune(tune, content, null);
            }
        });
        content.add(upload);
    }

    public JPanel getContent() {
        return content;
    }
}
