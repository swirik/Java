
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
    }
    public static void delay(int milliseconds) {
        try {
            for(int i = 0; i < 3; i++) {
                Thread.sleep(milliseconds);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            System.out.println("Delay interrupted");
        }
    }
    public static void clear() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }
}


