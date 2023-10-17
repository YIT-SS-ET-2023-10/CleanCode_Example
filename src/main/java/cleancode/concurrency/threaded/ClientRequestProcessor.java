package cleancode.concurrency.threaded;

import cleancode.concurrency.MessageUtils;

import java.io.IOException;
import java.net.Socket;

public class ClientRequestProcessor {
    Socket clientConnection;

    public ClientRequestProcessor(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    public void process() {
        try {
            System.out.printf("Server: getting message\n");
            String message = MessageUtils.getMessage(clientConnection);
            System.out.printf("Server: got message: %s\n", message);
            Thread.sleep(1000);
            System.out.printf("Server: sending reply: %s\n", message);
            MessageUtils.sendMessage(clientConnection, "Processed: " + message);
            System.out.printf("Server: sent\n");
            closeIgnoringException(clientConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void closeIgnoringException(Socket clientConnection) {
        if (clientConnection != null)
            try {
                clientConnection.close();
            } catch (IOException ignore) {
            }

    }

}
