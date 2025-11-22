package prodcons.v6;

import java.util.Random;

public class Producer implements Runnable {

    private IProdConsBuffer buffer;
    private int prodMin, prodMax, prodTime;
    private Random rand = new Random();

    public Producer(IProdConsBuffer buffer, int prodMin, int prodMax, int prodTime) {
        this.buffer = buffer;
        this.prodMin = prodMin;
        this.prodMax = prodMax;
        this.prodTime = prodTime;
    }

    @Override
    public void run() {
        int nbMsg = rand.nextInt(prodMax - prodMin + 1) + prodMin;
        
        for (int i = 0; i < nbMsg; i++) {
            Message m = new Message("Msg " + i + " (Th-" + Thread.currentThread().threadId() + ")");
            int copies = rand.nextInt(3) + 2; 

            try {
                System.out.println("[P-" + Thread.currentThread().getName() + "] -> Dépose " + m.getMsg() + " en " + copies + " ex.");
                buffer.put(m, copies);
                System.out.println("[P-" + Thread.currentThread().getName() + "] -> " + m.getMsg() + " FINI (toutes copies consommées)");
                
                Thread.sleep(prodTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.produced();
    }
}