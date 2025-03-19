package sd.lab1;

import java.net.* ;
import java.util.*;

/**
 * Basic TCP client... 
 *
 */
public class TcpClient {

	private static final String QUIT = "!quit";

	public static void main(String[] args) throws Exception {

		//TODO: Change to use the Discovery to obtain the hostname and port of the server (format tpc://hostname:port;
		Discovery discovery = new Discovery(Discovery.DISCOVERY_ADDR);
		discovery.start();

		Scanner scan = new Scanner(System.in);


		//Process the string with the server address to extract IP address and port

		URI[] uris = discovery.knownUrisOf("TcpServer", 1);
		URI first = uris[0];
		int port = first.getPort();
		String hostname = first.getHost();

		//Establish a TCP connection to the server and send lines of text from standard input until input is !quit
		try( Socket sock = new Socket( hostname, port)) {
			String input;
			do {
				input = scan.nextLine();
				sock.getOutputStream().write( (input + System.lineSeparator()).getBytes() );
			} while( ! input.equals(QUIT));

		}

		scan.close();

	}
}
