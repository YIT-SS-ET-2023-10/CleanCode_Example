package cleancode.concurrency.nonthreaded;

import cleancode.concurrency.MessageUtils;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.net.Socket;

class NonThreadedClientTest {
    private static final int PORT = 8009;
    private static final int TIMEOUT = 2000;

    NonThreadedServer server;
    Thread serverThread;

    @BeforeEach
    public void createServer() throws Exception {
        try {
            server = new NonThreadedServer(PORT, TIMEOUT);
            serverThread = new Thread(server);
            serverThread.start();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw e;
        }
    }

    @AfterEach
    public void shutdownServer() throws InterruptedException {
        if (server != null) {
            server.stopProcessing();
            serverThread.join();
        }
    }

    @Nested
    class TrivialClient implements Runnable {
        int clientNumber;

        TrivialClient(int clientNumber) {
            this.clientNumber = clientNumber;
        }

        public void run() {
            try {
                connectSendReceive(clientNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Timeout(10000)
    public void shouldRunInUnder10Seconds() throws Exception {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(new TrivialClient(i));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i) {
            threads[i].join();
        }
    }

    private void connectSendReceive(int i) throws IOException {
        System.out.printf("Client %2d: connecting\n", i);
        Socket socket = new Socket("localhost", PORT);
        System.out.printf("Client %2d: sending message\n", i);
        MessageUtils.sendMessage(socket, Integer.toString(i));
        System.out.printf("Client %2d: getting reply\n", i);
        MessageUtils.getMessage(socket);
        System.out.printf("Client %2d: finished\n", i);
        socket.close();
    }
}