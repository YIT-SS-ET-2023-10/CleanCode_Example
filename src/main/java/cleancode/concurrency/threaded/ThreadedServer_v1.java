package cleancode.concurrency.threaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer_v1 implements Runnable{
    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;
    ConnectionManager connectionManager;
    private ClientScheduler clientScheduler;

    public ThreadedServer_v1(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
        clientScheduler = new ThreadPerRequestScheduler();
        connectionManager = new ConnectionManager();
    }

    public void run() {
        System.out.printf("Server Starting\n");

        while (keepProcessing) {
            try {
                System.out.printf("accepting client\n");
                Socket clientConnection = connectionManager.awaitClient();
                System.out.printf("got client\n");

                ClientRequestProcessor requestProcessor = new ClientRequestProcessor(clientConnection);
                clientScheduler.schedule(requestProcessor);
            } catch (IOException e){
                connectionManager.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        connectionManager.shutdown();

    }


    class ConnectionManager {
        public Socket awaitClient() throws Exception{
            return serverSocket.accept();
        }

        public void shutdown() {
            keepProcessing = false;
            closeIgnoringException(serverSocket);
        }

        private void closeIgnoringException(ServerSocket serverSocket) {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException ignore) {
                }

        }
    }
}
