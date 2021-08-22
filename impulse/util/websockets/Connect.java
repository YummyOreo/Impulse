package impulse.util.websockets;

import impulse.Impulse;

public class Connect {
	public static Connect INSTANCE = new Connect();
	public boolean enabled = false;
	//"https://impusle-1.herokuapp.com"
	public void connectToServer() {
		//https://impulse-1.herokuapp.com/
		if (tryConnect("https://impusle-1.herokuapp.com")) {
			System.out.println("Trying again with, " + "https://localhost:8000");
		} else  if (tryConnect("http://localhost:8000")) {
			System.out.println("Trying again with, " + "https://impulse-1.herokuapp.com/");
			tryConnect("https://impulse-1.herokuapp.com/");
		}
	}
	
	public boolean tryConnect(String url) {
		Impulse.INSTANCE.socketClient.changeServer(url);
		String a = Impulse.INSTANCE.socketClient.sendRequest("a");
		if (a.equals("error")) {
			System.out.println("Failed to connect to server, " + url);
			return false;
		} else {
			System.out.println("Connected to server, " + url);
			enabled = true;
			return true;
		}
	}
	
}
