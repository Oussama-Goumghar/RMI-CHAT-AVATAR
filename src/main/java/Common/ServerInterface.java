package Common;

import java.rmi.*;
import java.util.UUID;
import java.util.*;

public interface ServerInterface extends Remote{
    public void sendMessage (UUID sender, UUID receiver, String message) throws RemoteException;
    public List<User> getOnlineContact(User sender) throws RemoteException;
}
