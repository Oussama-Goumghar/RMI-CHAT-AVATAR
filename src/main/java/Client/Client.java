package Client;

import Common.ServerInterface;
import Client.Logic.ClientApp;
import Client.Gui.ClientGui;

public class Client {

	public static void main(String[] args){
	
        ClientApp client = new ClientApp();
        ClientGui.init(client);
    }
}
