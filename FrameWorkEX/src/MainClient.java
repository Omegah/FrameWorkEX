import java.rmi.RemoteException;

public class MainClient {

	public static void main(String[] args) {

		try {
			OurUser user = new OurUser(generate(8), "152.77.82.229");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static String generate(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer pass = new StringBuffer();
		for (int x = 0; x < length; x++) {
			int i = (int) Math.floor(Math.random() * (chars.length() - 1));
			pass.append(chars.charAt(i));
		}
		return pass.toString();
	}

}
