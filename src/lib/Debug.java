package lib;

public class Debug {
    public enum Color{
        RED,
        GREEN,
        YELLOW,
        WHITE,
        CYAN
    }
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    public static void out(String input, Color color){
        System.out.println( getColor(color) + input + ANSI_RESET);
    }

    public static void out(String input){
        System.out.println( ANSI_WHITE + input + ANSI_RESET);
    }
    private static String getColor(Color color){
        return color == Color.RED? ANSI_RED: color == Color.GREEN? ANSI_GREEN: color == Color.YELLOW? ANSI_YELLOW : color == Color.CYAN? ANSI_CYAN : ANSI_WHITE;
    }
}


