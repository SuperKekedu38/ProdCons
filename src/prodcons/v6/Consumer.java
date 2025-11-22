package prodcons.v6;

public class Consumer implements Runnable {

    private IProdConsBuffer buffer;
    private int consTime;

    public Consumer(IProdConsBuffer buffer, int consTime) {
        this.buffer = buffer;
        this.consTime = consTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message m = buffer.get();
                
                if (m == null) {
                    System.out.println("[C-" + Thread.currentThread().getName() + "] -> Arrêt");
                    break;
                }
                
                System.out.println("[C-" + Thread.currentThread().getName() + "] -> A traité une copie de : " + m.getMsg());
                Thread.sleep(consTime);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}