package com.opensr5.io;

/**
* @author Bruno Ferreira
*         3/3/14
*/
public interface DataListener {
    void onDataArrived(byte[] freshData);
}
