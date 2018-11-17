package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ServerManager extends Thread {

	private IpManager ipManager;
	private int portNumber;
	private ServerSocket serverSocket;
	private ArrayList<Socket> clientsList;
	private String serverAdress;
	private boolean serverState = false;
	private int maxClient;

	ObservableList<Socket> data;
	ListView<Socket> listView;
	Label onHCount, offHCount;

	public ServerManager(String serverAdress, int portNumber, int maxClient, ObservableList<Socket> data,
			ListView<Socket> listView, Label onHCount, Label offHCount) {

		this.portNumber = portNumber;
		this.serverAdress = serverAdress;
		this.maxClient = maxClient;
		clientsList = new ArrayList<Socket>();
		this.data = data;
		data = FXCollections.observableArrayList();
		this.listView = listView;
		this.onHCount = onHCount;
		this.offHCount = offHCount;
		listView = new ListView<Socket>();
		configureServer();
	}

	public IpManager getIpManager() {
		return ipManager;
	}

	public void setIpManager(IpManager ipManager) {
		this.ipManager = ipManager;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ArrayList<Socket> getClientsList() {
		return clientsList;
	}

	public void setClientsList(ArrayList<Socket> clientsList) {
		this.clientsList = clientsList;
	}

	public int getClientCount() {
		return this.clientsList.size();
	}

	public String getServerAdress() {
		return serverAdress;
	}

	public void setServerAdress(String serverAdress) {
		this.serverAdress = serverAdress;
	}

	public boolean isServerState() {
		return serverState;
	}

	public void setServerState(boolean serverState) {
		this.serverState = serverState;
	}

	public int getMaxClient() {
		return this.maxClient;
	}

	public void setMaxClient(int maxClient) {
		this.maxClient = maxClient;
	}

	private void configureServer() {

		try {
			ipManager = new IpManager(InetAddress.getByName(this.serverAdress));
			serverSocket = new ServerSocket(this.portNumber, this.maxClient, ipManager.getIp());
			this.setServerState(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void newClient() throws IOException {
		Socket client = this.serverSocket.accept();
		System.out.println("A client has just connected");
		System.out.println(client.getInetAddress().getHostName());
		this.clientsList.add(client);

	}

	public Socket createClient() throws IOException {
		Socket client = this.serverSocket.accept();
		this.clientsList.add(client);
		return client;
	}

	public boolean pingByHost(String addr) {
		// Any Open port on other machine
		// openPort = 22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
		try {
			Socket soc = new Socket(addr, 60010);
			/// soc.connect(new InetSocketAddress(addr, 60010), 0);

			return true;
		} catch (IOException ex) {
			return false;
		}

	}

	public int getConnectedHostCount() {
		return 0;
	}

	/*
	 * public ArrayList<String> getConnectedHost() { int ipType =
	 * this.ipManager.getIpGroup(); ArrayList<String> machine = new
	 * ArrayList<>(); String ip = ipManager.getIp().toString(); if (ipType == 1)
	 * { ip = ip.substring(1, ip.indexOf(".")) + ".";
	 *
	 * } else if (ipType == 2) { System.out.println(ip); ip = ip.substring(1,
	 * ip.length()); String ipTab[]=ip.split("\\.");
	 * ip=ipTab[0]+"."+ipTab[1]+".";
	 *
	 * System.out.println(ip); for (int i = 0; i < 255; i++) { for(int
	 * j=0;j<255;j++){ String host = ip + i+"."+j; if(machine.contains(host))
	 * continue; //IPHelper ipThread = new IPHelper(machine, host, 3000);
	 * //ipThread.start(); new Runnable() ;
	 *
	 *
	 *
	 *
	 * }
	 *
	 * } else if (ipType == 3) { ip = ip.substring(1, ip.lastIndexOf(".")) +
	 * "."; // while(true){ for (int i = 1; i < 255; i++) { String host = ip +
	 * i; if(machine.contains(host)) continue; IPHelper ipThread = new
	 * IPHelper(machine, host, 3000); ipThread.start(); } // }
	 *
	 * } else if (ipType == 4) { // return null; } // System.out.println(ip);
	 *
	 * return machine; }
	 */
	public void run() {
		this.setServerState(true);
		while (this.isServerState()) {
			int off = 0, on = 0;
			if (isServerState() == false)
				break;
			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = this.serverSocket.accept();

				System.out.println(server.getInetAddress().getHostName());
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
				String host = in.readLine();
				if (data.size() != 0) {
					boolean exist = false;
					int i = 0;
					for (Socket s : data) {

						if (s.getInetAddress().getHostAddress().equals(server.getInetAddress().getHostAddress())) {
							exist = true;
							data.remove(i);
							data.add(i, server);

							// break;
						}
						i++;
					}
					i = 0;
					if (!exist) {
						data.add(server);

						System.out.println("Just connected to " + server.getRemoteSocketAddress());
						/*
						 * PrintWriter out = new
						 * PrintWriter(server.getOutputStream());
						 * out.println("netstat"); out.flush(); BufferedReader
						 * in = new BufferedReader(new
						 * InputStreamReader(server.getInputStream())); String
						 * result; while ((result = in.readLine()) != null) {
						 * System.err.println(result); }
						 */

					}
				} else {
					data.add(server);

				}
				listView.setItems(data);
				listView.refresh();

				/*in = new BufferedReader(new InputStreamReader(server.getInputStream()));
				String result;
				while ((result = in.readLine()) != null) {
					System.err.println("***********"+result);
				}*/

			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
