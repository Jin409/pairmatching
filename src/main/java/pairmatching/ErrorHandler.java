package pairmatching;

public class ErrorHandler {

    public static void handle(Exception e) {
        System.out.println("[ERROR]" + e.getMessage());
    }
}