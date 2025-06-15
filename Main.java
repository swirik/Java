
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
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


