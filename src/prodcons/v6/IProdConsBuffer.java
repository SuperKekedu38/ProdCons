package prodcons.v6;

public interface IProdConsBuffer {
/**
 * Put n instances of the message m in the buffer.
 * The producer blocks until all n instances are consumed.
 **/
public void put(Message m, int n) throws InterruptedException;
/**
* Retrieve a message from the buffer,
* following a FIFO order (if M1 was put before M2, M1
* is retrieved before M2)
**
**/
public Message get() throws InterruptedException;
/**
* Retrieve n consecutive messages from the prodcons buffer
**/
public int nmsg();
/**
* Returns the total number of messages that have
* been put in the buffer since its creation
**/
public int totmsg();

public void produced();
}
