import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.codec.digest.DigestUtils;

public class LicenseGenerator {
	
	private static String salt = "EXTREAMPOS";
	
	public static void main(String[] args) {
		try {
			LicenseGenerator.getLicense();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void getLicense() throws SocketException, UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
		String computername = InetAddress.getLocalHost().getHostName();
		byte[] mac = networkInterface.getHardwareAddress();
		System.out.println("Mac Address	 = " + mac[0] + mac[1] +mac[2] +mac[3] +mac[4] +mac[5]);
		System.out.println("ComputerName	 = "+ computername);
		String machineUnique = mac[0] + mac[1] + salt +mac[2] +mac[3] + salt +mac[4] +mac[5]+ salt +computername;
		String encryptedText = DigestUtils.sha1Hex(machineUnique);
		System.out.println("License		 = " + encryptedText);
	}
	
}
