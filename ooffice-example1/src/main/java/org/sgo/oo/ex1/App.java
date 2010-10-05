package org.sgo.oo.ex1;

import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		try {
			System.out.println("Started");
			// get the remote office component context
			XComponentContext xContext = Bootstrap
					.bootstrap();

			System.out.println("Connected to a running office ...");

			XMultiComponentFactory xMCF = xContext
					.getServiceManager();

			String available = (xMCF != null ? "availabl e" : "not available");
			System.out.println("remote ServiceManager is " + available);
		} catch (java.lang.Throwable e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}

	}
}
