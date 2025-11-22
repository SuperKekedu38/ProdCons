package prodcons.v6;

public class ProdConsBuffer implements IProdConsBuffer {

    private Message[] buffer;
    private int bufSize;
    private int count = 0;
    private int total = 0;
    
    private int in = 0;
    private int out = 0;

    private int totProd;
    private int finishedProd = 0;

    public ProdConsBuffer(int bufSize, int totProd) {
        this.bufSize = bufSize;
        this.totProd = totProd;
        this.buffer = new Message[bufSize];
    }

    @Override
    public void put(Message m, int n) throws InterruptedException {
        m.setTotalCopies(n);

        synchronized (this) {
            while (count == bufSize) {
                wait();
            }
            buffer[in] = m;
            in = (in + 1) % bufSize;
            count++;
            total++;
            notifyAll();
        }

        m.waitUntilFinished();
    }

    @Override
    public Message get() throws InterruptedException {
        Message m = null;
        boolean isLast = false;

        synchronized (this) {
            while (count == 0) {
                if (finishedProd == totProd) {
                    return null;
                }
                wait();
            }

            m = buffer[out];
            
            isLast = m.consume();

            if (isLast) {
                out = (out + 1) % bufSize;
                count--;
                notifyAll();
                
                m.signalFinished();
            }
        }

        m.waitUntilFinished();

        return m;
    }

    @Override
    public synchronized void produced() {
        finishedProd++;
        notifyAll();
    }

    @Override
    public int nmsg() {
        return count;
    }

    @Override
    public int totmsg() {
        return total;
    }
}