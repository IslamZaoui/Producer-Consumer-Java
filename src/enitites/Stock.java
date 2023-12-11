package enitites;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final List<Integer> storage = new ArrayList<>();
    public static int MAXIMUM_STOCK = 50;
    public static int MINIMUM_STOCK = 15;

    public void Store(int item) {
        storage.add(item);
    }

    public int Retrieve() {
        return storage.removeFirst();
    }

    public int GetSize() {
        return storage.size();
    }
    public boolean isMaximumStock() {
        return storage.size() >= MAXIMUM_STOCK;
    }

    public boolean isMinimumStock() {
        return storage.size() <= MINIMUM_STOCK;
    }
}