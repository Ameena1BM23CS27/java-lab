class SharedResource {
    private int data;
    private boolean isDataAvailable = false;

    
    public synchronized void produceData(int data) throws InterruptedException {
        while (isDataAvailable) {
            wait(); 
        }
        this.data = data;
        System.out.println("Put: " + data);
        isDataAvailable = true;
        notify(); 
    }

   
    public synchronized void consumeData() throws InterruptedException {
        while (!isDataAvailable) {
            wait(); 
        }
        System.out.println("Got: " + data);
        isDataAvailable = false;
        notify();  
    }
}

public class IPCDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    sharedResource.produceData(i);
                    Thread.sleep(1000);  
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    sharedResource.consumeData();
                    Thread.sleep(1500);  
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
