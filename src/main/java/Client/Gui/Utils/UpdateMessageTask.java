package Client.Gui.Utils;

import java.lang.Thread;
import Client.Gui.Screen.Chat;
import Client.Utils.Message;

public class UpdateMessageTask extends Thread{
    private Chat chat;
    private String message;

    public UpdateMessageTask(Chat chat, String message){
        this.chat = chat;
        this.message = message;
    } 
    
    public void run(){
        this.chat.addMessage(Message.receivedMessage(this.message));
    }
}
