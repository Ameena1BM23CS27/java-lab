
class Message1 extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("BMS College of Engineering");
                Thread.sleep(10000);  
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}


class Message2 implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("CSE");
                Thread.sleep(2000);  
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class MultiThreadExample {
    public static void main(String[] args) {
        
        Message1 thread1 = new Message1();
        thread1.start();

        
        Message2 message2 = new Message2();
        Thread thread2 = new Thread(message2);
        thread2.start();
    }
}