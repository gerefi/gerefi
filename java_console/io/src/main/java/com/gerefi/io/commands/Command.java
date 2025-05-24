package com.gerefi.io.commands;

import com.gerefi.io.IoStream;

import java.io.IOException;

public interface Command {
    byte getCommand();

    void handle(IoStream stream) throws IOException;
}
