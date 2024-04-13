package genrics;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class HelloClient {

  public static void main(String[] args) throws Exception {

    InetAddress inet = InetAddress.getByName("localhost");

    try( Socket s = new Socket(inet, 2000);
    	    OutputStream o = s.getOutputStream(); PrintWriter p = new PrintWriter(o);
    	    InputStream in = s.getInputStream();  Scanner sc = new Scanner(in);){
        p.println("paul");
        p.flush();

        String inputLine = sc.nextLine();
        System.out.println("Client: " + inputLine);
    }
  }
}