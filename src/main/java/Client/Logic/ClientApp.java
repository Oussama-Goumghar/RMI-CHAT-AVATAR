package Client.Logic;

import Common.User;
import Common.ServerInterface;
import Client.Utils.Message;
import Client.Gui.Screen.Chat;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.UUID;
import java.rmi.RemoteException;

       
public class ClientApp{
    private ServerInterface server;
    private User user;
    private ArrayList <User> onlineContact;
    private HashMap <UUID, ArrayList <Message>> message;
    private Chat chat;
    
    public ClientApp(){
        this.user = new User();
        this.message = new HashMap <UUID, ArrayList<Message>>();
    }

    public void connect(ServerInterface server){
        this.server = server;
    }
    
    public void receiveMessage(UUID sender, String message){
        if(chat.getContactUUID()!= null && chat.getContactUUID().equals(sender))
            chat.updateMessageReceived(message);
        this.message.get(sender).add(Message.receivedMessage(message));
    }

    public void sendMessage(UUID receiver, String message){
        try{
           this.message.get(receiver).add(Message.sentMessage(message));
           server.sendMessage(this.getUser().getUUID(), receiver, message);
        }catch(RemoteException e) {e.printStackTrace();}
    }

    public void getFromServerOnlineContact(){
        try{
            this.onlineContact =  (ArrayList <User>)server.getOnlineContact(this.user);
            this.onlineContact.forEach(user ->
                    this.message.put(user.getUUID(), new ArrayList <Message> ()));
        }catch(RemoteException e) {e.printStackTrace();}
    }

    public ArrayList <User>  getOnlineContact(){
        return this.onlineContact;
    }

    public HashMap <UUID, ArrayList <Message>> getMessage(){
        return this.message;
    }

    public void updateOnlineContact(User user){
        this.onlineContact.add(user); 
        this.message.put(user.getUUID(), new ArrayList <Message> ());
        this.chat.updateOnlineContact(user);
    }

    public void setChat(Chat chat){
        this.chat = chat;
    }

    public User getUser(){
        return this.user;
    }
}
