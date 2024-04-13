package genrics;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloServer {

	public static void main(String[] args) throws IOException {
		Socket s;

		try (ServerSocket ss = new ServerSocket(2000)) {
			System.out.println("Server: waiting for connection ..");

			while (true) {
				s = ss.accept();
				try (InputStream in = s.getInputStream();
						Scanner r = new Scanner(in);
						OutputStream o = s.getOutputStream();
						PrintWriter p = new PrintWriter(o);) {

					String inputLine;
					inputLine = r.nextLine();
					p.println("Hello " + inputLine + " from Hello Server");
				}
			}
		}
	}
}