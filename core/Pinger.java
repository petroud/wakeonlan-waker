package core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Pinger {
	
	
	private static final int COM_PORT = 9;
	
	
	public Pinger() {
		
	}
	
	
	public String getLocalIP() {
		String address; 
		try {
			address = InetAddress.getLocalHost().getHostAddress();
			return address;
		}catch(Exception e) {
			return null;
		}
	}
	
	
	public void wakeUp(String ipStr, String macStr) throws Exception {
		
		 try {
	            byte[] macBytes = getMacBytes(macStr);
	            byte[] bytes = new byte[6 + 16 * macBytes.length];
	            for (int i = 0; i < 6; i++) {
	                bytes[i] = (byte) 0xff;
	            }
	            for (int i = 6; i < bytes.length; i += macBytes.length) {
	                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
	            }
	            
	            InetAddress address = InetAddress.getByName(ipStr);
	            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, COM_PORT);
	            DatagramSocket socket = new DatagramSocket();
	            socket.send(packet);
	            socket.close();
	            
	        }
	        catch (Exception e) {
	        	throw new Exception();
	        }
		
	}
	
	
	private byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        
		byte[] bytes = new byte[6];
        
        String[] hex = macStr.split("(\\:|\\-)");
        
        if (hex.length != 6) {
            throw new IllegalArgumentException();
        }
        
        
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        
        return bytes;
    }
	

}