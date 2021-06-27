package Client.Gui.Utils;

import java.lang.Thread;
import Client.Gui.Screen.Chat;
import Common.User;

public class UpdateOnlineTask extends Thread{
    private Chat chat;
    private User user;

    public UpdateOnlineTask(Chat chat, User user){
        this.chat = chat;
        this.user = user;
    } 
    
    public void run(){
        this.chat.addContact(user);
    }
}
