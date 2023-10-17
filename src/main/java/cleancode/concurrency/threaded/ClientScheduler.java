package cleancode.concurrency.threaded;

public interface ClientScheduler {
    void schedule(ClientRequestProcessor requestProcessor);
}
