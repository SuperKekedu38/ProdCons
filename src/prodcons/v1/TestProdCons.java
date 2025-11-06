package prodcons.v1;

import java.io.IOException;
import java.util.Properties;

public class TestProdCons {


	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("prodcons/v1/options.xml"));

			
			int nProd = Integer.parseInt(properties.getProperty("nProd"));
			int nCons = Integer.parseInt(properties.getProperty("nCons"));
			int bufSize = Integer.parseInt(properties.getProperty("bufSize"));
            int prodTime = Integer.parseInt(properties.getProperty("prodTime"));
            int consTime = Integer.parseInt(properties.getProperty("consTime"));
            int prodMin = Integer.parseInt(properties.getProperty("prodMin"));
            int prodMax = Integer.parseInt(properties.getProperty("prodMax"));
            
            IProdConsBuffer buffer = new ProdConsBuffer(bufSize);

            for (int i = 0; i < nProd; i++) {
                Thread t = new Thread(new Producer(buffer, prodMin, prodMax, prodTime));
                t.setName("Prod-" + i);
                t.start();
            }

            for (int i = 0; i < nCons; i++) {
                Thread t = new Thread(new Consumer(buffer, consTime));
                t.setName("Cons-" + i);
                t.start();
            }
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
