package models;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		/*try {
			Socket skt = new Socket("192.168.1.9", 2999);

		} catch (Exception e) {
			System.out.print("Whoops! It didn't work!\n");
		}*/
		//System.out.println(System.getProperty("user.name"));
		InetAddress ip;
		try {
			ip = InetAddress.getByName("192.168.1.9");
			IpManager ipManager = new IpManager(ip);
			System.out.println(ipManager.getMacAddress(null));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	   }

}
