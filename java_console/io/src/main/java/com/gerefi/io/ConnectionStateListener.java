package com.gerefi.io;

/**
 * @author Bruno Ferreira
 *         3/1/2017
 * @see ConnectionStatusValue
 */
public interface ConnectionStateListener extends ConnectionFailedListener {
    ConnectionStateListener VOID = new AbstractConnectionStateListener();

    /**
     * This method is invoked once we have connection & configuration from controller
     */
    void onConnectionEstablished();
}
