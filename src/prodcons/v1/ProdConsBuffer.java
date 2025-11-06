package prodcons.v1;

public class ProdConsBuffer implements IProdConsBuffer{

	private int nfull;
	private int nempty;
	private Message[] buffer;
	private int bufSize;
	
	
	public ProdConsBuffer() {
		
	}
	
	@Override
	public void put(Message m) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int nmsg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totmsg() {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
}
