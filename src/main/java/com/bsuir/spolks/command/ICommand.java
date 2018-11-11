package com.bsuir.spolks.command;

import com.bsuir.spolks.connection.ClientSession;
import com.bsuir.spolks.exception.WrongCommandFormatException;

import java.nio.channels.SocketChannel;
import java.util.Map;

public interface ICommand {

    /**
     * Execute command.
     */
    void execute(ClientSession session);

    /**
     * Put token to command.
     *
     * @param name
     * @param value
     */
    void putToken(String name, String value);

    /**
     * Get all command tokens.
     *
     * @return hash map
     */
    Map<String, String> getTokens();

    /**
     * Verify inputted tokens.
     */
    void verifyTokens() throws WrongCommandFormatException;

    /**
     * Build command instance.
     *
     * @return instance
     */
    ICommand build();

    /**
     * Set command channel.
     * @param channel
     */
    void setChannel(SocketChannel channel);
}
