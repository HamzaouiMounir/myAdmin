package models;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class IpManager {
	private InetAddress ip ;
	private int ipGroup ;
	private String networkAdress,broadcastAdress;
	public IpManager(InetAddress ip){
		this.ip=ip;
		ipGroup=this.LoadIpGroup();
	}
	public void setIp(InetAddress ip){
		this.ip=ip;
	}
	public InetAddress getIp(){
		return this.ip;
	}
	public int LoadIpGroup(){
		if(isValidRange("0.0.0.0", "127.255.255.255", ip.getHostAddress())){
			this.setNetworkAdress("0.0.0.0");
			this.setBroadcastAdress("127.255.255.255");
			this.setIpGroup(1);

		}else if(isValidRange("128.0.0.0", "191.255.255.255", ip.getHostAddress())){
			this.setNetworkAdress("128.0.0.0");
			this.setBroadcastAdress("191.255.255.255");
			this.setIpGroup(2);
		}
		else if(isValidRange("192.0.0.0", "223.255.255.255", ip.getHostAddress())){
			this.setNetworkAdress("192.0.0.0");
			this.setBroadcastAdress("223.255.255.255");
			this.setIpGroup(3);
		}
		else if(isValidRange("224.0.0.0", "239.255.255.255", ip.getHostAddress())){
			this.setNetworkAdress("224.0.0.0");
			this.setBroadcastAdress("239.255.255.255");
			this.setIpGroup(4);
		}
		return this.getIpGroup();
	}

	 public int getIpGroup() {
		return ipGroup;
	}
	public String getNetworkAdress() {
		return networkAdress;
	}
	public void setNetworkAdress(String networkAdress) {
		this.networkAdress = networkAdress;
	}
	public String getBroadcastAdress() {
		return broadcastAdress;
	}
	public void setBroadcastAdress(String broadcastAdress) {
		this.broadcastAdress = broadcastAdress;
	}
	public void setIpGroup(int ipGroup) {
		this.ipGroup = ipGroup;
	}
	public  long ipToLong(InetAddress ip) {
			byte[] octets = ip.getAddress();
			long result = 0;
			for (byte octet : octets) {
				result <<= 8;
				result |= octet & 0xff;
			}
			return result;
		}
	public  boolean isValidRange(String ipStart,String ipEnd,String ipToCheck){
		try {
			long ipLo = ipToLong(InetAddress.getByName(ipStart));
			long ipHi = ipToLong(InetAddress.getByName(ipEnd));
			long ipToTest = ipToLong(InetAddress.getByName(ipToCheck));
			return (ipToTest >= ipLo && ipToTest <= ipHi);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
	}
	public String getMacAddress(String  ipAddress){
		StringBuilder sb = new StringBuilder();
		try {

			//ip = InetAddress.getByName(ipAddress);
			System.out.println("Current IP address : " + ip.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");


			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}


		} catch (SocketException e){

			e.printStackTrace();

		}
		return sb.toString();

	}
}
