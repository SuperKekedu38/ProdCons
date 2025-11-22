package prodcons.v6;

public class Message {

    private String msg;
    private int totalCopies;
    private int consumedCount = 0;
    
    public Message(String msg) {
        this.msg = msg;
    }

    public synchronized void setTotalCopies(int n) {
        this.totalCopies = n;
    }

    public synchronized boolean consume() {
        consumedCount++;
        return consumedCount == totalCopies;
    }

    public synchronized void waitUntilFinished() throws InterruptedException {
        while (consumedCount < totalCopies) {
            wait();
        }
        notifyAll();
    }

    public synchronized void signalFinished() {
        notifyAll();
    }

    public String getMsg() {
        return msg;
    }
}