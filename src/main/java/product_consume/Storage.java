package product_consume;

/**
 * Created by yaming on 17-5-8.
 */
public class Storage {
    private static final int MaxNum=100;
    private static int CurrntNum=50;
    //单个仓库对象
    private static Storage storage;
    private Storage(){}
    public static Storage getInstance(){
        if(storage==null){
            storage=new Storage();
        }
        return storage;
    }

    //生产商品
    public synchronized void product(){
        while (CurrntNum>=MaxNum/2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        CurrntNum++;
        System.out.println("当前线程"+Thread.currentThread().getName()+"生产了一个商品.当前库存量"+CurrntNum);
        notifyAll();
    }

    //消费商品
    public synchronized void consume(){
        while (CurrntNum<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        CurrntNum--;
        System.out.println("当前线程"+Thread.currentThread().getName()+"消费了一个商品.当前库存量"+CurrntNum);
        notifyAll();
    }
}
