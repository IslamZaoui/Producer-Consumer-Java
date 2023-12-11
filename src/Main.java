import Threads.Client;
import Threads.Manager;
import lib.Debug;

import java.util.Scanner;

import static lib.Utils.TimingCalculator;

public class Main {
    public static Scanner read = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        Manager manager = new Manager();

        Debug.out("Enter number of clients: ");
        int numClients = read.nextInt();

        Debug.out("Timing: "+TimingCalculator(numClients));

        Client[] clients = new Client[numClients];
        Thread[] clientThreads = new Thread[numClients];

        for (int i = 0; i < numClients; i++) {
            clients[i] = new Client();
            clientThreads[i] = new Thread(clients[i]);
        }

        Thread factoryThread = Manager.getFactoryThread();
        Thread managerThread = new Thread(manager);

        for (Thread clientThread : clientThreads) {
            clientThread.start();
        }
        managerThread.start();
        factoryThread.start();

        managerThread.join();
        factoryThread.join();
        for (Thread clientThread : clientThreads) {
            clientThread.join();
        }

    }
}
