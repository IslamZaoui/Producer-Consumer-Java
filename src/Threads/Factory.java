package Threads;

import lib.Debug;
import lib.Utils;

import static lib.Utils.TimingCalculator;

public class Factory implements Runnable {
    private boolean isProduction = true;
    public boolean isProduction() {
        return isProduction;
    }

    public void setProduction(boolean x) {
        this.isProduction = x;
    }

    public int Produce() {
        return Utils.random.nextInt(10000);
    }

    @Override
    public void run() {
        while (true) {
            if (isProduction()) {
                try {
                    Manager.empty.acquire();
                } catch (InterruptedException e) {
                }

                try {
                    Manager.mutex.acquire();

                    //Critical Section
                    int product = Produce();
                    Manager.getStock().Store(product);

                    Debug.out("Factory: Product (" + product + ") added to stock.", Debug.Color.GREEN);
                    Debug.out("stock: "+ Manager.getStock().GetSize(), Debug.Color.YELLOW);

                    Manager.mutex.release();
                    Manager.full.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try{
                    Thread.sleep(TimingCalculator(Client.getCount()));
                }
                catch (InterruptedException e){}

            }
        }
    }
}
