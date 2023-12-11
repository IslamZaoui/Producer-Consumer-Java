package Threads;

import lib.Debug;

public class Client implements Runnable {
    private static int count = 0;
    private final int id;

    public static int getCount(){
        return Client.count;
    }

    public Client() {
        this.id = ++count;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Manager.full.acquire();
                Manager.mutex.acquire();

                //Critical Section
                Debug.out("Client-" + id + " removed Product (" + Manager.getStock().Retrieve() +").", Debug.Color.RED);
                Debug.out("Stock: " + Manager.getStock().GetSize(), Debug.Color.YELLOW);

                Manager.mutex.release();
                Manager.empty.release();

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}