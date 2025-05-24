package com.gerefi.maintenance;

import com.gerefi.ui.StatusWindow;

public class StatusWindowAnimation {
    private final StatusAnimation animation;
    public StatusWindowAnimation(StatusWindow wnd) {
        animation = new StatusAnimation(new StatusAnimation.StatusConsumer() {
            @Override
            public void onStatus(String niceStatus) {
                wnd.getContent().setStatus(niceStatus);
            }
        }, "Working");
    }

    public void stop() {
        animation.stop();
    }
}
