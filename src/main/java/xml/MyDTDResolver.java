import org.xml.sax.*;
import java.io.*;

public class MyDTDResolver implements EntityResolver {
	public static final String[] dtds = {
		"http://java.sun.com/dtd/web-app_2_3.dtd",
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd",
	};

	public static final String DTDDIR = "/home/ian/dtds";

	public InputSource resolveEntity (String publicId, String systemId) {
		for (int i=0; i<dtds.length; i++) {
			if (systemId.equals(dtds[i])) {
				// return a local copy
				try {
					String dtdFile = 
						systemId.substring(systemId.lastIndexOf('/'));
						// includes the /
					return new InputSource(
						new FileReader(DTDDIR + dtdFile));
				} catch (IOException ex) {
					System.err.println("+================================+");
					System.err.println("DTD ERROR: " + ex.toString());
					System.err.println("... Trying to get from web...");
					System.err.println("+================================+");
					return null;
				}
			}
		}
		// Not matched any of the ones in the array.
		return null;
	}
}