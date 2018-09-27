package Network;

import Common.ClientInterface;
import Common.ServerInterface;
import Common.User;
import Logic.ClientApp;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.UUID;

public class ClientRMI extends UnicastRemoteObject implements ClientInterface{
    private ClientApp client;

    public ClientRMI(ClientApp client) throws RemoteException{
        super();
        this.client = client;
    }

    public void receiveMessage(UUID sender, String message){
        this.client.receiveMessage(sender, message);
    }

    public User getUser(){
        return this.client.getUser();
    }

    public void updateOnlineContact(User user){
        this.client.updateOnlineContact(user);
    }

    public static void init(ClientApp client){
        try{
            ClientInterface rmiClient = new ClientRMI(client);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind(client.getUser().getUUID().toString(), rmiClient);
            client.connect((ServerInterface)registry.lookup("Server")); 
        }
        catch(RemoteException e1) {e1.printStackTrace();}
        catch(AlreadyBoundException e2) {e2.printStackTrace();}
        catch(NotBoundException e3){e3.printStackTrace();}
    }
}
