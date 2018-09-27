package Client;

import Common.ServerInterface;
import Logic.ClientApp;
import Gui.ClientGui;

public class Client {

	public static void main(String[] args){
	
        ClientApp client = new ClientApp();
        ClientGui.init(client);
    }
}
