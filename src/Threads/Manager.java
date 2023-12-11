package Threads;

import enitites.Stock;
import lib.Debug;
import lib.Semaphore;

public class Manager implements Runnable {
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore empty = new Semaphore(Stock.MAXIMUM_STOCK);
    public static Semaphore full = new Semaphore(0);
    private static final Factory factory = new Factory();
    private static Thread factoryThread;
    private static final Stock stock = new Stock();

    public Manager() {
        Manager.factoryThread = new Thread(factory);
    }

    public static Stock getStock() {
        return stock;
    }

    public static Thread getFactoryThread(){
        return factoryThread;
    }

    @Override
    public void run() {
        while (true) {
            if (factory.isProduction()) {
                if (stock.isMaximumStock()) {
                    factoryThread.interrupt();
                    factory.setProduction(false);
                    Debug.out("Factory Paused⏸️ Production",Debug.Color.CYAN);
                }
            } else {
                if (stock.isMinimumStock()) {
                    factory.setProduction(true);
                    factoryThread = (new Thread(factory));
                    factoryThread.start();
                    Debug.out("Factory Resumed▶️ Production",Debug.Color.CYAN);
                }
            }

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
