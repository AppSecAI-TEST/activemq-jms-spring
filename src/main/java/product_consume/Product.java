package product_consume;

/**
 * Created by yaming on 17-5-8.
 */
public class Product implements Runnable {
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        Storage.getInstance().product();
        }
    }
}
