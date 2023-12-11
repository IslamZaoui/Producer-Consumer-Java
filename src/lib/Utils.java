package lib;

import java.util.Random;

public class Utils {
    public static Random random = new Random();
    public static int TimingCalculator(int input){
        int initialMaxTime = 500;
        int minimalTime = 55;
        return Math.max((int) (initialMaxTime / Math.pow(2, input - 1)), minimalTime);
    }
}
