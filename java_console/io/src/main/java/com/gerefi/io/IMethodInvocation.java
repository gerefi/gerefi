package com.gerefi.io;

/**
 * Andrey Belomutskiy, (c) 2013-2020
 */
public interface IMethodInvocation {
    String getCommand();

    int getTimeout();

    InvocationConfirmationListener getListener();

    boolean isFireEvent();
}
