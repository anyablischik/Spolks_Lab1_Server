package com.bsuir.spolks.command;

import com.bsuir.spolks.connection.ClientSession;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class EchoCommand extends AbstractCommand {

    EchoCommand() {
        Arrays.stream(AvailableToken.values()).forEach(t -> availableTokens.put(t.getName(), t.getRegex()));
    }

    /**
     * Execute command.
     */
    @Override
    public void execute(ClientSession session) {
        try {
            String content = getTokens().get(AvailableToken.CONTENT.getName());

            if (content != null) {
                executeEcho(content);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error: " + e.getMessage());
        }
    }

    /**
     * Build command instance.
     *
     * @return instance
     */
    @Override
    public ICommand build() {
        return new EchoCommand();
    }

    private void executeEcho(String content) throws IOException {
        LOGGER.log(Level.INFO, "Received message: " + content);
        ByteBuffer buff = ByteBuffer.wrap(content.getBytes());
        channel.write(buff);
    }

    private enum AvailableToken {
        CONTENT("content", null);

        private String name;
        private String regex;

        AvailableToken(String name, String regex) {
            this.name = name;
            this.regex = regex;
        }

        public String getName() {
            return name;
        }

        public String getRegex() {
            return regex;
        }
    }
}
