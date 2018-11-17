package models;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur {

	private int clientNumber;
	private Port port ;
	public static void main(String ... args){

		//String data = "Connected";
		try{
				ServerManager manager = new ServerManager("localhost", 2999, 200,null,null,null,null);
				System.out.println("Server Listening");
				//InetAddress address = InetAddress.getByName("192.168.1.7");

				//System.out.println(InetAddress.getByName("192.168.1.7").getCanonicalHostName());

//System.out.println(Inet4Address.getByName("localhost").getCanonicalHostName()+"/"+InetAddress.getByName("192.168.1.10").getHostName());
				//System.out.println(manager.isServerState());
				//System.out.println(manager.getIpManager().getIpGroup());
				//ArrayList<String> test = new ArrayList<String>();
					 //test =manager.getConnectedHost();
				//	manager.scanIP();
				ArrayList<Socket> listClient=manager.getClientsList();
				while(true){

						Socket socket = manager.createClient();
						IpManager ip = new IpManager(InetAddress.getByName(socket.getInetAddress().getHostAddress()));
						System.out.println(socket.getInetAddress().getCanonicalHostName());
						System.out.println(ip.getMacAddress(null));
						System.out.println( manager.getClientCount() +"client");
						DataOutputStream output = new DataOutputStream(socket.getOutputStream());
						DataInputStream input =  new DataInputStream(socket.getInputStream());
						//BufferedReader buffer = new BufferedReader( new InputStreamReader( socket.getInputStream() ) ) ;
						String line;
						while ( ( line = input.readLine() ) != null )
						{
						System.out.println(line);
						}
						/*manager.setClientsList(listClient);
						for(Socket s :listClient){
							System.out.println(s.getLocalAddress());
						}*/

				}
			//InetAddress ip = InetAddress.getByName("192.168.1.9");
			//IpManager ipm = new IpManager(ip);
			//ServerSocket srvSock = new ServerSocket(2999,100,ip);

		//	System.out.println(srvSock.getLocalSocketAddress());

			//int hostNumber = 0;
		//	System.out.println(ipm.getIpGroup() +" |" +ipm.getNetworkAdress()+" ;" +ipm.getBroadcastAdress());
			/*while(true){
				if(hostNumber>5){
					break;
				}
				Socket client = srvSock.accept();
				System.out.print("Server has connected!\n");
				if(client!=null)
					System.out.println("Client has Connected :"+client.getInetAddress());
				System.out.println();
		         PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		         System.out.print("Sending string: '" + data + "'\n");
		         out.print(data);
		         out.close();
		         hostNumber++;
		         System.out.println(hostNumber);
		         client.close();
			}*/
			//System.out.println("Server reached 5 client");
	        // srvSock.close();
		}catch(Exception e ){
			e.printStackTrace();
		}


	}

}
