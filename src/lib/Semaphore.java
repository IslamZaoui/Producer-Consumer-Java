package lib;

public class Semaphore {
    private int count;

    public Semaphore(int count) {
        this.count = count;
    }

    public synchronized void acquire() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        count--;
    }

    public synchronized void release() {
        count++;
        notify();
    }
}
