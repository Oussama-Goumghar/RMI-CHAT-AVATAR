package Common;

import java.rmi.*;
import java.util.UUID;

public interface ClientInterface extends Remote {
    public void receiveMessage(UUID sender, String message) throws RemoteException;
    public void updateOnlineContact(User user) throws RemoteException;
    public User getUser() throws RemoteException; 
}

