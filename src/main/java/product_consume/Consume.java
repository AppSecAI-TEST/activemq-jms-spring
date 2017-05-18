package product_consume;

/**
 * Created by yaming on 17-5-8.
 */
public class Consume implements Runnable{

    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Storage.getInstance().consume();
        }
    }
}
