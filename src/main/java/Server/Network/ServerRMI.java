package Network;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.UUID;
import java.util.Arrays;
import java.util.*;
import Common.ServerInterface;
import Common.ClientInterface;
import Common.User;

public class ServerRMI extends UnicastRemoteObject implements ServerInterface{
    
    public ServerRMI() throws RemoteException{
        super();
    }
    public void sendMessage(UUID sender, UUID receiver, String message){
       try{
           Registry registry = LocateRegistry.getRegistry();
           ClientInterface receiverStub =(ClientInterface) registry.lookup(receiver.toString());
           receiverStub.receiveMessage(sender, message);
        }
        catch(RemoteException e1) {e1.printStackTrace();}
        catch(NotBoundException e2) {e2.printStackTrace();}
    };

    public List<User> getOnlineContact(User sender){ 
        ArrayList <User> list = new <User> ArrayList();
        ArrayList <String> diff = new <String> ArrayList();
        try{
            Registry registry = LocateRegistry.getRegistry();
            diff.addAll(Arrays.asList(registry.list()));
            diff.remove(sender.getUUID().toString());
            diff.remove("Server");
            diff.forEach(elem -> {
                try{
                    ClientInterface client = (ClientInterface)registry.lookup(elem);
                    client.updateOnlineContact(sender);
                    list.add(client.getUser());
                }catch (RemoteException e1) {e1.printStackTrace();}
                 catch (NotBoundException e2) {e2.printStackTrace();} 
            });
        }catch(RemoteException e1) {e1.printStackTrace();}
         return list;
    }

    public static void init(){
        try{
            ServerInterface stub = new ServerRMI();
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();  
            registry.bind("Server", stub);
        }
        catch(RemoteException e1) {e1.printStackTrace();}
        catch(AlreadyBoundException e2) {e2.printStackTrace();}
    }
}
